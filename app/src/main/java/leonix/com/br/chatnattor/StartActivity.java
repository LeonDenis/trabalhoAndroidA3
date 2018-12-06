package leonix.com.br.chatnattor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import leonix.com.br.executor.UsuarioDAO;
import leonix.com.br.model.Usuario;

public class StartActivity extends AppCompatActivity {

    Button btnLogar = null, btnAbrirCadastro = null;
    EditText txtNickName = null, txtSenha = null;

    ArrayList<Usuario> usuarios = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // Controles.
        this.btnLogar = findViewById(R.id.btnLogar);
        this.btnAbrirCadastro = findViewById(R.id.btnAbrirCadastro);
        this.txtNickName = findViewById(R.id.txtNickName);
        this.txtSenha = findViewById(R.id.txtSenha);
        
        // Cadastro.
        this.btnAbrirCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, CadastroActivity.class);
                // Indo para tela de cadastro.
                startActivity(intent);
            }
        });
        // Logar.
        this.btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                efetuarLogin(txtNickName.getText().toString(), txtSenha.getText().toString());
            }
        });
    }

    private void obterUsuarios() {
        try {
            String usuariosUrl = String.format(getResources().getString(R.string.leonix_api), "usuarios");
            LeonixApi leonixApi = new LeonixApi();
            leonixApi.getUsuarios(usuariosUrl, new Runnable() {
                @Override
                public void run() {
                    usuarios = Statics.usuarios;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void efetuarLogin(final String nick, final String senha) {
        try {
            String usuariosUrl = String.format(getResources().getString(R.string.leonix_api), "logar?nick=" + nick + "&senha=" + senha);
            LeonixApi leonixApi = new LeonixApi();
            leonixApi.logar(usuariosUrl, new Runnable() {
                @Override
                public void run() {
                    verificarStatus(nick, senha);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void verificarStatus(final String nick, final String senha) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Statics.status.getStatus() == 1) {
                    Toast.makeText(getBaseContext(), "Usuário ou Senha incorretos.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Logado com sucesso.", Toast.LENGTH_SHORT).show();
                    Usuario usuario = new Usuario();
                    usuario.setNick(nick);
                    usuario.setSenha(senha);
                    UsuarioDAO usuarioDAO = new UsuarioDAO(getBaseContext());
                    usuarioDAO.addUsuario(usuario);
                    Intent intent = new Intent(StartActivity.this, ChatActivity.class);
                    // Indo para tela de chat.
                    startActivity(intent);
                }
            }
        });
    }
}

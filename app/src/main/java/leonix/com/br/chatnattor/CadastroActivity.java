package leonix.com.br.chatnattor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    Button btnCadastro = null;
    EditText txtNickName = null, txtSenha = null, txtSenhaNov = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.btnCadastro = findViewById(R.id.btnCadastro);
        this.txtNickName = findViewById(R.id.txtNickName);
        this.txtSenha = findViewById(R.id.txtSenha);
        this.txtSenhaNov = findViewById(R.id.txtSenhaNov);

        this.btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nick = txtNickName.getText().toString();
                    String senha = txtSenha.getText().toString();

                    String usuariosUrl = String.format(getResources().getString(R.string.leonix_api), String.format("cadastrar?nick=%s&senha=%s", nick, senha));
                    LeonixApi leonixApi = new LeonixApi();
                    leonixApi.logar(usuariosUrl, new Runnable() {
                        @Override
                        public void run() {
                            verificarStatus();
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void verificarStatus() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Statics.status.getStatus() == 1) {
                        Toast.makeText(getBaseContext(), "Usuário já existe.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (Exception e) {
                    Log.d("ERRO", e.getMessage());
                }
            }
        });
    }
}

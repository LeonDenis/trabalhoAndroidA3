package leonix.com.br.chatnattor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView imgLogo = null;
    TextView txtLogo = null, txtLeonix = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Itens logo.
        this.imgLogo = this.findViewById(R.id.imgLogo);
        this.txtLogo = this.findViewById(R.id.txtLogo);
        this.txtLeonix = this.findViewById(R.id.txtLeonix);
        // Obtendo animação.
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        // Iniciando animação.
        this.imgLogo.startAnimation(fadeIn);
        this.txtLogo.startAnimation(fadeIn);
        this.txtLeonix.startAnimation(fadeIn);
        // Declarando inteção.
        final Intent intent = new Intent(this, StartActivity.class);
        // Instanciando e executando a thread.
        new Thread() {
            public void run() {
                try {
                    // Fazendo a thread dormir(esperar animação acabar).
                    sleep(2300);
                    // Animação de saida(fade out).
                    imgLogo.startAnimation(fadeOut);
                    txtLogo.startAnimation(fadeOut);
                    txtLeonix.startAnimation(fadeOut);
                    // Thread dormir (esperando animação).
                    sleep(1350);
                } catch (Exception e) {
                    // Print no erro.
                    e.printStackTrace();
                } finally {
                    // Indo para outra tela.
                    startActivity(intent);
                    // Finalizando thread.
                    finish();
                }
            }
        }.start();
    }
}

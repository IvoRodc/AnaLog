package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import static java.lang.Thread.sleep;


public class SplashActivity extends AppCompatActivity {

    //Duração da animação em milisegundos
    private static final int ANIMATION_DURATION = 700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //TESTE APENAS
       // Rolo r = new Rolo("TESTE",400, 35, 36, "Teste de rolo e cenas", 0 , this );
        //Exposicao e = new Exposicao(1, 100, 100, "Teste exp", 1, 0, this);




        ImageView logoView = findViewById(R.id.logo_splash);

        //Obter posição final do logo
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int modifierY = -((displayMetrics.heightPixels - logoView.getHeight())/2);

        //configurar animação para mover o logo
        ObjectAnimator moverLogo = ObjectAnimator.ofFloat(logoView, "translationY", modifierY/4);
        moverLogo.setDuration(ANIMATION_DURATION);
        moverLogo.setInterpolator(new AccelerateDecelerateInterpolator());

        //configurar animação para mudar o alpha
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(logoView, "alpha", 0f, 1f);
        fadeIn.setDuration(ANIMATION_DURATION);

        //configurar animação para mudar o tamanho do logo
        ObjectAnimator resizeX = ObjectAnimator.ofFloat(logoView, "scaleX", 4f);
        resizeX.setDuration(ANIMATION_DURATION);
        resizeX.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator resizeY = ObjectAnimator.ofFloat(logoView, "scaleY", 4f);
        resizeY.setDuration(ANIMATION_DURATION);
        resizeY.setInterpolator(new AccelerateDecelerateInterpolator());


        //configurar listener para mudar de atividade quando a animação acaba
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try{
                    sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashActivity.this, RolosActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.play(moverLogo).with(fadeIn).with(resizeX).with(resizeY);
        animatorSet.start();

    }
}

package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.AnaLog.Objetiva;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class SplashActivity extends AppCompatActivity {

    //Duração da animação em milisegundos
    private static final int ANIMATION_DURATION = 700;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=this;
        //TESTE APENAS
        /*Rolo r = new Rolo("TESTE",400, 35, 2, "Teste de rolo e cenas", 1 , this );
        Exposicao e = new Exposicao(100, 100, 100, "Teste exp", 1, 1, this);
        Objetiva o = new Objetiva("Objectiva", "ModeloObj", this);
        Objetiva o1 = new Objetiva("Objectiva2", "ModeloObj2", this);
        Camera c = new Camera("RolleiCord", "IIb", this);
        Camera c1 = new Camera("Nikon", "FE",this);*/



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
                boolean control=false;
                try{
                    sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                if (prefs.getBoolean("PREFS_PROFILE_NOTF", false)==true) {
                    //corre metodo de analise de rolos para ver se estao cheios.
                    control= CheckTermRolls();
                }
                if (!control) {
                    Intent intent = new Intent(SplashActivity.this, RolosActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }


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


    public boolean CheckTermRolls(){
        DBHandler db = new DBHandler(this);
        ArrayList<Rolo> listaRolos = new ArrayList<>(db.getRolos().values());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<listaRolos.size(); i++) {
            if (listaRolos.get(i).getnExposicoes() > (listaRolos.get(i).getMaxExposicoes() - 5)) {
                stringBuilder.append(listaRolos.get(i).getTitulo()+" \n");
            }
        }
        if (stringBuilder.toString().isEmpty())
            return false;
        AlertDialog.Builder ad = new AlertDialog.Builder(SplashActivity.this);
        ad.setTitle(getString(R.string.Msg_notif));
        ad.setMessage(stringBuilder.toString());
        ad.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(SplashActivity.this, RolosActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    }
                });
        ad.show();
        return true;
    }
}

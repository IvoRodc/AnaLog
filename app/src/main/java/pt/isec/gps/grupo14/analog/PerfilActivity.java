package pt.isec.gps.grupo14.analog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;

public class PerfilActivity extends Activity {

    private static final String PREFS_PROFILE_NAME = "PREFS_PROFILE_NAME";
    private static final String PREFS_PROFILE_NOTF = "PREFS_PROFILE_NOTF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean NotifStatus;
        String UserName= prefs.getString(PREFS_PROFILE_NAME, null);

        if (UserName==null) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.putString(PREFS_PROFILE_NAME, "Utilizador");
            UserName= "Utilizador";
            editor.putBoolean(PREFS_PROFILE_NOTF, false);
            NotifStatus=false;
        }else{
            NotifStatus= prefs.getBoolean(PREFS_PROFILE_NOTF, false);
        }
        
    }

}

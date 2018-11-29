package pt.isec.gps.grupo14.analog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import androidx.appcompat.widget.Toolbar;

public class PerfilActivity extends Activity {

    private static final String PREFS_PROFILE_NAME = "PREFS_PROFILE_NAME";
    private static final String PREFS_PROFILE_NOTF = "PREFS_PROFILE_NOTF";

    TextInputEditText Usern;
    Switch Notif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar Toolbar = findViewById(R.id.toolbar);
        Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeProfileInSharedPreferences();
                finish();
            }
        });


        boolean NotifStatus;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String UserName= prefs.getString(PREFS_PROFILE_NAME, null); //tenta carregar o nome do registry

        if (UserName==null) { //se não houver nome definido é a primeiro start do programa
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.putString(PREFS_PROFILE_NAME, "Utilizador");
            UserName= "Utilizador";
            editor.putBoolean(PREFS_PROFILE_NOTF, false);
            NotifStatus=false;
            editor.commit();
        }else{ //senao for o primeiro start
            NotifStatus= prefs.getBoolean(PREFS_PROFILE_NOTF, false);
        }

        Usern = (TextInputEditText) findViewById(R.id.UserName);
        Notif = (Switch) findViewById(R.id.Notif);

        //define os valores
        Usern.setText(UserName);
        Notif.setChecked(NotifStatus);
    }

    public void writeProfileInSharedPreferences(){ //função update
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.putString(PREFS_PROFILE_NAME, Usern.getText().toString());
        editor.putBoolean(PREFS_PROFILE_NOTF, Notif.isChecked());

        editor.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        writeProfileInSharedPreferences();
        finish();
    }

    public void onClickPerfilUpdate(View view) {
        writeProfileInSharedPreferences();
        finish();
    }
}

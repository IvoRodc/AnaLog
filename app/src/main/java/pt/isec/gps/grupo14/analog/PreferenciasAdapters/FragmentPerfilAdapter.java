package pt.isec.gps.grupo14.analog.PreferenciasAdapters;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import pt.isec.gps.grupo14.analog.R;

public class FragmentPerfilAdapter extends Fragment {

    private static final String PREFS_PROFILE_NAME = "PREFS_PROFILE_NAME";
    private static final String PREFS_PROFILE_NOTF = "PREFS_PROFILE_NOTF";
    TextInputEditText Usern;
    Switch Notif;

    View v;

    public FragmentPerfilAdapter(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_perfil, container, false);
        ((TextInputEditText)v.findViewById(R.id.UserName)).setText("Utilizador");

        boolean NotifStatus;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
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

        Usern = v.findViewById(R.id.UserName);
        Notif = v.findViewById(R.id.Notif);

        //define os valores

        Usern.setText(UserName);
        Notif.setChecked(NotifStatus);

        return v;
    }
}

package pt.isec.gps.grupo14.analog.PreferenciasAdapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import pt.isec.gps.grupo14.analog.R;

public class FragmentPerfilAdapter extends Fragment {

    View v;

    public FragmentPerfilAdapter(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_perfil, container, false);
        ((TextInputEditText)v.findViewById(R.id.UserName)).setText("Utilizador");
        return v;
    }
}

package pt.isec.gps.grupo14.analog.PreferenciasAdapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import pt.isec.gps.grupo14.analog.AnaLog.Objetiva;
import pt.isec.gps.grupo14.analog.R;

public class FragmentObjetivasAdapter extends Fragment {

    View v;
    ArrayList<Objetiva> listaObjetivas;

    public FragmentObjetivasAdapter(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cameras, container, false);
        return v;
    }
}

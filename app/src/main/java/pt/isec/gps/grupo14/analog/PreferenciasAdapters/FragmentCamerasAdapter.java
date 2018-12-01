package pt.isec.gps.grupo14.analog.PreferenciasAdapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.R;

public class FragmentCamerasAdapter extends Fragment {

    View v;
    private RecyclerView recyclerView;

    public FragmentCamerasAdapter(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cameras, container, false);
        recyclerView = v.findViewById(R.id.lista_cameras);
        ListaCamerasAdapter listaCamerasAdapter = new ListaCamerasAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listaCamerasAdapter);


        return v;
    }

}

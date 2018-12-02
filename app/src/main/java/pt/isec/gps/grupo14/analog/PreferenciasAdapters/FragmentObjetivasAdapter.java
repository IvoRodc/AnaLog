package pt.isec.gps.grupo14.analog.PreferenciasAdapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.R;

public class FragmentObjetivasAdapter extends Fragment {

    View v;
    private RecyclerView recyclerView;

    public FragmentObjetivasAdapter(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_objetivas, container, false);

        recyclerView = v.findViewById(R.id.lista_objetivas);
        ListaObjetivasAdapter listaObjetivasAdapter = new ListaObjetivasAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listaObjetivasAdapter);
        return v;
    }
}

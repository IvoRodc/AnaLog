package pt.isec.gps.grupo14.analog.PreferenciasAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Objetiva;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;
import pt.isec.gps.grupo14.analog.R;

public class ListaObjetivasAdapter extends RecyclerView.Adapter {

    private DBHandler db;
    private ArrayList<Objetiva> listaObjetivas;

    ListaObjetivasAdapter(Context context) {
        db = new DBHandler(context);
        listaObjetivas = new ArrayList<>();
        try{
        listaObjetivas = new ArrayList<>(db.getObjetivas().values());
        } catch (NullPointerException exception){
            Log.d("ANALOG LOG", "BASE DE DADOS VAZIA");
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView marca;
        AppCompatTextView modelo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            marca = itemView.findViewById(R.id.equipamento_marca);
            modelo = itemView.findViewById(R.id.equipamento_modelo);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipamento_card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Objetiva obj = listaObjetivas.get(position);
        ((ViewHolder)viewHolder).marca.setText(obj.getMarca());
        ((ViewHolder)viewHolder).modelo.setText(obj.getModelo());
    }

    @Override
    public int getItemCount() {
        return listaObjetivas.size();
    }

    public void addNewObjetiva(Objetiva obj){
        listaObjetivas.add(0, obj);
        notifyItemInserted(0);
    }
}

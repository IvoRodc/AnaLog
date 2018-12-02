package pt.isec.gps.grupo14.analog.PreferenciasAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;
import pt.isec.gps.grupo14.analog.R;

public class ListaCamerasAdapter extends RecyclerView.Adapter {

    private DBHandler db;
    private ArrayList<Camera> listaCameras;

    ListaCamerasAdapter(Context context){
        db = new DBHandler(context);
        listaCameras = new ArrayList<>();
        try{
            listaCameras = new ArrayList<>(db.getCameras().values());
        } catch (NullPointerException exception){
            Log.d("ANALOG LOG", "BASE DE DADOS VAZIA");
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView marca;
        AppCompatTextView modelo;
        MaterialButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            marca = itemView.findViewById(R.id.equipamento_marca);
            modelo = itemView.findViewById(R.id.equipamento_modelo);
            delete = itemView.findViewById(R.id.Rem_eqp);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipamento_card_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final Camera c = listaCameras.get(position);
        ((ViewHolder)viewHolder).marca.setText(c.getMarca());
        ((ViewHolder)viewHolder).modelo.setText(c.getModelo());
        ((ViewHolder)viewHolder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rmvCamera(position);
                db.removeCamera(c.getIdCamera());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCameras.size();
    }

    public void addNewCamera(Camera c){
        listaCameras.add(0, c);
        notifyItemInserted(0);
    }
    public void rmvCamera(int pos){
        listaCameras.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, listaCameras.size());
        notifyDataSetChanged();
    }
}

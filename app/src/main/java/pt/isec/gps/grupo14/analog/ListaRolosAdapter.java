package pt.isec.gps.grupo14.analog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class ListaRolosAdapter extends RecyclerView.Adapter {

    ArrayList<Rolo> listaRolos;
    DBHandler db;

    ListaRolosAdapter(Context context)
    {
        db = new DBHandler(context);
        listaRolos=new ArrayList<>(db.getRolos().values());

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Integer idRolo;
        AppCompatTextView nFotos;
        AppCompatTextView nomeRolo;
        AppCompatTextView nomeCamera;
        AppCompatTextView dataCriacao;
        AppCompatTextView descRolo;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nFotos = itemView.findViewById(R.id.num_fotos);
            nomeRolo = itemView.findViewById(R.id.nome_rolo);
            nomeCamera = itemView.findViewById(R.id.camera);
            dataCriacao = itemView.findViewById(R.id.data_rolo);
            descRolo = itemView.findViewById(R.id.descricao_rolo);



        }

        public void setId(int id){
            idRolo=id;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Mudar atividade
                    Intent intent = new Intent(itemView.getContext(), ExposicaoActivity.class);
                    intent.putExtra("idrolo", idRolo);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rolos_card_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // Obter dados da base de dados
        Rolo r = listaRolos.get(position);
        //Camera c = db.getCamera(r.getIdCamera());
        ((ViewHolder)viewHolder).nFotos.setText(r.getnExposicoes() + "/" + r.getMaxExposicoes());
        ((ViewHolder)viewHolder).nomeRolo.setText(r.getTitulo());
        //((ViewHolder)viewHolder).nomeCamera.setText(c.getMarca()+ " " +c.getModelo());
        ((ViewHolder)viewHolder).nomeCamera.setText("CAmera");
        ((ViewHolder)viewHolder).dataCriacao.setText(r.getData());
        ((ViewHolder)viewHolder).descRolo.setText(r.getDescricao());
        ((ViewHolder)viewHolder).setId(r.getIdRolo());
    }

    @Override
    public int getItemCount() {
        //return size of hashmap
        return listaRolos.size();
    }
}

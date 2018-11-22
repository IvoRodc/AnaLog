package pt.isec.gps.grupo14.analog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class ListaRolosAdapter extends RecyclerView.Adapter {

    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView nFotos;
        AppCompatTextView nomeRolo;
        AppCompatTextView nomeCamera;
        AppCompatTextView dataCriacao;
        AppCompatTextView descRolo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nFotos = itemView.findViewById(R.id.num_fotos);
            nomeRolo = itemView.findViewById(R.id.nome_rolo);
            nomeCamera = itemView.findViewById(R.id.camera);
            dataCriacao = itemView.findViewById(R.id.data_rolo);
            descRolo = itemView.findViewById(R.id.descricao_rolo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "click detected on item " + getAdapterPosition(),
                            Snackbar.LENGTH_SHORT).setAction("Action", null)
                            .show();
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
        // Substituir o texto
        ((ViewHolder)viewHolder).nFotos.setText("0 / 32");
        ((ViewHolder)viewHolder).nomeRolo.setText("Rolo 1");
        ((ViewHolder)viewHolder).nomeCamera.setText("Camera + Modelo");
        ((ViewHolder)viewHolder).dataCriacao.setText("31 Jan 2018");
        ((ViewHolder)viewHolder).descRolo.setText("Descrição concisa da cena");
    }

    @Override
    public int getItemCount() {
        //return size of hashmap
        return 10;
    }
}

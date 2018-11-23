package pt.isec.gps.grupo14.analog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class ListaExpAdapter extends RecyclerView.Adapter{

    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView dataFoto;
        AppCompatTextView descFoto;
        AppCompatTextView nomeFoto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            dataFoto = itemView.findViewById(R.id.Card_data_foto);
            descFoto = itemView.findViewById(R.id.Card_Desc_Foto);
            nomeFoto =itemView.findViewById(R.id.Card_nome_foto);
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
                .inflate(R.layout.exposicoes_card_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // Obter dados da base de dados
        // Substituir o texto

        ((ViewHolder)viewHolder).nomeFoto.setText("Foto 1");

        ((ViewHolder)viewHolder).dataFoto.setText("31 Jan 2018");
        ((ViewHolder)viewHolder).descFoto.setText("Descrição concisa da cena");
    }

    @Override
    public int getItemCount() {
        //return size of hashmap
        return 10;
    }
}

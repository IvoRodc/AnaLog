package pt.isec.gps.grupo14.analog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_EdtExp;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class ListaExpAdapter extends RecyclerView.Adapter{

    DBHandler db;
    ArrayList<Exposicao> ListaExp;
    FragmentActivity c;
    int idRolo;

    ListaExpAdapter (FragmentActivity c , int idRolo, Context context){

        this.idRolo= idRolo;
        db = new DBHandler(context);
        this.c=c;
        ListaExp=new ArrayList<>();
        try {
            ListaExp=new ArrayList<>(db.getExposicoes(idRolo));
        }catch (NullPointerException exception){
            Log.d("ANALOG LOG", "BASE DE DADOS VAZIA");
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        Integer idexp;
        AppCompatTextView dataFoto;
        AppCompatTextView descFoto;
        AppCompatTextView nomeFoto;
        AppCompatTextView AberturaFoto;
        AppCompatTextView VelFoto;
        AppCompatTextView DistFoto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dataFoto = itemView.findViewById(R.id.Card_data_foto);
            descFoto = itemView.findViewById(R.id.Card_Desc_Foto);
            nomeFoto =itemView.findViewById(R.id.Card_nome_foto);
            AberturaFoto =itemView.findViewById(R.id.Card_Fval_foto);
            VelFoto =itemView.findViewById(R.id.Card_Velval_foto);
            DistFoto =itemView.findViewById(R.id.Card_Distval_foto);


        }
        public void setId(int id){
            idexp=id;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomSheet_EdtExp bottomSheetEdtExp = new BottomSheet_EdtExp();
                    Bundle bEdt = new Bundle();
                    bEdt.putInt("IDExp", idexp);
                    bottomSheetEdtExp.setArguments(bEdt);
                    if(!bottomSheetEdtExp.isOpen()){
                        bottomSheetEdtExp.show( c.getSupportFragmentManager(), "BottomSheet_EdtRolo");
                    }
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

        Exposicao e = ListaExp.get(position);
        ((ViewHolder)viewHolder).nomeFoto.setText("Foto "+ (ListaExp.size() -(position)));
        ((ViewHolder)viewHolder).dataFoto.setText(e.getData());
        ((ViewHolder)viewHolder).descFoto.setText(e.getDescricao());
        ((ViewHolder)viewHolder).AberturaFoto.setText(Float.toString(e.getAbertura()));
        ((ViewHolder)viewHolder).VelFoto.setText(Integer.toString(e.getVelDisparo()));
        ((ViewHolder)viewHolder).DistFoto.setText(Integer.toString(e.getDistFocal()));
        ((ViewHolder)viewHolder).setId(e.getIdExposicao());
    }

    @Override
    public int getItemCount() {
        //return size of hashmap
        return ListaExp.size();
    }
}

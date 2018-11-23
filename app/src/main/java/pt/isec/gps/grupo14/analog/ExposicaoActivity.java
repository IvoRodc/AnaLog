package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ExposicaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposicao);
        //VARS para o cabeçalho
        AppCompatTextView nFotos= findViewById(R.id.Cab_num_Fotos);
        //AppCompatTextView nomeRolo= findViewById(R.id.title_rolo);
        AppCompatTextView nomeCamera = findViewById(R.id.Cab_camera);
        AppCompatTextView dataCriacao =findViewById(R.id.Cab_data_criacao);
        AppCompatTextView descRolo = findViewById(R.id.Cab_desc_rolo);

        //dados da BD

        // termino dados BD

        //preencher recycler view
        RecyclerView recyclerView = findViewById(R.id.lista_exposicoes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new ListaExpAdapter();
        recyclerView.setAdapter(adapter);
    }
}
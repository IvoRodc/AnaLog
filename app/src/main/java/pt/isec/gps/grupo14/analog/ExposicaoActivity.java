package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

public class ExposicaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposicao);
        int IDRolo=0;
        Intent intent = getIntent();

        if (intent != null)
            IDRolo = intent.getIntExtra("idrolo", 0); //Get id rolo

        //TOOLBAR config
        Toolbar collapsingToolbar = findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.title_Exp);

        titulo.setText("Rolo #" + IDRolo);
        assert getSupportActionBar() != null;
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //VARS para o cabeçalho
        AppCompatTextView nFotos= findViewById(R.id.Cab_num_Fotos);
        //AppCompatTextView nomeRolo= findViewById(R.id.title_rolo);
        AppCompatTextView nomeCamera = findViewById(R.id.Cab_camera);
        AppCompatTextView dataCriacao =findViewById(R.id.Cab_data_criacao);
        AppCompatTextView descRolo = findViewById(R.id.Cab_desc_rolo);

        //dados da BD, utilizando o IDRolo

        //termino dados BD

        //preencher recycler view
        RecyclerView recyclerView = findViewById(R.id.lista_exposicoes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new ListaExpAdapter();
        recyclerView.setAdapter(adapter);
    }
}
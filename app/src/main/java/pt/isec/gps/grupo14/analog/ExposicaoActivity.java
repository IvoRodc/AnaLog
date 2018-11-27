package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExposicaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposicao);

        //vars
        int IDRolo=-1;
        Intent intent = getIntent();
        DBHandler db = new DBHandler(this);
        Rolo rolo= db.getRolo(IDRolo);

        if (intent != null) {
            IDRolo = intent.getIntExtra("idrolo", -1); //Get id rolo
        } else
        {
            finish();
        }

        //TOOLBAR config
       Toolbar Toolbar = findViewById(R.id.toolbar);
       Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

        TextView titulo = findViewById(R.id.title_Exp);
        titulo.setText("Rolo #" + IDRolo);



        //VARS para o cabeçalho
        AppCompatTextView nFotos= findViewById(R.id.Cab_num_Fotos);
        AppCompatTextView nomeRolo= findViewById(R.id.title_Exp);
        AppCompatTextView nomeCamera = findViewById(R.id.Cab_camera);
        AppCompatTextView dataCriacao =findViewById(R.id.Cab_data_criacao);
        AppCompatTextView descRolo = findViewById(R.id.Cab_desc_rolo);

        //dados da BD, utilizando o IDRolo
        nFotos.setText(rolo.getnExposicoes() + "/" + rolo.getMaxExposicoes());
        nomeRolo.setText(rolo.getTitulo());
       // Camera c = db.getCamera(rolo.getIdCamera());
        //nomeCamera.setText(c.getMarca()+ " " +c.getModelo());
        dataCriacao.setText(rolo.getData());
        descRolo.setText(rolo.getDescricao());
        //termino dados BD

        //preencher recycler view
        RecyclerView recyclerView = findViewById(R.id.lista_exposicoes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new ListaExpAdapter(IDRolo);

        recyclerView.setAdapter(adapter);
    }
}
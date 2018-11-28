package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_AddExp;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_EdtRolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

import android.content.Intent;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;

public class ExposicaoActivity extends AppCompatActivity {

        BottomSheet_AddExp bottomSheet;
        BottomSheet_EdtRolo bottomSheetEdtRolo;
        Rolo rolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposicao);

        BottomAppBar bab = findViewById(R.id.app_bar_bottom);
        setSupportActionBar(bab);
        //vars
        int IDRolo=-1;
        Intent intent = getIntent();
        DBHandler db = new DBHandler(this);


        if (intent != null) {
            IDRolo = intent.getIntExtra("idrolo", -1); //Get id rolo
        } else
        {
            finish();
        }
        rolo= db.getRolo(IDRolo);
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
        RecyclerView.Adapter adapter = new ListaExpAdapter(IDRolo, this);

        recyclerView.setAdapter(adapter);

        bottomSheet = new BottomSheet_AddExp();
        Bundle bundle = new Bundle();
        bundle.putInt("IDrolo", IDRolo);
        bottomSheet.setArguments(bundle);

        bottomSheetEdtRolo = new BottomSheet_EdtRolo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_exp, menu);

        for(int i=0; i<menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable!=null){
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exp_edit:
                Bundle rolo_b = new Bundle();
                rolo_b.putInt("IDRolo", rolo.getIdRolo());
                bottomSheetEdtRolo.setArguments(rolo_b);
                if(!bottomSheetEdtRolo.isOpen()){
                    bottomSheetEdtRolo.show(getSupportFragmentManager(), "BottomSheet_EdtRolo");
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickAddExp(View v){
        if(!bottomSheet.isOpen()){
            bottomSheet.show(getSupportFragmentManager(), "BottomSheet_AddRolo");
        }
    }
}
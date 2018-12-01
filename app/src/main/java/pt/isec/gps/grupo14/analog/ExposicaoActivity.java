package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Objetiva;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_AddExp;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_EdtExp;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_EdtRolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ExposicaoActivity extends AppCompatActivity {

        BottomSheet_AddExp bottomSheet;
        BottomSheet_EdtRolo bottomSheetEdtRolo;
        Rolo rolo;
        DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposicao);

        BottomAppBar bab = findViewById(R.id.app_bar_bottom);
        setSupportActionBar(bab);
        //vars
        int IDRolo=-1;
        Intent intent = getIntent();
        db = new DBHandler(this);


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

        //se o rolo ja estiver a maxima capacidade desabilita o botao de adicionar novas exp
        FloatingActionButton fab = findViewById(R.id.fab_addExp);
        if(rolo.getnExposicoes()==rolo.getMaxExposicoes())
        {
            fab.setEnabled(false);
            fab.setSupportBackgroundTintList(ContextCompat.getColorStateList(this, R.color.disabledButton));
        }



        //VARS para o cabeçalho
        AppCompatTextView nFotos= findViewById(R.id.Cab_num_Fotos);
        AppCompatTextView nomeRolo= findViewById(R.id.title_Exp);
        AppCompatTextView nomeCamera = findViewById(R.id.Cab_camera);
        AppCompatTextView dataCriacao =findViewById(R.id.Cab_data_criacao);
        AppCompatTextView descRolo = findViewById(R.id.Cab_desc_rolo);

        //dados da BD, utilizando o IDRolo
        nFotos.setText(rolo.getnExposicoes() + "/" + rolo.getMaxExposicoes());
        nomeRolo.setText(rolo.getTitulo());
        Camera c = db.getCamera(rolo.getIdCamera());
        nomeCamera.setText(c.getMarca()+ " " +c.getModelo());
        dataCriacao.setText(rolo.getData());
        descRolo.setText(rolo.getDescricao());
        //termino dados BD

        //preencher recycler view
        RecyclerView recyclerView = findViewById(R.id.lista_exposicoes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new ListaExpAdapter( this,IDRolo, this);

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
            case R.id.menu_rolos_settings:
                //Mudar para a activity definições
                Intent intent = new Intent(this, PreferenciasActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_exp_edit:
                Bundle rolo_b = new Bundle();
                rolo_b.putInt("IDRolo", rolo.getIdRolo());
                bottomSheetEdtRolo.setArguments(rolo_b);
                if(!bottomSheetEdtRolo.isOpen()){
                    bottomSheetEdtRolo.show(getSupportFragmentManager(), "BottomSheet_EdtRolo");
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickAddExp(View v){
        if(!bottomSheet.isOpen()){
            bottomSheet.show(getSupportFragmentManager(), "BottomSheet_AddRolo");
        }
    }


    public void onClickDialogISO(View view){
        final String[] isos = getResources().getStringArray(R.array.ISO_values);
        final View v = view;
        AlertDialog.Builder ad = new AlertDialog.Builder(ExposicaoActivity.this);
        ad.setTitle(getString(R.string.Escolha_ISO));

        ad.setSingleChoiceItems(R.array.ISO_values, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((TextInputEditText)v.findViewById(R.id.ISO_Edt_Rolo)).setText(isos[which]);
                dialog.dismiss();
            }
        });

        ad.show();
    }

    public void onClickDeleteRolo(View view){
        final View v = view;
        final Context context = this;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getString(R.string.elmRolo));
        alert.setMessage(getString(R.string.elmRolo_text));
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
                db.removeRolo(rolo.getIdRolo());
                Intent intent = new Intent(context,RolosActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        alert.show();
    }
    public void onClickDeleteExp(View view){
        final View v = view;
        final Context context = this;

        final String id = view.getTag().toString();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getString(R.string.Elm_exp));
        alert.setMessage(getString(R.string.Elm_exp_text));
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
                db.removeExposicao(Integer.parseInt(id));
                Intent intent = new Intent(context,ExposicaoActivity.class);
                intent.putExtra("idrolo", rolo.getIdRolo());
                startActivity(intent);
                finish();
            }
        });
        alert.show();
    }


    public void  onClickDialogCam(View view){
        final View v = view;
        DBHandler db = new DBHandler(this);
        final ArrayList<Camera> Cameras = new ArrayList<>(db.getCameras().values());
        final ArrayList<String> CameraNames = new ArrayList<String>();

        for (int i=0; i<Cameras.size(); i++)
        {
            CameraNames.add((Cameras.get(i).getMarca()+ " "+Cameras.get(i).getModelo()));
        }

        AlertDialog.Builder ad = new AlertDialog.Builder(ExposicaoActivity.this);
        ad.setTitle(getString(R.string.Cam_escolha));

        ad.setSingleChoiceItems(CameraNames.toArray(new String[CameraNames.size()]), -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((TextInputEditText)v.findViewById(R.id.IDCAM_Edt_Rolo)).setText(Integer.toString(Cameras.get(which).getIdCamera()));
                dialog.dismiss();
            }
        });
        ad.show();
    }

    public void  onClickDialogObj(View view){
        final View v = view;
        DBHandler db = new DBHandler(this);
        final ArrayList<Objetiva> Objetivas;
        try {
             Objetivas = new ArrayList<>(db.getObjetivas().values());
        }catch (NullPointerException e){

            Log.e("DEBUG:", "Sem objectivas na lista");
            return;
        }
        final ArrayList<String> ObjNames = new ArrayList<String>();

        for (int i=0; i<Objetivas.size(); i++)
        {
            ObjNames.add((Objetivas.get(i).getMarca()+ " "+Objetivas.get(i).getModelo()));
        }

        AlertDialog.Builder ad = new AlertDialog.Builder(ExposicaoActivity.this);
        ad.setTitle(getString(R.string.Cam_escolha));

        ad.setSingleChoiceItems(ObjNames.toArray(new String[ObjNames.size()]), -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((TextInputEditText)v.findViewById(R.id.Lente_New_Exp)).setText(Integer.toString(Objetivas.get(which).getIdObjetiva()));
                dialog.dismiss();
            }
        });

        ad.show();

    }

    public void onClickDialogFormato(View view){
        final String[] formatos = getResources().getStringArray(R.array.formato_values);
        final View v = view;
        AlertDialog.Builder ad = new AlertDialog.Builder(ExposicaoActivity.this);
        ad.setTitle(getString(R.string.Escolha_forma));

        ad.setSingleChoiceItems(R.array.formato_values, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((TextInputEditText)v.findViewById(R.id.Formato_Edt_Rolo)).setText(formatos[which]);
                dialog.dismiss();
            }
        });

        ad.show();
    }
}
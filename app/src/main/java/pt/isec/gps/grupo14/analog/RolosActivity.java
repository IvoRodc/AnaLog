package pt.isec.gps.grupo14.analog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_AddRolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class RolosActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    BottomSheet_AddRolo bottomSheet;
    ListaRolosAdapter adapter;
    MenuItem setttingsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rolos);

        BottomAppBar bab = findViewById(R.id.app_bar_bottom);
        setSupportActionBar(bab);

        //preencher recycler view
        RecyclerView recyclerView = findViewById(R.id.lista_rolos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ListaRolosAdapter(this);
        recyclerView.setAdapter(adapter);
        bottomSheet = new BottomSheet_AddRolo();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getString("PREFS_PROFILE_NAME", null)==null){//senao houver dados definidos, o user é reencaminhado para o perfil
            //inicia o intent do perfil
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }



    public void onClickAddRolo(View v){
        if(!bottomSheet.isOpen()){
            bottomSheet.show(getSupportFragmentManager(), "BottomSheet_AddRolo");
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_rolos, menu);

        for(int i=0; i<menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable!=null){
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        }
        MenuItem def = menu.findItem(R.id.menu_rolos_settings);
        MenuItem menuItem = menu.findItem(R.id.menu_rolos_search);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

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
        }
        return super.onOptionsItemSelected(item);

    }


    public void  onClickDialogCam(View view){
        final View v = view;
        DBHandler db = new DBHandler(this);
        final ArrayList<Camera> Cameras;
        try{
        Cameras = new ArrayList<>(db.getCameras().values());
        }catch (NullPointerException e)
        {
            Log.e("DEBUG:", "Sem Maquinas na lista");
            return;
        }
        final ArrayList<String> CameraNames = new ArrayList<String>();

        for (int i=0; i<Cameras.size(); i++)
        {
            CameraNames.add((Cameras.get(i).getMarca()+ " "+Cameras.get(i).getModelo()));
        }

        AlertDialog.Builder ad = new AlertDialog.Builder(RolosActivity.this);
        ad.setTitle(getString(R.string.Cam_escolha));

        ad.setSingleChoiceItems(CameraNames.toArray(new String[CameraNames.size()]), -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((TextInputEditText)v.findViewById(R.id.IDCAM_New_Rolo)).setText(CameraNames.get(which));
                dialog.dismiss();
            }
        });

        ad.show();

    }

    public void onClickDialogISO(View view){
        final String[] isos = getResources().getStringArray(R.array.ISO_values);
        final View v = view;
        AlertDialog.Builder ad = new AlertDialog.Builder(RolosActivity.this);
        ad.setTitle(getString(R.string.Escolha_ISO));

        ad.setSingleChoiceItems(R.array.ISO_values, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((TextInputEditText)v.findViewById(R.id.ISO_New_Rolo)).setText(isos[which]);
                dialog.dismiss();
            }
        });

        ad.show();
    }

    public void onClickDialogFormato(View view){
        final String[] formatos = getResources().getStringArray(R.array.formato_values);
        final View v = view;
        AlertDialog.Builder ad = new AlertDialog.Builder(RolosActivity.this);
        ad.setTitle(getString(R.string.Escolha_forma));

        ad.setSingleChoiceItems(R.array.formato_values, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((TextInputEditText)v.findViewById(R.id.Formato_New_Rolo)).setText(formatos[which]);
                dialog.dismiss();
            }
        });

        ad.show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.searchRolo(query.toLowerCase());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.searchRolo(newText.toLowerCase());
        return true;
    }
}

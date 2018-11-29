package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_AddRolo;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

import android.app.AlertDialog;
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
import com.google.android.material.textfield.TextInputEditText;

public class RolosActivity extends AppCompatActivity {


    BottomSheet_AddRolo bottomSheet;

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
        RecyclerView.Adapter adapter = new ListaRolosAdapter(this);
        recyclerView.setAdapter(adapter);

        bottomSheet = new BottomSheet_AddRolo();

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_rolos_settings:
                //Mudar para a activity definições
                Intent intent = new Intent(this, PerfilActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_rolos_search:
                //Mostar caixa de pesquisa
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);

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
}

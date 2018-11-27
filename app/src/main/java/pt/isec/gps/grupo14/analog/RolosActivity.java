package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;

public class RolosActivity extends AppCompatActivity {

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
        RecyclerView.Adapter adapter = new ListaRolosAdapter();
        recyclerView.setAdapter(adapter);
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

        /*Drawable drawable = menu.findItem(R.id.menu_rolos_settings).getIcon();
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white));
        menu.findItem(R.id.menu_rolos_settings).setIcon(drawable);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_rolos_settings:
                //Mudar para a activity definições
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            case R.id.menu_rolos_search:
                //Mostar caixa de pesquisa
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

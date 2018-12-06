package pt.isec.gps.grupo14.analog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Switch;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_AddCamera;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_AddObjetiva;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.FragmentCamerasAdapter;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.FragmentObjetivasAdapter;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.FragmentPerfilAdapter;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.ViewPagerAdapter;

public class PreferenciasActivity extends AppCompatActivity {

    private static final String PREFS_PROFILE_NAME = "PREFS_PROFILE_NAME";
    private static final String PREFS_PROFILE_NOTF = "PREFS_PROFILE_NOTF";

    AppCompatTextView titulo;
    FloatingActionButton fab;
    BottomSheet_AddCamera bottomSheet_addCamera;
    BottomSheet_AddObjetiva bottomSheet_addObjetiva;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        titulo = findViewById(R.id.title_preferencias);
        fab = findViewById(R.id.fab_preferencias);

        ((Toolbar)findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //add Fragment Here
        adapter.addFragment(new FragmentPerfilAdapter());
        adapter.addFragment(new FragmentCamerasAdapter());
        adapter.addFragment(new FragmentObjetivasAdapter());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setUpTabsIcons();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                if(position == 0){
                    fab.setImageDrawable(getDrawable(R.drawable.ic_round_done));
                    titulo.setText(getString(R.string.titulo_perfil));
                } else if(position == 1){
                    fab.setImageDrawable(getDrawable(R.drawable.ic_round_add));
                    titulo.setText(getString(R.string.titulo_camera));
                }
                else if(position == 2){
                    fab.setImageDrawable(getDrawable(R.drawable.ic_round_add));
                    titulo.setText(getString(R.string.titulo_objetivas));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bottomSheet_addCamera = new BottomSheet_AddCamera();
        bottomSheet_addObjetiva = new BottomSheet_AddObjetiva();


    }

    //Função para definir os icon das tabs
    private void setUpTabsIcons(){
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_lens);
    }

    public void writeProfileInSharedPreferences(){ //função update
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();

        //TextInputEditText Usern = tabLayout.getChildAt(0).findViewById(R.id.UserName);
        //Switch Notif = tabLayout.getChildAt(0).findViewById(R.id.Notif);

        TextInputEditText Usern = adapter.getItem(0).getView().findViewById(R.id.UserName);
        Switch Notif = adapter.getItem(0).getView().findViewById(R.id.Notif);
        if(!Usern.getText().toString().isEmpty()) {
            editor.putString(PREFS_PROFILE_NAME, Usern.getText().toString());
        }else{
            editor.putString(PREFS_PROFILE_NAME,"Utilizador");
        }
        editor.putBoolean(PREFS_PROFILE_NOTF, Notif.isChecked());

        editor.commit();
    }


    public void onClickFabPreferencias(View v) {
        if(position == 0){
            writeProfileInSharedPreferences();
        }
        else if (position == 1) {
            if (!bottomSheet_addCamera.isOpen()) {
                bottomSheet_addCamera.show(getSupportFragmentManager(), "BottomSheet_AddCamera");
            }
        } else if(position == 2){
            if(!bottomSheet_addObjetiva.isOpen()) {
                bottomSheet_addObjetiva.show(getSupportFragmentManager(), "BottomSheet_AddObjetiva");
            }
        }
    }
}

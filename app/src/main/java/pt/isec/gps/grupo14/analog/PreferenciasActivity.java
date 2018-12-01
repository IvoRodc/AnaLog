package pt.isec.gps.grupo14.analog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import pt.isec.gps.grupo14.analog.BottomSheet.BottomSheet_AddCamera;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.FragmentCamerasAdapter;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.FragmentObjetivasAdapter;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.FragmentPerfilAdapter;
import pt.isec.gps.grupo14.analog.PreferenciasAdapters.ViewPagerAdapter;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class PreferenciasActivity extends AppCompatActivity {

    AppCompatTextView titulo;
    FloatingActionButton fab;
    BottomSheet_AddCamera bottomSheet_addCamera;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    int position;

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
    }

    //Função para definir os icon das tabs
    private void setUpTabsIcons(){
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_lens);
    }

    public void onClickFabPreferencias(View v){
        if(position == 1){
            if(!bottomSheet_addCamera.isOpen()){
                bottomSheet_addCamera.show(getSupportFragmentManager(), "BottomSheet_AddCamera");
            }
        }
    }
}

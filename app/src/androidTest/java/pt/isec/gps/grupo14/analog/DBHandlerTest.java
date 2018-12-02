package pt.isec.gps.grupo14.analog;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.AnaLog.Objetiva;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class DBHandlerTest {

    Context context;
    DBHandler db;
    Rolo r;
    Exposicao e;

    @Before
    public void setup(){

        context = InstrumentationRegistry.getTargetContext();
       db = new DBHandler(context);
        r = new Rolo("TESTE",400, 35, 2, "Teste de rolo e cenas", 1 , context );
        Rolo r2 = new Rolo("TESTE",400, 35, 2, "Teste de rolo e cenas", 1 , context );

        e = new Exposicao(100, 100, 100, "Teste exp", 1, 1, context);
        Exposicao e1 = new Exposicao(100, 100, 100, "Teste exp", 1, 1, context);

        Objetiva o = new Objetiva("Objectiva", "ModeloObj", context);
        Objetiva o1 = new Objetiva("Objectiva2", "ModeloObj2", context);
        Camera c = new Camera("RolleiCord", "IIb", context);
        Camera c1 = new Camera("Nikon", "FE",context);

    }

    @After
    public void cleanUp(){

        context.deleteDatabase("AnaLog");
    }
    @Test
    public void getRolo() {

        Rolo rt = db.getRolo(1);

        assertNotNull(rt);
        assertEquals(r.getIdRolo(),rt.getIdRolo());
    }

    @Test
    public void getRolos() {
        ArrayList<Rolo> listarolos = new ArrayList<>(db.getRolos().values());
        int obj = listarolos.size();


        assertEquals(2,obj,0);
    }

    @Test
    public void addRolo() {


        Rolo rt = new Rolo(3, "TESTE",400, 35, 2, "Teste de rolo e cenas",
                false, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()), 1,1 );
        int obj = db.addRolo(rt);//insert only one roll

        assertEquals(3,obj);
        assertEquals(3, db.getRolos().size());
    }

    @Test
    public void updateRolo() {
        Rolo rt = new Rolo(1, "UPDATE",400, 35, 2, "Teste de rolo e cenas",
                false, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()), 1,1 );

        int obj = db.updateRolo(rt);
        assertEquals(1, obj);
        assertEquals("UPDATE", db.getRolo(1).getTitulo());

    }

    @Test
    public void removeRolo() {

        int obj = db.removeRolo(1);
        assertEquals(0, obj);
        assertEquals(null, db.getRolo(1));
    }

    @Test
    public void getExposicao() {
        Exposicao exp = db.getExposicao(1);

        assertNotNull(0);
        assertEquals(e.getIdExposicao(),exp.getIdExposicao());
    }

    @Test
    public void getExposicoes() {
        ArrayList<Exposicao> listaExp = new ArrayList<>(db.getExposicoes(1).values());
        int obj = listaExp.size();


        assertEquals(2,obj);

    }


    @Test
    public void updateExposicao() {
        Exposicao exp = new Exposicao(1, 400, 2, 400,
        "UPDATE", new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()), 1,1 );


        int obj = db.updateExposicao(exp);
        assertEquals(1, obj);
        assertEquals("UPDATE", db.getExposicao(1).getDescricao());

    }

    @Test
    public void removeExposicao() {
    }

    @Test
    public void getCamera() {
    }

    @Test
    public void getCameras() {
    }

    @Test
    public void addCamera() {
    }

    @Test
    public void removeCamera() {
    }

    @Test
    public void getObjetiva() {
    }

    @Test
    public void getObjetivas() {
    }

    @Test
    public void addObjetiva() {
    }

    @Test
    public void removeObjetiva() {
    }
}
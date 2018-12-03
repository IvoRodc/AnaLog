package pt.isec.gps.grupo14.analog;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import androidx.test.InstrumentationRegistry;
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;
import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

import static org.junit.Assert.assertEquals;

public class SplashActivityTest {

    Context context;
    DBHandler db;

    @Before
    public void setup(){

        context = InstrumentationRegistry.getTargetContext();
         db = new DBHandler(context);
        Rolo r = new Rolo("TESTE",400, 35, 5, "Teste de rolo e cenas", "teste" , context );


        Exposicao e = new Exposicao(100, 100, 100, "Teste exp", 1, "teste", context);
        Exposicao e1 = new Exposicao(100, 100, 100, "Teste exp", 1, "teste", context);


    }

    @Test
    public void checkTermRolls() {

        boolean obj = CheckTermRolls();

        assertEquals(false,obj);
    }
    @Test
    public void checkTermRollsTrue() {

        boolean obj = CheckTermRolls();

        assertEquals(true,obj);
    }

    @After
    public void cleanUp(){

        context.deleteDatabase("AnaLog");
    }


    public boolean CheckTermRolls(){

        ArrayList<Rolo> listaRolos = new ArrayList<>(db.getRolos().values());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<listaRolos.size(); i++) {
            if (listaRolos.get(i).getnExposicoes() > (listaRolos.get(i).getMaxExposicoes() - 5) && listaRolos.get(i).getnExposicoes()!=listaRolos.get(i).getMaxExposicoes()) {
                stringBuilder.append(listaRolos.get(i).getTitulo()+" \n");
            }
        }
        if (stringBuilder.toString().isEmpty())
            return false;
        else
        return true;
    }
}
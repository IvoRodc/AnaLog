package pt.isec.gps.grupo14.analog.AnaLog;

import android.content.Context;

import java.util.HashMap;

import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class GestorRolos {

    HashMap<Integer,Rolo> listaRolos;

    public GestorRolos(Context context) {
        this.listaRolos = new DBHandler(context).getRolos();
    }

    public Rolo getRolo(int idRolo)
    {

       return this.listaRolos.get(idRolo);

    }

    public void addRolo(Rolo rolo)
    {
        this.listaRolos.put(rolo.getIdRolo(),rolo);
    }

    public int getNRolos()
    {
        return this.listaRolos.size();
    }

    public int updateDB()
    {
        return 0;
    }
}

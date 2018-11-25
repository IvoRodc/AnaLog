package pt.isec.gps.grupo14.analog.AnaLog;

import java.util.HashMap;

import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class GestorRolos {

    HashMap<int,Rolo> listaRolos;

    public GestorRolos() {
        this.listaRolos = new DBHandler().getRolos();
    }

    public Rolo getRolo(int idRolo)
    {

       return this.listaRolos.get(idRolo);

    }

    public void addRolo(Rolo rolo)
    {
        this.listaRolos.put(rolo.getId(),rolo);
    }

    public int getNRolos()
    {
        return this.listaRolos.size();
    }

    public int updateDB()
    {

    }
}

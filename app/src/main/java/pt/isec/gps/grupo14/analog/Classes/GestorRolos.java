package pt.isec.gps.grupo14.analog.Classes;

import java.util.HashMap;

public class GestorRolos {

    HashMap<int,Rolo> listaRolos;

    public GestorRolos() {
        this.listaRolos = new DBInterface().getRolos();
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

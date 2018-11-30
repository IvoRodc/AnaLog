package pt.isec.gps.grupo14.analog.AnaLog;

import android.content.Context;

import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class Objetiva {
    private int idObjetiva;
    private String marca;
    private String modelo;

    public Objetiva(String marca, String modelo, Context context){
        this.marca = marca;
        this.modelo = modelo;
        this.idObjetiva = new DBHandler(context).addObjetiva(this);
    }

    public Objetiva(int idObjetiva, String marca, String modelo){
        this.idObjetiva = idObjetiva;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getIdObjetiva() {
        return idObjetiva;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
}

package pt.isec.gps.grupo14.analog.AnaLog;

import android.content.Context;

import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class Camera {
    private int idCamera;
    private String marca;
    private String modelo;

    public Camera(String marca, String modelo, Context context){
        this.marca = marca;
        this.modelo = modelo;
        this.idCamera = new DBHandler(context).addCamera(this);

    }

    public Camera(int idCamera, String marca, String modelo){
        this.idCamera = idCamera;
        this.marca = marca;
        this.modelo = modelo;

    }

    public int getIdCamera() {
        return idCamera;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

}

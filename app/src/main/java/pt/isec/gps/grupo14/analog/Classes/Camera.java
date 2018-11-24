package pt.isec.gps.grupo14.analog.Classes;

public class Camera {
    private int idCamera;
    private String marca;
    private String modelo;

    Camera(String marca, String modelo){
        this.marca = marca;
        this.modelo = modelo;
    }

    Camera(int idCamera, String marca, String modelo){
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

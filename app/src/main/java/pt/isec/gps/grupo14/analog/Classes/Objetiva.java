package pt.isec.gps.grupo14.analog.Classes;

public class Objetiva {
    private int idObjetiva;
    private String marca;
    private String modelo;

    public Objetiva(String marca, String modelo){
        this.marca = marca;
        this.modelo = modelo;
        //chamar a função para adicionar objetiva
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

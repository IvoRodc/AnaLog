package pt.isec.gps.grupo14.analog.Classes;

import java.util.Date;

public class Exposicao {

    int idExposicao;
    int idRolo;
    String descricao;
    float velDisparo;
    float abertura;
    float distFocal;
    Date data;
    int idObjetiva;

    public Exposicao(int idExposicao, int idRolo, String descricao, float velDisparo, float abertura, float distFocal, Date data, int idObjetiva) {
        this.idExposicao = idExposicao;
        this.idRolo = idRolo;
        this.descricao = descricao;
        this.velDisparo = velDisparo;
        this.abertura = abertura;
        this.distFocal = distFocal;
        this.data = data;
        this.idObjetiva = idObjetiva;
    }

    public int getIdExposicao() {
        return idExposicao;
    }

    public int getIdRolo() {
        return idRolo;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getVelDisparo() {
        return velDisparo;
    }

    public float getAbertura() {
        return abertura;
    }

    public float getDistFocal() {
        return distFocal;
    }

    public Date getData() {
        return data;
    }

    public int getIdObjetiva() {
        return idObjetiva;
    }

    boolean updateRolo(String desc, float disp, float ab, float dist, int idPbj )
    {

        this.descricao = desc;
        this.velDisparo = disp;
        this.abertura = ab;
        this.distFocal = dist;
        this.idObjetiva = idObj;

    }
}

package pt.isec.gps.grupo14.analog.AnaLog;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class Exposicao {

    private int idExposicao;
    private int idRolo;
    private String descricao;
    private int velDisparo;
    private float abertura;
    private int distFocal;
    private String data;
    private int idObjetiva;

    public Exposicao(int velDisparo, float abertura, int distFocal, String descricao, int idRolo,
                     int idObjetiva, Context context){

        this.velDisparo = velDisparo;
        this.abertura = abertura;
        this.distFocal = distFocal;
        this.descricao = descricao;
        this.idRolo = idRolo;
        this.idObjetiva = idObjetiva;

        this.data = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        this.idExposicao = new DBHandler(context).addExposicao(this);
    }

    public Exposicao(int idExposicao, int velDisparo, float abertura, int distFocal,
                     String descricao, String data, int idRolo, int idObjetiva) {
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

    public int getVelDisparo() {
        return velDisparo;
    }

    public float getAbertura() {
        return abertura;
    }

    public int getDistFocal() {
        return distFocal;
    }

    public String getData() {
        return data;
    }

    public int getIdObjetiva() {
        return idObjetiva;
    }

    public int updateExposicao(String desc, int disp, float ab, int dist, int idObj,
                               Context context) {
        this.descricao = desc;
        this.velDisparo = disp;
        this.abertura = ab;
        this.distFocal = dist;
        this.idObjetiva = idObj;

        return new DBHandler(context).updateExposicao(this);
    }
}

package pt.isec.gps.grupo14.analog.AnaLog;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class Rolo {

    private int idRolo;
    private String titulo;
    private int iso;
    private int formato;
    private String descricao;
    private boolean revelado;
    private String data;
    private String idCamera;
    private int maxExposicoes;
    private int nExposicoes;

    public Rolo(String titulo, int iso, int formato, int maxExposicoes, String descricao,
                String idCamera, Context context) {
        this.titulo = titulo;
        this.iso = iso;
        this.formato = formato;
        this.maxExposicoes = maxExposicoes;
        this.descricao = descricao;
        this.idCamera = idCamera;
        this.nExposicoes = 0;
        this.data = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        this.revelado = false;
        this.idRolo = new DBHandler(context).addRolo(this);
    }

    public Rolo(int id, String titulo, int iso, int formato, int maxExposicoes, String descricao,
                boolean revelado, String data, String idCamera, int nExposicoes) {
        this.idRolo = id;
        this.titulo = titulo;
        this.iso = iso;
        this.formato = formato;
        this.descricao = descricao;
        this.revelado = revelado;
        this.data = data;
        this.idCamera = idCamera;
        this.maxExposicoes = maxExposicoes;
        this.nExposicoes = nExposicoes;
    }

    public int getIdRolo() {
        return idRolo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getIso() {
        return iso;
    }

    public int getFormato() {
        return formato;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isRevelado() {
        return revelado;
    }

    public String getData() {
        return data;
    }

    public String getIdCamera() {
        return idCamera;
    }

    public int getMaxExposicoes() {
        return maxExposicoes;
    }

    public int getnExposicoes() {
        return nExposicoes;
    }

    public int updateRolo(String titulo, int iso, int form, int max, String desc,
                          boolean rev, String idCamera, Context context) {
        this.titulo = titulo;
        this.iso = iso;
        this.formato = form;
        this.maxExposicoes = max;
        this.descricao = desc;
        this.revelado = rev;
        this.idCamera = idCamera;

        return new DBHandler(context).updateRolo(this);
    }
}

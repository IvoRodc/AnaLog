package pt.isec.gps.grupo14.analog.Classes;

import java.util.Date;

public class Rolo {

    int id;
    String titulo;
    float iso;
    int formato;
    String descricao;
    boolean revelado;
    Date data;
    int idCamera;
    int maxExposicoes;
    int nExposicoes;

    public Rolo(int id, String titulo, float iso, int formato, String descricao, boolean revelado, Date data, int idCamera, int maxExposicoes, int nExposicoes) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public float getIso() {
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

    public Date getData() {
        return data;
    }

    public int getIdCamera() {
        return idCamera;
    }

    public int getMaxExposicoes() {
        return maxExposicoes;
    }

    public int getnExposicoes() {
        return nExposicoes;
    }

    public boolean updateRolo(String titulo, float iso, int form, int nExp, int max, String desc, boolean rev, int idCamera)
    {

        this.titulo = titulo;
        this.iso = iso;
        this.formato = form;
        this.nExposicoes = nExp;
        this.maxExposicoes = max;
        this.descricao = desc;
        this.revelado = rev;
        this.idCamera = idCamera;

    }

    void updateDB()
    {

    }
}

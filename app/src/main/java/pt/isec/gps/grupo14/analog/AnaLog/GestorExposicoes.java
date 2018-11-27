package pt.isec.gps.grupo14.analog.AnaLog;

import android.content.Context;

import java.util.HashMap;

import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class GestorExposicoes {

    HashMap<Integer,Exposicao> listaExposicoes;
    int idRolo;

    public GestorExposicoes(Context context) {
        //this.listaExposicoes = new DBHandler(context).getExposicoes();
    }

    public Exposicao getExposicoes(int idExposicao)
    {
        return this.listaExposicoes.get(idExposicao);
    }

    public void addExposicao(Exposicao exposicao)
    {
        this.listaExposicoes.put(exposicao.getIdExposicao(),exposicao);
    }

    public int getNExposicoes()
    {
        return this.listaExposicoes.size();
    }

    public int updateDB()
    {
        return 0;
    }

}

package pt.isec.gps.grupo14.analog.AnaLog;

import java.util.HashMap;

import pt.isec.gps.grupo14.analog.DataBase.DBHandler;

public class GestorExposicoes {

    HashMap<int,Exposicao> listaExposicoes;
    int idRolo;

    public GestorExposicoes() {
        this.listaExposicoes = new DBHandler().getExposicoes();
    }

    public Exposicao getExposicoes(int idExposicao)
    {

        return this.listaExposicoes.get(idExposicao);

    }

    public void addExposicao(Exposicao exposicao)
    {
        this.listaExposicoes.put(exposicao.getId(),exposicao);
    }

    public int getNExposicoes()
    {
        return this.listaExposicoes.size();
    }

    public int updateDB()
    {

    }

}

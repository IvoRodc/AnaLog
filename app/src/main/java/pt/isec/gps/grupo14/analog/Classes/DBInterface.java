package pt.isec.gps.grupo14.analog.Classes;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.NfcAdapter;
import android.util.Log;

import java.util.Date;

public class DBInterface extends SQLiteOpenHelper {

    //region TABELA CAMERA
    private static final String table_camera = "Camera";
    private static final String IDcam = "Idcam";
    private static final String MarcaCam = "Marca";
    private static final String ModeloCam = "Modelo";
    //endregion

    //region TABELA OBJETIVA
    private static final String table_objetiva = "Objetiva";
    private static final String IDobj = "IDobj";
    private static final String MarcaObj = "Marca";
    private static final String ModeloObj = "Marca";
    //endregion

    //region TABELA ROLO
    private static final String table_rolo = "Rolo";
    private static final String IDrolo = "IDrolo";
    private static final String Titulo = "Titulo";
    private static final String ISO = "ISO";
    private static final String Formato = "Formato";
    private static final String NExposicoes = "NExposicoes";
    private static final String DescricaoRolo = "Descricao";
    private static final String Revelado = "Revelado";
    private static final String DataRolo = "Data";
    //endregion

    //region TABELA EXPOSICAO
    private static final String table_exposicao = "Exposicao";
    private static final String IDexp = "IDexp";
    private static final String VelDisparo = "VelDisparo";
    private static final String Abertura = "Abertura";
    private static final String DistFocal = "DistFocal";
    private static final String DescricaoExp = "Descricao";
    private static final String DataExp = "Data";
    //endregion


    @Override
    public void onCreate(SQLiteDatabase db) {

        //region CREATE_TABLE_CAMERA
        String CREATE_TABLE_CAMERA = "CREATE TABLE IF NOT EXISTS"
                + table_camera + " ("
                + IDcam + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MarcaCam +  "VARCHAR(20), "
                + ModeloCam + " VARCHAR(20))";
        //endregion

        //region CREATE_TABLE_OBJETIVA
        String CREATE_TABLE_OBJETIVA = "CREATE TABLE IF NOT EXISTS"
                + table_objetiva + " ("
                + IDobj + "INTEGER,"
                + MarcaObj + " VARCHAR(20),"
                + ModeloObj + " VARCHAR(20))";
        //endregion

        //region CREATE_TABLE_ROLO
        String CREATE_TABLE_ROLO = "CREATE TABLE IF NOT EXISTS"
        + table_rolo + " ("
        + IDrolo + "INTEGER PRIMARY KEY AUTOINCREMENT, "
        + Titulo + " VARCHAR(20),"
        + ISO + " FLOAT,"
        + Formato + " INTEGER,"
        + NExposicoes + " INTEGER,"
        + DescricaoRolo + " TEXT,"
        + Revelado + "BOOLEAN,"
        + DataRolo + "DATE)";
        //endregion



        //region CREATE_TABLE_EXPOSICAO
        String CREATE_TABLE_EXPOSICAO = "CREATE TABLE IF NOT EXISTS"
                + table_exposicao + " ("
                + IDexp + " INTEGER, "
                + VelDisparo + " FLOAT,"
                + Abertura + " FLOAT,"
                + DistFocal + " FLOAT,"
                + DescricaoExp + " TEXT,"
                + DataExp + " DATE)";
        //endregion


        Log.w("Camera ->", CREATE_TABLE_CAMERA);
        Log.w("Objetiva ->", CREATE_TABLE_OBJETIVA);
        Log.w("Rolo ->", CREATE_TABLE_ROLO);
        Log.w("Exposicao ->", CREATE_TABLE_EXPOSICAO);

        db.execSQL(CREATE_TABLE_CAMERA);
        db.execSQL(CREATE_TABLE_OBJETIVA);
        db.execSQL(CREATE_TABLE_ROLO);
        db.execSQL(CREATE_TABLE_EXPOSICAO);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Create table again
        //onCreate(db);
    }

    public void addRolo (Rolo r){
        
    }
}

package pt.isec.gps.grupo14.analog.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class DBInterface extends SQLiteOpenHelper {

    //Versão
    private static final int DB_versao = 1;

    //Nome da base de dados
    private static final String DB_name = "AnaLog";

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


    public DBInterface(Context context){
        super(context, DB_name, null, DB_versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //region CREATE_TABLE_CAMERA
        String CREATE_TABLE_CAMERA = "CREATE TABLE IF NOT EXISTS "
                + table_camera + " ("
                + IDcam + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MarcaCam +  " VARCHAR(20), "
                + ModeloCam + " VARCHAR(20))";
        //endregion

        //region CREATE_TABLE_OBJETIVA
        String CREATE_TABLE_OBJETIVA = "CREATE TABLE IF NOT EXISTS "
                + table_objetiva + " ("
                + IDobj + "INTEGER,"
                + MarcaObj + " VARCHAR(20),"
                + ModeloObj + " VARCHAR(20))";
        //endregion

        //region CREATE_TABLE_ROLO
        String CREATE_TABLE_ROLO = "CREATE TABLE IF NOT EXISTS "
        + table_rolo + " ("
        + IDrolo + "INTEGER PRIMARY KEY AUTOINCREMENT, "
        + Titulo + " VARCHAR(20),"
        + ISO + " INTEGER,"
        + Formato + " INTEGER,"
        + NExposicoes + " INTEGER,"
        + DescricaoRolo + " TEXT,"
        + Revelado + " BOOLEAN,"
        + DataRolo + " DATE,"
        + IDcam + " INTEGER)";
        //endregion


        //region CREATE_TABLE_EXPOSICAO
        String CREATE_TABLE_EXPOSICAO = "CREATE TABLE IF NOT EXISTS "
                + table_exposicao + " ("
                + IDexp + " INTEGER, "
                + VelDisparo + " INTEGER,"
                + Abertura + " FLOAT,"
                + DistFocal + " INTEGER,"
                + DescricaoExp + " TEXT,"
                + DataExp + " DATE,"
                + IDrolo + "INTEGER, "
                + IDobj + "INTEGER)";
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

    public Rolo getRolo(int idRolo) {
        String selectQuery = "SELECT * FROM " + table_rolo + " WHERE " + IDrolo + "=" + idRolo;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        db.close();
        return  new rolo(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),
                cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),
                cursor.getInt(6),cursor.getInt(7),cursor.getInt(8));
    }

    public HashMap<Integer, Rolo> getRolos(){
        String selectQuery = "SELECT * FROM " + table_rolo;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        db.close();

        if(cursor.getCount()>0){
            Map<Integer, Rolo> listaRolos = new HashMap<>();

            do{
                Rolo r =  new rolo(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),
                        cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),
                        cursor.getInt(6),cursor.getInt(7),cursor.getInt(8));
                listaRolos.put(r.getID(), r);
            }while(cursor.moveToNext());

            return listaRolos;
        }
        else {
            return null;
        }
    }

    public int addRolo (Rolo rolo){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(IDrolo, rolo.getID());
        value.put(Titulo, rolo.getTitulo());
        value.put(ISO, rolo.getISO());
        value.put(Formato, rolo.getFormato());
        value.put(NExposicoes, rolo.getMaxExposicoes());
        value.put(DescricaoRolo, rolo.getDescricao());
        value.put(Revelado, rolo.getRevelado());
        value.put(DataRolo, rolo.getDate());

        //Insert Row
        db.insert(table_rolo, null, value);

        String selectQuery = "SELECT " + IDrolo + " FROM " + table_rolo + " ORDER BY 'DESC' ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        db.close();

        return cursor.getInt(0);
    }

    public void updateRolo(Rolo rolo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(IDrolo, rolo.getID());
        value.put(Titulo, rolo.getTitulo());
        value.put(ISO, rolo.getISO());
        value.put(Formato, rolo.getFormato());
        value.put(NExposicoes, rolo.getMaxExposicoes());
        value.put(DescricaoRolo, rolo.getDescricao());
        value.put(Revelado, rolo.getRevelado());
        value.put(DataRolo, rolo.getDate());

        db.update(table_rolo, value,"IDrolo= ?", new String [] {rolo.getID()});

        db.close();
    }

    public void removeRolo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_rolo);
        db.close();
    }

    public Exposicao getExposicao(int idExposicao){
        String selectQuery = "SELECT * FROM " + table_exposicao + " WHERE " + IDexp + "=" + idExposicao;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        db.close();

        return  new exposicao(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),
                cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),
                cursor.getInt(6),cursor.getInt(7));
    }

    public HashMap<Integer, Esposicao> getExposicoes(){
        String selectQuery = "SELECT * FROM " + table_exposicao;
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        db.close();

        if(cursor.getCount()>0){
            Map<Integer, Exposicao> listaExposicoes = new HashMap<>();

            do{
                Exposicao exp = new exposicao(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),
                        cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),
                        cursor.getInt(6),cursor.getInt(7));
                listaExposicoes.put(exp.getID(),exp);
            }while(cursor.moveToNext());

            return listaExposicoes;
        }
        else {
            return null;
        }
    }

    public int addExposicao(Exposicao exp){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(IDexp, exp.getID());
        value.put(VelDisparo, exp.getVelDisparo());
        value.put(Abertura, exp.getAbertura());
        value.put(DistFocal, exp.getDistFocal());
        value.put(DescricaoExp, exp.getDescricao());
        value.put(DataExp, exp.getData());

        //Insert Row
        db.insert(table_exposicao,null,value);

        String selectQuery = "SELECT " + IDexp + " FROM " + table_exposicao + " ORDER BY 'DESC' ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        db.close();

        return cursor.getInt(0);
    }

    public void updateExposicao(Exposicao exp){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(IDexp, exp.getID());
        value.put(VelDisparo, exp.getVelDisparo());
        value.put(Abertura, exp.getAbertura());
        value.put(DistFocal, exp.getDistFocal());
        value.put(DescricaoExp, exp.getDescricao());
        value.put(DataExp, exp.getData());

        db.update(table_exposicao,value,"IDexp = ?", new String[] {exp.getID()});

        db.close();
    }

    public void removeExposicao(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_exposicao);
        db.close();
    }


    //-------------- Cameras

    public Camera getCamera(int idCamera){
        String selectQuery = "SELECT * FROM " + table_camera + " WHERE " + IDcam + "=" + idCamera;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        db.close();

        return  new Camera(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
    }

    public HashMap<Integer, Camera> getCameras(){
        String selectQuery = "SELECT * FROM " + table_camera;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        db.close();

        if(cursor.getCount()>0){
            HashMap<Integer, Camera> listaCameras = new HashMap<>();

            do{
                Camera cam = new Camera(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                listaCameras.put(cam.getIdCamera(),cam);
            }while(cursor.moveToNext());

            return listaCameras;
        }
        else {
            return null;
        }
    }

    public void addCamera(Camera cam) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(MarcaCam, cam.getMarca());
        value.put(ModeloCam, cam.getModelo());

        //Insert Row
        db.insert(table_camera, null, value);
        db.close();
    }

    public void removeCamera(int idCamera){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_camera + " WHERE " + IDobj + "=" + idCamera);
        db.close();
    }


    //-------------- Objetivas

    public Objetiva getObjetiva(int idObjetiva){
        String selectQuery = "SELECT * FROM " + table_objetiva + " WHERE " + IDobj + "=" + idObjetiva;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        db.close();

        return  new Objetiva(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
    }

    public HashMap<Integer, Objetiva> getObjetivas(){
        String selectQuery = "SELECT * FROM " + table_objetiva;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        db.close();

        if(cursor.getCount()>0){
            HashMap<Integer, Objetiva> listaObjetivas = new HashMap<>();

            do{
                Objetiva obj = new Objetiva(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                listaObjetivas.put(obj.getIdObjetiva(),obj);
            }while(cursor.moveToNext());

            return listaObjetivas;
        }
        else {
            return null;
        }
    }

    public int addObjetiva (Objetiva obj){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(MarcaObj, obj.getMarca());
        value.put(ModeloObj, obj.getModelo());

        //Insert Row
        db.insert(table_objetiva, null, value);

        String lastId = "SELECT " + IDcam + " FROM "+ table_objetiva + " ORDER BY 'DESC' LIMIT 1";
        Cursor cursor = db.rawQuery(lastId, null);
        db.close();
        return cursor.getInt(0);
    }

    public void removeObjetiva(int idObj){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_objetiva + " WHERE "+IDobj +"="+idObj);
        db.close();
    }
}

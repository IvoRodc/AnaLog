package pt.isec.gps.grupo14.analog.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.HashMap;

import pt.isec.gps.grupo14.analog.AnaLog.Camera;
import pt.isec.gps.grupo14.analog.AnaLog.Exposicao;
import pt.isec.gps.grupo14.analog.AnaLog.Objetiva;
import pt.isec.gps.grupo14.analog.AnaLog.Rolo;

public class DBHandler extends SQLiteOpenHelper {

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


    public DBHandler(Context context){
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
    }


    //region Métodos Rolos

    /**
     * @param idRolo id do rolo que se pretende receber
     * @return objeto do tipo Rolo que armazena os dados do rolo prentendido
     */
    public Rolo getRolo(int idRolo) {
        String selectQuery = "SELECT * FROM " + table_rolo + " WHERE " + IDrolo + "=" + idRolo;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0){
            String getNExposicoes = "SELECT COUNT("+IDexp+") FROM " + table_exposicao + " WHERE "
                    + IDrolo + "="+idRolo;
            Cursor countCursor = db.rawQuery(getNExposicoes, null);
            db.close();
            return new Rolo(cursor.getInt(0), cursor.getString(1),
                cursor.getInt(2), cursor.getInt(3),
                cursor.getInt(4), cursor.getString(5), Boolean.parseBoolean(cursor.getString(6)),
                cursor.getString(7), cursor.getInt(8), countCursor.getInt(0));
        } else {
            db.close();
            return null;
        }
    }

    /**
     * Reutiliza o método getRolo()
     * @return todos os rolos numa HashMap com key=idRolo que armazena objetos do tipo Rolo
     */
    public HashMap<Integer, Rolo> getRolos(){
        String selectQuery = "SELECT "+IDrolo+" FROM " + table_rolo;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        db.close();

        if(cursor.getCount()>0){
            HashMap<Integer, Rolo> listaRolos = new HashMap<>();

            do{
                //reutiliza o método getRolo para preencher o mapa de Rolos
                listaRolos.put(cursor.getInt(0), getRolo(cursor.getInt(0)));
            }while(cursor.moveToNext());
            return listaRolos;
        }
        else {
            return null;
        }
    }


    /**
     * @param rolo objeto do tipo Rolo a adicionar à base de dados
     * @return id do rolo adicionado
     */
    public int addRolo (Rolo rolo){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Titulo, rolo.getTitulo());
        value.put(ISO, rolo.getIso());
        value.put(Formato, rolo.getFormato());
        value.put(NExposicoes, rolo.getMaxExposicoes());
        value.put(DescricaoRolo, rolo.getDescricao());
        value.put(Revelado, rolo.isRevelado());
        value.put(DataRolo, rolo.getData());
        value.put(IDcam, rolo.getIdCamera());

        //Insert Row
        int idLastInsert = (int)db.insert(table_rolo, null, value);

        db.close();

        return idLastInsert;
    }


    /**
     * @param rolo objeto do tipo rolo com os dados a atualizar na base de dados
     * @return número de linhas afetadas
     */
    public int updateRolo(Rolo rolo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(Titulo, rolo.getTitulo());
        value.put(ISO, rolo.getIso());
        value.put(Formato, rolo.getFormato());
        value.put(NExposicoes, rolo.getMaxExposicoes());
        value.put(DescricaoRolo, rolo.getDescricao());
        value.put(Revelado, rolo.isRevelado());
        value.put(DataRolo, rolo.getData());
        value.put(IDcam, rolo.getIdCamera());

        int affectedRows = db.update(table_rolo, value,"IDrolo = ?", new String [] {""+rolo.getIdRolo()});
        db.close();

        return affectedRows;
    }

    /**
     * @param idRolo id do rolo que se pretende eliminar
     * @return número de linhas afetadas
     */
    public int removeRolo(int idRolo){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_rolo, IDrolo+"= ?", new String[]{""+idRolo});
        int affectedRows = db.delete(table_rolo, IDrolo+"= ?", new String[]{""+idRolo});
        db.close();
        return affectedRows;
    }

    //endregion

    //region Métodos Exposições

    /**
     * @param idExposicao id da exposição que se pretende obter
     * @return objeto do tipo Exposicao que armazena os dados da exposição pretendida
     */
    public Exposicao getExposicao(int idExposicao){
        String selectQuery = "SELECT * FROM " + table_exposicao + " WHERE " + IDexp + "=" + idExposicao;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        db.close();

        if(cursor.getCount() > 0){
            return new Exposicao(cursor.getInt(0),cursor.getInt(1),cursor.getFloat(2),
                    cursor.getInt(3),cursor.getString(4),cursor.getString(5),
                    cursor.getInt(6),cursor.getInt(7));
        } else {
            return null;
        }
    }

    /**
     * Reutiliza o método getExposicao()
     * @return todos as exposições numa HashMap com key=idExposicao que armazena objetos do tipo Exposicao
     */
    public HashMap<Integer, Exposicao> getExposicoes(){
        String selectQuery = "SELECT "+IDexp+" FROM " + table_exposicao;
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        db.close();

        if(cursor.getCount()>0){
            HashMap<Integer, Exposicao> listaExposicoes = new HashMap<>();

            do{
                listaExposicoes.put(cursor.getInt(0), getExposicao(cursor.getInt(0)));
            }while(cursor.moveToNext());
            return listaExposicoes;
        }
        else {
            return null;
        }
    }


    /**
     * @param exp objeto do tipo Exposicao a adicionar à base de dados
     * @return id da exposição adicionada
     */
    public int addExposicao(Exposicao exp){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(VelDisparo, exp.getVelDisparo());
        value.put(Abertura, exp.getAbertura());
        value.put(DistFocal, exp.getDistFocal());
        value.put(DescricaoExp, exp.getDescricao());
        value.put(DataExp, exp.getData());
        value.put(IDrolo, exp.getIdRolo());
        value.put(IDobj, exp.getIdObjetiva());

        //Insert Row
        int idExposicao = (int)db.insert(table_exposicao,null, value);
        db.close();

        return idExposicao;
    }

    /**
     * @param exp objeto do tipo Exposicao com os dados a atualizar na base de dados
     * @return nº de linhas afetadas
     */
    public int updateExposicao(Exposicao exp){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(VelDisparo, exp.getVelDisparo());
        value.put(Abertura, exp.getAbertura());
        value.put(DistFocal, exp.getDistFocal());
        value.put(DescricaoExp, exp.getDescricao());
        value.put(DataExp, exp.getData());
        value.put(IDrolo, exp.getIdRolo());
        value.put(IDobj, exp.getIdObjetiva());

        int affectedRows = db.update(table_exposicao,value,"IDexp = ?", new String[] {""+exp.getIdExposicao()});
        db.close();

        return affectedRows;
    }

    /**
     * @param idExposicao id da exposição que se pretende eliminar
     * @return nº de linhas afetadas
     */
    public int removeExposicao(int idExposicao){
        SQLiteDatabase db = this.getWritableDatabase();
        int affectedRows = db.delete(table_exposicao, IDexp + "= ?", new String[]{""+idExposicao});
        db.close();
        return affectedRows;
    }

    //endregion

    //region Métodos Cameras

    /**
     * @param idCamera id da camera que se pretende receber
     * @return objeto do tipo Camera que armazena os dados da Camera pretendida
     */
    public Camera getCamera(int idCamera){
        String selectQuery = "SELECT * FROM " + table_camera + " WHERE " + IDcam + "=" + idCamera;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        db.close();

        return  new Camera(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
    }

    /**
     * Reutiliza o método getCamera()
     * @return todos as cameras numa HashMap com key=idCamera que armazena objetos do tipo Camera
     */
    public HashMap<Integer, Camera> getCameras(){
        String selectQuery = "SELECT "+ IDcam +" FROM " + table_camera;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        db.close();

        if(cursor.getCount()>0){
            HashMap<Integer, Camera> listaCameras = new HashMap<>();

            do{
                listaCameras.put(cursor.getInt(0),getCamera(cursor.getInt(0)));
            }while(cursor.moveToNext());
            return listaCameras;
        }
        else {
            return null;
        }
    }

    /**
     * @param cam objeto do tipo Camera para registar na base de dados
     * @return id da camera registada
     */
    public int addCamera(Camera cam) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(MarcaCam, cam.getMarca());
        value.put(ModeloCam, cam.getModelo());

        //Insert Row
        int idCamera = (int)db.insert(table_camera, null, value);
        db.close();

        return idCamera;
    }

    /**
     * @param idCamera id da camera que se pretende eliminar
     * @return nº de linhas afetadas
     */
    public int removeCamera(int idCamera){
        SQLiteDatabase db = this.getWritableDatabase();
        int affectedRows = db.delete(table_camera, IDcam+"= ?", new String[]{""+idCamera});
        db.close();
        return affectedRows;
    }

    //endregion

    //region Métodos Objetivas

    /**
     * @param idObjetiva id da objetiva que se pretende receber
     * @return objeto do tipo Objetiva que armazena os dados da objetiva pretendida
     */
    public Objetiva getObjetiva(int idObjetiva){
        String selectQuery = "SELECT * FROM " + table_objetiva + " WHERE " + IDobj + "=" + idObjetiva;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        db.close();

        return  new Objetiva(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
    }

    /**
     * Reutiliza o método getObjetiva()
     * @return todos as objetivas numa HashMap com key=idObjetiva que armazena objetos do tipo Objetiva
     */
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

    /**
     * @param obj objeto do tipo Objetiva para registar na base de dados
     * @return id da objetiva registada
     */
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

    /**
     * @param idObj id da objetiva que se pretende eliminar
     * @return nº de linhas afetadas
     */
    public int removeObjetiva(int idObj){
        SQLiteDatabase db = this.getWritableDatabase();
        int affectedRows = db.delete(table_objetiva, IDobj+"= ?", new String[]{""+idObj});
        db.close();

        return affectedRows;
    }

    //endregion
}
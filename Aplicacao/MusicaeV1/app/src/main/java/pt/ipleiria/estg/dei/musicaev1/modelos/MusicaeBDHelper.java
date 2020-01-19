package pt.ipleiria.estg.dei.musicaev1.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MusicaeBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "musicaedb";
    private static final String TABLE_NAME = "profiles";

    private static final String PROFILE_ID = "id";
    private static final String PROFILE_NOME = "nome";
    private static final String PROFILE_SEXO = "sexo";
    private static final String PROFILE_DATANAC = "dataNasc";
    private static final String PROFILE_NRTELEMOVEL = "nrtelemovel";
    private static final String PROFILE_DESCRICAO = "descricao";
    private static final String PROFILE_FOTO = "foto";
    private static final String PROFILE_LOCALIDADE = "localidade";


    private final SQLiteDatabase database;

    public MusicaeBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TBL_CREATE_PROFILE = "CREATE TABLE " + TABLE_NAME + "(" +
                PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PROFILE_NOME + " TEXT NOT NULL, " +
                PROFILE_SEXO + " TEXT NOT NULL, " +
                PROFILE_DATANAC + " DATETIME NOT NULL, " +
                PROFILE_NRTELEMOVEL + " INTEGER NOT NULL, " +
                PROFILE_DESCRICAO + " INTEGER NOT NULL, " +
                PROFILE_FOTO + " BLOB, " +
                PROFILE_LOCALIDADE + " TEXT NOT NULL);";
        db.execSQL(TBL_CREATE_PROFILE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //*************************** MÉTODOS CRUD ***************************

    public ArrayList<Perfil> getAllPerfisBD(){
        ArrayList<Perfil> perfis = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME, new String[]{
                        PROFILE_ID, PROFILE_NOME, PROFILE_SEXO, PROFILE_DATANAC, PROFILE_NRTELEMOVEL, PROFILE_DESCRICAO, PROFILE_FOTO, PROFILE_LOCALIDADE},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do{
                Perfil auxPerfil = new Perfil(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
                perfis.add(auxPerfil);
            }while (cursor.moveToNext());
        }
        return perfis;
    }

    public void adicionarPerfilBD(Perfil perfil){
        ContentValues values = new ContentValues();
        values.put(PROFILE_NOME, perfil.getNome());
        values.put(PROFILE_SEXO, perfil.getSexo());
        values.put(PROFILE_DATANAC, perfil.getDatanasc());
        values.put(PROFILE_NRTELEMOVEL, perfil.getNrtelemovel());
        values.put(PROFILE_DESCRICAO, perfil.getDescricao());
        values.put(PROFILE_FOTO, perfil.getFoto());
        values.put(PROFILE_LOCALIDADE, perfil.getLocalidade());

        this.database.insert(TABLE_NAME, null, values);
    }

    public boolean guardarPerfilBD(Perfil perfil){
        ContentValues values = new ContentValues();
        values.put(PROFILE_NOME, perfil.getNome());
        values.put(PROFILE_SEXO, perfil.getSexo());
        values.put(PROFILE_DATANAC, perfil.getDatanasc());
        values.put(PROFILE_NRTELEMOVEL, perfil.getNrtelemovel());
        values.put(PROFILE_DESCRICAO, perfil.getDescricao());
        values.put(PROFILE_FOTO, perfil.getFoto());
        values.put(PROFILE_LOCALIDADE, perfil.getLocalidade());

        return this.database.update(TABLE_NAME, values, "id = ?", new String[]{"" + perfil.getId()}) > 0;
    }

    public boolean removerPerfilBD(int idPerfil){
        return (this.database.delete(TABLE_NAME, "id = ?", new String[]{"" + idPerfil}) == 1);
    }

    public void removerAllPerfil(){
        this.database.delete(TABLE_NAME, null, null);
    }
}

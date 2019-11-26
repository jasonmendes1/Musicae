package pt.ipleiria.estg.dei.musicaev1.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PerfilBDTESTE extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "musicaebd";
    private static final String TABLE_NAME = "profiles";

    //Campos:
    private static final String ID_PERFIL = "IdProfile";
    private static final String NOME_PERFIL = "Nome";
    private static final String SEXO_PERFIL = "Sexo";
    private static final String DATANASC_PERFIL = "DataNac";
    private static final String DESCRICAO_PERFIL = "Descricao";
    private static final String FOTO_PERFIL = "Foto";
    private static final String LOCALIDADE_PERFIL = "Localidade";
    private static final String EXPERIENCIA_PERFIL = "Experiencia";

    private final SQLiteDatabase database;

    public PerfilBDTESTE(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTabelaPerfil = "CREATE TABLE " + TABLE_NAME +
                "(" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_PERFIL + " TEXT NOT NULL, " +
                SEXO_PERFIL + " TEXT NOT NULL, " +
                DATANASC_PERFIL + " DATETIME NOT NULL, " +
                DESCRICAO_PERFIL + " TEXT NOT NULL, " +
                FOTO_PERFIL + " INTEGER, " +
                LOCALIDADE_PERFIL + " TEXT NOT NULL, " +
                EXPERIENCIA_PERFIL + " TEXT NOT NULL " +
                ");";

        sqLiteDatabase.execSQL(createTabelaPerfil);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    // *************************************** METODOS CRUD ***************************************
    public ArrayList<Perfil> getAllPerfils(){
        ArrayList<Perfil> perfils = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME, new String[]{
                ID_PERFIL, NOME_PERFIL, SEXO_PERFIL, DATANASC_PERFIL, DESCRICAO_PERFIL, FOTO_PERFIL, LOCALIDADE_PERFIL, EXPERIENCIA_PERFIL}, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Perfil auxPerfil = new Perfil(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6), cursor.getString(7),cursor.getString(8),cursor.getString(9));
                auxPerfil.setId(cursor.getInt(0));
                perfils.add(auxPerfil);
            }while (cursor.moveToNext());
        }
        return perfils;
    }
}

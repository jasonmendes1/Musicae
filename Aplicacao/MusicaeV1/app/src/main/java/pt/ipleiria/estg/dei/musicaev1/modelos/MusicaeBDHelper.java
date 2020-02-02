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
    private static final String TABLE_BANDA = "bandas";
    private static final String TABLE_HABILIDADE = "habilidades";
    private static final String TABLE_GENERO = "generos";
    private static final String TABLE_FEED = "bandahabilidades";

    private static final String HABILIDADE_ID = "Id";
    private static final String HABILIDADE_NOME = "Nome";
    private static final String GENERO_ID = "Id";
    private static final String GENERO_NOME = "Nome";

    private static final String BANDA_ID = "Id";
    private static final String BANDA_NOME = "Nome";
    private static final String BANDA_DESCRICAO = "Descricao";
    private static final String BANDA_LOCALIZACAO = "Localizacao";
    private static final String BANDA_CONTACTO = "Contacto";
    private static final String BANDA_LOGO = "Logo";
    private static final String BANDA_REMOVIDA = "Removida";
    private static final String BANDA_ID_GENERO = "IdGenero";

    private static final String TABLE_PROFILES = "profiles";

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

        String TBL_CREATE_PROFILE = "CREATE TABLE " + TABLE_PROFILES + "(" +
                PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PROFILE_NOME + " TEXT NOT NULL, " +
                PROFILE_SEXO + " TEXT NOT NULL, " +
                PROFILE_DATANAC + " DATETIME NOT NULL, " +
                PROFILE_DESCRICAO + " INTEGER NOT NULL, " +
                PROFILE_FOTO + " BLOB, " +
                PROFILE_LOCALIDADE + " TEXT NOT NULL);";
        db.execSQL(TBL_CREATE_PROFILE);


        String TBL_CREATE_GENERO = "CREATE TABLE " + TABLE_GENERO + "(" +
                GENERO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GENERO_NOME + " TEXT NOT NULL);";
        db.execSQL(TBL_CREATE_GENERO);


        String TBL_CREATE_HABILIDADE = "CREATE TABLE " + TABLE_HABILIDADE + "(" +
                HABILIDADE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HABILIDADE_NOME + " TEXT NOT NULL);";
        db.execSQL(TBL_CREATE_HABILIDADE);


        String TBL_CREATE_BANDA = "CREATE TABLE " + TABLE_BANDA + "(" +
                BANDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BANDA_NOME + " TEXT NOT NULL, " +
                BANDA_DESCRICAO + " TEXT NOT NULL, " +
                BANDA_LOCALIZACAO + " TEXT NOT NULL, " +
                BANDA_CONTACTO + " INTEGER NOT NULL, " +
                BANDA_LOGO + " BLOB, " +
                BANDA_REMOVIDA + " INTEGER NOT NULL, " +
                BANDA_ID_GENERO + " INTEGER REFERENCES " + TABLE_GENERO + "(" + GENERO_ID + ")" + ")";
        db.execSQL(TBL_CREATE_BANDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GENERO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HABILIDADE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BANDA);
        this.onCreate(sqLiteDatabase);
    }

    //*************************** MÉTODOS CRUD ***************************

    public ArrayList<Perfil> getAllPerfisBD(){
        ArrayList<Perfil> perfis = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_PROFILES, new String[]{
                        PROFILE_ID, PROFILE_NOME, PROFILE_SEXO, PROFILE_DATANAC, PROFILE_DESCRICAO, PROFILE_FOTO, PROFILE_LOCALIDADE},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do{
                Perfil auxPerfil = new Perfil(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                perfis.add(auxPerfil);
            }while (cursor.moveToNext());
        }
        return perfis;
    }

    public ArrayList<Banda> getAllBandasBD(){
        ArrayList<Banda> bandas = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_BANDA, new String[]{
                        BANDA_ID, BANDA_NOME, BANDA_DESCRICAO, BANDA_LOCALIZACAO, BANDA_CONTACTO, BANDA_LOGO, BANDA_REMOVIDA, BANDA_ID_GENERO},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do{
                Banda auxBanda = new Banda(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
                bandas.add(auxBanda);
            }while (cursor.moveToNext());
        }
        return bandas;
    }

    public void adicionarPerfilBD(Perfil perfil){
        ContentValues values = new ContentValues();
        values.put(PROFILE_NOME, perfil.getNome());
        values.put(PROFILE_SEXO, perfil.getSexo());
        values.put(PROFILE_DATANAC, perfil.getDatanasc());
        values.put(PROFILE_DESCRICAO, perfil.getDescricao());
        values.put(PROFILE_FOTO, perfil.getFoto());
        values.put(PROFILE_LOCALIDADE, perfil.getLocalidade());

        this.database.insert(TABLE_PROFILES, null, values);
    }

    public void adicionarBandaBD(Banda banda){
        ContentValues values = new ContentValues();
        values.put(BANDA_NOME, banda.getNome());
        values.put(BANDA_DESCRICAO, banda.getDescricao());
        values.put(BANDA_LOCALIZACAO, banda.getLocalizacao());
        values.put(BANDA_CONTACTO, banda.getContacto());
        values.put(BANDA_LOGO, banda.getCapa());

        this.database.insert(TABLE_BANDA, null, values);
    }

    public boolean guardarPerfilBD(Perfil perfil){
        ContentValues values = new ContentValues();
        values.put(PROFILE_NOME, perfil.getNome());
        values.put(PROFILE_SEXO, perfil.getSexo());
        values.put(PROFILE_DATANAC, perfil.getDatanasc());
        values.put(PROFILE_DESCRICAO, perfil.getDescricao());
        values.put(PROFILE_FOTO, perfil.getFoto());
        values.put(PROFILE_LOCALIDADE, perfil.getLocalidade());

        return this.database.update(TABLE_PROFILES, values, "id = ?", new String[]{"" + perfil.getId()}) > 0;
    }

    public boolean guardarBandaBD(Banda banda){
        ContentValues values = new ContentValues();
        values.put(BANDA_NOME, banda.getNome());
        values.put(BANDA_DESCRICAO, banda.getDescricao());
        values.put(BANDA_LOCALIZACAO, banda.getLocalizacao());
        values.put(BANDA_CONTACTO, banda.getContacto());
        values.put(BANDA_LOGO, banda.getCapa());

        return this.database.update(TABLE_BANDA, values, "id = ?", new String[]{"" + banda.getId()}) > 0;
    }

    public boolean removerPerfilBD(int idPerfil){
        return (this.database.delete(TABLE_PROFILES, "id = ?", new String[]{"" + idPerfil}) == 1);
    }

    public boolean removerBandaBD(int idBanda){
        return (this.database.delete(TABLE_BANDA, "id = ?", new String[]{"" + idBanda}) == 1);
    }

    public void removerAllPerfil(){
        this.database.delete(TABLE_PROFILES, null, null);
    }

    public void removerAllBandaBD(){
        this.database.delete(TABLE_BANDA, null, null);
    }
}

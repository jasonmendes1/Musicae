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

    private static final String FEED_ID_BANDA = "IdBanda";
    private static final String FEED_ID_HABILIDADE = "IdHabilidade";
    private static final String FEED_EXPERIENCIA = "experiencia";
    private static final String FEED_COMPROMISSO = "compromisso";

    private final SQLiteDatabase database;

    public MusicaeBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

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

        String TBL_CREATE_FEED = "CREATE TABLE " + TABLE_FEED + "(" +
                FEED_ID_BANDA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FEED_ID_HABILIDADE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FEED_EXPERIENCIA + " TEXT NOT NULL, " +
                FEED_COMPROMISSO + " TEXT NOT NULL, " +
                FEED_ID_BANDA + " INTEGER REFERENCES " + TABLE_BANDA + "(" + BANDA_ID + ")" +
                FEED_ID_HABILIDADE + " INTEGER REFERENCES " + TABLE_HABILIDADE + "(" + HABILIDADE_ID + ")" + ")";
        db.execSQL(TBL_CREATE_FEED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GENERO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HABILIDADE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BANDA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FEED);
        this.onCreate(sqLiteDatabase);
    }

    //*************************** MÉTODOS CRUD ***************************

    public ArrayList<Feed> getAllFeedBD(){
        ArrayList<Feed> bandasFeed = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_BANDA, new String[]{
                        BANDA_ID, BANDA_NOME, BANDA_DESCRICAO, BANDA_LOCALIZACAO, BANDA_CONTACTO, BANDA_LOGO, BANDA_REMOVIDA, BANDA_ID_GENERO},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do{
                Feed auxFeed = new Feed(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                bandasFeed.add(auxFeed);
            }while (cursor.moveToNext());
        }
        return bandasFeed;
    }

    public void adicionarFeedBD(Feed feed){
        ContentValues values = new ContentValues();
        values.put(BANDA_NOME, feed.getNome());
        values.put(HABILIDADE_NOME, feed.getInstrumento());
        values.put(FEED_EXPERIENCIA, feed.getExperiencia());
        values.put(FEED_COMPROMISSO, feed.getCompromisso());
        values.put(BANDA_LOGO, feed.getCapa());

        this.database.insert(TABLE_FEED, null, values);
    }

    public boolean guardarFeedBD(Feed feed){
        ContentValues values = new ContentValues();
        values.put(BANDA_NOME, feed.getNome());
        values.put(HABILIDADE_NOME, feed.getInstrumento());
        values.put(FEED_EXPERIENCIA, feed.getExperiencia());
        values.put(FEED_COMPROMISSO, feed.getCompromisso());
        values.put(BANDA_LOGO, feed.getCapa());

        return this.database.update(TABLE_FEED, values, "id = ?", new String[]{"" + feed.getId()}) > 0;
    }

    public boolean removerFeedBD(int idFeed){
        return (this.database.delete(TABLE_FEED, "id = ?", new String[]{"" + idFeed}) == 1);
    }

    public void removerAllFeedBD(){
        this.database.delete(TABLE_FEED, null, null);
    }
}

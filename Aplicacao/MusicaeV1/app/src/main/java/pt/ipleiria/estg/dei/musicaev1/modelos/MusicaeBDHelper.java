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
    private static final String TABLE_NAME = "Banda";

    private static final String ID_BANDA = "id";
    private static final String NOME_BANDA = "nome";
    private static final String GENERO_BANDA = "genero";
    private static final String LOCALIZACAO_BANDA = "localizacao";
    private static final String CONTACTO_BANDA = "contacto";
    private static final String DESCRICAO_BANDA = "descricao";
    private static final String CAPA_BANDA = "capa";

    private final SQLiteDatabase database;

    public MusicaeBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createBandaTable = "CREATE TABLE " + TABLE_NAME + "(" +
                ID_BANDA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_BANDA + " TEXT NOT NULL, " +
                GENERO_BANDA + " TEXT NOT NULL, " +
                LOCALIZACAO_BANDA + " TEXT NOT NULL, " +
                CONTACTO_BANDA + " INTEGER NOT NULL, " +
                DESCRICAO_BANDA + " TEXT NOT NULL, " +
                CAPA_BANDA + " INTEGER);";
        sqLiteDatabase.execSQL(createBandaTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //*************************** MÉTODOS CRUD ***************************

    public ArrayList<Banda> getAllBandasBD(){
        ArrayList<Banda> bandas = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME, new String[]{
                        ID_BANDA, NOME_BANDA, GENERO_BANDA, LOCALIZACAO_BANDA, CONTACTO_BANDA, DESCRICAO_BANDA, CAPA_BANDA},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do{
                Banda auxBanda = new Banda(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
                bandas.add(auxBanda);
            }while (cursor.moveToNext());
        }
        return bandas;
    }

    public void adicionarBandaBD(Banda banda){
        ContentValues values = new ContentValues();
        values.put(NOME_BANDA, banda.getNome());
        values.put(GENERO_BANDA, banda.getGenero());
        values.put(LOCALIZACAO_BANDA, banda.getLocalizacao());
        values.put(CONTACTO_BANDA, banda.getContacto());
        values.put(DESCRICAO_BANDA, banda.getDescricao());
        values.put(CAPA_BANDA, banda.getCapa());

        this.database.insert(TABLE_NAME, null, values);
    }

    public boolean guardarBandaBD(Banda banda){
        ContentValues values = new ContentValues();
        values.put(NOME_BANDA, banda.getNome());
        values.put(GENERO_BANDA, banda.getGenero());
        values.put(LOCALIZACAO_BANDA, banda.getLocalizacao());
        values.put(CONTACTO_BANDA, banda.getContacto());
        values.put(DESCRICAO_BANDA, banda.getDescricao());
        values.put(CAPA_BANDA, banda.getCapa());

        return this.database.update(TABLE_NAME, values, "id = ?", new String[]{"" + banda.getId()}) > 0;
    }

    public boolean removerBandaBD(int idBanda){
        return (this.database.delete(TABLE_NAME, "id = ?", new String[]{"" + idBanda}) == 1);
    }

    public void removerAllBandasBD(){
        this.database.delete(TABLE_NAME, null, null);
    }
}

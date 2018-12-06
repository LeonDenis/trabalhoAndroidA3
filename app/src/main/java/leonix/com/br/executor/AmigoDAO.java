package leonix.com.br.executor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import leonix.com.br.chatnattor.SQLiteHelper;
import leonix.com.br.model.Amigo;

public class AmigoDAO {
    // DataBase e DataBaseHelper
    private SQLiteDatabase db;
    private SQLiteHelper helper;

    // Tabela de amigos.
    private static final String TABLE_AMIGO = SQLiteHelper.TABLE_AMIGO;

    // Colunas.
    private static final String KEY_ID = "id";
    private static final String KEY_USUARIOA = "idUsuarioA";
    private static final String KEY_USUARIOB = "idUsuarioB";
    private static final String[] COLUMNS = {KEY_ID, KEY_USUARIOA, KEY_USUARIOB};

    public AmigoDAO(Context context) {
        this.helper = new SQLiteHelper(context);
        this.db = helper.openDatabase();
    }

    public long addAmigo(Amigo Amigo) {

        ContentValues values = new ContentValues();

        values.put(KEY_USUARIOA, Amigo.getIdUsuarioA());
        values.put(KEY_USUARIOB, Amigo.getIdUsuarioB());

        return this.db.insert(TABLE_AMIGO, null, values);
    }

    public Amigo getAmigo(Integer id) {

        Cursor cursor = this.db.query(TABLE_AMIGO, COLUMNS, KEY_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Amigo amigo = new Amigo();
        amigo.setId(cursor.getInt(0));
        amigo.setIdUsuarioA(cursor.getInt(1));
        amigo.setIdUsuarioB(cursor.getInt(2));

        return amigo;
    }

    public ArrayList<Amigo> getAllAmigos() {

        ArrayList<Amigo> amigos = new ArrayList();

        Cursor cursor = this.db.rawQuery(String.format("SELECT * FROM %s", TABLE_AMIGO), null);

        if (cursor != null) {
            Amigo amigo = null;
            while (cursor.moveToNext()) {
                amigo = new Amigo();
                amigo.setId(cursor.getInt(0));
                amigo.setIdUsuarioA(cursor.getInt(1));
                amigo.setIdUsuarioB(cursor.getInt(2));
                amigos.add(amigo);
            }
            amigo = null;
            cursor = null;
        }

        return amigos;
    }

    public void deleteAmigo(Amigo amigo) {
        this.db.delete(TABLE_AMIGO, KEY_ID + " = ? AND " + KEY_USUARIOA + " = ? AND " + KEY_USUARIOB + " = ?",
                new String[]{String.valueOf(amigo.getId()),
                        String.valueOf(amigo.getIdUsuarioA()),
                        String.valueOf(amigo.getIdUsuarioB())});
    }

    public void dispose() {
        this.db.close();
        this.db = null;
        this.helper = null;
    }
}

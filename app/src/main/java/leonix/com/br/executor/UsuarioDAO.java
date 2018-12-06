package leonix.com.br.executor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import leonix.com.br.chatnattor.SQLiteHelper;
import leonix.com.br.model.Usuario;

public class UsuarioDAO {
    // DataBase e DataBaseHelper
    private SQLiteDatabase db;
    private SQLiteHelper helper;

    // Tabela de usuários.
    private static final String TABLE_USUARIO = SQLiteHelper.TABLE_USUARIO;

    // Colunas.
    private static final String KEY_ID = "id";
    private static final String KEY_NICK = "nick";
    private static final String KEY_SENHA = "senha";
    private static final String[] COLUMNS = {KEY_ID, KEY_NICK,KEY_SENHA};

    public UsuarioDAO(Context context) {
        this.helper = new SQLiteHelper(context);
        this.db = helper.openDatabase();
    }

    public long addUsuario(Usuario usuario) {

        ContentValues values = new ContentValues();

        values.put(KEY_NICK, usuario.getNick());

        return this.db.insert(TABLE_USUARIO, null, values);
    }

    public Usuario getUsuario(Integer id) {

        Cursor cursor = this.db.query(TABLE_USUARIO, COLUMNS, KEY_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Usuario usuario = new Usuario();
        usuario.setId(cursor.getInt(0));
        usuario.setNick(cursor.getString(1));
        usuario.setSenha(cursor.getString(2));

        return usuario;
    }

    public ArrayList<Usuario> getAllUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList();

        Cursor cursor = this.db.rawQuery(String.format("SELECT * FROM %s", TABLE_USUARIO), null);

        if (cursor != null) {
            Usuario usuario = null;
            while (cursor.moveToNext()) {
                usuario = new Usuario();
                usuario.setId(cursor.getInt(0));
                usuario.setNick(cursor.getString(1));
                usuario.setSenha(cursor.getString(2));
                usuarios.add(usuario);
            }
            usuario = null;
            cursor = null;
        }

        return usuarios;
    }

    public void deleteUsuario(Usuario usuario) {
        this.db.delete(TABLE_USUARIO, KEY_ID + " = ? ", new String[]{String.valueOf(usuario.getId())});
    }

    public void dispose() {
        this.db.close();
        this.db = null;
        this.helper = null;
    }

}

package leonix.com.br.executor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import leonix.com.br.chatnattor.SQLiteHelper;
import leonix.com.br.model.Amigo;
import leonix.com.br.model.Chat;

public class ChatDAO {
    // DataBase e DataBaseHelper
    private SQLiteDatabase db;
    private SQLiteHelper helper;

    // Tabela de chat.
    private static final String TABLE_CHAT = SQLiteHelper.TABLE_CHAT;

    // Colunas.
    private static final String KEY_ID = "id";
    private static final String KEY_USUARIOA = "idUsuarioA";
    private static final String KEY_USUARIOB = "idUsuarioB";
    private static final String KEY_MENSAGEM = "mensagem";
    private static final String[] COLUMNS = {KEY_ID, KEY_USUARIOA, KEY_USUARIOB,KEY_MENSAGEM};

    public ChatDAO(Context context) {
        this.helper = new SQLiteHelper(context);
        this.db = helper.openDatabase();
    }

    public long addChat(Chat chat) {

        ContentValues values = new ContentValues();

        values.put(KEY_USUARIOA, chat.getIdUsuarioA());
        values.put(KEY_USUARIOB, chat.getIdUsuarioB());
        values.put(KEY_MENSAGEM, chat.getMensagem());

        return this.db.insert(TABLE_CHAT, null, values);
    }

    public Chat getChat(Integer id) {

        Cursor cursor = this.db.query(TABLE_CHAT, COLUMNS, KEY_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Chat chat = new Chat();
        chat.setId(cursor.getInt(0));
        chat.setIdUsuarioA(cursor.getInt(1));
        chat.setIdUsuarioB(cursor.getInt(2));
        chat.setMensagem(cursor.getString(2));

        return chat;
    }

    public ArrayList<Chat> getAllChats() {

        ArrayList<Chat> chats = new ArrayList();

        Cursor cursor = this.db.rawQuery(String.format("SELECT * FROM %s", TABLE_CHAT), null);

        if (cursor != null) {
            Chat chat = new Chat();
            while (cursor.moveToNext()) {
                chat = new Chat();
                chat.setId(cursor.getInt(0));
                chat.setIdUsuarioA(cursor.getInt(1));
                chat.setIdUsuarioB(cursor.getInt(2));
                chat.setMensagem(cursor.getString(3));
                chats.add(chat);
            }
            chat = null;
            cursor = null;
        }

        return chats;
    }

    public void deleteChat(Chat chat) {
        this.db.delete(TABLE_CHAT, KEY_ID + " = ? ", new String[]{String.valueOf(chat.getId())});
    }

    public void dispose() {
        this.db.close();
        this.db = null;
        this.helper = null;
    }
}

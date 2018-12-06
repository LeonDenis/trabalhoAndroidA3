package leonix.com.br.chatnattor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Versão do DataBase.
    private static final int DATABASE_VERSION = 1;
    // Nome do DataBase
    private static final String DATABASE_NAME = "chatnattor.db";
    // Tabela de usuários.
    public static final String TABLE_USUARIO = "usuario";
    // Tabela de amigos.
    public static final String TABLE_AMIGO = "amigo";
    // Tabela de chat.
    public static final String TABLE_CHAT = "chat";

    public SQLiteHelper(Context context) {
        // Setando o contexto.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase openDatabase() {
        // Abrindo o DataBase.
        return this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criando tabela de usuários.
        db.execSQL(String.format("CREATE TABLE %s (id INTEGER PRIMARY KEY AUTOINCREMENT, nick TEXT, senha TEXT)", TABLE_USUARIO));
        // Criando tabela de amigos.
        db.execSQL(String.format("CREATE TABLE %s (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuarioA INTEGER, idUsuarioB INTEGER)", TABLE_AMIGO));
        // Criando tabela de chat.
        db.execSQL(String.format("CREATE TABLE %s (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuarioA INTEGER, idUsuarioB INTEGER, mensagem TEXT)", TABLE_CHAT));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Deleta a tabela de usuários.
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_USUARIO));
        // Deleta a tabela de amigos.
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_AMIGO));
        // Deleta a tabela de chat.
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_CHAT));
        // Criando tabelas.
        this.onCreate(db);
    }
}

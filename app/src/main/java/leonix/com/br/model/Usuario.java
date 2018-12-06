package leonix.com.br.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    /**
     * Versão.
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nick;

    private  String senha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

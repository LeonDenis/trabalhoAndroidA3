package leonix.com.br.model;

import java.io.Serializable;

public class Amigo implements Serializable {

    /**
     * Versão.
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer idUsuarioA;

    private Integer idUsuarioB;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuarioA() {
        return idUsuarioA;
    }

    public void setIdUsuarioA(Integer idUsuarioA) {
        this.idUsuarioA = idUsuarioA;
    }

    public Integer getIdUsuarioB() {
        return idUsuarioB;
    }

    public void setIdUsuarioB(Integer idUsuarioB) {
        this.idUsuarioB = idUsuarioB;
    }
}

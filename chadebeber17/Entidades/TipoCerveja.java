package k3.chadebeber17.Entidades;

import java.io.Serializable;

/**
 * Created by Kesia on 08/12/2015.
 */


public class TipoCerveja {


    private String descricao;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

}

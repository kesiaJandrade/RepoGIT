package k3.chadebeber17.Entidades;

import android.graphics.Bitmap;
import android.media.Image;
import android.text.Editable;

import java.io.Serializable;

/**
 * Created by Rodrigo on 08/12/2015.
 */
public class Cerveja implements Serializable {



    public static String ID = "_id";
    public static String MARCA = "marca";
    public static String DESCRICAO = "descricao";
    public static String TIPO = "tipo";
    public static String ALCOOL = "alcool";
    public static String QUANTIDADE = "quantidade";
    public static String LOCALCOMPRA = "localCompra";
    public static String FOTO = "localCompra";
    public static String CAMINHOFOTO = "caminhoFoto";
    public static String DIRETORIOFOTO = "diretorioFoto";
    public static String VOTO = "voto";




    private long id;
    private String marca;
    private String descricao;
    private String tipo;
    private String alcool;
    private String quantidade;
    private String localCompra;
    private String foto;
    private String caminhoFoto;
    private String diretorioFoto;
    private String voto;



    public Cerveja() { //construtor da classe

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getAlcool() {
        return alcool;
    }

    public void setAlcool(String alcool) {
        this.alcool = alcool;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getLocalCompra() {
        return localCompra;
    }

    public void setLocalCompra(String localCompra) {
        this.localCompra = localCompra;

    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;

    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getDiretorioFoto() {
        return diretorioFoto;
    }

    public void setDiretorioFoto(String diretorioFoto) {
        this.diretorioFoto = diretorioFoto;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    @Override
    public String toString(){
        return marca; //precisa ser implementado porque o sistema precisa retornar uma string em vez do objeto
    }



}

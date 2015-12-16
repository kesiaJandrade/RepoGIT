package k3.chadebeber17.Domínio;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ArrayAdapter;
import android.widget.*;

import java.io.FileInputStream;
import java.io.IOException;

import k3.chadebeber17.Entidades.Cerveja;

/**
 * Created by Rodrigo on 11/12/2015.
 */
public class RepositorioCerveja
{
    private SQLiteDatabase conn;

    public RepositorioCerveja(SQLiteDatabase conn){

        this.conn = conn;
    }


    public ContentValues preencherDados(Cerveja cerveja) {

        ContentValues values = new ContentValues();

        values.put("marca", cerveja.getMarca());
        values.put("tipo", cerveja.getTipo());
        values.put("descricao", cerveja.getDescricao());
        values.put("alcool", cerveja.getAlcool());
        values.put("quantidade", cerveja.getQuantidade());
        values.put("localCompra", cerveja.getLocalCompra());
        values.put("foto", cerveja.getFoto());
        values.put("caminhoFoto", cerveja.getCaminhoFoto());
        values.put("diretorioFoto", cerveja.getDiretorioFoto());
        values.put("voto", cerveja.getVoto());

                return values;
    }


    public void inserirCerveja(Cerveja cerveja){

        ContentValues values = preencherDados(cerveja);
        conn.insertOrThrow("Cerveja", null, values);

    }

    public void alterarCerveja(Cerveja cerveja){

        ContentValues values = preencherDados(cerveja);
        conn.update("Cerveja", values, "_id = ? ", new String[]{String.valueOf(cerveja.getId())});

    }

    public void excluirCerveja(long id){

        conn.delete("Cerveja", "_id = ? ", new String[]{String.valueOf(id)});
    }

    public ArrayAdapter<Cerveja> listagemCerveja(Context context){

        ArrayAdapter<Cerveja> adpCerveja = new ArrayAdapter<Cerveja>(context, android.R.layout.simple_list_item_1);

               Cursor cursor = conn.query("Cerveja", null, null, null, null, null, null); ///Cursor cursor = conn.query("foto_anexo",null,"caminho = ?",new String[]{caminho},null,null,null);
                if (cursor.getCount() > 0){

                    cursor.moveToFirst(); //posiciona no primeiro registro para que seja possível percorrer a tabela e recuperar os valores dos registros

                    do {

                        Cerveja cerveja = new Cerveja();

                        cerveja.setId(cursor.getLong(cursor.getColumnIndex(Cerveja.ID)));
                        cerveja.setMarca(cursor.getString(cursor.getColumnIndex(Cerveja.MARCA)));
                        cerveja.setTipo(cursor.getString(cursor.getColumnIndex(Cerveja.TIPO)));
                        cerveja.setDescricao(cursor.getString(cursor.getColumnIndex(Cerveja.DESCRICAO)));
                        cerveja.setAlcool(cursor.getString(cursor.getColumnIndex(Cerveja.ALCOOL)));
                        cerveja.setQuantidade(cursor.getString(cursor.getColumnIndex(Cerveja.QUANTIDADE)));
                        cerveja.setLocalCompra(cursor.getString(cursor.getColumnIndex(Cerveja.LOCALCOMPRA)));
                        cerveja.setFoto(cursor.getString(cursor.getColumnIndex(Cerveja.FOTO)));
                        cerveja.setVoto(cursor.getString(cursor.getColumnIndex(Cerveja.VOTO)));                 ///////
                        cerveja.setCaminhoFoto(cursor.getString(cursor.getColumnIndex(Cerveja.CAMINHOFOTO)));
                        cerveja.setDiretorioFoto(cursor.getString(cursor.getColumnIndex(Cerveja.DIRETORIOFOTO)));



                        adpCerveja.add(cerveja);

                    }while (cursor.moveToNext());


                }

                return adpCerveja;

    }




}

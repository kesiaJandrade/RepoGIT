package k3.chadebeber17;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.database.sqlite.*;
import android.database.*;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import k3.chadebeber17.DataBase.DataBase;
import k3.chadebeber17.Domínio.RepositorioCerveja;
import k3.chadebeber17.Entidades.Cerveja;
import k3.chadebeber17.Entidades.TipoCerveja;

public class CadCerveja extends AppCompatActivity {


    private ArrayAdapter<TipoCerveja> spAdapter;
    private List<TipoCerveja> spItens;

    private EditText edtMarca;
    private Spinner spinnerTipoCerv;
    private EditText edtDescricao;
    private EditText edtAlcool;
    private EditText edtQuantidade;
    private EditText edtComprou;
    private EditText editTextCaminho;

    private byte[] imgens = null;
    private static final int REQUEST_IMAGE = 1;
    private ImageView fotoCerveja;


    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioCerveja repositorioCerveja;
    private Cerveja cerveja;
    private RepositorioCerveja adpCerveja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cerveja);
        Toolbar toolbar = (Toolbar) findViewById(R.id.inc_toolbar_app);
        setSupportActionBar(toolbar);

        FloatingActionButton floacSalvar = (FloatingActionButton) findViewById(R.id.floacSalvar);
        floacSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                salvar();
                Intent intent = new Intent();

                setResult(RESULT_OK, intent);

                finish();

            }
        });

        /* dataBase = new DataBase(this);
        conn = dataBase.getWritableDatabase();

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();
        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }*/

        edtMarca = (EditText) findViewById(R.id.edtMarca);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        edtAlcool = (EditText) findViewById(R.id.edtAcool);
        edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
        edtComprou = (EditText) findViewById(R.id.edtComprou);
        fotoCerveja = (ImageView) findViewById(R.id.imgFotoCerveja);
        editTextCaminho = (EditText) findViewById(R.id.editTextCaminho);

        spinnerTipoCerv = (Spinner) findViewById(R.id.spinnerTipoCerv);//este é o id do campo na activity
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerTipoCerv, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoCerv.setAdapter(adapter);

        spItens = new ArrayList<TipoCerveja>();

        TipoCerveja t1 = new TipoCerveja();
        t1.setDescricao("Abbey");
        spItens.add(t1);

        TipoCerveja t2 = new TipoCerveja();
        t2.setDescricao("Altbier");
        spItens.add(t2);

        TipoCerveja t3 = new TipoCerveja();
        t3.setDescricao("Amber/Brown ou Red Ale");
        spItens.add(t3);

        TipoCerveja t4 = new TipoCerveja();
        t4.setDescricao("American Strong Ale");
        spItens.add(t4);

        TipoCerveja t5 = new TipoCerveja();
        t5.setDescricao("Belgian Specialty Ale");
        spItens.add(t5);

        TipoCerveja t6 = new TipoCerveja();
        t6.setDescricao("Bock");
        spItens.add(t6);

        TipoCerveja t7 = new TipoCerveja();
        t7.setDescricao("Chope");
        spItens.add(t7);

        TipoCerveja t8 = new TipoCerveja();
        t8.setDescricao("Dark Lager");
        spItens.add(t8);

        TipoCerveja t9 = new TipoCerveja();
        t9.setDescricao("Fruit Bier");
        spItens.add(t9);

        TipoCerveja t10 = new TipoCerveja();
        t10.setDescricao("Indian Pale Ale(IPA)");
        spItens.add(t10);

        TipoCerveja t11 = new TipoCerveja();
        t11.setDescricao("Irish Red Ale(IPA)");
        spItens.add(t11);

        TipoCerveja t12 = new TipoCerveja();
        t12.setDescricao("Lager");
        spItens.add(t12);

        TipoCerveja t13 = new TipoCerveja();
        t13.setDescricao("Lambic");
        spItens.add(t13);

        TipoCerveja t14 = new TipoCerveja();
        t14.setDescricao("Lambic Fuit");
        spItens.add(t14);

        TipoCerveja t15 = new TipoCerveja();
        t15.setDescricao("Malt Liquor");
        spItens.add(t15);

        TipoCerveja t16 = new TipoCerveja();
        t16.setDescricao("Pale Ale");
        spItens.add(t16);

        TipoCerveja t17 = new TipoCerveja();
        t17.setDescricao("Pale Lager");
        spItens.add(t17);

        TipoCerveja t18 = new TipoCerveja();
        t18.setDescricao("Porter");
        spItens.add(t18);

        TipoCerveja t19 = new TipoCerveja();
        t19.setDescricao("Rauchbier");
        spItens.add(t19);

        TipoCerveja t20 = new TipoCerveja();
        t20.setDescricao("Scotch Ale");
        spItens.add(t20);

        TipoCerveja t21 = new TipoCerveja();
        t21.setDescricao("Stout");
        spItens.add(t21);

        TipoCerveja t22 = new TipoCerveja();
        t22.setDescricao("Straight/Unblended");
        spItens.add(t22);

        TipoCerveja t23 = new TipoCerveja();
        t23.setDescricao("Strong Ale");
        spItens.add(t23);

        TipoCerveja t24 = new TipoCerveja();
        t24.setDescricao("Trapista");
        spItens.add(t24);

        TipoCerveja t25 = new TipoCerveja();
        t25.setDescricao("Weissbier/Weizenbier");
        spItens.add(t25);

        Bundle bundle = getIntent().getExtras(); //retorna um objeto do tipo bundle

        if ((bundle != null) && (bundle.containsKey("Cerveja"))) {
            cerveja = (Cerveja) bundle.getSerializable("Cerveja");
            preencheDados();

        } else {
            cerveja = new Cerveja();
        }

        try {                                                     // criando conexão com o banco de dados
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioCerveja = new RepositorioCerveja(conn);


        } catch (SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }


   }


    public void tirarFoto(View view) {

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 0);    //passando a foto para outra activity
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap img = (Bitmap) bundle.get("data");


                ImageView fotoCerveja = (ImageView) findViewById(R.id.imgFotoCerveja);
                fotoCerveja.setImageBitmap(img);

                Uri tempUri = getIntent().getData();

                editTextCaminho.setText(String.valueOf(getImageUri(getApplicationContext(), img)));

            }
        }
    }

        public Uri getImageUri(Context inContext, Bitmap img) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String caminhoImagem = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), img, "Title", null);
        return Uri.parse(caminhoImagem);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cad_cerveja, menu);


        if (cerveja.getId() > 0)
            menu.getItem(1).setVisible(true);


        if (cerveja.getId() > 0)
            menu.getItem(0).setVisible(true);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.acao_votar:
                startActivity(new Intent(this, Votar.class));
                break;

            case R.id.acao_excluirCerveja:
                excluir();
                finish();

                break;
        }


        return super.onOptionsItemSelected(item);
    }



    private void preencheDados() {                                 // é chamado quando um item é clicado na tela de listagem


        editTextCaminho.setText(cerveja.getCaminhoFoto());  ///falta pegar a imagem e colocar no imageView


        edtMarca.setText(cerveja.getMarca());
        edtDescricao.setText(cerveja.getDescricao());
        spinnerTipoCerv.setSelection(Integer.parseInt(cerveja.getTipo()));
        edtAlcool.setText(cerveja.getAlcool());
        edtQuantidade.setText(cerveja.getQuantidade());
        edtComprou.setText(cerveja.getLocalCompra());
    }

    private void salvar() {

        try {

            cerveja.setMarca(edtMarca.getText().toString());
            cerveja.setDescricao(edtDescricao.getText().toString());
            cerveja.setAlcool(edtAlcool.getText().toString());
            cerveja.setQuantidade(edtQuantidade.getText().toString());
            cerveja.setTipo(String.valueOf(spinnerTipoCerv.getSelectedItemPosition()));
            cerveja.setLocalCompra(edtComprou.getText().toString());
            cerveja.setCaminhoFoto(editTextCaminho.getText().toString());


            if (cerveja.getId() == 0) {
                repositorioCerveja.inserirCerveja(cerveja);
                Toast.makeText(this, "Cerveja salva com sucesso", Toast.LENGTH_LONG).show();

            } else
                repositorioCerveja.alterarCerveja(cerveja);

        }catch (Exception ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao salvar os dados: " + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }

    }


    private void excluir(){

        try{


            repositorioCerveja.excluirCerveja(cerveja.getId());
            Toast.makeText(this, "Cerveja excluída", Toast.LENGTH_LONG).show();

        }catch (Exception ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao excluir os dados: " + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }

}

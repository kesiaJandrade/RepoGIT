package k3.chadebeber17;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.security.cert.CertPathValidator;

import k3.chadebeber17.DataBase.DataBase;
import k3.chadebeber17.Domínio.RepositorioCerveja;
import k3.chadebeber17.Entidades.Cerveja;


public class ListaCerveja extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    private Toolbar toolbar;
    private ListView lstCervejas;
    private EditText edtPesquisar;
    private ArrayAdapter<Cerveja> adpCerveja;


    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioCerveja repositorioCerveja;

    int REQ_CODE = 1; // CÓDIGO DA ACTIVITY DE CADASTRO DE CEVERJA CHAMADA PELO MENU NOVA CERVEJA

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cerveja);
        Toolbar toolbar = (Toolbar) findViewById(R.id.inc_toolbar_app);
        setSupportActionBar(toolbar);

        lstCervejas = (ListView) findViewById(R.id.lstCervejas);
        lstCervejas.setOnItemClickListener(this);
        edtPesquisar = (EditText) findViewById(R.id.edtPesquisar);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioCerveja = new RepositorioCerveja(conn);
            adpCerveja = repositorioCerveja.listagemCerveja(this);
            lstCervejas.setAdapter(adpCerveja);

            FiltraDados filtraDados = new FiltraDados(adpCerveja);

            edtPesquisar.addTextChangedListener(filtraDados);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_cerveja, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_cadastroCerveja){
            Intent intent = new Intent(getApplicationContext(), CadCerveja.class);
            startActivityForResult(intent,REQ_CODE);

            //startActivity(new Intent(k3.chadebeber.this, CadastroCerveja.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){


            adpCerveja = repositorioCerveja.listagemCerveja(this);
            lstCervejas.setAdapter(adpCerveja);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Cerveja cerveja = adpCerveja.getItem(position); //passa a posição do objeto selecionado

        Intent intent = new Intent(this, CadCerveja.class);
        intent.putExtra("Cerveja", cerveja);

        startActivityForResult(intent, 0);
    }

    private class FiltraDados implements TextWatcher{


       private ArrayAdapter<Cerveja> arrayAdapter;

       private FiltraDados (ArrayAdapter<Cerveja> arrayAdapter){
           this.arrayAdapter = arrayAdapter;
       }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}

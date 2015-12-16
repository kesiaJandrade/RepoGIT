package k3.chadebeber17;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import k3.chadebeber17.Entidades.Cerveja;

public class Votar extends AppCompatActivity {

    private static TextView txtNotaGeral;
    private static RatingBar ratingBarVoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.inc_toolbar_app);
        setSupportActionBar(toolbar);
        recebeVoto();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void recebeVoto(){
        ratingBarVoto = (RatingBar)findViewById(R.id.radStarVoto);
        txtNotaGeral = (TextView)findViewById(R.id.txtNotaGeral);

        ratingBarVoto.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                txtNotaGeral.setText(String.valueOf(rating));
            }
        });
    }



}

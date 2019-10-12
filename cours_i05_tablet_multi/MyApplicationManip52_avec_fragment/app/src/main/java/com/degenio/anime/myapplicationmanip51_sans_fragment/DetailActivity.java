package com.degenio.anime.myapplicationmanip51_sans_fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DetailActivity extends AppCompatActivity
        implements DetailFragment.OnFragmentInteractionListener {
//    private TextView txtDetail;
//    String[] optionsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        optionsDetails =
//                getResources().getStringArray(R.array.optionsDetails);
//        setWidget();
//        //recuperer le detail à partir de l'intent
//        Intent recu = getIntent();
//        int numero = recu.getIntExtra("positionItem",0);
//        txtDetail.setText(optionsDetails[numero]);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

//    private void setWidget() {
//        txtDetail = (TextView) findViewById(R.id.txtDetail);
//    }

}

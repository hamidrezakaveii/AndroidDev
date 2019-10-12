package com.degenio.anime.myapplicationmanip51_sans_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DetailActivity extends AppCompatActivity {
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
        //Recuperer le fragment DEtailFragment
        DetailFragment frg =
                (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.frgDetail);

        Intent monIntent = getIntent();
        int index = monIntent.getIntExtra("menuPosition",0);
        frg.afficherDetailMenu(index);


    }



}

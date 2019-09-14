package com.example.a2_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class DestinationActivity extends AppCompatActivity {
    private EditText txtVotreNom, txtVotreSalaire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setWidgets();
        setListeners();

    }

    private void setListeners() {

    }

    private void setWidgets() {

        txtVotreNom = findViewById(R.id.txtVotreNom);
        txtVotreSalaire = findViewById(R.id.txtVotreSalaire);

        Intent desIntent = getIntent();

        String nom = desIntent.getStringExtra("nom");
        Double salaire = desIntent.getDoubleExtra("salaire", 0.0);

        txtVotreNom.setText(nom);
        txtVotreSalaire.setText(String.valueOf(salaire + 1000));

    }

}

package com.example.threadbase;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtDecompte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        //Creer un Runnable pour afficher les chiffres de 10 à 0
        final Handler monHandler = new Handler();
        Runnable decompteur = new Runnable() {
            @Override
            public void run() {
                //decompte
                for (int i = 10; i >= 0; i--) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.v("hafed", "valeur:" + i);

                    final int value = i;
                    monHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtDecompte.setText(String.valueOf(value));
                        }
                    });
                }
                //demarrer MenuActivity
                Intent monIntent = new Intent(MainActivity.this,
                        MenuActivity.class);
                startActivity(monIntent);

            }
        };
        //demarrer le thread
        Thread toto = new Thread(decompteur);
        toto.start();
    }

    private void setWidgets() {
        txtDecompte = findViewById(R.id.txtDecompte);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

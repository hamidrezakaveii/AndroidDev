package com.example.formation.project_alerts;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnCommander;
    private Button btnTelecharger;
    private String choix;
    private EditText txtProduit;
    private EditText txtChoix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        btnCommander = findViewById(R.id.btnCommander);
        btnCommander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherDialogue();
            }
        });


        btnTelecharger = findViewById(R.id.btnTelecharger);
        btnTelecharger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //afficherDialogueProgress();
                afficherDialoguePersonalise();
            }
        });

        txtChoix = findViewById(R.id.txtChoix);
        txtChoix.setText(txtProduit.getText().toString());
    }


    private void afficherDialoguePersonalise() {

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View monDialogue = inflater.inflate(R.layout.dialogue2, null);

        //Dialog monDialogue = new Dialog(MainActivity.this);
        //monDialogue.setContentView(R.layout.dialogue2);
        //monDialogue.setTitle("Command Speciale");
        TextView tv = monDialogue.findViewById(R.id.txtDialouge);
        tv.setText("Choix de gout important ici");

         txtProduit = monDialogue.findViewById(R.id.txtProduit);

        //creet objet de builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Etes vous sur la commender?")
                //.setTitle(R.string.titr)
                .setCancelable(true)
                .setView(monDialogue)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, txtProduit.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Neutre", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        //creer le dialoque
        AlertDialog alerte = builder.create();
        //afficher le dialouge
        alerte.show();

        //monDialogue.show();
    }

    private void afficherDialogueProgress() {
        ProgressBar progressBar = new ProgressBar(MainActivity.this);
        progressBar.setProgress(45);
        progressBar.setMax(100);
    }

    private void afficherDialogue() {
        //creet objet de builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Etes vous sur la commender?")
        .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Dialouge de base")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Neutre", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        //creer le dialoque
        AlertDialog alerte = builder.create();
        //afficher le dialouge
        alerte.show();
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

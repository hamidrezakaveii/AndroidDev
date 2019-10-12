package com.degenio.anime.myapplicationmanip51_sans_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ListeActivity extends AppCompatActivity implements InteractionListener {

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



    @Override
    public void itemSelection(int s) {
       // Log.v("hafed","fragment");
        DetailFragment frg =
                (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.frgDetail);
        //detecter si fragment DEtailFragment est dans ListeActivity
        //Si Non, créer intent et appeler DEtailActivity

        //Si oui, chercher directement OptionsDetail de Fragment Detail
        //Afficher dans le TextView de DetailFragment le texte correspondant
        if (frg ==null || !frg.isInLayout()  ){
            // Log.v("hafed","frg non trouve");
            //DetailFragment n'Est pas avec ListFragment, utiliser Intent
            //recuperer le detail à partir de l'intent
            Intent monIntent = new Intent(ListeActivity.this, DetailActivity.class);
            monIntent.putExtra("menuPosition", s);
            startActivity(monIntent);
        }else{

            //on a ListFragment et DetailFragment dans la meme activité ListeActivity
            //pas de intent
            //Log.v("hafed","frg trouve");
            frg.afficherDetailMenu(s);
        }
    }
}

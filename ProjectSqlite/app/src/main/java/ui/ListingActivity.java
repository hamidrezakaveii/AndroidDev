package ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.formation.projectsqlite.R;

import java.util.ArrayList;

import database.DataBaseAdapter;
import model.Personne;

public class ListingActivity extends AppCompatActivity {
    private ListView listView;
    private DataBaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
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

        setWidgets();
        populateData();
    }

    private void setWidgets() {
        listView = findViewById(R.id.listingView);
    }

    private void populateData() {
        dbAdapter = new DataBaseAdapter(ListingActivity.this);
        dbAdapter.openDatabase();
        ArrayList<Personne> listing = dbAdapter.selectionerData();
        dbAdapter.close();

        //afficher list view
        ArrayAdapter<Personne> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listing);
        listView.setAdapter(listAdapter);
    }

}

package ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.formation.projectsqlite.R;

import database.DataBaseAdapter;

public class MainActivity extends AppCompatActivity {
    private Button btnSave;
    private Button btnRead;
    private EditText txtID, txtNom;
    private DataBaseAdapter dbAdapter;

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

        setWidgets();
        setListener();
    }

    private void setListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter = new DataBaseAdapter(MainActivity.this);
                dbAdapter.openDatabase();
                dbAdapter.insertData(Integer.parseInt(txtID.getText().toString()), txtNom.getText().toString());
                dbAdapter.close();
            }
        });


        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In the MainActivity
                //dbAdapter = new DataBaseAdapter(MainActivity.this);
                //dbAdapter.openDatabase();
                //dbAdapter.selectionerData();
                //In the ListingActivity
                Intent monIntent = new Intent(MainActivity.this, ListingActivity.class);
                startActivity(monIntent);

            }
        });
    }

    private void setWidgets() {
        btnRead = findViewById(R.id.btnRead);
        btnSave = findViewById(R.id.btnSave);
        txtID = findViewById(R.id.txtID);
        txtNom = findViewById(R.id.txtNom);
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

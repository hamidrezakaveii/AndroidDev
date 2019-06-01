package com.example.formation.pojectlayout_sandwitch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCommander;
    private RadioGroup rbgChoixDeSandwich;
    private RadioButton rbnChoixDeSandwich;
    private CheckBox chkMayonnaise;
    private CheckBox chkKetchup;
    private String ChoixOptions;
    private String ChoixDeSandwich;



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

    private void setWidgets() {
        btnCommander = findViewById(R.id.btnCommander);
        rbgChoixDeSandwich = findViewById(R.id.rbgChoixDeSandwitch);
        chkMayonnaise = findViewById(R.id.chkMayounaise);
        chkKetchup = findViewById(R.id.chkKetchup);


    }

    private void setListener() {
        //2.Second way for asign the listener
//        btnCommander.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Sandwich", Toast.LENGTH_LONG).show();
//            }
//        });

        //3.Third way for assign the listener

        int idd = rbgChoixDeSandwich.getCheckedRadioButtonId();
        rbnChoixDeSandwich = findViewById(idd);
        if(chkMayonnaise.isSelected()){ ChoixOptions += chkMayonnaise.getText().toString();};
        if(chkKetchup.isSelected()){ ChoixOptions += chkKetchup.getText().toString();};
        ChoixDeSandwich = rbnChoixDeSandwich.getText().toString();
        btnCommander.setOnClickListener(this);

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

    //3.Third way for add the listener
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCommander){
            //Toast.makeText(MainActivity.this, "Sandwich", Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this, ChoixDeSandwich + " " + ChoixOptions  , Toast.LENGTH_LONG).show();
        }
    }

    //1.Frist way for add the listener
//    public void onCommander(View view) {
//
//        Toast.makeText(MainActivity.this, "Sandwich", Toast.LENGTH_LONG).show();
//    }
}

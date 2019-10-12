package com.example.gpsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnArreter, btnRechercher;
    private Intent monIntentService;
    private BroadcastReceiver intentReceiver;
    private IntentFilter filtreGPS;

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
        //preparer et creer le broadcast receiver
        intentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                double latitude = intent.getDoubleExtra("latitude", 0.0);
                double longitude = intent.getDoubleExtra("longitude", 0.0);
                Toast.makeText(MainActivity.this, "long:"+longitude+", lat:"+
                        latitude,Toast.LENGTH_SHORT).show();
            }
        };

        setWidgets();
        setListeners();
        //enregistrer le intentReceiver
        filtreGPS = new IntentFilter();
        filtreGPS.addAction("GPS_ACTION_CMV");
        registerReceiver(intentReceiver, filtreGPS);
    }

    private void setListeners() {
        btnArreter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monIntentService != null) {
                    stopService(monIntentService);
                }

            }
        });

        btnRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creer le service
                monIntentService = new Intent(MainActivity.this,
                        GPSService.class);
                //Demarrer Service
                startService(monIntentService);
            }
        });
    }

    private void setWidgets() {
        btnRechercher = findViewById(R.id.btnRechercher);
        btnArreter = findViewById(R.id.btnArreter);
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

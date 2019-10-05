package com.example.a2_localisationwithgps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private Button btnStart, btnStop;
    private EditText latitude, longitude;
    private Intent intentGPS;
    private IntentFilter intentFilter;
    private BroadcastReceiver receiver;
    //double latitudev, longitudev;

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


        //Set widgets
        setWidgets();
        setListener();

        intentFilter = new IntentFilter();
        intentFilter.addAction("GPS");


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                 double latitudev = intent.getDoubleExtra("latitude",0.0);
                 double longitudev = intent.getDoubleExtra("longitude",0.0);
                latitude.setText(String.valueOf(latitudev));
                longitude.setText(String.valueOf(longitudev));
            }
        };

        registerReceiver(receiver, intentFilter);









    }

    private void setListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentGPS = new Intent(MainActivity.this, ServiceGPS2.class);
                startService(intentGPS);

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intentGPS);
            }
        });
    }

    private void setWidgets() {
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        latitude = findViewById(R.id.edtLatitude);
        longitude = findViewById(R.id.edtLongitude);


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

    /*private class MyListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            //Obtenir latitude et longitude
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Toast.makeText(MainActivity.this, "Latitude: " + latitude +" Longitude: "+longitude,Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }*/
}

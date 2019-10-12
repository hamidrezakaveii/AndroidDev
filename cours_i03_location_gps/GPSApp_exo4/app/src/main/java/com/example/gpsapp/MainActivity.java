package com.example.gpsapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnDemarrer, btnArreter;
    private TextView txtLongitude, txtLatitude;
    private Intent monService;
    private BroadcastReceiver receiver;

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

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

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                double longitude = intent.getDoubleExtra("longitude", 0.0);
                double latitude = intent.getDoubleExtra("latitude", 0.0);
                txtLatitude.setText(String.valueOf(latitude));
                txtLongitude.setText(String.valueOf(longitude));
            }
        };
        setWidgets();
        setListeners();

        IntentFilter gpsFilter = new IntentFilter();
        gpsFilter.addAction("CMV_GPS");
        registerReceiver(receiver,gpsFilter);


    }

    private void setListeners() {
        btnDemarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monService = new Intent(MainActivity.this,
                        ServiceLocalisation.class);

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                        // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }

                startService(monService);
            }
        });

        btnArreter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monService != null)
                    stopService(monService);

            }
        });
    }

    private void setWidgets() {
        btnArreter = findViewById(R.id.btnArreter);
        btnDemarrer = findViewById(R.id.btnDemarrer);
        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
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

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        startService(new Intent(this, ServiceLocalisation.class));
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }


}

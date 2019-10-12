package com.example.gpsapp;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ServiceLocalisation extends Service {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    public ServiceLocalisation() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    String provider;
    LocationManager locationManager;
    LocationListener listener;

    @Override
    public void onCreate() {
        super.onCreate();
        //Location manager
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(serviceName);

        //Location Listener
        listener = new TotoListener();

        //demarrer l'ecoute des points Location
        provider = LocationManager.GPS_PROVIDER;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("hafed", "Demarrage de service");

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission. ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(provider,
                    5, 100, listener);
        }

//        locationManager.requestLocationUpdates(provider,
//                5, 100, listener);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v("hafed", "Arret de service");
        super.onDestroy();
    }

    private class TotoListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            //obtenir latitude et longitude
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Toast.makeText(getApplicationContext(), "Long:" + longitude +
                    "lat:" + latitude, Toast.LENGTH_SHORT).show();

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
    }
}

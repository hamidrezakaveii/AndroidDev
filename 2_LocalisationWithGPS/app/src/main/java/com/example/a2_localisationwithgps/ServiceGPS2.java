package com.example.a2_localisationwithgps;

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
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class ServiceGPS2 extends Service {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private LocationManager locationManager;
    private LocationListener listener;
    String provider;

    public ServiceGPS2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("GPS", "Create the service");

        // Location manager
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(serviceName);


        // Location listener
        listener = new ServiceGPS2.MyListener();

        //Démarrer l'ecoute des poines location
        provider = LocationManager.GPS_PROVIDER;
        if (ContextCompat.checkSelfPermission(ServiceGPS2.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(ServiceGPS.this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(ServiceGPS.this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//
//                // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
        }


    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("GPS", "Start the service");
        if (ContextCompat.checkSelfPermission(ServiceGPS2.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(ServiceGPS.this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(ServiceGPS.this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//
//                // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
        }
        locationManager.requestLocationUpdates(provider, 1000, 100, listener);
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        Log.v("GPS", "Stop the service");
        super.onDestroy();
    }




    private class MyListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Intent resultIntent = new Intent(ServiceGPS2.this, MainActivity.class);
            resultIntent.setAction("GPS");
            resultIntent.putExtra("latitude", latitude);
            resultIntent.putExtra("longitude", longitude);

            sendBroadcast(resultIntent);
            //Toast.makeText(MainActivity.this, "Latitude: " + latitude +" Longitude: "+longitude,Toast.LENGTH_SHORT).show();
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

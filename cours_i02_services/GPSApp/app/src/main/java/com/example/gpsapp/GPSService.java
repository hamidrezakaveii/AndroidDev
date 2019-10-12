package com.example.gpsapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class GPSService extends Service {
    public GPSService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("hafed", "Demarrage du GPS");
        Intent intentGps = new Intent();
        //Demarrer le GPS
        recherchePointGPS();
        //Definit Action à ecouter
        intentGps.setAction("GPS_ACTION_CMV");
        //mettre lat et long dans le intent
        for (int i=0; i<10; i++) {
            intentGps.putExtra("longitude", 43.0+.5*i);
            intentGps.putExtra("latitude", -73.0+.5*i);
            //faire le broadcast
            sendBroadcast(intentGps);
        }


        return super.onStartCommand(intent, flags, startId);
    }

    private void recherchePointGPS() {
        //simulation de GPS
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.v("hafed", "Arret du GPS");
        super.onDestroy();
    }
}

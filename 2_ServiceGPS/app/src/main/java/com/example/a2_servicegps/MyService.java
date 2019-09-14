package com.example.a2_servicegps;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    int latitude;
    int langitude;
    boolean flag = true;
    Intent desIntent;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("kevin","Service est demarre");
        langitude = 10;
        langitude = 50;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //int i = 0;
        while (flag){
            //latitude ++;
            //langitude ++;
            Log.v("kevin", "Latitude:" + latitude +" Longitude: "+langitude);
        }
        //Log.v("kevin", "Latitude: , Longitude: ");
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        flag = false;
        super.onDestroy();

       // Toast.makeText(this, "Arret", Toast.LENGTH_LONG).show();
        Log.v("kevin","Service est arreter");
    }
}

package com.example.gpsapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceLocalisation extends Service {
    public ServiceLocalisation() {
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
        Log.v("hafed", "Demarrage de service");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v("hafed", "Arret de service");
        super.onDestroy();
    }
}

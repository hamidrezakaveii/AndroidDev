package com.example.servicegps;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MonService extends Service {
    int latitude  , longitude  ;
    boolean flag = true;

    public MonService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        latitude = 30; longitude = 40;
        super.onCreate();
        Log.v("hafed", "Service est démarré!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        while (flag){
            Log.v("hafed",
                    "Latitude:"+latitude++ +", longitude:"+longitude++);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
        Log.v("hafed", "Service est arreté!");
    }
}

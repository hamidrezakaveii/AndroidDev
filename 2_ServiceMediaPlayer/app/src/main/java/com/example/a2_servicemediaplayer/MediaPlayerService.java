package com.example.a2_servicemediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MediaPlayerService extends Service {
    MediaPlayer mp;
    public MediaPlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mp = MediaPlayer.create(this, R.raw.braincandy);
        mp.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        Toast.makeText(this, "Start", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Stop", Toast.LENGTH_LONG).show();
        mp.stop();
        super.onDestroy();
    }
}

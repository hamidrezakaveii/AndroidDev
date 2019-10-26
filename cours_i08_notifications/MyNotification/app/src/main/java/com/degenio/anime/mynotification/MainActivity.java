package com.degenio.anime.mynotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final static int APP_ID = 100;
    int numberMessages = 0;
    Button btnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setWidgets();
        setListeners();
    }

    private void setListeners() {
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creerNotification();
            }
        });
    }

    private void setWidgets() {
        btnNotification = (Button) findViewById(R.id.btnNotification);
    }

    public void creerNotification() {
        // Objet notification
        Notification.Builder notification =
                new Notification.Builder(this)
                        .setSmallIcon(R.drawable.notif)
                        .setContentTitle(getString(R.string.titre_important))
                        .setContentText("Builder-Cliquer ici pour les détails de la notification")
                        .setTicker("Nouvelle notification-Urgent!")
                        .setChannelId("chano")
                        .setAutoCancel(true);

        // intent pour démarrer un activité
        Intent intent = new Intent(MainActivity.this,
                CibleActivity.class);
        PendingIntent pendingActivity = PendingIntent.getActivity(
                MainActivity.this, 0,
                intent, 0);
        // indiquer la destination
        notification.setContentIntent(pendingActivity);

        // lancer la notification
        NotificationManager nm =
                (NotificationManager) getSystemService(
                        NOTIFICATION_SERVICE);

        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new
                NotificationChannel("chano",
                "chano", importance);
        nm.createNotificationChannel(mChannel);

        nm.notify(APP_ID, notification.build());
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

package com.example.a2_notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnLancerNotif;
    private Button btnSendNotif;
    private static int App_ID = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidgets();
        setListener();
    }

    private void setListener() {
        btnLancerNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Lancer Notif" ,Toast.LENGTH_LONG).show();
                Context context = getApplicationContext();
                LayoutInflater inflater = getLayoutInflater();
                View toastRoot = inflater.inflate(R.layout.toast, null);

                Toast toast = new Toast(context);
                toast.setView(toastRoot);
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);

                //Modifier le text
                TextView tv = (TextView) toastRoot.findViewById(R.id.txtMessage);
                tv.setText("New text");
                toast.show();
            }
        });

        btnSendNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNotification();

            }
        });
    }


    private void createNotification() {
        // Objet notification
        Notification.Builder notification = new Notification.Builder(MainActivity.this)
                .setSmallIcon(R.drawable.notif)
                .setContentTitle("Detail de notification builder")
                .setContentText("Builder-Cliquer ici pour les detail de notification")
                .setTicker("Nouvelle notification urgent!")
                .setAutoCancel(true);

        // intent pour démarrer un activité
        Intent intent = new Intent(MainActivity.this, CibleActivity.class);
        PendingIntent pendingActivity = PendingIntent.getActivity(MainActivity.this, 0,
                intent, 0);
        // indiquer la destination
        notification.setContentIntent(pendingActivity);

        // lancer la notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //int importance = NotificationManager.IMPORTANCE_HIGH;
        //@RequiresApi(api = Build.VERSION_CODES.O)
        //NotificationChannel mChannel = new NotificationChannel("ch1","ch1",importance);
        nm.notify(App_ID, notification.build());
    }

    private void setWidgets() {
        btnLancerNotif = findViewById(R.id.btnLancerNotif);
        btnSendNotif = findViewById(R.id.btnSendNotif);
    }
}

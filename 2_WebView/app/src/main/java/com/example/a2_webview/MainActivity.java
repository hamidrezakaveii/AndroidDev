package com.example.a2_webview;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnChargerImage;
    private TextView txtHyperLink;
    private int CODE_APPEL = 5444;

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

        //1.Intégrer WebView dans une activitéIntégrer WebView dans une activité
        //WebView fureteur = (WebView) findViewById(R.id.web_engine);
        //fureteur.loadUrl("http://www.yahoo.com");

        //2.Intégrer WebView dans une activité avec du HTML
        /*WebView fureteur = (WebView) findViewById(R.id.web_engine);
        String data = "<html>" + "<body><h1>Bienvenue Monde !</h1></body>"
                + "</html>";
        fureteur.loadData(data, "text/html", "UTF-8");*/

        //3.Chargement d’images dans un WebView et utilisation des préférences de WebView
        btnChargerImage = findViewById(R.id.btnChargerImage);
        btnChargerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(imageIntent, "Selectionner image"), CODE_APPEL);


                    }
        });


        //4.Utilisation de TextView avec WebView pour ajouter un support pour des hyperliens
        txtHyperLink = findViewById(R.id.txtHyperLink);
        txtHyperLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView fureteur = (WebView) findViewById(R.id.web_engine);
                String data = "<html>" + "<body><h1>Bienvenue Monde !</h1></body>"
                        + "</html>";
                fureteur.loadData(data, "text/html", "UTF-8");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        WebView fureteur = (WebView) findViewById(R.id.web_engine);
        if (resultCode == RESULT_OK) {
            if (requestCode == CODE_APPEL) {
                Uri imageUri = data.getData();
                String imagePath = "";
                String[] imgData = { MediaStore.Images.Media.DATA };
                Cursor imgCursor = managedQuery(imageUri, imgData, null, null, null);
                if(imgCursor!=null) {
                    int index = imgCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    imgCursor.moveToFirst();
                    imagePath = imgCursor.getString(index);
                }
                else{
                    imagePath = imageUri.getPath();
                }
                fureteur.loadUrl("file:///"+imagePath);

            }
        }
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

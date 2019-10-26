package com.degenio.anime.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnCharger;
    private WebView fureteur;

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
//        WebView fureteur = (WebView) findViewById(R.id.web_engine);
//        fureteur.loadUrl("http://degenio.com");

//        WebView fureteur = (WebView) findViewById(R.id.web_engine);
//        String data = "<html>" + "<body><h1>Bienvenue Monde !</h1></body>"
//                + "</html>";
//        fureteur.loadData(data, "text/html", "UTF-8");


    }

    private void setListeners() {
        btnCharger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "<html>" + "<body><h3>" +
                        "Pour plus de détails sur notre produit, " +
                        "visitez notre site sur <a href=\"http://www.degenio.com\">degenio</a>, " +
                        "merci de votre interet.</h3></body>"
                        + "</html>";
                fureteur.loadData(data, "text/html", "UTF-8");
            }
        });
    }

    private void setWidgets() {
        btnCharger = (Button) findViewById(R.id.btnCharger);
        fureteur = (WebView) findViewById(R.id.web_engine);
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

package com.degenio.anime.manip11_1asynctask;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    Hashtable<Integer, Conseil> mConseils;

    // Server URLs
    public static final String SERVER_BASE = "https://www.degenio.com/html5/";
    public static final String SERVER_CONSEILS = SERVER_BASE + "msg.xml";

    // Tag names pour les conseils dans le fichier msg.xml
    public static final String XML_TAG_CONSEIL_BLOCK = "conseils";
    public static final String XML_TAG_CONSEIL = "conseil";
    public static final String XML_TAG_CONSEIL_ATTRIBUTE_NUMBER = "number";
    public static final String XML_TAG_CONSEIL_ATTRIBUTE_TEXT = "text";

    public static final String DEBUG_TAG = "hafed";

    TextView mText;
    ConseilTask downloader;
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
        mText = (TextView) findViewById(R.id.txtQuestions);
        // Télécharger les conseils du site en tâche de fond

        int startPosition = 1;
        mConseils = new Hashtable<Integer, MainActivity.Conseil>();
        downloader = new ConseilTask();
        downloader.execute(SERVER_CONSEILS, startPosition);
    }

    private String getConseilText(Integer numConseil) {
        String text = null;
        Conseil conseilCourant = (Conseil) mConseils.get(numConseil);
        if (conseilCourant != null) {
            text = conseilCourant.mText;

        }
        return text;
    }

    public void afficheConseils() {
        // afficher les conseils téléchargés
        for (int i = 1; i < mConseils.size(); i++) {
            mText.append(i + "-" + getConseilText(i) + "\n");
        }
    }

    // représentation d'un conseil
    private class Conseil {
        @SuppressWarnings("unused")
        int mNumber;
        String mText;

        public Conseil(int conseilNum, String conseilText) {
            mNumber = conseilNum;
            mText = conseilText;
        }
    }

    ProgressDialog pleaseWaitDialog;

    private class ConseilTask extends AsyncTask<Object, String, Boolean> {

        @Override
        protected Boolean doInBackground(Object... params) {
            boolean result = false;
            try {
                // must put parameters in correct order and correct type,
                // otherwise a ClassCastException will be thrown
                int startingNumber = (Integer) params[1];
                String pathConseilUrl = params[0] + "";

                result = chargerBatchConseils(startingNumber, pathConseilUrl);
            } catch (Exception e) {
                Log.v(DEBUG_TAG, "Erreur XML telechargement et traitement", e);
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!isCancelled()) {
                if (result) {
                    afficheConseils();
                } else {
                    Toast.makeText(getApplicationContext(), "Pas de conseil",
                            Toast.LENGTH_LONG).show();
                }
                pleaseWaitDialog.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pleaseWaitDialog = ProgressDialog.show(MainActivity.this,
                    "Conseils", "Téléchargement données...", true, true);
            pleaseWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    ConseilTask.this.cancel(true);
                }
            });
        }

        @Override
        protected void onCancelled() {
            pleaseWaitDialog.dismiss();
        }

        // chargement des conseils après lecture XML
        private boolean chargerBatchConseils(int startQuestionNumber,
                                             String xmlSource) {
            boolean result = false;
            // vider le Hashmap
            mConseils.clear();

            // Contacter le server pour un batch de conseil data,
            XmlPullParser conseilBatch;
            try {
                URL xmlUrl = new URL(xmlSource);
                conseilBatch = XmlPullParserFactory.newInstance()
                        .newPullParser();
                conseilBatch.setInput(xmlUrl.openStream(), null);
            } catch (XmlPullParserException e1) {
                conseilBatch = null;
                Log.v("hafed", "Failed to initialize pull parser", e1);
            } catch (IOException e) {
                conseilBatch = null;
                Log.v("hafed", "IO Failure during pull parser initialization",
                        e);
            }

            // Parse le XML
            if (conseilBatch != null) {
                try {
                    parseXMLConseilBatch(conseilBatch);
                    result = true;
                } catch (XmlPullParserException e) {
                    Log.v(DEBUG_TAG, "Pull Parser failure", e);
                } catch (IOException e) {
                    Log.v(DEBUG_TAG, "IO Exception parsing XML", e);
                }
            }

            return result;

        }

        private void parseXMLConseilBatch(XmlPullParser questionBatch)
                throws XmlPullParserException, IOException {
            int eventType = -1;

            // conseils XML
            while (eventType != XmlResourceParser.END_DOCUMENT
                    && !isCancelled()) {
                if (eventType == XmlResourceParser.START_TAG) {

                    // Get nom tag (conseils ou conseil)
                    String strName = questionBatch.getName();
                    Log.v(DEBUG_TAG, strName);
                    if (strName.equals(XML_TAG_CONSEIL)) {

                        String questionNumber = questionBatch
                                .getAttributeValue(null,
                                        XML_TAG_CONSEIL_ATTRIBUTE_NUMBER);
                        Integer conseilNum = new Integer(questionNumber);
                        String conseilText = questionBatch.getAttributeValue(
                                null, XML_TAG_CONSEIL_ATTRIBUTE_TEXT);

                        // Save data dans hashtable
                        mConseils.put(conseilNum, new Conseil(conseilNum,
                                conseilText));
                    }
                }
                eventType = questionBatch.next();
            }
        }

    } // fin de classe ConseilTask


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

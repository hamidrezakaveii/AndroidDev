package com.example.formation.ecrituredefichiertexte;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    private Button btnSauvgarder;
    private EditText txtSaisir;
    private EditText txtLire;
    private Button btnLire;


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
        setListener();

    }

    private void setListener() {
        btnSauvgarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        btnLire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });
    }

    private void readData() {
        FileInputStream fis = null;

        try{
            fis = openFileInput("fichier711.txt");
            byte [] readData = new byte[255];
            try{
                fis.read(readData);
                txtLire.setText(new String(readData));
                Toast.makeText(MainActivity.this, "Lire!", Toast.LENGTH_SHORT);
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (fis != null){
                try{
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readRawData() {
        InputStream stream = getResources().openRawResource(R.raw.aide);
        String data = lireStream(stream);
        txtLire.setText(data);

    }

    private String lireStream(InputStream stream) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder ligneBuffer = new StringBuilder();
        String ligne;

        try{
            isr = new InputStreamReader(stream, "UTF-8");
            br = new BufferedReader(isr);

            while( (ligne = br.readLine()) != null){
                ligneBuffer.append(ligne);
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ligneBuffer.toString();
    }

    private void saveData() {
        //File txtSaisir et mettre dans fichier
        //File fichier = new File("fichier711.txt")
        FileOutputStream fos = null;

        try{
            fos = openFileOutput("fichier711.txt", MODE_APPEND);
            byte [] byteSaisir = txtSaisir.getText().toString().getBytes();
            byte [] nextLine = "\n".getBytes();
            fos.write(byteSaisir);
            fos.write(nextLine);
            Toast.makeText(MainActivity.this, "Sauvgarder!", Toast.LENGTH_SHORT);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void setWidgets() {
        btnSauvgarder = findViewById(R.id.btnSauvgarder);
        txtSaisir = findViewById(R.id.txtSaisir);
        txtLire = findViewById(R.id.txtLire);
        btnLire = findViewById(R.id.btnLire);
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
         if(id == R.id.actionSave){
            saveData();
        }else if(id == R.id.actionRead){
            readData();
        }else if(id == R.id.actionPolicy){
            readRawData();
    }

        return super.onOptionsItemSelected(item);
    }


}

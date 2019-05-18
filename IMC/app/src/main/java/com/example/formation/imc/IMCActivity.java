package com.example.formation.imc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class IMCActivity extends AppCompatActivity {
    //array pour risque et classification
    String[] aRisque;
    String[] aClassification;
    int borne = 5;
    private RadioGroup brnTypeMusure;
    private TextView txtResultatIMC, txtResultatClasse, txtResultatRisque;
    private Button btnEffacer, btnCalculer;
    private RadioButton brnMetrique, brnImperial;
    private EditText txtPoids, txtTaille;
    private int radioCheckedId = -1;
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnCalculer) {
                calculateIMC();
            } else {
                reset();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
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

        aRisque = getResources().getStringArray(R.array.risque);
        aClassification = getResources().getStringArray(R.array.classification);

        setWidgets();
        setListener();

    }

    private void setListener() {
        btnCalculer.setOnClickListener(mClickListener);
        btnEffacer.setOnClickListener(mClickListener);

    }

    ;

    private void reset() {
        txtPoids.setText("");
        txtTaille.setText("");
        txtResultatClasse.setText("");
        txtResultatIMC.setText("");
        txtResultatRisque.setText("");
        brnTypeMusure.clearCheck();
        brnTypeMusure.check(R.id.brnMetrique);
        txtPoids.requestFocus();
    }

    private void calculateIMC() {
        Double poids = Double.parseDouble(txtPoids.getText().toString());
        Double taille = Double.parseDouble(txtTaille.getText().toString());

        boolean isError = false;
        //verifier les valeurs negatives


        if (taille <= 0.0) {
            ShowAlertError(R.string.altTailleMouvais, txtTaille.getId());
            isError = true;
        }
        if (poids <= 0.0) {
            ShowAlertError(R.string.altPoidsMouvais, txtPoids.getId());
            isError = true;
        }


        //verifier biuton radio pour la musure
        if (radioCheckedId == -1) {
            radioCheckedId = brnTypeMusure.getCheckedRadioButtonId();
            switch (radioCheckedId) {
                case R.id.brnMetrique:
                    break;
                case R.id.brnImperial:
                    break;
            }

            Double imc;
            if (!isError) {
                imc = poids / (taille * taille);

                //obtenir index pour des tableax risque et classification
                int borne = getInterval(imc);
                //apple de l'affichage
                setDisplay(borne, imc);
            }
        }
    }

    private void ShowAlertError(int megId, final int fieldId) {

        new AlertDialog.Builder(this)
                .setTitle(R.string.altTitreErrour)
                .setMessage(megId)
                .setNeutralButton("close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }

    private void setDisplay(int borne, Double imc) {
        txtResultatIMC.setText(imc.toString());
        txtResultatClasse.setText(aClassification[borne]);
        txtResultatRisque.setText(aRisque[borne]);
    }

    private int getInterval(Double imc) {

        int borne = 5;
        if(imc< 18.5)
            borne =0;
        else if(imc < 24.9)
            borne =1;
        else if(imc< 29.9)
            borne =2;
        else if(imc< 34.9)
            borne =3;
        else if(imc< 39.9)
            borne =4;
        else if(imc >= 40.0)
            borne =5;

        return borne;
    }




    private void setWidgets() {
        brnTypeMusure = findViewById(R.id.brnTypeMusure);
        txtResultatIMC = findViewById(R.id.txtResultatIMC);
        txtResultatClasse = findViewById(R.id.txtResultatClasse);
        txtResultatRisque = findViewById(R.id.txtResultatRisque);
        btnEffacer = findViewById(R.id.btnEffacer);
        btnCalculer = findViewById(R.id.btnCalculer);
        brnMetrique = findViewById(R.id.brnMetrique);
        brnImperial = findViewById(R.id.brnImperial);
        txtPoids = findViewById(R.id.txtPoids);
        txtTaille = findViewById(R.id.txtTaille);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_imc, menu);
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

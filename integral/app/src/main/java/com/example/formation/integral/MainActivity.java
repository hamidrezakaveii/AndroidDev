package com.example.formation.integral;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import model.Item;
import model.RegistreItem;
import persistence.DatabaseAdapter;

public class MainActivity extends AppCompatActivity {
    private LinearLayout llItem;
    private int counter = 0;
    private RegistreItem registre;
    private DatabaseAdapter adapter;

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

        llItem = findViewById(R.id.llItem);
        registre = new RegistreItem(MainActivity.this);

        adapter = new DatabaseAdapter(this);
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
            if((id == R.id.ajout_item)){
                openDialogItem();
//            TextView tv = new TextView(MainActivity.this);
//            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
//            setBorderView(tv);
//            counter++;
//
//            tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ((TextView)v).setPaintFlags( ((TextView)v).getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                }
//            });
//
//            tv.setText(String.valueOf(counter));
//            llItem.addView(tv);
        }else if (id == R.id.affiche_list){
                registre.afficherItem();
            }else if((id == R.id.save_liste)){

                sauvgarderListe();

            }

        return super.onOptionsItemSelected(item);
    }

    private void sauvgarderListe() {
        String nomListe = "Liste_Episrie";
        adapter.openDatabase();
        adapter.insertData(registre, nomListe);

    }

    private void openDialogItem() {
        final int[] couleur = new int[1];
        LayoutInflater inflator = LayoutInflater.from(MainActivity.this);
        View monDialouges = inflator.inflate(R.layout.daialog_produit2, null);

        final EditText txtItem = monDialouges.findViewById(R.id.txtItem);
        ImageView img1 = monDialouges.findViewById(R.id.img1);
        ImageView img2 = monDialouges.findViewById(R.id.img2);
        ImageView img3 = monDialouges.findViewById(R.id.img3);

        //Gere le lien avec le couleur bleu
        Drawable imgB = getResources().getDrawable(R.drawable.blue);
        img1.setImageDrawable(imgB);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couleur[0] = 0XFF0000FF;
            }
        });


        //Gere le lien avec le couleur rouge
        Drawable imgR = getResources().getDrawable(R.drawable.red);
        img2.setImageDrawable(imgR);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couleur[0] = 0XFFFF0000;
            }
        });


        //Gere le lien avec le couleur vert
        Drawable imgV = getResources().getDrawable(R.drawable.green);
        img3.setImageDrawable(imgV);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couleur[0] = 0XFF00FF00;
            }
        });

        //construire alert
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choix_produit)
        .setMessage(R.string.saisir_choix_produit)
        .setView(monDialouges)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setViewItem(txtItem.getText().toString(), couleur);
                        Log.v("hami", String.valueOf(couleur));
                    }


                })

                .setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, getString(R.string.Annulation), Toast.LENGTH_LONG).show();
                    }
                }).create();

        builder.show();

    }

    private void setViewItem(String s, int[] couleur) {

        TextView tv = new TextView(MainActivity.this);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        setBorderView(tv, couleur);

           tv.setOnClickListener(new View.OnClickListener() {
               @Override
              public void onClick(View v) {
                   ((TextView)v).setPaintFlags( ((TextView)v).getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);
                }
            });



            tv.setText(s);
            llItem.addView(tv);

            //add to liste des registre
            Item item = new Item(s, couleur[0]);
            registre.ajouterItem(item);
    }



    private void setBorderView(TextView tv, int[]couleur) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(couleur[0]);
        gd.setCornerRadius(5);
        gd.setStroke(1, couleur[0]);
        tv.setBackground(gd);

        //setter layout params
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(5,5,5, 5);
        tv.setLayoutParams(llp);

    }
}

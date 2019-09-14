package com.degenio.integra1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

import dao.DatabaseAdapter;
import modele.Item;
import modele.ListeItem;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private LinearLayout linear = null;
    private HashMap<Integer, FlexboxLayout> panelItems;
    private ListeItem registre;
    private DatabaseAdapter adapter;
    String nom_liste;

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
        //creer la liste
        registre = new ListeItem(this);
        //Handle pour DatabaseAdapter
        adapter = new DatabaseAdapter(this);
        //creer le LinearLayout global
        panelItems = new HashMap<>();
    }

    private void setListeners() {


    }

    private void openDialogListe() {

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View subView = inflater.inflate(R.layout.dialog_nom_liste, null);
        final EditText prdText = (EditText) subView.findViewById(R.id.dialogEditText);


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Indiquer le nom de la liste");
        builder.setMessage("Liste à sauvegarder");
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nom_liste = prdText.getText().toString();
                adapter.openDatabase();
                adapter.insertData(registre, nom_liste);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
    }


    private void setWidgets() {
        linear = findViewById(R.id.llProduit);
    }

    private void setViewProduit(String texte, int[] couleur) {
        // counter++;
        TextView tv = new TextView(MainActivity.this);
        //ajouter dans la liste
        Item item = new Item(texte, couleur[0]);
        registre.ajouterItem(item);
        setBorderView(tv, couleur);
        tv.setText(texte);
        //check color pour linear layout
        addlayoutPanel(couleur[0], tv);


        // linear.addView(tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) v).setPaintFlags(((TextView) v).getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);
                //enlever de la liste lorsque barré
                registre.enleverItem((TextView) v);
            }
        });
    }

    private void addlayoutPanel(int couleur, TextView tv) {
        int[] couleurs = {getResources().getColor(R.color.LightBlue), getResources().getColor(R.color.LightGreen),
                getResources().getColor(R.color.PaleVioletRed),0};
        //utilisation de FlexboxLayout


        for (int i = 0; i < couleurs.length; i++) {

            if (couleur == couleurs[i]) {
                //check si le layout a deja été créé
                if (panelItems.containsKey(couleurs[i])) {
                    panelItems.get(couleurs[i]).addView(tv);
                } else {//creer le panel
                    FlexboxLayout ll = new FlexboxLayout(MainActivity.this);
                    ll.setFlexDirection(FlexDirection.ROW);
                    ll.setFlexWrap(FlexWrap.WRAP);
                    panelItems.put(couleurs[i], ll);
                    linear.addView(ll);
                    ll.addView(tv);
                    View view = ll.getChildAt(0);
                    FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
                    lp.setOrder(-1);
                   // lp.setFlexGrow(2);
                    view.setLayoutParams(lp);
                }
            }

        }
    }

    private void setBorderView(TextView tv, int[] couleur) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(couleur[0]);
        gd.setCornerRadius(15);
        gd.setStroke(1, couleur[0]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            tv.setBackground(gd);
        }
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(15, 15, 15, 15);
        tv.setPadding(15, 15, 15, 15);
        tv.setLayoutParams(llp);
    }

    private void openDialogAddItem() {
        final int[] couleur = new int[1];
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View subView = inflater.inflate(R.layout.dialog_produit, null);
        final EditText prdText = subView.findViewById(R.id.dialogEditText);
        final ImageView subImg1 = subView.findViewById(R.id.img1);
        final ImageView subImg2 = subView.findViewById(R.id.img2);
        final ImageView subImg3 = subView.findViewById(R.id.img3);
        Drawable imgb = getResources().getDrawable(R.drawable.blue);
        subImg1.setImageDrawable(imgb);
        subImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couleur[0] = getResources().getColor(R.color.LightBlue);
            }
        });
        Drawable imgr = getResources().getDrawable(R.drawable.red);
        subImg2.setImageDrawable(imgr);
        subImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couleur[0] = getResources().getColor(R.color.PaleVioletRed);
            }
        });
        Drawable imgg = getResources().getDrawable(R.drawable.green);
        subImg3.setImageDrawable(imgg);
        subImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couleur[0] = getResources().getColor(R.color.LightGreen);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choix de produit");
        builder.setMessage("Sélectionner une catégorie de produit");
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setViewProduit(prdText.getText().toString(), couleur);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
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
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (id) {
            case R.id.ajout_item:
                openDialogAddItem();
                break;

            case R.id.save_liste:
                openDialogListe();
                break;

            case R.id.clear_liste:
                break;

            case R.id.delete_liste:
                deleteListe();
                break;

            case R.id.share_liste:
                shareListe();//reminder pour le moment
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void shareListe() {


            if (Build.VERSION.SDK_INT >= 14)
                startActivity(new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("titre", 2131492910).putExtra("description", "Group class").putExtra("eventLocation", "Epicerie").putExtra("Disponibilite", 0));

    }

    private void deleteListe() {
        new MaterialDialog.Builder(this).content("Suppression de la liste")
                .positiveText("Accepter")
                .negativeText("Annuler")
                .positiveColorRes(R.color.colorPrimary)
                .neutralColorRes(R.color.colorPrimary)
                .negativeColorRes(R.color.colorPrimary)
                .callback(new MaterialDialog.ButtonCallback() {
                    public void onPositive(MaterialDialog paramAnonymousMaterialDialog) {
//                        ItemController.Instance(itemlist.this.self).DeleteList(itemlist.this.listId);
//                        itemlist.this.mNavigationDrawerFragment.listRemoved();
                        Toast.makeText(MainActivity.this, "Suppression de liste", Toast.LENGTH_LONG).show();
                    }
                })
                .show();


    }
}

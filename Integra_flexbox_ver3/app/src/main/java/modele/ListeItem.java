package modele;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hafv72012 on 2016-10-21.
 */
public class ListeItem extends ArrayList<Item> {
private Context context;
    private String nom_liste;
    public ListeItem(Context context) {
        this.context = context;
    }

    public void ajouterItem(Item item){
        this.add(item);
    }

    public void enleverItem(TextView tv){
        byte index =-1,i=0;
        for (Item item : this){
            if (item.getDescription().equals(tv.getText().toString())){
                index = i;
                break;
            }
            i++;
        }
        Toast.makeText(context,"index:"+index, Toast.LENGTH_LONG).show();
        if (index !=-1)
        this.remove(index);
    }

    public void listeItems(){
        for (Item item : this){
            Toast.makeText(context, item.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

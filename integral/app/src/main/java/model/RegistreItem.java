package model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistreItem extends ArrayList<Item> {

    private Context context;

    public RegistreItem(Context context){
        this.context = context;
    }

    public void ajouterItem(Item item){
        this.add(item);
    }

    public void afficherItem(){
        for(Item item : this){
            Toast.makeText(context, item.toString(), Toast.LENGTH_LONG).show();
        }
    }

}

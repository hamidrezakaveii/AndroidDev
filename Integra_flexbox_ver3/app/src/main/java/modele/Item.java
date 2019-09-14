package modele;

/**
 * Created by hafv72012 on 2016-10-21.
 */
public class Item {
    private String description;
    private int couleur;

    public Item(String description) {
        this.description = description;
    }

    public Item() {
    }

    public Item(String description, int couleur) {
        this.description = description;
        this.couleur = couleur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", couleur=" + couleur +
                '}';
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }
}

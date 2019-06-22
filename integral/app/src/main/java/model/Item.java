package model;

public class Item {
    private String description;
    private int couleur;

    public Item() {
    }

    public Item(String description, int couleur) {
        this.description = description;
        this.couleur = couleur;
    }

    public String getDescription() {
        return description;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", couleur=" + couleur +
                '}';
    }
}

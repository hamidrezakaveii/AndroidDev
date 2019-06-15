package model;

public class Personne {
    private int _id;
    private String nom;

    public int get_id() {
        return _id;
    }

    public String getNom() {
        return nom;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Personne(int _id, String nom) {
        this._id = _id;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "_id=" + _id +
                ", nom='" + nom + '\'' +
                '}';
    }
}

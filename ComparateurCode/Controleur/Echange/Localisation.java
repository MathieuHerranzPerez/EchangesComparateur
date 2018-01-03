package ComparateurCode.Controleur.Echange;

public class Localisation {

    private String nom;
    private Pays pays;

    public Localisation(String nom, Pays pays) {
        this.nom = nom;
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    public Pays getPays() {
        return pays;
    }
}

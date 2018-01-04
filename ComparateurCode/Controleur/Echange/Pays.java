package ComparateurCode.Controleur.Echange;

public class Pays {

    private String nom;
    private Continent continent;

    public Pays() {
        this.nom = "";
        this.continent = null;
    }

    public Pays(String nom) {
        this.nom = nom;
        this.continent = null;
    }

    public Pays(String nom, Continent continent) {
        this.nom = nom;
        this.continent = continent;
    }

    public String getNom() {
        return nom;
    }

    public Continent getContinent() {
        return continent;
    }
}

package ComparateurCode.Controleur;

public class Pays {

    private String nom;
    private Continent continent;


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

package ComparateurCode.Modele;

public class PaysM {

    private String nom;
    private Continent continent;


    public PaysM(String nom, Continent continent) {
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

package ComparateurCode.Controleur.Echange;

public class SousDomaine {

    private String nom;
    private Domaine domaine;

    public SousDomaine() {
        this.nom = "";
        this.domaine = new Domaine();
    }

    public SousDomaine(Domaine domaine) {
        this.nom = "";
        this.domaine = domaine;
    }

    public SousDomaine(String nom, Domaine domaine) {
        this.nom = nom;
        this.domaine = domaine;
    }

    public String getNom() {
        return nom;
    }

    public Domaine getDomaine() {
        return domaine;
    }
}

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

    @Override
    public String toString() {
        return "SousDomaine{" +
                "nom='" + nom + '\'' +
                ", domaine=" + domaine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof SousDomaine)) return false;

        SousDomaine sousDom = (SousDomaine) o;
        return (this.nom.equals(sousDom.getNom()) && this.domaine.equals(sousDom.getDomaine()));
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (domaine != null ? domaine.hashCode() : 0);
        return result;
    }
}

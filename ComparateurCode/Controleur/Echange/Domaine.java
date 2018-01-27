package ComparateurCode.Controleur.Echange;

public class Domaine {

    private String nom;

    public Domaine() {
        this.nom = "";
    }

    public Domaine(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Domaine{" +
                "nom='" + nom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Domaine)) return false;
        Domaine domaine = (Domaine) o;
        return this.nom.equals((domaine).getNom());
    }

    @Override
    public int hashCode() {
        return nom != null ? nom.hashCode() : 0;
    }
}

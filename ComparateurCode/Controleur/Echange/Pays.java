package ComparateurCode.Controleur.Echange;

public class Pays {

    private String nom;

    public Pays() {
        this.nom = "";
    }

    public Pays(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "nom='" + nom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Pays)) return false;
        Pays pays = (Pays) o;
        return this.nom.equals((pays).getNom());
    }

    @Override
    public int hashCode() {
        return nom != null ? nom.hashCode() : 0;
    }
}

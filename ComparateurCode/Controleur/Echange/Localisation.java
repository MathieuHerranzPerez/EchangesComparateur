package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.LocalisationM;
import ComparateurCode.Vue.FenetreErreur;

import java.util.ArrayList;

public class Localisation {

    private static ArrayList<Localisation> listLocalisation = new ArrayList<>();

    private String nom;
    private Pays pays;

    public Localisation() {
        this.nom = "";
        this.pays = new Pays();
    }

    public Localisation(Pays pays) {
        this.nom = "";
        this.pays = pays;
    }

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

    @Override
    public String toString() {
        return "Localisation{" +
                "nom='" + nom + '\'' +
                ", pays=" + pays +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Localisation)) return false;

        Localisation loc = (Localisation) o;

        return this.nom.equals(loc.getNom()) && this.pays.equals(loc.pays);
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (pays != null ? pays.hashCode() : 0);
        return result;
    }

    public static ArrayList<Localisation> getListLocalisation() {
        // Si la liste est vide, on la remplie
        if(listLocalisation.isEmpty()) {
            listLocalisation = LocalisationM.getLocalisations();
        }
        return listLocalisation;
    }

    public static void ajouterLocalisation(String nom, Pays pays) {
        // vérifier si la localisation n'est pas déjà en BD
        if(LocalisationM.getLocalisation(nom, pays) != null) {
            FenetreErreur f = new FenetreErreur("Localisation déjà en BD");
        }
        else {
            LocalisationM.ajouterLocalisation(nom, pays);
        }
    }
}

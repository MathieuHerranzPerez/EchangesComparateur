package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.PaysM;

import java.util.ArrayList;

/**
 * Représente un pays, au sens minimaliste du terme, avec un nom
 */
public class Pays {

    /**
     * Contient la liste de tous les pays en Base de données
     */
    private static ArrayList<Pays> listPays = new ArrayList<>();

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
        return nom;
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

    /**
     * Si la liste de Pays est vide, on l'a remplie avec la BD
     * @return ArrayList, la liste de Pays
     */
    public static ArrayList<Pays> getPays() {
        if(listPays.isEmpty()) {
            mettreAJourListe();
        }
        return listPays;
    }

    /**
     * Met à jour la liste des Pays graçe aux pays présents en BD
     */
    public static void mettreAJourListe() {
        listPays = PaysM.getPaysFromId();
    }

    /**
     * Renvoie les pays utilisés dans l'application
     * @return ArrayList, la liste des pays utilisés
     */
    public static ArrayList<Pays> getPaysUtilises() {
        return PaysM.getPaysUtilises();
    }

    /**
     * Renvoie les pays d'écoles de départ présent en BD
     * @return ArrayList, la liste des pays utilisés
     */
    public static ArrayList<Pays> getPaysDepart() {
        return PaysM.getPaysDepart();
    }
}

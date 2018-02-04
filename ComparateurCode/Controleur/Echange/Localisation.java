package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.LocalisationM;
import ComparateurCode.Vue.FenetreErreur;

import java.text.Normalizer;
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
        return nom;
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

    /**
     * Renvoie, et met à jour si besoin, la liste de toutes les localisations présentes en BD
     * @return ArrayList<Localisation>, les localisations
     */
    public static ArrayList<Localisation> getListLocalisation() {
        // Si la liste est vide, on la remplie
        if(listLocalisation.isEmpty()) {
            mettreAJourListe();
        }
        return listLocalisation;
    }

    /**
     * Ajoute la localisation désignée par le nom et le pays en BD, si elle n'est pas présente
     * Met à jour la liste des localisations
     * @param nom String, le nom de la localisation à ajouter
     * @param pays Pays, le pays de la localisation
     */
    public static void ajouterLocalisation(String nom, Pays pays) {
        Localisation newLoc = new Localisation(nom, pays);
        // vérifier si la localisation n'est pas déjà en BD
        if(LocalisationM.isLocalisationInBD(newLoc)) {
        }
        else {
            LocalisationM.ajouterLocalisation(newLoc);
            mettreAJourListe();
        }
    }

    // compare 2 noms de localisations en s'appuyant sur le théorème de la distance de Levenshtein
    // = nombre d'opérations (subsitution, suppression, ajout) nécessaire pour passer de locA à locB
    public static int distance(String locA, String locB) {
        // enleve les majucules
        locA = locA.toLowerCase();
        locB = locB.toLowerCase();

        // supprime les accents
        locA = Normalizer.normalize(locA, Normalizer.Form.NFD);
        locA = locA.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        locB = Normalizer.normalize(locB, Normalizer.Form.NFD);
        locB = locB.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // i == 0
        int [] costs = new int [locB.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= locA.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= locB.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), locA.charAt(i - 1) == locB.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[locB.length()];
    }


    /**
     * Met à jour la liste des localisation graçce aux éléments en BD
     */
    public static void mettreAJourListe() {
        listLocalisation = LocalisationM.getLocalisations();
    }
}

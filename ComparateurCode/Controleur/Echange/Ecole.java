package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.EcoleM;
import ComparateurCode.Vue.FenetreErreur;
import ComparateurCode.Vue.FenetreParcourirEcole;

import java.util.ArrayList;

/**
 * Une école a un nom et une Localisation
 */
public class Ecole {

    private static ArrayList<Ecole> listEcole = new ArrayList<>();

    private String nom;
    private Localisation localisation;

    public Ecole() {
        this.nom = "";
        this.localisation = new Localisation();
    }

    public Ecole(Localisation localisation) {
        this.nom = "";
        this.localisation = localisation;
    }

    public Ecole(String nom, Localisation localisation) {
        this.nom = nom;
        this.localisation = localisation;
    }

    public String getNom() {
        return nom;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Ecole))
            return false;

        Ecole ecole = (Ecole) o;

        return this.nom.equals(ecole.getNom()) && this.localisation.equals(ecole.getLocalisation());
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (localisation != null ? localisation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return nom;
    }

    public static ArrayList<Ecole> getListEcole() {
        if(listEcole.isEmpty()) {
            mettreAJourListe();
        }
        return listEcole;
    }

    /**
     * Ajoute l'école correspondante aux attributs donnés en parametres si elle n'est pas déjà présente en BD
     * Met à jour la liste des écoles avec cet ajout
     * @param nom String, le nom de l'école
     * @param loc String, le lieu où se trouve l'école
     * @param pays Pays, la pays où se trouve le lieu
     */
    public static void ajouterEcole(String nom, String loc, Pays pays) {
        Ecole newEcole = new Ecole(nom, new Localisation(loc, pays));
        // On vérifie qu'elle n'est pas déjà dans la BD
        if(EcoleM.isEcoleInBD(newEcole)) {
            FenetreErreur f = new FenetreErreur("Ecole déjà en BD");
        }
        // Si elle n'y est pas, on l'ajoute
        else {
            EcoleM.ajouterEcole(newEcole);
            mettreAJourListe();
        }
    }

    /**
     * Remplace oldEcole par l'école correpondante aux attributs passés en parametres
     * Ne fait rien si l'ancienne école et la nouvelle sont indentiques, et si la nouvelle existe déjà en BD
     * Met à jour la liste des écoles avec ce changement
     * @param oldEcole Ecole, l'école à modifier
     * @param nom String, le nouveau nom de l'école
     * @param localisation String, la nouvelle localisation de l'école
     * @param pays Pays, le Pays de la localisation
     */
    public static void modifierEcole(Ecole oldEcole, String nom, String localisation, Pays pays) {
        // On vérifie qu'elle change
        if(oldEcole.getNom().equals(nom) && oldEcole.getLocalisation().toString().equals(localisation)
                && oldEcole.getLocalisation().getPays().equals(pays)) {
            FenetreErreur f = new FenetreErreur("Ecole inchangée");
        }
        else {
            // On vérifie que la nouvelle n'est pas dans la BD
            Ecole newEcole = new Ecole(nom, new Localisation(localisation, pays));
            if(EcoleM.isEcoleInBD(newEcole)) {
                FenetreErreur f = new FenetreErreur("Ecole déjà en base de données");
            }
            else {
                // On la modifie
                EcoleM.modifierEcole(oldEcole, newEcole);
                //mettreAJourListe();
                // on remove l'ancinne, et on add la nouvelle
                listEcole.remove(oldEcole);
                listEcole.add(newEcole);
            }
        }
    }

    /**
     * Récupère toutes les écoles de la BD pour mettre à jour listEcole
     */
    public static void mettreAJourListe() {
        listEcole = EcoleM.getEcoles(); // On met le tableau à jour
    }

    /**
     * Si besoin, met à jour la liste des écoles avant de creer une nouvelle fenetre de parcours des écoles
     * @see FenetreParcourirEcole
     */
    public static void parcourirEcole() {
        if(listEcole.isEmpty()) {
            mettreAJourListe();
        }
        FenetreParcourirEcole fen = new FenetreParcourirEcole(listEcole);
    }

    /**
     * Supprime, de la BD et de la liste, l'école passée en parametre
     * @param ecole Ecole, l'école à supprimer
     */
    public static void supprimerEcole(Ecole ecole) {
        EcoleM.supprimerEcole(ecole);
        mettreAJourListe();
    }

    /**
     * Renvoie une liste des écoles presente dans le pays passé en parametre
     * @param p Pays, pays dont on veux les écoles
     * @return ArrayList, les écoles dans le pays
     */
    public static ArrayList<Ecole> getEcolesFromPays(Pays p) {
        if(listEcole.isEmpty()) {
            mettreAJourListe();
        }

        ArrayList<Ecole> ecoleFromPays = new ArrayList<>();

        for(Ecole e : listEcole) {
            // si le pays de l'école = celui qu'on recherche
            if(e.getLocalisation().getPays().equals(p))
                ecoleFromPays.add(e);
        }

        return ecoleFromPays;
    }
}

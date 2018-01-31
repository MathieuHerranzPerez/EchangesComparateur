package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.EcoleM;
import ComparateurCode.Vue.FenetreErreur;
import ComparateurCode.Vue.FenetreParcourirEcole;

import java.util.ArrayList;

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
     * Ajoute l'école dans la BD
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

    public static void mettreAJourListe() {
        listEcole = EcoleM.getEcoles(); // On met le tableau à jour
    }

    public static void parcourirEcole() {
        if(listEcole.isEmpty()) {
            mettreAJourListe();
        }
        FenetreParcourirEcole fen = new FenetreParcourirEcole(listEcole);
    }

    public static void supprimerEcole(Ecole ecole) {
        EcoleM.supprimerEcole(ecole);
        mettreAJourListe();
    }
}

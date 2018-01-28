package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.EcoleM;

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
        return "Ecole{" +
                "nom='" + nom + '\'' +
                ", localisation=" + localisation +
                '}';
    }

    public static ArrayList<Ecole> getListEcole() {
        if(listEcole.isEmpty()) {
            listEcole = EcoleM.getEcoles();
        }
        return listEcole;
    }

    /**
     * Ajoute l'école dans la BD
     * @param nom
     * @param localisation
     * @param pays
     */
    public static void ajouterEcole(String nom, String localisation, Pays pays) {
        // On vérifie qu'elle n'est pas déjà dans la BD
        if(EcoleM.getEcole(nom, localisation, pays) != null) {
            EcoleM.ajouterEcole(nom, localisation, pays);
        }
        // Si elle n'y est pas, on l'ajoute
        else {
            EcoleM.ajouterEcole(nom, localisation, pays);
        }
    }
}

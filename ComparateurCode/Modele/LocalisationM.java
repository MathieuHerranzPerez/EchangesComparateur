package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Localisation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 * Communique avec la base de données pour intéragir sur les Localisations en BD
 */
public class LocalisationM {

    private static TreeMap<Integer, Localisation> treeMapLocalisation = new TreeMap<>();

    /**
     * Retourne un arrayList de Location (ou null)
     * @return ArrayList
     */
    public static ArrayList<Localisation> getLocalisations() {
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM LOCALISATION ORDER BY Nom");

            ArrayList<Localisation> res = new ArrayList<>();
            while (result.next()) {
                Localisation l = new Localisation(result.getObject(2).toString(),
                        PaysM.getPaysFromId(Integer.parseInt(result.getObject(3).toString())));
                res.add(l);
                treeMapLocalisation.put(Integer.parseInt(result.getObject(1).toString()), l);
            }
            result.close();
            state.close();

            return res;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retourne la Localisation associée à la key (l'ID en BD)
     * @param key int
     * @return Localisation
     */
    public static Localisation isLocalisationInBD(int key) {
        if(treeMapLocalisation.size() == 0) {
            getLocalisations();
        }
        return treeMapLocalisation.get(key);
    }

    /**
     * Vérifie si la location est présente en BD
     * @param loc Localisation
     * @return boolean
     */
    public static boolean isLocalisationInBD(Localisation loc) {
        Statement state;
        boolean res = false;
        try {
            state = ConnexionBD.getInstance().createStatement();
            String requete = "SELECT * FROM LOCALISATION WHERE Nom = '" + loc.getNom() + "' AND Pays = " + PaysM.getId(loc.getPays()).toString() + ";";

            ResultSet result = state.executeQuery(requete);
            if(result.next()) {
                res = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * Ajoute la Localisation en BD
     * @param localisation Localisation
     */
    public static void ajouterLocalisation(Localisation localisation) {
        String requete = "INSERT INTO LOCALISATION (Nom, Pays) VALUES (?, ?);";
        PreparedStatement prepare;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, localisation.getNom());
            prepare.setString(2, PaysM.getId(localisation.getPays()).toString());

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // On met à jour le TreeMap
        getLocalisations();
    }

    /**
     * Retourne l'id de la localisation dans la BD
     * @param localisation Localisation
     * @return Integer, l'ID
     */
    public static Integer getId(Localisation localisation) {

        if(treeMapLocalisation.size() == 0) {
            getLocalisations();
        }
        Set<Integer> ss = treeMapLocalisation.keySet();
        Integer res = null;
        for(Integer i : ss) {
            // si la localisation a le même nom et le même pays
            if(treeMapLocalisation.get(i).equals(localisation)) {
                res = i;
            }
        }
        return res;
    }

    /**
     * Supprime la Localisation passée en parametre de la BD
     * @param localisation Localisation
     */
    public static void supprimerLoc(Localisation localisation) {
        String requeteSupprLoc = "DELETE FROM LOCALISATION WHERE Id = ? ;";
        PreparedStatement prepare = null;
        Integer idLoc = getId(localisation);
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requeteSupprLoc);

            prepare.setString(1, idLoc.toString());
            prepare.executeUpdate();

            // On met à jour le TreeMap
            treeMapLocalisation.remove(idLoc);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Formation;
import ComparateurCode.Controleur.Echange.SousDomaine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 * Communique avec la base de données pour intéragir sur les Formations en BD
 */
public class FormationM {

    private static TreeMap<Integer, Formation> treeMapFormation = new TreeMap<>();

    /**
     * Retourne la liste de toutes les formaions présentes en BD
     * @return ArrayList
     */
    public static ArrayList<Formation> getFormations() {
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM FORMATION ORDER BY Nom");

            ArrayList<Formation> res = new ArrayList<>();
            while (result.next()) {
                Formation f = new Formation(result.getObject(2).toString(),
                        Integer.parseInt(result.getObject(3).toString()),
                        result.getObject(4).toString(),
                        SousDomaineM.getSousDomaineFromId(Integer.parseInt(result.getObject(5).toString())));
                res.add(f);
                treeMapFormation.put(Integer.parseInt(result.getObject(1).toString()), f);
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
     * Retourne toutes les langues des formations qui sont en BD
     * @return ArrayList
     */
    public static ArrayList<String> getLangues() {
        String requete = "SELECT DISTINCT Langue FROM FORMATION;";
        Statement state;
        ArrayList<String> res = new ArrayList<>();
        try {
            state = ConnexionBD.getInstance().createStatement();
            ResultSet result = state.executeQuery(requete);

            while(result.next()) {
                res.add(result.getString("Langue"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Vérifie si la Formation passée en parametre est présente en BD
     * @param f Formation
     * @return boolean
     */
    public static boolean isFormationInBD(Formation f) {
        boolean res = false;
        try {
            // On regarde si le SousDomaine y est
            if(SousDomaineM.isSousDomaineInBD(f.getSousDomaine())) {

                // On regarde si la Formation y est
                Statement state;
                state = ConnexionBD.getInstance().createStatement();
                String requete = "SELECT * FROM FORMATION WHERE Nom = '" + f.getNom() + "' " +
                        "AND Duree = " + f.getDuree() + " AND Langue = '" + f.getLangue() + "' " +
                        "AND SousDomaine = " + SousDomaineM.getId(f.getSousDomaine()) + ";";

                ResultSet result = state.executeQuery(requete);
                if (result.next()) {
                    res = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Retourne la Formation à l'ID passé en parametre
     * @param key int
     * @return Fromation
     */
    public static Formation getFormationFromId(int key) {
        if(treeMapFormation.size() == 0) {
            getFormations();
        }
        return treeMapFormation.get(key);
    }

    /**
     * Retourne l'ID en BD de la Formation passée en parametre
     * @param f Formation
     * @return Integer
     */
    public static Integer getId(Formation f) {

        if(treeMapFormation.size() == 0) {
            getFormations();
        }
        Set<Integer> ss = treeMapFormation.keySet();
        Integer res = null;
        for(Integer i : ss) {
            if(treeMapFormation.get(i).equals(f)) {
                res = i;
            }
        }
        return res;
    }

    /**
     * Ajoute la Fomration passée en paramtre dans la BD
     * @param f Formation
     */
    public static void ajouterFormation(Formation f) {
        String requete = "INSERT INTO FORMATION (Nom, Duree, Langue, SousDomaine) VALUES (?, ?, ?, ?);";
        PreparedStatement prepare;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, f.getNom());
            prepare.setString(2, Integer.toString(f.getDuree()));
            prepare.setString(3, f.getLangue());
            prepare.setString(4, SousDomaineM.getId(f.getSousDomaine()).toString());

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // On met à jour le TreeMap
        getFormations();
    }

    /**
     * Supprimer la Formation passée en parametre de la BD, et le SousDomaine si il se retrouve seul
     * @param f Fromation
     */
    public static void supprimerFormation(Formation f) {
        // Si le sous domaine se retrouve seul, on le supprime
        Integer idFormation = getId(f);
        Integer idSousDomaine = SousDomaineM.getId(f.getSousDomaine());

        String requete = "SELECT * FROM FORMATION WHERE SousDomaine = " + idSousDomaine + ";";
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery(requete);
            result.last();
            int nbRes = result.getRow();    // on recupere le nombre de résultats

            // On supprime la formation
            String requeteSuppr = "DELETE FROM FORMATION WHERE Id = ? ;";
            PreparedStatement prepare = ConnexionBD.getInstance().prepareStatement(requeteSuppr);

            prepare.setInt(1, idFormation);
            prepare.executeUpdate();

            // On supprime le sous domaine si il n'y en a qu'un
            if(nbRes < 2) {
                SousDomaineM.supprimerSousDomaine(f.getSousDomaine());
            }

            // on met à jour le treeMap
            treeMapFormation.remove(idFormation);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

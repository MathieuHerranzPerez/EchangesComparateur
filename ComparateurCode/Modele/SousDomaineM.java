package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Domaine;
import ComparateurCode.Controleur.Echange.SousDomaine;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 * Communique avec la base de données pour intéragir sur les SousDomaines en BD
 */
public class SousDomaineM {

    private static TreeMap<Integer, SousDomaine> treeMapSousDomaine = new TreeMap<>();

    /**
     * Retourne la liste des SousDomaines qui ont pour Domaine celui passé en parametre
     * @param d Domaine
     * @return ArrayList
     */
    public static ArrayList<SousDomaine> getSousDomainesFromDomaine(Domaine d) {
        Statement state;
        ArrayList<SousDomaine> tabSousDomaine = new ArrayList<>();

        try {
            state = ComparateurCode.Modele.ConnexionBD.getInstance().createStatement();
            //L'objet ResultSet contient le résultat de la requète SQL
            ResultSet result = state.executeQuery("SELECT Nom, Domaine FROM SOUSDOMAINE WHERE Domaine = " + DomaineM.getId(d) + " ORDER BY Nom");

            while (result.next()) {
                tabSousDomaine.add(new SousDomaine(result.getString("Nom"), DomaineM.getDomaineFromId(result.getInt("Domaine"))));
            }

            result.close();
            state.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tabSousDomaine;
    }

    /**
     * Retourne les SousDomains présents en BD
     * @return ArrayList
     */
    public static ArrayList<SousDomaine> getSousDomaines() {
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM SOUSDOMAINE ORDER BY Nom");

            ArrayList<SousDomaine> res = new ArrayList<>();
            while (result.next()) {
                SousDomaine d = new SousDomaine(result.getObject(2).toString(),
                        DomaineM.getDomaineFromId(Integer.parseInt(result.getObject(3).toString())));
                res.add(d);
                treeMapSousDomaine.put(Integer.parseInt(result.getObject(1).toString()), d);
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
     * Vérifie si le SousDomaine passé en parametre est présent en BD
     * @param d SousDomaine
     * @return boolean
     */
    public static boolean isSousDomaineInBD(SousDomaine d) {
        boolean res = false;
        try {
            // On regarde si le Domaine y est
            if(DomaineM.isDomaineInBD(d.getDomaine())) {

                // On regarde si le SousDOmaine y est
                Statement state;
                state = ConnexionBD.getInstance().createStatement();
                String requete = "SELECT * FROM SOUSDOMAINE WHERE Nom = '" + d.getNom() + "' AND Domaine = " + DomaineM.getId(d.getDomaine()).toString() + ";";

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
     * Retourne le SousDomaine à l'id passé en parametre
     * @param key int
     * @return SousDomaine
     */
    public static SousDomaine getSousDomaineFromId(int key) {
        if(treeMapSousDomaine.size() == 0) {
            getSousDomaines();
        }
        return treeMapSousDomaine.get(key);
    }

    /**
     * Ajoute le SousDOmaine passé en parametre, dans la BD
     * @param d SousDomaine
     */
    public static void ajouterSousDomaine(SousDomaine d) {
        String requete = "INSERT INTO SOUSDOMAINE (Nom, Domaine) VALUES (?, ?);";
        PreparedStatement prepare;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, d.getNom());
            prepare.setString(2, DomaineM.getId(d.getDomaine()).toString());

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // On met à jour le TreeMap
        getSousDomaines();
    }

    /**
     * Retourne l'ID du SousDomaine passé en parametre
     * @param d SousDomaine
     * @return Integer
     */
    public static Integer getId(SousDomaine d) {

        if(treeMapSousDomaine.size() == 0) {
            getSousDomaines();
        }
        Set<Integer> ss = treeMapSousDomaine.keySet();
        Integer res = null;
        for(Integer i : ss) {
            // si la localisation a le même nom et le même pays
            if(treeMapSousDomaine.get(i).equals(d)) {
                res = i;
            }
        }
        return res;
    }

    /**
     * Supprime le SousDomaine passé en parametre, et le Domaine correspondant si il n'est plus utilisé
     * @param sd SousDomaine
     */
    public static void supprimerSousDomaine(SousDomaine sd) {
        // Si le domaine se retrouve seul, on le supprime
        Integer idSousDomaine = getId(sd);
        Integer idDomaine = DomaineM.getId(sd.getDomaine());

        String requete = "SELECT * FROM SOUSDOMAINE WHERE Domaine = " + idDomaine + ";";
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery(requete);
            result.last();
            int nbRes = result.getRow();    // on recupere le nombre de résultats

            // On supprime le sous domaine
            String requeteSuppr = "DELETE FROM SOUSDOMAINE WHERE Id = ? ;";
            PreparedStatement prepare = ConnexionBD.getInstance().prepareStatement(requeteSuppr);

            prepare.setInt(1, idSousDomaine);
            prepare.executeUpdate();

            // On supprime le domaine si il n'y en a qu'un
            if(nbRes < 2) {
                DomaineM.supprimerDomaine(sd.getDomaine());
            }

            // on met à jour le treeMap
            treeMapSousDomaine.remove(idSousDomaine);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

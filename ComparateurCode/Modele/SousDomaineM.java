package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Domaine;
import ComparateurCode.Controleur.Echange.SousDomaine;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class SousDomaineM {
    private static ArrayList<SousDomaine> listSousDomaine = new ArrayList<>();
    private static TreeMap<Integer, SousDomaine> treeMapSousDomaine = new TreeMap<>();

    public static void setNom(String ancienNom, String nouveauNom) {
        Statement state = null;

        try {
            state = ComparateurCode.Modele.ConnexionBD.getInstance().createStatement();
            state.executeUpdate("UPDATE SOUSDOMAINE SET Nom = '" + nouveauNom + "' WHERE Nom ='" + ancienNom + "'");
            state.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static ArrayList<SousDomaine> getSousDomaines() {
//        Statement state = null;
//        ArrayList<SousDomaine> tabSousDomaine = new ArrayList<>();
//
//        try {
//            state = ComparateurCode.Modele.ConnexionBD.getInstance().createStatement();
//            //L'objet ResultSet contient le résultat de la requète SQL
//            ResultSet result = state.executeQuery("SELECT Nom, Domaine FROM SOUSDOMAINE");
//            //On récupère les MetaData
//            ResultSetMetaData resultMeta = result.getMetaData();
//
//            while (result.next()) {
//                tabSousDomaine.add(new SousDomaine(result.getString("Nom"), DomaineM.getDomaineFromId(result.getInt("Domaine"))));
//            }
//
//            result.close();
//            state.close();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return tabSousDomaine;
//    }

    public static ArrayList<SousDomaine> getSousDomainesFromDomaine(Domaine d) {
        Statement state = null;
        ArrayList<SousDomaine> tabSousDomaine = new ArrayList<>();

        try {
            state = ComparateurCode.Modele.ConnexionBD.getInstance().createStatement();
            //L'objet ResultSet contient le résultat de la requète SQL
            ResultSet result = state.executeQuery("SELECT Nom, Domaine FROM SOUSDOMAINE WHERE Domaine = " + d.getId());
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

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

    public static ArrayList<SousDomaine> getSousDomaines() {
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM SOUSDOMAINE");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

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

    public static SousDomaine getSousDomaineFromId(int key) {
        if(treeMapSousDomaine.size() == 0) {
            getSousDomaines();
        }
        return treeMapSousDomaine.get(key);
    }

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
}

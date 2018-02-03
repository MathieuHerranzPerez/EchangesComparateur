package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Domaine;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;


public class DomaineM {

    private static ArrayList<DomaineM> listDomaines;
    private static TreeMap<Integer, Domaine> treeMapDomaine = new TreeMap<>();

    public static void setNom(String ancienNom, String nouveauNom) {
        Statement state = null;

        try {
            state = ConnexionBD.getInstance().createStatement();
            state.executeUpdate("UPDATE DOMAINE SET Nom = '" + nouveauNom + "' WHERE Nom ='" + ancienNom + "'");
            state.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static ArrayList<Domaine> getDomaines() {
//        Statement state = null;
//        ArrayList<Domaine> tabDomaine = new ArrayList<>();
//
//        try {
//            state = ConnexionBD.getInstance().createStatement();
//            //L'objet ResultSet contient le résultat de la requète SQL
//            ResultSet result = state.executeQuery("SELECT Id, Nom FROM DOMAINE");
//            //On récupère les MetaData
//            ResultSetMetaData resultMeta = result.getMetaData();
//
//            while (result.next()) {
//                tabDomaine.add(new Domaine(result.getInt("Id"), result.getString("Nom")));
//            }
//
//            result.close();
//            state.close();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return tabDomaine;
//    }


    public static Domaine getDomaineFromId(int id) {
        Statement state = null;
        Domaine domaine = null;

        try {
            state = ConnexionBD.getInstance().createStatement();
            //L'objet ResultSet contient le résultat de la requète SQL
            ResultSet result = state.executeQuery("SELECT Id, Nom FROM DOMAINE WHERE Id=" + id);
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();


            while (result.next()) {
                domaine = new Domaine(result.getInt("Id"), result.getString("Nom"));
            }

            result.close();
            state.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return domaine;
    }

    public static ArrayList<DomaineM> getListDomaines() {
        return listDomaines;
    }

    public static ArrayList<Domaine> getDomaines() {
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM DOMAINE");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            ArrayList<Domaine> res = new ArrayList<>();
            while (result.next()) {
                Domaine d = new Domaine(result.getObject(2).toString());
                res.add(d);
                treeMapDomaine.put(Integer.parseInt(result.getObject(1).toString()), d);
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

    public static boolean isDomaineInBD(Domaine d) {
        Statement state;
        boolean res = false;
        try {
            state = ConnexionBD.getInstance().createStatement();
            String requete = "SELECT * FROM DOMAINE WHERE Nom = '" + d.getNom() + "';";

            ResultSet result = state.executeQuery(requete);
            if(result.next()) {
                res = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void ajouterDomaine(Domaine d) {
        String requete = "INSERT INTO DOMAINE (Nom) VALUES (?);";
        PreparedStatement prepare;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, d.getNom());

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // On met à jour le TreeMap
        getDomaines();
    }

    public static Integer getId(Domaine domaine) {

        if(treeMapDomaine.size() == 0) {
            getDomaines();
        }
        Set<Integer> ss = treeMapDomaine.keySet();
        Integer res = null;
        for(Integer i : ss) {
            // si la localisation a le même nom et le même pays
            if(treeMapDomaine.get(i).equals(domaine)) {
                res = i;
            }
        }
        return res;
    }

    public static void supprimerDomaine(Domaine d) {
        String requeteSuppr = "DELETE FROM DOMAINE WHERE Id = ? ;";
        PreparedStatement prepare;
        Integer idDomaine = getId(d);
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requeteSuppr);

            prepare.setInt(1, idDomaine);
            prepare.executeUpdate();

            // On met à jour le TreeMap
            treeMapDomaine.remove(idDomaine);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Domaine;
import ComparateurCode.Controleur.Echange.SousDomaine;

import java.sql.*;
import java.util.ArrayList;


public class DomaineM {

    private static ArrayList<DomaineM> listDomaines;

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

    public static ArrayList<Domaine> getDomaines() {
        Statement state = null;
        ArrayList<Domaine> tabDomaine = new ArrayList<>();

        try {
            state = ConnexionBD.getInstance().createStatement();
            //L'objet ResultSet contient le résultat de la requète SQL
            ResultSet result = state.executeQuery("SELECT Id, Nom FROM DOMAINE");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            while (result.next()) {
                tabDomaine.add(new Domaine(result.getInt("Id"), result.getString("Nom")));
            }

            result.close();
            state.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return tabDomaine;
    }


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
}

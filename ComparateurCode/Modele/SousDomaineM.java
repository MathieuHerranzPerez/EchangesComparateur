package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Domaine;
import ComparateurCode.Controleur.Echange.SousDomaine;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SousDomaineM {
    private static ArrayList<SousDomaine> listSousDomaine = new ArrayList<>();

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

    public static ArrayList<SousDomaine> getSousDomaines() {
        Statement state = null;
        ArrayList<SousDomaine> tabSousDomaine = new ArrayList<>();

        try {
            state = ComparateurCode.Modele.ConnexionBD.getInstance().createStatement();
            //L'objet ResultSet contient le résultat de la requète SQL
            ResultSet result = state.executeQuery("SELECT Nom, Domaine FROM SOUSDOMAINE");
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
}

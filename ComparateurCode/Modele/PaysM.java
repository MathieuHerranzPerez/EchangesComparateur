package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Pays;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PaysM {

    /**
     * Pour retenir la correspondance Id, Pays (nom)
     */
    private static TreeMap<Integer, Pays> treeMapPays = new TreeMap<>();

    public static ArrayList<Pays> getPaysFromId() {
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM PAYS");

            ArrayList<Pays> res = new ArrayList<>();
            while (result.next()) {
                Pays p = new Pays(result.getObject(2).toString());
                treeMapPays.put(Integer.parseInt(result.getObject(1).toString()), p);
                res.add(p);
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


    public static Pays getPaysFromId(int key) {
        if(treeMapPays.size() == 0) {
            getPaysFromId();
        }
        return treeMapPays.get(key);
    }

    /**
     * Retourne l'id du pays dans la BD
     * @param pays
     * @return
     */
    public static Integer getId(Pays pays) {
        if(treeMapPays.size() == 0) {
            getPaysFromId();
        }
        Set<Integer> ss = treeMapPays.keySet();
        Integer res = null;
        for(Integer i : ss) {
            if(treeMapPays.get(i).getNom().equals(pays.getNom())) {
                res = i;
            }
        }
        return res;
    }

    public static ArrayList<Pays> getPaysUtilises() {
        if(treeMapPays.size() == 0) {
            getPaysFromId();
        }
        String requete = "SELECT * FROM PAYS WHERE Id IN" +
                " (SELECT DISTINCT Pays FROM LOCALISATION);";
        return executerRequeteRetourDansArray(requete);
    }

    public static ArrayList<Pays> getPaysDepart() {
        if(treeMapPays.size() == 0) {
            getPaysFromId();
        }
        String requete = "SELECT * FROM PAYS WHERE Id IN" +
                " (SELECT Pays FROM LOCALISATION WHERE Id IN" +
                    " (SELECT Localisation FROM ECOLE WHERE Id IN" +
                        "(SELECT EcoleDep FROM ECHANGE)));";
        return executerRequeteRetourDansArray(requete);
    }

    private static ArrayList<Pays> executerRequeteRetourDansArray(String requete) {
        Statement state;
        ArrayList<Pays> res = new ArrayList<>();
        try {
            state = ConnexionBD.getInstance().createStatement();
            ResultSet result = state.executeQuery(requete);
            while(result.next()) {
                res.add(treeMapPays.get(Integer.parseInt(result.getObject("Id").toString())));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] argv) {
        System.out.println(getId(new Pays("France")));
    }
}

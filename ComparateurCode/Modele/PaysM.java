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

    public static ArrayList<Pays> getPays() {
        Statement state = null;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM PAYS");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

//            result.last();
//            result.getRow();
//            result.beforeFirst();

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


    public static Pays getPays(int key) {
        if(treeMapPays.size() == 0) {
            getPays();
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
            getPays();
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



    public static void main(String[] argv) {
        System.out.println(getId(new Pays("France")));
    }
}

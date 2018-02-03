package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Formation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class FormationM {

    private static TreeMap<Integer, Formation> treeMapFormation = new TreeMap<>();

    public static ArrayList<Formation> getFormations() {
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM FORMATION");

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

    public static Formation getFormationFromId(int key) {
        if(treeMapFormation.size() == 0) {
            getFormations();
        }
        return treeMapFormation.get(key);
    }

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
}

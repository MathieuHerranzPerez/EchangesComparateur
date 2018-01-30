package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Localisation;
import ComparateurCode.Controleur.Echange.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class LocalisationM {

    private static TreeMap<Integer, Localisation> treeMapLocalisation = new TreeMap<>();

    /**
     * Met à jour le treeMap graçe à la BD, return un arrayList de Location (ou null)
     * @return
     */
    public static ArrayList<Localisation> getLocalisations() {
        Statement state = null;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM LOCALISATION");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            ArrayList<Localisation> res = new ArrayList<>();
            while (result.next()) {
                Localisation l = new Localisation(result.getObject(2).toString(),
                        PaysM.getPays(Integer.parseInt(result.getObject(3).toString())));
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
     * retourne la Localisation associée à la key
     * @param key
     * @return
     */
    public static Localisation getLocalisation(int key) {
        if(treeMapLocalisation.size() == 0) {
            getLocalisations();
        }
        return treeMapLocalisation.get(key);
    }

    /**
     * Return, si elle est présente, la localisation désignée par nom et pays
     * @param nom
     * @param pays
     * @return
     */
    public static Localisation getLocalisation(String nom, Pays pays) {
        Statement state;
        Localisation res = null;
        try {
            state = ConnexionBD.getInstance().createStatement();
            String requete = "SELECT * FROM LOCALISATION WHERE Nom = '" + nom + "' AND Pays = " + PaysM.getId(pays).toString() + ";";

            ResultSet result = state.executeQuery(requete);
            if(result.next()) {
                res = getLocalisation(Integer.parseInt(result.getObject(1).toString()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Ajoute la Localisation avec Nom et Pays en base de données
     * @param nom
     * @param pays
     */
    public static void ajouterLocalisation(String nom, Pays pays) {
        String requete = "INSERT INTO LOCALISATION (Nom, Pays) VALUES (?, ?);";
        PreparedStatement prepare = null;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, nom);
            prepare.setString(2, PaysM.getId(pays).toString());

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne l'id de la localisation dans la BD
     * @param localisation
     * @return
     */
    public static Integer getId(String localisation, Pays pays) {

        if(treeMapLocalisation.size() == 0) {
            getLocalisations();
        }
        Set<Integer> ss = treeMapLocalisation.keySet();
        Integer res = null;
        for(Integer i : ss) {
            // si la localisation a le même nom et le même pays
            if(treeMapLocalisation.get(i).getNom().equals(localisation) && treeMapLocalisation.get(i).getPays().equals(pays)) {
                res = i;
            }
        }
        return res;
    }
}

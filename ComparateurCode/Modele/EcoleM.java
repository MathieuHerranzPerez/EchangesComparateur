package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Localisation;
import ComparateurCode.Controleur.Echange.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.Thread.sleep;

public class EcoleM {
    private static TreeMap<Integer, Ecole> treeMapEcole = new TreeMap<>();

    public static ArrayList<Ecole> getEcoles() {
        Statement state = null;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM ECOLE");

            ArrayList<Ecole> res = new ArrayList<>();
            while (result.next()) {
                Ecole e = new Ecole(result.getObject(2).toString(),
                        LocalisationM.getLocalisation(Integer.parseInt(result.getObject(3).toString())));
                res.add(e);
                treeMapEcole.put(Integer.parseInt(result.getObject(1).toString()), e);
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
     * Return l'école qui a key pour id
     * @param key
     * @return
     */
    public static Ecole getEcole(int key) {
        if(treeMapEcole.size() == 0) {
            getEcoles();
        }
        return treeMapEcole.get(key);
    }

    /**
     * Return l'école nom située à localisation en pays
     * @param nom
     * @param localisation
     * @param pays
     * @return ecole ou null
     */
    public static Ecole getEcole(String nom, String localisation, Pays pays) {
        Statement state = null;
        Ecole res = null;
        try {
            state = ConnexionBD.getInstance().createStatement();
            if(LocalisationM.getId(localisation, pays) != null) {
                String requete = "SELECT * FROM ECOLE WHERE Nom = '" + nom + "' AND Localisation = " + LocalisationM.getId(localisation, pays).toString() + ";";
                ResultSet result = state.executeQuery(requete);
                if(result.next()) {
                    res = getEcole(Integer.parseInt(result.getObject(1).toString()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Ajoute l'Ecole avec Nom et Pays en base de données
     * @param nom
     * @param pays
     */
    public static void ajouterEcole(String nom, String localisation, Pays pays) {
        String requete = "INSERT INTO ECOLE (Nom, Localisation) VALUES (?, ?);";
        PreparedStatement prepare = null;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, nom);
            // si la localisation n'existe pas on la creer
            if(LocalisationM.getId(localisation, pays) == null) {
                LocalisationM.ajouterLocalisation(localisation, pays);
            }
            System.out.println(LocalisationM.getId(localisation, pays));
            prepare.setString(2, LocalisationM.getId(localisation, pays).toString());

            System.out.println("Ajout Ecole " + requete + " " + prepare);

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // on met à jour le treeMap
        getEcoles();
    }
}

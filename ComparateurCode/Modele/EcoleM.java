package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;


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
            prepare.setString(2, LocalisationM.getId(localisation, pays).toString());

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifie l'ecole d'ancien nom et d'ancienne localisation par la nouvelle
     * @param oldNom
     * @param oldLocalisation
     * @param oldPays
     * @param nom
     * @param localisation
     * @param pays
     */
    public static void modifierEcole(String oldNom, String oldLocalisation, Pays oldPays, String nom, String localisation, Pays pays) {
        if(treeMapEcole.size() == 0) {
            getEcoles();
        }
        String requete = "UPDATE ECOLE SET NOM = ?, Localisation = ? WHERE ID = ?;";
        PreparedStatement prepare = null;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, nom);
            // Si la localisation n'existe pas on la creer
            if(LocalisationM.getId(localisation, pays) == null) {
                LocalisationM.ajouterLocalisation(localisation, pays);
            }
            prepare.setString(2, LocalisationM.getId(localisation, pays).toString());
            prepare.setString(3, getId(oldNom, oldLocalisation, oldPays).toString());

            // On modifie
            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne l'id de la localisation dans la BD
     * @param ecole
     * @return
     */
    public static Integer getId(String ecole, String localisation, Pays pays) {

        if(treeMapEcole.size() == 0) {
            getEcoles();
        }
        Set<Integer> ss = treeMapEcole.keySet();
        Integer res = null;
        for(Integer i : ss) {
            // si la localisation a le même nom et le même pays (c'est la même école)
            if(treeMapEcole.get(i).getNom().equals(ecole) &&
                    LocalisationM.getId(treeMapEcole.get(i).getLocalisation().getNom(), pays).equals(LocalisationM.getId(localisation, pays))) {
                res = i;
            }
        }
        return res;
    }
}

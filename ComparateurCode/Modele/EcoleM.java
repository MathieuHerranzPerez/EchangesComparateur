package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Localisation;
import ComparateurCode.Controleur.Echange.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;


public class EcoleM {
    private static TreeMap<Integer, Ecole> treeMapEcole = new TreeMap<>();

    public static ArrayList<Ecole> getEcoles() {
        Statement state;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM ECOLE ORDER BY Nom");

            ArrayList<Ecole> res = new ArrayList<>();
            while (result.next()) {
                Ecole e = new Ecole(result.getObject(2).toString(),
                        LocalisationM.isLocalisationInBD(Integer.parseInt(result.getObject(3).toString())));
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
    public static Ecole isEcoleInBD(int key) {
        if(treeMapEcole.size() == 0) {
            getEcoles();
        }
        return treeMapEcole.get(key);
    }

    /**
     * Retourne vrai si l'ecole est dans la base de données
     * @param ecole
     * @return
     */
    public static boolean isEcoleInBD(Ecole ecole) {
        Statement state;
        boolean res = false;
        try {
            state = ConnexionBD.getInstance().createStatement();
            if(LocalisationM.getId(ecole.getLocalisation()) != null) {
                String requete = "SELECT * FROM ECOLE WHERE Nom = '" + ecole.getNom() + "' AND Localisation = " + LocalisationM.getId(ecole.getLocalisation()).toString() + ";";
                ResultSet result = state.executeQuery(requete);
                if(result.next()) {
                    res = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Ajoute l'Ecole en base de données
     * @param ecole
     */
    public static void ajouterEcole(Ecole ecole) {
        String requete = "INSERT INTO ECOLE (Nom, Localisation) VALUES (?, ?);";
        PreparedStatement prepare = null;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, ecole.getNom());

            // On ajoute la localisation
            Localisation.ajouterLocalisation(ecole.getLocalisation().getNom(), ecole.getLocalisation().getPays());

            prepare.setString(2, LocalisationM.getId(ecole.getLocalisation()).toString());

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // On met à jour le treeMap
        getEcoles();
    }

    /**
     * Modifie l'ecole d'ancien nom et d'ancienne localisation par la nouvelle
     * Dans le cas où la localisation viendrait à se retrouver seule, on la supprime
     * @param oldEcole
     * @param nouvelleEcole
     */
    public static void modifierEcole(Ecole oldEcole, Ecole nouvelleEcole) {
        if(treeMapEcole.size() == 0) {
            getEcoles();
        }
        String requete = "UPDATE ECOLE SET Nom = ?, Localisation = ? WHERE Id = ?;";
        PreparedStatement prepare = null;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, nouvelleEcole.getNom());
            // Si la localisation n'existe pas on la creer
            if(LocalisationM.getId(nouvelleEcole.getLocalisation()) == null) {
                LocalisationM.ajouterLocalisation(nouvelleEcole.getLocalisation());
            }
            prepare.setString(2, LocalisationM.getId(nouvelleEcole.getLocalisation()).toString());
            prepare.setString(3, getId(oldEcole).toString());

            // On modifie
            prepare.executeUpdate();

            // On met à jour le treeMap
            int key = getId(oldEcole);
            treeMapEcole.remove(key);
            treeMapEcole.put(key, nouvelleEcole);



            // On vérifie que la localisation ne se retrouve pas seule
            if(! oldEcole.getLocalisation().equals(nouvelleEcole.getLocalisation())) {
                requete = "SELECT COUNT(*) FROM ECOLE WHERE Localisation = " + LocalisationM.getId(oldEcole.getLocalisation()) + " ;";

                Statement state;
                state = ConnexionBD.getInstance().createStatement();

                ResultSet result = state.executeQuery(requete);
                if(result.next()) {
                    // Si c'est le cas, on la supprime
                    if (Integer.parseInt(result.getObject(1).toString()) < 1) {
                        LocalisationM.supprimerLoc(oldEcole.getLocalisation());
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne l'id de l'école dans la BD
     * @param ecole
     * @return
     */
    public static Integer getId(Ecole ecole) {

        if(treeMapEcole.size() == 0) {
            getEcoles();
        }
        Set<Integer> ss = treeMapEcole.keySet();
        Integer res = null;
        for(Integer i : ss) {
            // si la localisation a le même nom et le même pays (c'est la même école)
            if(treeMapEcole.get(i).equals(ecole)) {
                res = i;
            }
        }
        return res;
    }

    public static Ecole getEcoleFromId(int key) {
        if(treeMapEcole.size() == 0) {
            getEcoles();
        }
        return treeMapEcole.get(key);
    }

    /**
     * Supprime l'école et la localisation si elle n'est plus utile
     * @param ecole
     */
    public static void supprimerEcole(Ecole ecole) {
        // Si la localisation se retrouve seule, on la supprime
        Integer idEcole = getId(ecole);
        Integer idLoc = LocalisationM.getId(ecole.getLocalisation());

        String requeteLoc = "SELECT * FROM ECOLE WHERE Localisation = " + idLoc + ";";
        Statement state = null;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery(requeteLoc);
            result.last();
            int nbRes = result.getRow();    // on recupere le nombre de résultats

            // On suppriemr l'école
            String requeteSupprEcole = "DELETE FROM ECOLE WHERE Id = ? ;";
            PreparedStatement prepare = ConnexionBD.getInstance().prepareStatement(requeteSupprEcole);

            prepare.setString(1, idEcole.toString());
            prepare.executeUpdate();

            // On supprime la loc si il n'y en a qu'une
            if(nbRes < 2) {
                LocalisationM.supprimerLoc(ecole.getLocalisation());
            }

            // on met à jour le treeMap
            treeMapEcole.remove(idEcole);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

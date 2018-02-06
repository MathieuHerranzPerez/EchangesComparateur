package ComparateurCode.Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Communique avec la base de données pour récupérer des informations sur les identifiants Administrateurs
 */
public class AdminM {

    /**
     * Renvoie l'ID de l'identifiant d'administration stocké en BD
     * @return String, l'ID
     */
    public static String getId() {
        Statement state;
        String res = null;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT Identifiant FROM ADMIN");

            if(result.next()) {
                res = result.getObject("Identifiant").toString();
            }
            result.close();
            state.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Renvoie le mode de passe d'administration stocké en BD
     * @return String, le mot de passe
     */
    public static String getMDP() {
        Statement state;
        String res = null;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT MotDePasse FROM ADMIN");

            if(result.next()) {
                res = result.getObject("MotDePasse").toString();
            }
            result.close();
            state.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}

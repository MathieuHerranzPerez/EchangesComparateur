package ComparateurCode.Modele;

import java.sql.*;

/**
 * Singleton offrant un accès unique vers la BD
 */
public class ConnexionBD {

    private static final String id = "145728";
    private static final String mdp = "PolyProjet";
    private static final String url = "jdbc:mysql://mysql-projettopsecretgm.alwaysdata.net/projettopsecretgm_comparateurechanges?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static volatile Connection connexionBD = null;

    /**
     * Créer (si nécessaire) et retourne l'instance de la connexion BD
     * @return la connexion
     */
    public static Connection getInstance() {
        if(connexionBD == null) {
            new ConnexionBD();
        }
        return connexionBD;
    }

    private ConnexionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexionBD = DriverManager.getConnection(url, id, mdp);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

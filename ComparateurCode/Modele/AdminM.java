package ComparateurCode.Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminM {

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

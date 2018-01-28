package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Pays;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class PaysM {

    private static Pays[] pays = null;

    public static void tabPays() {
        Statement state = null;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM PAYS");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            result.last();
            pays = new Pays[result.getRow()];
            result.beforeFirst();

            int cpt = 0;
            while (result.next()) {
                pays[cpt] = new Pays(result.getObject(2).toString());
                ++cpt;
            }

            result.close();
            state.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Pays[] getPays() {
        if(pays == null) {
            tabPays();
        }
        return pays;
    }
}

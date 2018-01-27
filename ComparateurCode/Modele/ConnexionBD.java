package ComparateurCode.Modele;

import javax.swing.*;
import java.sql.*;

public class ConnexionBD {

    private static final String id = "145728";
    private static final String mdp = "PolyProjet";
    private static final String url = "jdbc:mysql://mysql-projettopsecretgm.alwaysdata.net/projettopsecretgm_comparateurechanges?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static volatile Connection connexionBD = null;

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


    public static void main(String[] argv) {
        //Création d'un objet Statement
        Statement state = null;
        try {
            state = ConnexionBD.getInstance().createStatement();
            //L'objet ResultSet contient le r�sultat de la requ�te SQL
            ResultSet result = state.executeQuery("SELECT * FROM PAYS");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            System.out.println("\n**********************************");
            //On affiche le nom des colonnes
            for (int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

            System.out.println("\n**********************************");

            while (result.next()) {
                for (int i = 1; i <= resultMeta.getColumnCount(); i++)
                    System.out.print("\t" + result.getObject(i).toString() + "\t |");

                System.out.println("\n---------------------------------");

            }

            result.close();
            state.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

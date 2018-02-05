package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Echange;
import ComparateurCode.Controleur.Echange.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationM {

    public static void ajouterReservation(Reservation r) {
        String requete = "INSERT INTO RESERVATION (Nom, Prenom, Telephone, Autre, Echange) VALUES (?, ?, ?, ?, ?);";

        PreparedStatement prepare;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, r.getNom());
            prepare.setString(2, r.getPrenom());
            prepare.setString(3, r.getTelephone());
            prepare.setString(4, r.getAutre());
            prepare.setInt(5, EchangeM.getId(r.getEchange()));

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isReservationInBD(Reservation r) {
        Statement state;
        boolean res = false;
        try {
            state = ConnexionBD.getInstance().createStatement();
            String requete = "SELECT Id FROM RESERVATION WHERE Nom = '" + r.getNom() +
                    "' AND Prenom = '" + r.getPrenom() +
                    "' AND Telephone = '" + r.getTelephone() +
                    "' AND Echange = '" + EchangeM.getId(r.getEchange()) + "';";

            ResultSet result = state.executeQuery(requete);
            if(result.next()) {
                res = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}

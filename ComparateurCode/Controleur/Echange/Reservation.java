package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.ReservationM;
import ComparateurCode.Vue.FenetreErreur;

public class Reservation {

    private String nom;
    private String prenom;
    private String telephone;
    private String autre;
    private Echange echange;

    public Reservation(String nom, String prenom, String telephone, String autre, Echange echange) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.autre = autre;
        this.echange = echange;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAutre() {
        return autre;
    }
    public Echange getEchange() {
        return echange;
    }


    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Reservation))
            return false;

        Reservation reservation = (Reservation) o;
        return this.nom.equals(reservation.getNom()) && this.prenom.equals(reservation.getPrenom())
                && this.telephone.equals(reservation.getTelephone()) && this.echange.equals(reservation.getEchange());
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (echange != null ? echange.hashCode() : 0);
        return result;
    }

    public static void ajouterReservation(Echange e, String nom, String prenom, String telephone, String autre) {
        Reservation reseravation = new Reservation(nom, prenom, telephone, autre, e);

        // On vérifie qu'elle n'est pas déjà en BD
        if(!ReservationM.isReservationInBD(reseravation)) {
            // On l'ajoute
            ReservationM.ajouterReservation(reseravation);
        }
        else {
            FenetreErreur fen = new FenetreErreur("Quelqu'un a déjà émis une réservation sous ce nom et pour cette école");
        }
    }
}

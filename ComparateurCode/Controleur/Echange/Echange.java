package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.EchangeM;
import ComparateurCode.Modele.FormationM;
import ComparateurCode.Vue.FenetreErreur;
import ComparateurCode.Vue.FenetreParcourirEchange;

import java.util.ArrayList;

public class Echange {

    private int id;
    private Integer duree;              // en mois
    private Ecole ecoleDepart;
    private Ecole ecoleArrivee;
    private Formation formation;

    private static ArrayList<Echange> listEchange = new ArrayList<>();

    // ----- setter
//    public void setId(int id) {
//        this.id = id;
//    }

    /*public void setAnnee(int annee) {
        this.annee = annee;
    }*/

//    public void setDuree(Integer duree) {
//        this.duree = duree;
//    }
//
//    public void setEcoleDepart(Ecole ecoleDepart) {
//        this.ecoleDepart = ecoleDepart;
//    }
//
//    public void setEcoleArrivee(Ecole ecoleArrivee) {
//        this.ecoleArrivee = ecoleArrivee;
//    }
//
//    public void setFormation(Formation formation) {
//        this.formation = formation;
//    }

    // ----- getter
    public int getId() {
        return id;
    }

   /* public int getAnnee() {
        return annee;
    }*/

    public Integer getDuree() {
        return duree;
    }

    public Ecole getEcoleDepart() {
        return ecoleDepart;
    }

    public String getNomEcolePrincipale() {
        return ecoleDepart.getNom();
    }

    public Formation getFormation() {
        return formation;
    }

    public Ecole getEcoleArrivee() {
        return ecoleArrivee;
    }

    public String getNomEcoleEchange() {
        return getEcoleArrivee().getNom();
    }

    public String getNomLocalisationEcoleEchange() {
        return getEcoleArrivee().getLocalisation().getNom();
    }

    public String getNomPaysEcoleEchange() {
        return getEcoleArrivee().getLocalisation().getPays().getNom();
    }

    public String getNomFormation() {
        return getFormation().getNom();
    }

    public String getLangue() {
        return getFormation().getLangue();
    }

    public String getNomSousDomaine() {
        return formation.getSousDomaine().getNom();
    }

    public String getNomDomaine() {
        return formation.getSousDomaine().getDomaine().getNom();
    }

    public static ArrayList<Echange> getEchanges() {
        if(listEchange.isEmpty()) {
            mettreAJourListe();
        }
        return listEchange;
    }

    @Override
    public String toString() {
        return "Echange{" +
                "id=" + id +
                ", duree=" + duree +
                ", ecoleDepart=" + ecoleDepart +
                ", ecoleArrivee=" + ecoleArrivee +
                ", formation=" + formation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Echange))
            return false;

        Echange echange = (Echange) o;

        return this.duree == echange.getDuree() && this.ecoleDepart.equals(echange.getEcoleDepart()) &&
                this.ecoleArrivee.equals(echange.getEcoleArrivee()) && this.formation.equals(echange.getFormation());
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + duree;
        result = 31 * result + (ecoleDepart != null ? ecoleDepart.hashCode() : 0);
        result = 31 * result + (ecoleArrivee != null ? ecoleArrivee.hashCode() : 0);
        result = 31 * result + (formation != null ? formation.hashCode() : 0);
        return result;
    }

    public Echange(int id, Integer duree, Ecole ecoleDepart, Ecole ecoleArrivee, Formation formation) {
        this.id = id;
        this.duree = duree;
        this.ecoleDepart = ecoleDepart;
        this.ecoleArrivee = ecoleArrivee;
        this.formation = formation;
    }

    public Echange(Integer duree, Ecole ecoleDepart, Ecole ecoleArrivee, Formation formation) {
        this.duree = duree;
        this.ecoleDepart = ecoleDepart;
        this.ecoleArrivee = ecoleArrivee;
        this.formation = formation;
    }

    public static void ajouterEchange(Ecole ecoleDep, Ecole ecoleArr, String nomFormation, Integer duree, Integer dureeFormation, String langue,
                                      String nomSousDomaine, String nomDomaine) {
        Echange newEchange = new Echange(duree, ecoleDep, ecoleArr,
                new Formation(nomFormation, dureeFormation, langue,
                        new SousDomaine(nomSousDomaine,
                                new Domaine(nomDomaine))));

        // On vérifie que l'échange n'existe pas
        if(!EchangeM.isEchangeInBD(newEchange)) {

            // On ajoute la formation
            Formation.ajouterFormation(newEchange.getFormation());

            // On ajoute l'echange
            EchangeM.ajouterEchange(newEchange);
        }
        else {
            FenetreErreur f = new FenetreErreur("Echange déjà en BD");
        }
    }

    public static void mettreAJourListe() {
        listEchange = EchangeM.getEchanges(); // On met le tableau à jour
    }

    public static void parcourirEchange() {
        if(listEchange.isEmpty()) {
            mettreAJourListe();
        }
        FenetreParcourirEchange fen = new FenetreParcourirEchange(listEchange);
    }

    public static void modifierEchange(Echange oldEchange, Integer duree, String nomFormation, Integer dureeFormation, String langue,
                                       String nomSousDomaine, String nomDomaine, Ecole ecoleDep, Ecole ecoleArr) {
        // On vérifie qu'il change
        if(oldEchange.getDuree() == duree && oldEchange.getFormation().toString().equals(nomFormation) &&
                oldEchange.getFormation().getDuree() == dureeFormation && oldEchange.getFormation().getLangue().equals(langue) &&
                oldEchange.getFormation().getSousDomaine().getNom().equals(nomSousDomaine) &&
                oldEchange.getFormation().getSousDomaine().getDomaine().getNom().equals(nomDomaine) &&
                oldEchange.getEcoleDepart().equals(ecoleDep) && oldEchange.getEcoleArrivee().equals(ecoleArr)) {
            FenetreErreur f = new FenetreErreur("Echange inchangé");
        }
        else {
            // On vérifie que le nouveau n'est pas dans la BD
            Echange newEchange = new Echange(duree, ecoleDep, ecoleArr,
                    new Formation(nomFormation, dureeFormation, langue, new SousDomaine(nomSousDomaine, new Domaine(nomDomaine))));
            if(EchangeM.isEchangeInBD(newEchange)) {
                FenetreErreur f = new FenetreErreur("Echange déjà en base de données");
            }
            else {
                // On le modifie
                EchangeM.modifierEchange(oldEchange, newEchange);
                //mettreAJourListe();
                // on remove l'ancinne, et on add la nouvelle
                listEchange.remove(oldEchange);
                listEchange.add(newEchange);
            }
        }
    }

    public static void supprimerEchange(Echange echange) {
        EchangeM.supprimerEchange(echange);
        mettreAJourListe();
    }
}

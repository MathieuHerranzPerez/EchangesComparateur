package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.EchangeM;
import ComparateurCode.Modele.FormationM;
import ComparateurCode.Vue.FenetreErreur;
import ComparateurCode.Vue.FenetreParcourirEchange;

import java.util.ArrayList;

public class Echange {

    private Integer duree;              // en mois
    private Ecole ecoleDepart;
    private Ecole ecoleArrivee;
    private Formation formation;

    private static ArrayList<Echange> listEchange = new ArrayList<>();

    public Integer getDuree() {
        return duree;
    }

    public Ecole getEcoleDepart() {
        return ecoleDepart;
    }

    public Formation getFormation() {
        return formation;
    }

    public Ecole getEcoleArrivee() {
        return ecoleArrivee;
    }

    public String getNomLocalisationEcoleEchange() {
        return getEcoleArrivee().getLocalisation().getNom();
    }

    public String getNomPaysEcoleEchange() {
        return getEcoleArrivee().getLocalisation().getPays().getNom();
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
        int result = 0;
        result = 31 * result + duree;
        result = 31 * result + (ecoleDepart != null ? ecoleDepart.hashCode() : 0);
        result = 31 * result + (ecoleArrivee != null ? ecoleArrivee.hashCode() : 0);
        result = 31 * result + (formation != null ? formation.hashCode() : 0);
        return result;
    }

    public Echange(Integer duree, Ecole ecoleDepart, Ecole ecoleArrivee, Formation formation) {
        this.duree = duree;
        this.ecoleDepart = ecoleDepart;
        this.ecoleArrivee = ecoleArrivee;
        this.formation = formation;
    }

    /**
     * On ajoute l'échange correspondant aux paramettres de la fonctions en base de données si il n'est pas déjà présent
     * Dans le cas d'un ajout, on met la liste des échanges à jour avec le nouvel
     * @param ecoleDep Ecole, l'école de départ
     * @param ecoleArr Ecole, l'école d'arrivée
     * @param nomFormation String, le nom de la formation
     * @param duree Integer, la durée de l'échange
     * @param dureeFormation Integer, la durée de la formation
     * @param langue String, la langue des cours de la formation
     * @param nomSousDomaine String, nom du sous domaine de la formation
     * @param nomDomaine String, nom du domaine de la formation
     */
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

    /**
     * Met à jour la liste des échanges
     */
    public static void mettreAJourListe() {
        listEchange = EchangeM.getEchanges(); // On met le tableau à jour
    }

    /**
     * Charge la liste des échanges si besoin, et appele FenetreParcourirEchange
     * @see FenetreParcourirEchange
     */
    public static void parcourirEchange() {
        if(listEchange.isEmpty()) {
            mettreAJourListe();
        }
        FenetreParcourirEchange fen = new FenetreParcourirEchange(listEchange);
    }

    /**
     * Modifie, si besoin, l'échange oldEchange, par le nouveau dont les attributs sont passés en paramètres, en base de donnée
     * Met la liste des échanges à jour avec ce changement
     * @param oldEchange Echange, l'ancien échange
     * @param duree Integer, la durée du nouvel echange
     * @param nomFormation String, le nom de la formation du nouvel echange
     * @param dureeFormation Integer, la durée de la formation du nouvel echange
     * @param langue String, la langue des cours du nouvel echange
     * @param nomSousDomaine String, le nom du ous domaine de la fomration du nouvel echange
     * @param nomDomaine String, le nom du domaine de la formation du nouvel echange
     * @param ecoleDep Ecole, l'école de départ du nouvel echange
     * @param ecoleArr Ecole, l'école d'arrivée du nouvel echange
     */
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

    /**
     * Supprime l'échange donné en parametre de la base de données
     * Met à jour la liste des échanges
     * @param echange Echange, l'échange à supprimer
     */
    public static void supprimerEchange(Echange echange) {
        EchangeM.supprimerEchange(echange);
        mettreAJourListe();
    }
}

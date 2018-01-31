package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.*;

import java.util.ArrayList;
import java.util.Collections;

public class BinaryCompare implements Comparateur {

    private Echange reference;

    private int comparer(Echange echangeBD) {
        int resultat = 0;
        int malus = -10;
        int bonus = 10;


        // si l'utilisateur a spécifié une langue
        if(! reference.getLangue().equals("")) {
            // si l'echangeBD n'a pas la même langue que celle voulue
            if(! echangeBD.getLangue().equals(reference.getLangue())) {
                resultat += (5 * malus);
            }
            else {
                resultat += (4 * bonus);
            }
            System.out.println(reference.getLangue() + "   " + echangeBD.getLangue());
        }

        // si l'utilisateur a selectionné un pays et pas de ville
        if(! reference.getNomPaysEcoleEchange().equals("")
                && reference.getNomLocalisationEcoleEchange().equals("")) {
            // si l'echangeBD n'a pas le même pays que celui voulu
            if(! echangeBD.getNomPaysEcoleEchange().equals(reference.getNomPaysEcoleEchange())) {
                resultat += (4 * malus);
            }
            else {
                resultat += (3 * bonus);
            }
            System.out.println(reference.getNomPaysEcoleEchange() + "   " + echangeBD.getNomPaysEcoleEchange());
        }

        // si l'utilisateur a selectionné une ville
        if(! reference.getNomLocalisationEcoleEchange().equals("")) {
            // si l'echangeBD n'a pas la même ville que celle voulue
            if(! echangeBD.getNomLocalisationEcoleEchange().equals(reference.getNomLocalisationEcoleEchange())) {
                // si on est quand même dans le bon pays
                if(echangeBD.getNomPaysEcoleEchange().equals(reference.getNomPaysEcoleEchange())) {
                    resultat += (2 * bonus);
                }
                else {
                    resultat += (3 * malus);
                }
            }
            else {
                resultat += (5 * bonus);
            }
            System.out.println(reference.getNomLocalisationEcoleEchange() + "   " + echangeBD.getNomLocalisationEcoleEchange());
        }

        // si l'utilisateur a selectionné un domaine
        if(! reference.getNomDomaine().equals("")) {
            // si l'echangeBD n'a pas le même domaine
            if(! echangeBD.getNomDomaine().equals(reference.getNomDomaine())) {
                resultat += (7 * malus);
            }
            else {
                resultat += (7 * bonus);
            }
        }

        // si l'utilisateur a selectionné un sous domaine
        if(! reference.getNomSousDomaine().equals("")) {
            // si l'echangeBD n'a pas le même sous domaine
            if(! echangeBD.getNomSousDomaine().equals(reference.getNomSousDomaine())) {
                resultat += (1 * malus);
            }
            else {
                resultat += (4 * bonus);
            }
            System.out.println(reference.getNomSousDomaine() + "   " + echangeBD.getNomSousDomaine());

        }

        // si l'utilisateur a selectionné une durée
        if(reference.getDuree() != 0) {
            // si echangeBD n'a pas la même durée à 2 mois près
            if(echangeBD.getDuree() > reference.getDuree()+2 || echangeBD.getDuree() < reference.getDuree()-2) {
                resultat += (1 * malus);
            }
            else {
                resultat += (2 * bonus);
            }
            System.out.println(reference.getDuree() + "   " + echangeBD.getDuree());
        }
        System.out.println("RES : " + resultat);
        return resultat;
    }

    public BinaryCompare(Echange reference) {
        this.reference = reference;
    }

    @Override
    public int compare(Echange o1, Echange o2) {
        return this.comparer(o2) - this.comparer(o1);
    }

//    public static void main(String[] argv) {
//        // situation utilisateur
//        // d'où il vient
//        Pays france = new Pays("France");
//        Localisation localisationNull = new Localisation(france);
//        Ecole ecoleUti = new Ecole("Polytech", localisationNull);
//
//        SousDomaine info = new SousDomaine(new Domaine("Informatique"));
//        Formation formationInfo = new Formation(info);
//        // ce qu'il veut
//        Pays angleterre = new Pays("Angleterre");
//        // il n'a pas choisie de ville
//        Localisation localisationAngleterre = new Localisation(angleterre);
//        Ecole ecoleSouhaite = new Ecole(localisationAngleterre);
//
//        SousDomaine infoJeux = new SousDomaine("Jeux-video", new Domaine("Informatique"));
//        Formation formationVoulue = new Formation("Anglais", infoJeux);
//
//        Echange echangeParfait = new Echange(-1, 12, ecoleUti, ecoleSouhaite, formationVoulue);
//
//        // -------- ecole des echanges trouvées --------
//        Localisation localisationM = new Localisation("Marseille", france);
//        Ecole ecoleDep = new Ecole("Polytech", localisationM);
//
//        Localisation localisationA = new Localisation("Londres", angleterre);
//        Ecole ecoleArr = new Ecole("ecoleAnglaise", localisationA);
//
//        SousDomaine infoBD = new SousDomaine("BD", new Domaine("Informatique"));
//        Formation formationBD = new Formation("Anglais", infoBD);
//
//        Echange premierEchange = new Echange(1, 12, ecoleDep, ecoleArr, formationVoulue);
//        Echange deuxiemeEchange = new Echange(2, 12, ecoleDep, ecoleArr, formationBD);
//
//        ArrayList<Echange> ar = new ArrayList<>();
//
//        ar.add(premierEchange);
//        ar.add(deuxiemeEchange);
//
//        Echange troisiemeEchange = new Echange(3, 12, ecoleArr, ecoleDep, formationBD);
//        ar.add(troisiemeEchange);
//
//
//        Collections.sort(ar, new BinaryCompare(echangeParfait));
//
//        for(Echange e : ar) {
//            System.out.println(e);
//        }
//    }
}

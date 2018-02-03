package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.*;

import java.util.ArrayList;
import java.util.Collections;

public class BinaryCompare extends Comparateur {

    public BinaryCompare(Echange reference) {
        super(reference);
    }

    @Override
    public int compareSpecifique(Echange echangeBD) {
        int resultat = 0;

        // si l'utilisateur a spécifié une langue
        if( reference.getLangue() != null) {
            // si l'echangeBD n'a pas la même langue que celle voulue
            if(! echangeBD.getLangue().equals(reference.getLangue())) {
                resultat += (5 * malus);
            }
            else {
                resultat += (4 * bonus);
            }
            System.out.println(reference.getLangue() + "   " + echangeBD.getLangue());
        }

        // si l'utilisateur a selectionné une ville
        if(reference.getNomLocalisationEcoleEchange().equals("")) {
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

        // si l'utilisateur a selectionné un sous domaine
        if( reference.getNomSousDomaine() != null) {
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
        if(reference.getDuree() != null) {
            // si echangeBD n'a pas la même durée à 2 mois près
            if(echangeBD.getDuree() > reference.getDuree()+2 || echangeBD.getDuree() < reference.getDuree()-2) {
                resultat += (1 * malus);
            }
            else {
                resultat += (2 * bonus);
            }
            System.out.println(reference.getDuree() + "   " + echangeBD.getDuree());
        }

        return resultat;
    }


//    public static void main(String[] argv) {
//        // situation utilisateur
//        // d'où il vient
//        Pays france = new Pays("France");
//        Localisation localisationNull = new Localisation(france);
//        Ecole ecoleUti = new Ecole("Polytech", localisationNull);
//
//        SousDomaine info = new SousDomaine();
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

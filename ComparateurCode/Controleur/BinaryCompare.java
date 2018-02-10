package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.*;

import java.util.ArrayList;
import java.util.Collections;

public class BinaryCompare extends Comparateur {

    public BinaryCompare(Echange reference) {
        super(reference);
    }

    /**
     * Compare l'échange en parametre avec l'échange de référence et renvoie un socre refletant leurs similarités
     * @param echangeBD Echange, l'échange à comparer
     * @return int, score
     */
    @Override
    public int compareSpecifique(Echange echangeBD) {
        int resultat = 0;

        // si l'utilisateur a spécifié une langue
        if( reference.getLangue() != null) {
            // si l'echangeBD n'a pas la même langue que celle voulue
            if(! echangeBD.getLangue().equals(reference.getLangue())) {
                // si c'est de l'anglais
                if(echangeBD.getLangue().equals("Anglais")) {
                    resultat += (1 * bonus);
                }
                else {
                    resultat += (5 * malus);
                }
            }
            else {
                resultat += (4 * bonus);
            }
        }

        // si l'utilisateur a selectionné une ville
        if(! reference.getNomLocalisationEcoleEchange().equals("")) {
            // si l'echangeBD n'a pas la même ville que celle voulue
            if( echangeBD.distanceLocalisation(reference) > 2) { // 2 erreurs acceptées dans le nom de la ville
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
        }

        // si l'utilisateur a selectionné une durée
        if(reference.getDuree() != null) {
            // si echangeBD a la même durée à 2 mois près
            if(echangeBD.getDuree() <= reference.getDuree()+2 && echangeBD.getDuree() >= reference.getDuree()-2) {
                resultat += (2 * bonus);
            }
            // si echangeBD a la même durée à 3 mois près
            else if(echangeBD.getDuree() <= reference.getDuree()+3 || echangeBD.getDuree() >= reference.getDuree()-3) {
                resultat += (1 * bonus);
            }
            // si echangeBD n'a pas la même durée à 3 mois près
            else {
                resultat += (1 * malus);
            }
        }

        return resultat;
    }
}

package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.Echange;

public class BinaryCompare implements Comparateur {

    @Override
    public int comparer(Echange echangeUti, Echange echangeBD) {
        int resultat = 0;
        int malus = -10;
        int bonus = 10;


        // si l'utilisateur a spécifié une langue
        if(! echangeUti.getLangue().equals("")) {
            // si l'echangeBD n'a pas la même langue que celle voulue
            if(! echangeBD.getLangue().equals(echangeUti.getLangue())) {
                resultat += (5 * malus);
            }
            else {
                resultat += (4 * bonus);
            }
        }

        // si l'utilisateur a selectionné un pays et pas de ville
        if(! echangeUti.getNomPaysEcoleEchange().equals("")
                && echangeUti.getNomLocalisationEcoleEchange().equals("")) {
            // si l'echangeBD n'a pas le même pays que celui voulu
            if(! echangeBD.getNomPaysEcoleEchange().equals(echangeUti.getNomPaysEcoleEchange())) {
                resultat += (4 * malus);
            }
            else {
                resultat += (3 * bonus);
            }
        }

        // si l'utilisateur a selectionné une ville
        if(! echangeUti.getNomLocalisationEcoleEchange().equals("")) {
            // si l'echangeBD n'a pas la même ville que celle voulue
            if(! echangeBD.getNomLocalisationEcoleEchange().equals(echangeUti.getNomLocalisationEcoleEchange())) {
                // si on est quand même dans le bon pays
                if(echangeBD.getNomPaysEcoleEchange().equals(echangeUti.getNomPaysEcoleEchange())) {
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

        // si l'utilisateur a selectionné un domaine
        if(! echangeUti.getNomDomaine().equals("")) {
            // si l'echangeBD n'a pas le même domaine
            if(! echangeBD.getNomDomaine().equals(echangeUti.getNomDomaine())) {
                resultat += (7 * malus);
            }
            else {
                resultat += (7 * bonus);
            }
        }

        // si l'utilisateur a selectionné un sous domaine
        if(! echangeUti.getNomSousDomaine().equals("")) {
            // si l'echangeBD n'a pas le même sous domaine
            if(! echangeBD.getNomSousDomaine().equals(echangeUti.getNomSousDomaine())) {
                resultat += (1 * malus);
            }
            else {
                resultat += (4 * bonus);
            }
        }

        // si l'utilisateur a selectionné une durée
        if(echangeUti.getDuree() != 0) {
            // si echangeBD n'a pas la même durée à 2 mois près
            if(echangeBD.getDuree() > echangeUti.getDuree()+2 || echangeBD.getDuree() < echangeUti.getDuree()-2) {
                resultat += (1 * malus);
            }
            else {
                resultat += (2 * bonus);
            }
        }

        return resultat;
    }
}

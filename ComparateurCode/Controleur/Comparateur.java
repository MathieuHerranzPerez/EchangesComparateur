package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.Echange;

import java.util.Comparator;

/**
 * Class héritant de Comparator, permet de comparer des échanges par rapport à un échange de référence (en donnée membre)
 * Suit le design pattern TemplateMethod
 */
public abstract class Comparateur implements Comparator<Echange> {
    protected Echange reference;
    protected int malus = -10;
    protected int bonus = 10;


    public Comparateur(Echange reference) {
        this.reference = reference;
    }


    /**
     * Code commun aux classes filles, fait la comparaison d'un échange passé en paramètre (cf spec)
     * @param echangeBD Echange, l'échange à comparer
     * @return int, le score
     */
    public int comparer(Echange echangeBD) {
        int resultat = 0;

        // si l'utilisateur a selectionné un pays et pas de ville
        if( reference.getNomPaysEcoleEchange() != null && reference.getNomLocalisationEcoleEchange().equals("")) {
            // si l'echangeBD n'a pas le même pays que celui voulu
            if(! echangeBD.getNomPaysEcoleEchange().equals(reference.getNomPaysEcoleEchange())) {
                resultat += (4 * malus);
            }
            else {
                resultat += (3 * bonus);
            }
        }

        // si l'utilisateur a selectionné un domaine
        if( reference.getNomDomaine() != null) {
            // si l'echangeBD n'a pas le même domaine
            if(! echangeBD.getNomDomaine().equals(reference.getNomDomaine())) {
                resultat += (19 * malus);
                System.out.println(resultat + "    " + malus);
            }
            else {
                resultat += (7 * bonus);
            }
        }

        resultat += compareSpecifique(echangeBD);

        return resultat;
    }

    /**
     * Fonction de comparaison implémentée dans les classes filles
     * @param echangeBD Echange, l'échange à comparer
     * @return int, le score
     */
    public abstract int compareSpecifique(Echange echangeBD);

    /**
     * Fonction qui va être appelée lors d'un Collections.sort()
     * Dans le but de trier un tableau du score le plus élevé au plus bas
     * @param o1 Echange
     * @param o2 Echange
     * @return int, la différence entre les deux échanges
     */
    @Override
    public int compare(Echange o1, Echange o2) {
        return this.comparer(o2) - this.comparer(o1);
    }
}

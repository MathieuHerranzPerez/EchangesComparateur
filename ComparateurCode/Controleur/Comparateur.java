package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.Echange;

import java.util.Comparator;

public abstract class Comparateur implements Comparator<Echange> {
    protected Echange reference;
    protected int malus = -10;
    protected int bonus = 10;


    public Comparateur(Echange reference) {
        this.reference = reference;
    }


    private int comparer(Echange echangeBD) {
        int resultat = 0;


        // si l'utilisateur a selectionné un pays et pas de ville
        if(! reference.getNomPaysEcoleEchange().equals("")) {
            // si l'echangeBD n'a pas le même pays que celui voulu
            if(! echangeBD.getNomPaysEcoleEchange().equals(reference.getNomPaysEcoleEchange())) {
                resultat += (4 * malus);
            }
            else {
                resultat += (3 * bonus);
            }
            System.out.println(reference.getNomPaysEcoleEchange() + "   " + echangeBD.getNomPaysEcoleEchange());
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

        resultat += compareSpecifique(echangeBD);

        System.out.println("RES : " + resultat);
        return resultat;
    }


    public abstract int compareSpecifique(Echange echangeBD);

    @Override
    public int compare(Echange o1, Echange o2) {
        return this.comparer(o2) - this.comparer(o1);
    }

}

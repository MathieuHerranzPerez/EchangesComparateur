package ComparateurCode.Controleur;


import ComparateurCode.Controleur.Echange.Echange;

public class RapidCompare extends Comparateur {

    public RapidCompare(Echange reference) {
        super(reference);
    }

    @Override
    public int compareSpecifique(Echange echangeBD) {
        // comparaison simple donc rien de plus
        return 0;
    }
}

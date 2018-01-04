package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.Echange;

public interface Comparateur {
    public int comparer(Echange echangeUti, Echange echangeBD);
}

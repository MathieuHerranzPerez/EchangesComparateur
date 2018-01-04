package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.Echange;

import java.util.ArrayList;

public class Contexte {
    private Comparateur strategy;
    private ArrayList<Echange> echanges;

    public Contexte(ArrayList<Echange> echanges) {
        // TODO on choisie la strategie suivant les inputs de l'utilisateur

        this.echanges = echanges;
    }
}

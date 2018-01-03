package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Ecole;

/**
 * Peut-être à supprimer !
 */
public class EchangeM {
    private int id;
    private String type;
    private Ecole ecolePrincipale;
    private Ecole ecoleEchange;


    public EchangeM(int id, String type, Ecole ecolePrincipale, Ecole ecoleEchange) {
        this.id = id;
        this.type = type;
        this.ecolePrincipale = ecolePrincipale;
        this.ecoleEchange = ecoleEchange;
    }

    public EchangeM(int id, String type, Ecole ecoleEchange) {
        this.id = id;
        this.type = type;
        this.ecoleEchange = ecoleEchange;
    }

    public int getId() { return id; }

    public Ecole getEcolePrincipale() {
        return ecolePrincipale;
    }

    public Ecole getEcoleEchange() {
        return ecoleEchange;
    }
}

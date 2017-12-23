package ComparateurCode.Controleur;

import ComparateurCode.Modele.EcoleM;

public abstract class EchangeC {

    private int id;
    private String type;
    private EcoleM ecolePrincipale;
    private EcoleM ecoleEchange;


    // ----- setter
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEcolePrincipale(EcoleM ecolePrincipale) {
        this.ecolePrincipale = ecolePrincipale;
    }

    public void setEcoleEchange(EcoleM ecoleEchange) {
        this.ecoleEchange = ecoleEchange;
    }


    // ----- getter
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public EcoleM getEcolePrincipale() {
        return ecolePrincipale;
    }

    public EcoleM getEcoleEchange() {
        return ecoleEchange;
    }
}

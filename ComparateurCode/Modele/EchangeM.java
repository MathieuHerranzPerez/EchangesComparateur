package ComparateurCode.Modele;

/**
 * Peut-être à supprimer !
 */
public class EchangeM {
    private int id;
    private String type;
    private EcoleM ecolePrincipale;
    private EcoleM ecoleEchange;


    public EchangeM(int id, String type, EcoleM ecolePrincipale, EcoleM ecoleEchange) {
        this.id = id;
        this.type = type;
        this.ecolePrincipale = ecolePrincipale;
        this.ecoleEchange = ecoleEchange;
    }

    public EchangeM(int id, String type, EcoleM ecoleEchange) {
        this.id = id;
        this.type = type;
        this.ecoleEchange = ecoleEchange;
    }

    public int getId() { return id; }

    public EcoleM getEcolePrincipale() {
        return ecolePrincipale;
    }

    public EcoleM getEcoleEchange() {
        return ecoleEchange;
    }
}

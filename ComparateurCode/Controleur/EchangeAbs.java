package ComparateurCode.Controleur;

public abstract class EchangeAbs {

    private int id;
    private String type;
    private Ecole ecolePrincipale;
    private Ecole ecoleEchange;
    private Formation formation;


    // ----- setter
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEcolePrincipale(Ecole ecolePrincipale) {
        this.ecolePrincipale = ecolePrincipale;
    }

    public void setEcoleEchange(Ecole ecoleEchange) {
        this.ecoleEchange = ecoleEchange;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    // ----- getter
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Ecole getEcolePrincipale() {
        return ecolePrincipale;
    }

    public String getNomEcolePrincipale() {
        return ecolePrincipale.getNom();
    }

    public Formation getFormation() {
        return formation;
    }

    public Ecole getEcoleEchange() {
        return ecoleEchange;
    }

    public String getNomEcoleEchange() {
        return getEcoleEchange().getNom();
    }

    public String getNomLocalisationEcoleEchange() {
        return getEcoleEchange().getLocalisation().getNom();
    }

    public String getNomPaysEcoleEchange() {
        return getEcoleEchange().getLocalisation().getPays().getNom();
    }

    public String getNomForamtion() {
        return getFormation().getNom();
    }
}

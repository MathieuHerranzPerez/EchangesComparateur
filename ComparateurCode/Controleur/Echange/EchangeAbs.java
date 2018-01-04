package ComparateurCode.Controleur.Echange;

public abstract class EchangeAbs {

    private int id;
    //private int annee;
    private int duree;              // en mois
    private Ecole ecoleDepart;
    private Ecole ecoleArrivee;
    private Formation formation;

    // ----- setter
    public void setId(int id) {
        this.id = id;
    }

    /*public void setAnnee(int annee) {
        this.annee = annee;
    }*/

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setEcoleDepart(Ecole ecoleDepart) {
        this.ecoleDepart = ecoleDepart;
    }

    public void setEcoleArrivee(Ecole ecoleArrivee) {
        this.ecoleArrivee = ecoleArrivee;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    // ----- getter
    public int getId() {
        return id;
    }

   /* public int getAnnee() {
        return annee;
    }*/

    public int getDuree() {
        return duree;
    }

    public Ecole getEcoleDepart() {
        return ecoleDepart;
    }

    public String getNomEcolePrincipale() {
        return ecoleDepart.getNom();
    }

    public Formation getFormation() {
        return formation;
    }

    public Ecole getEcoleArrivee() {
        return ecoleArrivee;
    }

    public String getNomEcoleEchange() {
        return getEcoleArrivee().getNom();
    }

    public String getNomLocalisationEcoleEchange() {
        return getEcoleArrivee().getLocalisation().getNom();
    }

    public String getNomPaysEcoleEchange() {
        return getEcoleArrivee().getLocalisation().getPays().getNom();
    }

    public String getNomForamtion() {
        return getFormation().getNom();
    }

    public String getLangue() {
        return getFormation().getLangue();
    }

    public String getNomSousDomaine() {
        return formation.getSousDomaine().getNom();
    }

    public String getNomDomaine() {
        return formation.getSousDomaine().getDomaine().getNom();
    }
}

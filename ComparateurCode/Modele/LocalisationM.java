package ComparateurCode.Modele;

public class LocalisationM {

    private String lieu;
    private PaysM pays;

    public LocalisationM(String lieu, PaysM pays) {
        this.lieu = lieu;
        this.pays = pays;
    }

    public String getLieu() {
        return lieu;
    }

    public PaysM getPays() {
        return pays;
    }
}

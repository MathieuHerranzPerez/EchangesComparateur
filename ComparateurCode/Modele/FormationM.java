package ComparateurCode.Modele;

public class FormationM {

    private String nom;
    private int duree;


    public FormationM(String nom, int duree) {
        this.nom = nom;
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public int getDuree() {
        return duree;
    }
}

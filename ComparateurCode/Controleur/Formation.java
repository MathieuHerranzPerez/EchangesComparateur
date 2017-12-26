package ComparateurCode.Controleur;

public class Formation {

    private String nom;
    private int duree;
    private String langue;


    public Formation(String nom, int duree, String langue) {
        this.nom = nom;
        this.duree = duree;
        this.langue = langue;
    }

    public String getNom() {
        return nom;
    }

    public int getDuree() {
        return duree;
    }

    public String getLangue() {
        return langue;
    }
}

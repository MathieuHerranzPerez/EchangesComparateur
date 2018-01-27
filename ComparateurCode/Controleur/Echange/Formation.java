package ComparateurCode.Controleur.Echange;

public class Formation {

    private String nom;
    private int duree;
    private String langue;
    private SousDomaine sousDomaine;

    public Formation() {
        this.nom = "";
        this.duree = 0;
        this.langue = "";
        this.sousDomaine = new SousDomaine();
    }

    public Formation(SousDomaine sousDomaine) {
        this.nom = "";
        this.duree = 0;
        this.langue = "";
        this.sousDomaine = sousDomaine;
    }

    public Formation(int duree, SousDomaine sousDomaine) {
        this.nom = "";
        this.duree = duree;
        this.langue = "";
        this.sousDomaine = sousDomaine;
    }

    public Formation(String langue, SousDomaine sousDomaine) {
        this.nom = "";
        this.duree = 0;
        this.langue = langue;
        this.sousDomaine = sousDomaine;
    }

    public Formation(String langue, int duree, SousDomaine sousDomaine) {
        this.nom = "";
        this.duree = duree;
        this.langue = langue;
        this.sousDomaine = sousDomaine;
    }

    public Formation(String nom, int duree, String langue, SousDomaine sousDomaine) {
        this.nom = nom;
        this.duree = duree;
        this.langue = langue;
        this.sousDomaine = sousDomaine;
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

    public SousDomaine getSousDomaine() {
        return sousDomaine;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "nom='" + nom + '\'' +
                ", duree=" + duree +
                ", langue='" + langue + '\'' +
                ", sousDomaine=" + sousDomaine +
                '}';
    }
}

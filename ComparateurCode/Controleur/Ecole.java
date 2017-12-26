package ComparateurCode.Controleur;

public class Ecole {

    private String nom;
//    private ArrayList<Formation> formations;
    private Localisation localisation;


    public Ecole(String nom, Localisation localisation/*, ArrayList<Formation> formations*/) {
        this.nom = nom;
//        this.formations = formations;
        this.localisation = localisation;
    }

//    public Ecole(String nom, Localisation localisation, Formation ... formation) {
//        this.nom = nom;
//        for(Formation f : formation) {
//            formations.add(f);
//        }
//        this.localisation = localisation;
//    }

    public String getNom() {
        return nom;
    }

//    public ArrayList<Formation> getFormations() {
//        return formations;
//    }

    public Localisation getLocalisation() {
        return localisation;
    }
}

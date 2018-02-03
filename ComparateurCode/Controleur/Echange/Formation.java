package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.FormationM;

import java.util.ArrayList;

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

    public static ArrayList<String> getLangues() {
        return FormationM.getLangues();
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
       if(!(o instanceof Formation))
           return false;
       Formation formation = (Formation) o;
       if(!this.nom.equals(formation.getNom()) || this.duree != formation.getDuree() || !this.langue.equals(formation.getLangue()))
           return false;
       else if(!this.sousDomaine.equals(formation.getSousDomaine()))
           return false;

       return true;
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + duree;
        result = 31 * result + (langue != null ? langue.hashCode() : 0);
        result = 31 * result + (sousDomaine != null ? sousDomaine.hashCode() : 0);
        return result;
    }

    public static void ajouterFormation(Formation f) {
        // On vérifie qu'elle n'existe pas en BD
        if(!FormationM.isFormationInBD(f)) {
            // On ajoute le SousDomaine
            SousDomaine.ajouterSousDomaine(f.getSousDomaine());

            // On ajoute la formation
            FormationM.ajouterFormation(f);
        }
    }
}

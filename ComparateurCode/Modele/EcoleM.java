package ComparateurCode.Modele;

import java.util.ArrayList;

public class EcoleM {

    private String nom;
    private ArrayList<FormationM> formations;
    private LocalisationM localisation;


    public EcoleM(String nom, LocalisationM localisation, ArrayList<FormationM> formations) {
        this.nom = nom;
        this.formations = formations;
        this.localisation = localisation;
    }

    public EcoleM(String nom, LocalisationM localisation, FormationM ... formation) {
        this.nom = nom;
        for(FormationM f : formation) {
            formations.add(f);
        }
        this.localisation = localisation;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<FormationM> getFormations() {
        return formations;
    }

    public LocalisationM getLocalisation() {
        return localisation;
    }
}

package ComparateurCode.Controleur.Echange;

public class Echange extends EchangeAbs {

    public Echange(int id, /*int annee,*/ int duree, Ecole ecoleDepart, Ecole ecoleArrivee, Formation formation) {
        this.setId(id);
        /*this.setAnnee(annee);*/
        this.setDuree(duree);
        this.setEcoleDepart(ecoleDepart);
        this.setEcoleArrivee(ecoleArrivee);
        this.setFormation(formation);
    }
}

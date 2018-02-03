package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.DomaineM;

import java.util.ArrayList;

public class Domaine {

    private static ArrayList<Domaine> listDomaine = new ArrayList<>();

    private String nom;

    public Domaine(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }


    @Override
    public String toString() {
        return  nom;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Domaine)) return false;
        Domaine domaine = (Domaine) o;
        return this.nom.equals((domaine).getNom());
    }

    @Override
    public int hashCode() {
        return nom != null ? nom.hashCode() : 0;
    }

    public static ArrayList<Domaine> getListDomaine() {
        if(listDomaine.isEmpty()) {
            listDomaine = DomaineM.getDomaines();
        }
        return listDomaine;
    }

    /**
     * Si le domaine n'existe pas en BD, on l'ajoute
     * @param d Domaine, le domaine à ajouter en BD
     */
    public static void ajouterDomaine(Domaine d) {
        // On vérifie qu'il n'existe pas
        if(!DomaineM.isDomaineInBD(d)) {
            DomaineM.ajouterDomaine(d); // On l'ajoute
        }
    }


    public static void main(String[] args) {
        ArrayList<Domaine> tabDomaine = getListDomaine();

        for(Domaine d : tabDomaine)
            System.out.println(d.toString()+ "  / " + SousDomaine.getListSousDomaineFromDomaine(d));

        ArrayList<SousDomaine> tabSousDomaine = SousDomaine.getListSousDomaine();

        for(SousDomaine sousD : tabSousDomaine)
            System.out.println(sousD.toString() );

    }
}

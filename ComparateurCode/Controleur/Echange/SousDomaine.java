package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.SousDomaineM;

import java.util.ArrayList;

/**
 * Le Sous Domaine précise un Domaine
 */
public class SousDomaine {

    private static ArrayList<SousDomaine> listSousDomaine = new ArrayList<>();

    private String nom;
    private Domaine domaine; // id du nom de domaine

    public SousDomaine() {

        this.nom = "";
        this.domaine = null;
    }

    public SousDomaine(String nom, Domaine domaine) {

        this.nom = nom;
        this.domaine = domaine;
    }

    public String getNom() {
        return nom;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public static ArrayList<SousDomaine> getListSousDomaine() {
        if(listSousDomaine.isEmpty()) {
            listSousDomaine = SousDomaineM.getSousDomaines();
        }
        return listSousDomaine;
    }

    public static ArrayList<SousDomaine> getListSousDomaineFromDomaine(Domaine d) {
//        if(listSousDomaine.isEmpty()) {
//            listSousDomaine = SousDomaineM.getSousDomainesFromDomaine(d);
//        }
        return SousDomaineM.getSousDomainesFromDomaine(d);
    }


    @Override
    public boolean equals(Object o) {
        if(!(o instanceof SousDomaine))
            return false;

        SousDomaine sousDomaine = (SousDomaine) o;

        if(!this.nom.equals(sousDomaine.getNom()))
            return false;
        else if(!this.domaine.equals(sousDomaine.getDomaine()))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (domaine != null ? domaine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  nom;
    }

    /**
     * Ajoute, si pas déjà en base de données, le sous domaine d
     * @param d SousDomaine, le sous domaine à ajouter
     */
    public static void ajouterSousDomaine(SousDomaine d) {
        // On vérifie qu'il n'existe pas
        if(!SousDomaineM.isSousDomaineInBD(d)) {
            // On ajoute le Domaine
            Domaine.ajouterDomaine(d.getDomaine());
            // On ajoute le SousDomaine
            SousDomaineM.ajouterSousDomaine(d);
        }
    }
}

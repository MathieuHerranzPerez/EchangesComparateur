package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.SousDomaineM;

import java.util.ArrayList;

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
        if(listSousDomaine.isEmpty()) {
            listSousDomaine = SousDomaineM.getSousDomainesFromDomaine(d);
        }
        return listSousDomaine;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SousDomaine that = (SousDomaine) o;

        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        return domaine != null ? domaine.equals(that.domaine) : that.domaine == null;

    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (domaine != null ? domaine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SousDomaine{" +
                "nom='" + nom + '\'' +
                ", domaine=" + domaine +
                '}';
    }
}

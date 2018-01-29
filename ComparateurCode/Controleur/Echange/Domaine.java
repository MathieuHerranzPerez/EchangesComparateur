package ComparateurCode.Controleur.Echange;

import ComparateurCode.Modele.DomaineM;

import java.util.ArrayList;

public class Domaine {

    private static ArrayList<Domaine> listDomaine = new ArrayList<>();

    private int id;
    private String nom;

    public Domaine(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Domaine{" +
                "nom='" + nom + '\'' +
                '}';
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


    public static void main(String[] args) {
        ArrayList<Domaine> tabDomaine = getListDomaine();

        for(Domaine d : tabDomaine)
            System.out.println(d.toString()+ "  / " + SousDomaine.getListSousDomaineFromDomaine(d));

        ArrayList<SousDomaine> tabSousDomaine = SousDomaine.getListSousDomaine();

        for(SousDomaine sousD : tabSousDomaine)
            System.out.println(sousD.toString() );



    }



}

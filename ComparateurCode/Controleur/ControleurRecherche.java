package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.*;
import ComparateurCode.Vue.FenetreResultats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Créer le tableau final présenté à l'utilsiateur, et le trie suivant la fonction de comparaison
 * Suit le design pattern strategie
 */
public class ControleurRecherche {

    /**
     * Créer le tableau final présenté à l'utilsiateur et le trie suivant la fonction de comparaison, fait un appel à l'IHM
     * @param paysOrigine Pays
     * @param ecoleOrigine Ecole
     * @param paysSouhait Pays
     * @param villeSouhait String
     * @param langueSouhait String
     * @param domaineSouhait Domaine
     * @param sousDomaineSouhait SousDomaine
     * @param dureeEchange Integer
     */
    public static void sendRequest( Pays paysOrigine, Ecole ecoleOrigine,
            Pays paysSouhait, String villeSouhait, String langueSouhait, Domaine domaineSouhait,
            SousDomaine sousDomaineSouhait, Integer dureeEchange)
    {
        Comparateur comp;

        Ecole ecoleRef = new Ecole(new Localisation(villeSouhait, paysSouhait));

        String nomSousDomaine;
        if(sousDomaineSouhait == null)
            nomSousDomaine = null;
        else
            nomSousDomaine = sousDomaineSouhait.getNom();
        Formation formationRef = new Formation(langueSouhait,new SousDomaine(nomSousDomaine,domaineSouhait));

        Echange reference = new Echange(dureeEchange, ecoleOrigine, ecoleRef , formationRef);


        // on verifie si au moins un des champs facultatif a été rempli
        if(!villeSouhait.equals("") || langueSouhait != null || sousDomaineSouhait != null || dureeEchange != null) {
            // si oui alors stratégie de recherche complete
            comp = new BinaryCompare(reference);
        }
        else {
            // si non alors recherche rapide
            comp = new RapidCompare(reference);
        }

        ArrayList<Echange> listeEchange = Echange.getEchanges();
        Collections.sort(listeEchange, comp);

        // selection des 10 premiers elements
        ArrayList<Echange> echangesSelectionnes = new ArrayList<>();
        int i = 0;

        // selection des 10 premiers éléments ayant un résultat supérieur à 0 par rapport à la référence
        boolean estCoherent = true;
        while(estCoherent && i < 10 && i < listeEchange.size()) {
            //System.out.println("resultat : " + comp.comparer(listeEchange.get(i))); //affichageT
            if(comp.comparer(listeEchange.get(i)) >= 0)
                echangesSelectionnes.add(listeEchange.get(i));
            else
                estCoherent = false;
            ++i;
        }
        new FenetreResultats(echangesSelectionnes);
    }
}

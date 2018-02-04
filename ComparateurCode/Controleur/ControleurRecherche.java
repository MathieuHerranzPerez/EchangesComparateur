package ComparateurCode.Controleur;

import ComparateurCode.Controleur.Echange.*;
import ComparateurCode.Vue.FenetreResultats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ControleurRecherche {
    //TODO pour la ville comparer avec les localisations existantes dans controleurRecherche ville avec une marge derreur
    public static void sendRequest( Pays paysOrigine, Ecole ecoleOrigine, Domaine domaineOrigine,
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
            System.out.println("resultat : " + comp.compare(listeEchange.get(i), reference));
            if(comp.compare(listeEchange.get(i), reference) >= 0)
                echangesSelectionnes.add(listeEchange.get(i));
            else
                estCoherent = false;
            ++i;
        }
        new FenetreResultats(echangesSelectionnes);
    }
}

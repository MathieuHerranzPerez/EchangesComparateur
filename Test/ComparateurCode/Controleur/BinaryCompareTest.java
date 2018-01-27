//package ComparateurCode.Controleur;
//
//import ComparateurCode.Controleur.Echange.*;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//import static org.junit.Assert.*;
//
//public class BinaryCompareTest {
//
//    @Test
//    public void testComparer() throws Exception {
//        // situation utilisateur
//            // d'où il vient
//        Pays france = new Pays("France");
//        Localisation localisationNull = new Localisation(france);
//        Ecole ecoleUti = new Ecole("Polytech", localisationNull);
//
//        SousDomaine info = new SousDomaine(new Domaine("Informatique"));
//        Formation formationInfo = new Formation(info);
//            // ce qu'il veut
//        Pays angleterre = new Pays("Angleterre");
//        // il n'a pas choisie de ville
//        Localisation localisationAngleterre = new Localisation(angleterre);
//        Ecole ecoleSouhaite = new Ecole(localisationAngleterre);
//
//        SousDomaine infoJeux = new SousDomaine("Jeux-video", new Domaine("Informatique"));
//        Formation formationVoulue = new Formation("Anglais", infoJeux);
//
//        Echange echangeParfait = new Echange(-1, 12, ecoleUti, ecoleSouhaite, formationVoulue);
//
//        // -------- ecole des echanges trouvées --------
//        Localisation localisationM = new Localisation("Marseille", france);
//        Ecole ecoleDep = new Ecole("Polytech", localisationM);
//
//        Localisation localisationA = new Localisation("Londres", angleterre);
//        Ecole ecoleArr = new Ecole("ecoleAnglaise", localisationA);
//
//        SousDomaine infoBD = new SousDomaine("BD", new Domaine("Informatique"));
//        Formation formationBD = new Formation("Anglais", infoBD);
//
//        Echange premierEchange = new Echange(1, 12, ecoleDep, ecoleArr, formationVoulue);
//        Echange deuxiemeEchange = new Echange(2, 12, ecoleDep, ecoleArr, formationBD);
//
//        ArrayList<Echange> ar = new ArrayList<>();
//
//        ar.add(deuxiemeEchange);
//        ar.add(premierEchange);
//
//        Collections.sort(ar, new BinaryCompare(echangeParfait));
//
//        for(Echange e : ar) {
//            System.out.println(e);
//        }
//        fail("");
//    }
//}

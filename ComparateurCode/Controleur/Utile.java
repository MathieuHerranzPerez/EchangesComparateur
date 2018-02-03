package ComparateurCode.Controleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Utile {
    public static Vector<String> getLangues() {
        ArrayList<String> res = new ArrayList<>();
        res.add("Anglais");
        res.add("Arabe");
        res.add("Chinois");
        res.add("Espagnol");
        res.add("Francais");
        res.add("Russe");
        res.add("Albanais");
        res.add("Allemend");
        res.add("Amazigh");
        res.add("Arménien");
        res.add("Aymara");
        res.add("Bengali");
        res.add("Catalan");
        res.add("Coreen");
        res.add("Croate");
        res.add("Danois");
        res.add("Finnois");
        res.add("Guarani");
        res.add("Grec");
        res.add("Hongrois");
        res.add("Italien");
        res.add("Kiswajili");
        res.add("Malais");
        res.add("Mongol");
        res.add("Néerlandais");
        res.add("Occitan");
        res.add("Ourdou");
        res.add("Persan");
        res.add("Portugais");
        res.add("Quechua");
        res.add("Roumain");
        res.add("Samoan");
        res.add("Serbe");
        res.add("Sesotho");
        res.add("Slovaque");
        res.add("Slovène");
        res.add("Suédois");
        res.add("Tamoul");
        res.add("Turc");
        res.add("Biélorusse");
        res.add("Bulgare");
        res.add("Géorgien");
        res.add("Hébreu");
        res.add("Irlandais");
        res.add("Islandais");
        res.add("Japonsais");
        res.add("Latin");
        res.add("Letton");
        res.add("Lituanien");
        res.add("Luxembourgeois");
        res.add("Macédonien");
        res.add("Malgache");
        res.add("Maori");
        res.add("Népalais");
        res.add("Norvégien");
        res.add("Polonais");
        res.add("Thaï");
        res.add("Ukrainien");

        Collections.sort(res);
        return new Vector<>(res);
    }
}

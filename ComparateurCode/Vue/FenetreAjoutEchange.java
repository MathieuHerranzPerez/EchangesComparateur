package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Echange;
import ComparateurCode.Controleur.Echange.Ecole;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class FenetreAjoutEchange extends JFrame{
    private JTextField nomFormation = new JTextField(20);
    private JComboBox<Integer> dureeFormation;
    private JComboBox<String> langue;
    private JTextField nomSousDomaine = new JTextField(20);
    private JTextField nomDomaine = new JTextField(20);


    private JComboBox<Ecole> ecoleDep;
    private JComboBox<Ecole> ecoleArr;
    private JComboBox<Integer> duree;

    private JButton annuler = new JButton("Annuler");
    private JButton valider = new JButton("Valider");

    public FenetreAjoutEchange() {
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(700,500);


        JPanel p = new JPanel();
        this.add(p);

        /* ------ colonne Echange ------*/

        p.add(new JLabel("Ecole de départ "));
        ecoleDep = new JComboBox<>(new Vector<Ecole>(Ecole.getListEcole()));
        p.add(ecoleDep);

        p.add(new JLabel("Ecole d'arrivée "));
        ecoleArr = new JComboBox<>(new Vector<Ecole>(Ecole.getListEcole()));
        p.add(ecoleArr);

        p.add(new JLabel("Durée de l'échange (en mois) "));
        Integer[] annees = new Integer[18];
        for(int i = 1; i < 19; ++i){
            annees[i-1] = i;
        }
        duree = new JComboBox<>(annees);
        p.add(duree);

        /* ------ colonne Formation ------*/

        p.add(new JLabel("Formation "));
        p.add(nomFormation);

        p.add(new JLabel("Durée de la formation totale (en mois) "));
        annees = new Integer[60];
        for(int i = 1; i < 61; ++i){
            annees[i-1] = i;
        }
        dureeFormation = new JComboBox<>(annees);
        p.add(dureeFormation);

        p.add(new JLabel("Langue "));
        langue = new JComboBox<>(getLangues());
        p.add(langue);

        p.add(new JLabel("Nom du domaine "));
        p.add(nomDomaine);

        p.add(new JLabel("Nom du sous domaine"));
        p.add(nomSousDomaine);

        p.add(annuler);
        p.add(valider);

        annuler.addActionListener(new ClicAnnuler(this));
        valider.addActionListener(new ClicValider(this));

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private boolean champsCorrectes() {
        boolean condEcole = (ecoleDep.getSelectedItem() != null && ecoleArr.getSelectedItem() != null) &&
                !(ecoleDep.getSelectedItem().equals(ecoleArr.getSelectedItem()));
        boolean condFormation = !nomDomaine.getText().equalsIgnoreCase("") && !nomSousDomaine.getText().equals("") &&
                !nomFormation.getText().equals("");
        if(condEcole && condFormation)
            return true;
        else
            return false;
    }

    /**
     * Retourne sur la page d'administration
     */
    private class ClicAnnuler implements ActionListener {
        private JFrame f;
        public ClicAnnuler(JFrame f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame fen = new FenetreAdmin();
            f.dispose();
        }
    }

    private class ClicValider implements ActionListener {
        private JFrame f;
        public ClicValider(JFrame f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            // On vérifie les champs
            if(champsCorrectes()) {
                Echange.ajouterEchange((Ecole) ecoleDep.getSelectedItem(), (Ecole) ecoleArr.getSelectedItem(), nomFormation.getText(), (Integer) duree.getSelectedItem(),(Integer) dureeFormation.getSelectedItem(), (String) langue.getSelectedItem(),
                        nomSousDomaine.getText(), nomDomaine.getText());
                FenetreAdmin fen = new FenetreAdmin();
                f.dispose();
            }
            else {
                JFrame fenErreur = new FenetreErreur("Veuillez bien remplir les champs obligatoires");
            }
        }
    }

    private Vector<String> getLangues() {
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

    public static void main(String[] argv) {
        new FenetreAjoutEchange();
    }
}

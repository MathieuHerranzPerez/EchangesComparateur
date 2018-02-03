package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Echange;
import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Utile;

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
        langue = new JComboBox<>(Utile.getLangues());
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

    public static void main(String[] argv) {
        new FenetreAjoutEchange();
    }
}

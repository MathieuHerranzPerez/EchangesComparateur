package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Echange;
import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Utile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class FenetreModifierEchange extends JFrame {

    private Echange oldEchange;

    private JTextField nomFormation;
    private JComboBox<Integer> dureeFormation;
    private JComboBox<String> langue;
    private JTextField nomSousDomaine;
    private JTextField nomDomaine;


    private JComboBox<Ecole> ecoleDep;
    private JComboBox<Ecole> ecoleArr;
    private JComboBox<Integer> duree;

    private JButton annuler = new JButton("Annuler");
    private JButton valider = new JButton("Valider");

    public FenetreModifierEchange(Echange oldEchange) {
        this.oldEchange = oldEchange;
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(700,500);


        JPanel p = new JPanel();
        this.add(p);

        /* ------ colonne Echange ------*/

        p.add(new JLabel("Ecole de départ "));
        ecoleDep = new JComboBox<>(new Vector<Ecole>(Ecole.getListEcole()));
        ecoleDep.setSelectedItem(oldEchange.getEcoleDepart());
        p.add(ecoleDep);

        p.add(new JLabel("Ecole d'arrivée "));
        ecoleArr = new JComboBox<>(new Vector<Ecole>(Ecole.getListEcole()));
        ecoleArr.setSelectedItem(oldEchange.getEcoleArrivee());
        p.add(ecoleArr);

        p.add(new JLabel("Durée de l'échange (en mois) "));
        Integer[] annees = new Integer[18];
        for(int i = 1; i < 19; ++i){
            annees[i-1] = i;
        }
        duree = new JComboBox<>(annees);
        duree.setSelectedItem(oldEchange.getDuree());
        p.add(duree);

        /* ------ colonne Formation ------*/

        p.add(new JLabel("Formation "));
        nomFormation = new JTextField(oldEchange.getFormation().toString(), 20);
        p.add(nomFormation);

        p.add(new JLabel("Durée de la formation totale (en mois) "));
        annees = new Integer[60];
        for(int i = 1; i < 61; ++i){
            annees[i-1] = i;
        }
        dureeFormation = new JComboBox<>(annees);
        dureeFormation.setSelectedItem(oldEchange.getFormation().getDuree());
        p.add(dureeFormation);

        p.add(new JLabel("Langue "));
        langue = new JComboBox<>(Utile.getLangues());
        langue.setSelectedItem(oldEchange.getFormation().getLangue());
        p.add(langue);

        p.add(new JLabel("Nom du domaine "));
        nomDomaine = new JTextField(oldEchange.getFormation().getSousDomaine().getDomaine().toString(), 20);
        p.add(nomDomaine);

        p.add(new JLabel("Nom du sous domaine"));
        nomSousDomaine = new JTextField(oldEchange.getFormation().getSousDomaine().toString(), 20);
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

    private class ClicAnnuler implements ActionListener {
        private FenetreModifierEchange f;
        public ClicAnnuler(FenetreModifierEchange f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Echange.parcourirEchange();
            f.dispose();
        }
    }

    private class ClicValider implements ActionListener {
        private FenetreModifierEchange f;
        public ClicValider(FenetreModifierEchange f) {
            this.f = f;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // On vérifie les champs
            if(champsCorrectes()) {
                Echange.modifierEchange(oldEchange, (Integer) duree.getSelectedItem(), nomFormation.getText(), (Integer) dureeFormation.getSelectedItem(),
                        langue.getSelectedItem().toString(), nomSousDomaine.getText(), nomDomaine.getText(),
                        (Ecole) ecoleDep.getSelectedItem(), (Ecole) ecoleArr.getSelectedItem());
                Echange.parcourirEchange();
                f.dispose();
            }
            else {
                JFrame fenErreur = new FenetreErreur("Veuillez bien remplir les champs obligatoires");
            }
        }
    }
}

package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Echange;
import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Utile;

import javax.swing.*;
import java.awt.*;
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
        this.setSize(900,300);
        this.setLayout(new BorderLayout());
        JPanel content = new JPanel();
        this.add(content, BorderLayout.CENTER);

        content.setLayout(new GridLayout(1,2));

        /* ------------ colonne Echange ------------*/
        JPanel pLeft = new JPanel();
        content.add(pLeft);
        GridBagConstraints gbc = new GridBagConstraints();
        pLeft.setLayout(new GridBagLayout());

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        // Labels
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,10);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pLeft.add(new JLabel("Ecole de départ "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pLeft.add(new JLabel("Ecole d'arrivée "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pLeft.add(new JLabel("Durée de l'échange (en mois) "), gbc);


        // Fields
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx = 2;
        gbc.gridy = 1;
        ecoleDep = new JComboBox<>(new Vector<Ecole>(Ecole.getListEcole()));
        ecoleDep.setSelectedItem(oldEchange.getEcoleDepart());
        pLeft.add(ecoleDep, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        ecoleArr = new JComboBox<>(new Vector<Ecole>(Ecole.getListEcole()));
        ecoleArr.setSelectedItem(oldEchange.getEcoleArrivee());
        pLeft.add(ecoleArr, gbc);

        Integer[] annees = new Integer[18];
        for(int i = 1; i < 19; ++i){
            annees[i-1] = i;
        }
        gbc.gridx = 2;
        gbc.gridy = 3;
        duree = new JComboBox<>(annees);
        duree.setSelectedItem(oldEchange.getDuree());
        pLeft.add(duree, gbc);


        /* ------ colonne Formation ------*/
JPanel pRight = new JPanel();
content.add(pRight);
GridBagConstraints gbc2 = new GridBagConstraints();
pRight.setLayout(new GridBagLayout());

gbc2.weightx = 0.5;
gbc2.weighty = 0.5;

        // Labels
        gbc2.anchor = GridBagConstraints.LINE_END;
        gbc2.insets = new Insets(0,0,0,10);

        gbc2.gridx = 1;
        gbc2.gridy = 1;
        pRight.add(new JLabel("Formation "), gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 2;
        pRight.add(new JLabel("Durée de la formation totale (en mois) "), gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 3;
        pRight.add(new JLabel("Langue "), gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 4;
        pRight.add(new JLabel("Nom du domaine "), gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 5;
        pRight.add(new JLabel("Nom du sous domaine"), gbc2);

        // Fields
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.LINE_START;

        gbc2.gridx = 2;
        gbc2.gridy = 1;
        nomFormation = new JTextField(oldEchange.getFormation().toString(), 20);
        pRight.add(nomFormation, gbc2);

        annees = new Integer[60];
        for(int i = 1; i < 61; ++i){
            annees[i-1] = i;
        }
        gbc2.gridx = 2;
        gbc2.gridy = 2;
        dureeFormation = new JComboBox<>(annees);
        dureeFormation.setSelectedItem(oldEchange.getFormation().getDuree());
        pRight.add(dureeFormation, gbc2);

        gbc2.gridx = 2;
        gbc2.gridy = 3;
        langue = new JComboBox<>(Utile.getLangues());
        langue.setSelectedItem(oldEchange.getFormation().getLangue());
        pRight.add(langue, gbc2);

        gbc2.gridx = 2;
        gbc2.gridy = 4;
        nomDomaine = new JTextField(oldEchange.getFormation().getSousDomaine().getDomaine().toString(), 20);
        pRight.add(nomDomaine, gbc2);


        gbc2.gridx = 2;
        gbc2.gridy = 5;
        nomSousDomaine = new JTextField(oldEchange.getFormation().getSousDomaine().toString(), 20);
        pRight.add(nomSousDomaine, gbc2);

        JPanel panelButton = new JPanel();
        panelButton.add(annuler);
        panelButton.add(valider);
        this.add(panelButton, BorderLayout.SOUTH);

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

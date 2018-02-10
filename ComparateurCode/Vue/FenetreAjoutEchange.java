package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Echange;
import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Utile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * Fenetre avec les champs pour ajouter un echange
 */
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
        pLeft.add(ecoleDep, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        ecoleArr = new JComboBox<>(new Vector<Ecole>(Ecole.getListEcole()));
        pLeft.add(ecoleArr, gbc);

        Integer[] annees = new Integer[18];
        for(int i = 1; i < 19; ++i){
            annees[i-1] = i;
        }
        gbc.gridx = 2;
        gbc.gridy = 3;
        duree = new JComboBox<>(annees);
        pLeft.add(duree, gbc);


        /* ------------ colonne Formation ------------*/
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
        pRight.add(nomFormation, gbc2);

        annees = new Integer[60];
        for(int i = 1; i < 61; ++i){
            annees[i-1] = i;
        }
        gbc2.gridx = 2;
        gbc2.gridy = 2;
        dureeFormation = new JComboBox<>(annees);
        pRight.add(dureeFormation, gbc2);

        gbc2.gridx = 2;
        gbc2.gridy = 3;
        langue = new JComboBox<>(Utile.getLangues());
        pRight.add(langue, gbc2);

        gbc2.gridx = 2;
        gbc2.gridy = 4;
        pRight.add(nomDomaine, gbc2);

        gbc2.gridx = 2;
        gbc2.gridy = 5;
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

    /**
     * Vérifie si les champs obligatoires ont été renseignés (tous les champs)
     * @return boolean
     */
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
     * Retourne sur la page d'administration, et ferme celle-ci
     * @see FenetreAdmin
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

    /**
     * Vérifie si les champs sont corrects, ajoute l'échange, retourne sur la FenetreAdmin, et ferme celle-i
     * Sinon, créer une FenetreErreur demandant de bien remplir les champs obligatoires
     * @see FenetreAdmin
     * @see FenetreErreur
     */
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
}

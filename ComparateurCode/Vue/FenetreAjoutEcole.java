package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Pays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Fenetre avec les champs pour ajouter une école
 */
public class FenetreAjoutEcole extends JFrame {

    private JTextField nom = new JTextField(20);
    private JTextField ville = new JTextField(20);
    private JComboBox<Pays> paysList;

    private JButton annuler = new JButton("Annuler");
    private JButton valider = new JButton("Valider");

    public FenetreAjoutEcole() {
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(350,350);
        this.setLayout(new BorderLayout());

        JPanel p = new JPanel();
        this.add(p, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        p.setLayout(new GridBagLayout());

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        // Labels
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        p.add(new JLabel("Nom "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        p.add(new JLabel("Ville "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        p.add(new JLabel("Pays "), gbc);

        /* Fields */
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx = 2;
        gbc.gridy = 1;
        p.add(nom, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        p.add(ville, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        paysList = new JComboBox<>(new Vector<Pays>(Pays.getPays()));
        p.add(paysList, gbc);

        this.add(p);

        JPanel panelBottom = new JPanel();
        panelBottom.add(annuler);
        panelBottom.add(valider);
        this.add(panelBottom, BorderLayout.SOUTH);

        annuler.addActionListener(new ClicAnnuler(this));
        valider.addActionListener(new ClicValider(this));

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Verifie si les champs obligatoires ont été renseignés
     * @return vrai ou faux
     */
    private boolean champsCorrectes() {
        if(!(nom.getText().equals("")) && !(ville.getText().equals("")) && !(paysList.getSelectedItem() == null))
            return true;
        else
            return false;
    }

    /**
     * Retourne sur la page d'administration, et ferme celle-ci
     * @see FenetreAdmin
     */
    private class ClicAnnuler implements ActionListener {
        private FenetreAjoutEcole f;
        public ClicAnnuler(FenetreAjoutEcole f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame fen = new FenetreAdmin();
            f.dispose();
        }
    }

    /**
     * Vérifie les champs, ajoute l'ecole et retourne sur la page d'aministration, ferme celle-ci
     * Sinon, affiche une FenetreErreur
     * @see FenetreAdmin
     * @see FenetreErreur
     */
    private class ClicValider implements ActionListener {

        private FenetreAjoutEcole f;
        public ClicValider(FenetreAjoutEcole f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(f.champsCorrectes()) {
                Ecole.ajouterEcole(nom.getText(), ville.getText(), (Pays) paysList.getSelectedItem());
                FenetreAdmin fen = new FenetreAdmin();
                f.dispose();
            }
            else {
                JFrame fenErreur = new FenetreErreur("Veuillez remplir les champs obligatoires");
            }
        }
    }
}

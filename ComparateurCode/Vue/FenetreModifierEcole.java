package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Pays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Fenetre qui charge l'échange actuel afin de le modifier
 */
public class FenetreModifierEcole extends JFrame {

    private Ecole oldEcole;

    private JTextField nom;
    private JTextField ville = new JTextField(20);
    private JComboBox<Pays> paysList;

    private JButton annuler = new JButton("Annuler");
    private JButton valider = new JButton("Valider");


    public FenetreModifierEcole(Ecole oldEcole) {
        this.oldEcole = oldEcole;

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
        this.nom = new JTextField(oldEcole.toString(), 20);
        p.add(nom, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        this.ville = new JTextField(oldEcole.getLocalisation().toString(), 20);
        p.add(ville, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        paysList = new JComboBox<>(new Vector<Pays>(Pays.getPays()));
        paysList.setSelectedItem(oldEcole.getLocalisation().getPays());
        p.add(paysList, gbc);

        this.add(p);

        JPanel panelBottom = new JPanel();
        panelBottom.add(annuler);
        panelBottom.add(valider);
        this.add(panelBottom, BorderLayout.SOUTH);


        annuler.addActionListener(new FenetreModifierEcole.ClicAnnuler(this));
        valider.addActionListener(new FenetreModifierEcole.ClicValider(this));

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Vérifie si les champs ont bien été modifiés
     * @return boolean
     */
    private boolean champsCorrectes() {
        if(!(nom.getText().equals("")) && !(ville.getText().equals("")) && !(paysList.getSelectedItem() == null))
            return true;
        else
            return false;
    }

    /**
     * Revient sur la FenetreParcourirEcole en passant par le Controleur, et ferme celle-ci
     */
    private class ClicAnnuler implements ActionListener {
        private FenetreModifierEcole f;
        public ClicAnnuler(FenetreModifierEcole f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Ecole.parcourirEcole();
            f.dispose();
        }
    }

    /**
     * Vérifie les champs, modifie l'école, revient sur la page FenetreParcourirEcole, et ferme celle-ci
     * Sinon, affiche FenetreErreur
     * @see FenetreErreur
     */
    private class ClicValider implements ActionListener {
        private FenetreModifierEcole f;
        public ClicValider(FenetreModifierEcole f) {
            this.f = f;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (f.champsCorrectes()) {
                Ecole.modifierEcole(oldEcole, nom.getText(), ville.getText(), (Pays) paysList.getSelectedItem());
                Ecole.parcourirEcole();
                f.dispose();
            }
            else {
                JFrame fenErreur = new FenetreErreur("Veuillez remplir les champs obligatoires");
            }
        }
    }


}

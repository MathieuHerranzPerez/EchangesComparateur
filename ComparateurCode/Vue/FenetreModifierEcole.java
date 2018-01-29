package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Pays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class FenetreModifierEcole extends JFrame {

    private String oldNom;
    private String oldVille;
    private Pays oldPays;

    private JTextField nom;
    private JTextField ville = new JTextField(20);
    private JComboBox<Pays> paysList;

    private JButton annuler = new JButton("Annuler");
    private JButton valider = new JButton("Valider");

    /**
     * On a besoin dans le constructeur du nom, de la localisation et du Pays de l'échange que l'on s'apprete à modifier
     * @param oldNom
     * @param oldVille
     * @param oldPays
     */
    public FenetreModifierEcole(String oldNom, String oldVille, Pays oldPays) {
        this.oldNom = oldNom;
        this.oldVille = oldVille;
        this.oldPays = oldPays;

        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(700,500);

        JPanel p = new JPanel();
        this.add(p);
        p.add(new JLabel("Nom "), BorderLayout.CENTER);
        this.nom = new JTextField(oldNom, 20);
        p.add(nom, BorderLayout.CENTER);

        p.add(new JLabel("Ville "), BorderLayout.CENTER);
        this.ville = new JTextField(oldVille, 20);
        p.add(ville, BorderLayout.CENTER);

        p.add(new JLabel("Pays "), BorderLayout.CENTER);
        paysList = new JComboBox<>(new Vector<Pays>(Pays.getPays()));
        paysList.setSelectedItem(oldPays);
        p.add(paysList, BorderLayout.CENTER);

        p.add(annuler, BorderLayout.SOUTH);
        p.add(valider, BorderLayout.SOUTH);

        annuler.addActionListener(new FenetreModifierEcole.ClicAnnuler(this));
        valider.addActionListener(new FenetreModifierEcole.ClicValider(this));

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private class ClicAnnuler implements ActionListener {
        private FenetreModifierEcole f;
        public ClicAnnuler(FenetreModifierEcole f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame fen = new FenetreAdmin();
            f.dispose();
        }
    }

    private class ClicValider implements ActionListener {
        private FenetreModifierEcole f;
        public ClicValider(FenetreModifierEcole f) {
            this.f = f;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Ecole.modifierEcole(oldNom, oldVille, oldPays, nom.getText(), ville.getText(), (Pays) paysList.getSelectedItem());
            FenetreParcourirEcole fen = new FenetreParcourirEcole();
            f.dispose();
        }
    }

    public static void main(String[] argv) {
        FenetreModifierEcole f = new FenetreModifierEcole("Polytech M", "Marseille", new Pays("France"));
    }
}

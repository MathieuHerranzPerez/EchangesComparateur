package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Pays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
        this.setSize(700,500);

        JPanel p = new JPanel();
        this.add(p);
        p.add(new JLabel("Nom "), BorderLayout.CENTER);
        this.nom = new JTextField(oldEcole.toString(), 20);
        p.add(nom, BorderLayout.CENTER);

        p.add(new JLabel("Ville "), BorderLayout.CENTER);
        this.ville = new JTextField(oldEcole.getLocalisation().toString(), 20);
        p.add(ville, BorderLayout.CENTER);

        p.add(new JLabel("Pays "), BorderLayout.CENTER);
        paysList = new JComboBox<>(new Vector<Pays>(Pays.getPays()));
        paysList.setSelectedItem(oldEcole.getLocalisation().getPays());
        p.add(paysList, BorderLayout.CENTER);

        p.add(annuler, BorderLayout.SOUTH);
        p.add(valider, BorderLayout.SOUTH);

        annuler.addActionListener(new FenetreModifierEcole.ClicAnnuler(this));
        valider.addActionListener(new FenetreModifierEcole.ClicValider(this));

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private boolean champsCorrectes() {
        if(!(nom.getText().equals("")) && !(ville.getText().equals("")) && !(paysList.getSelectedItem() == null))
            return true;
        else
            return false;
    }

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

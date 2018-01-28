package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Pays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class FenetreAjoutEcole extends JFrame {

    private JTextField nom = new JTextField(20);
    private JTextField ville = new JTextField(20);
    private JComboBox<Pays> paysList;

    private JButton annuler = new JButton("Annuler");
    private JButton valider = new JButton("Valider");

    public FenetreAjoutEcole() {
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(700,500);


        JPanel p = new JPanel();
        this.add(p);
        p.add(new JLabel("Nom "), BorderLayout.CENTER);
        p.add(nom, BorderLayout.CENTER);

        p.add(new JLabel("Ville "), BorderLayout.CENTER);
        p.add(ville, BorderLayout.CENTER);

        p.add(new JLabel("Pays "), BorderLayout.CENTER);
        paysList = new JComboBox<>(new Vector<Pays>(Pays.getPays()));
        p.add(paysList, BorderLayout.CENTER);

        p.add(annuler, BorderLayout.SOUTH);  //TODO listener
        p.add(valider, BorderLayout.SOUTH);  //TODO listener

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

    private class ClicAnnuler implements ActionListener {
        private FenetreAjoutEcole f;
        public ClicAnnuler(FenetreAjoutEcole f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //JFrame fen = new FenetreAdmin(); TODO
            f.dispose();
        }
    }

    private class ClicValider implements ActionListener {

        private FenetreAjoutEcole f;
        public ClicValider(FenetreAjoutEcole f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(f.champsCorrectes()) {
                Ecole.ajouterEcole(nom.getText(), ville.getText(), (Pays) paysList.getSelectedItem());
            }
            else {
                JFrame fenErreur = new FenetreErreur("Veuillez remplir les champs obligatoires");
            }
        }
    }

    public static void main(String[] argv) {
        JFrame f = new FenetreAjoutEcole();
    }
}

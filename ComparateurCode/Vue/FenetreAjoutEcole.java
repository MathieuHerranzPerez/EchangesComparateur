package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Domaine;
import ComparateurCode.Controleur.Echange.Pays;

import javax.swing.*;
import java.awt.*;

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
        paysList = new JComboBox<>(Pays.tabPays());
        p.add(paysList, BorderLayout.CENTER);

        p.add(annuler, BorderLayout.SOUTH);  //TODO listener
        p.add(valider, BorderLayout.SOUTH);  //TODO listener

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] argv) {
        JFrame f = new FenetreAjoutEcole();
    }
}

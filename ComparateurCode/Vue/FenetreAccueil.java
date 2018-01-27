package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Domaine;
import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Pays;

import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame {

    private JMenuBar menu = new JMenuBar();
    private JButton admin = new JButton("Admin");

    private JLabel situationA = new JLabel("SituationActuel");
    private JLabel pays = new JLabel("Pays");
    private JComboBox<Pays> paysList = new JComboBox<>();
    private JLabel universite = new JLabel("Université de départ");
    private JComboBox<Ecole> ecoleList = new JComboBox<>();
    private JLabel domaine = new JLabel("Domaine");
    private JComboBox<Domaine> domaineList = new JComboBox<>();

    private JButton annuler = new JButton("Annuler");
    private JButton valider = new JButton("Valider");


    private JLabel souhait = new JLabel("Souhait");


    public FenetreAccueil() {
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(700,500);

        this.add(menu);
        menu.add(admin);    //TODO listener

        this.add(situationA, BorderLayout.WEST);
        this.add(pays, BorderLayout.WEST);
        this.add(paysList, BorderLayout.WEST); //TODO remplir
        this.add(universite, BorderLayout.WEST);
        this.add(ecoleList, BorderLayout.WEST); //TODO remplir
        this.add(domaine, BorderLayout.WEST);
        this.add(domaineList, BorderLayout.WEST); //TODO remplir


        this.add(souhait, BorderLayout.CENTER);



        this.add(annuler, BorderLayout.SOUTH);  //TODO listener
        this.add(valider, BorderLayout.SOUTH);  //TODO listener

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

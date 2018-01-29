package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Domaine;
import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Pays;
import ComparateurCode.Controleur.Echange.SousDomaine;

import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame {

    private JMenuBar menu = new JMenuBar();
    private JButton admin = new JButton("Admin");

    private JLabel situationA = new JLabel("Situation actuelle");
    private JLabel pays = new JLabel("Pays");
    private JComboBox<Pays> paysList = new JComboBox<>();
    private JLabel universite = new JLabel("Université de départ");
    private JComboBox<Ecole> ecoleList = new JComboBox<>();
    private JLabel domaine = new JLabel("DomaineM");
    private JComboBox<Domaine> domaineList = new JComboBox<>();

    private JLabel souhait = new JLabel("Souhait");
    private JLabel paysSouhait = new JLabel("Pays souhaité");
    private JComboBox<Pays> paysListSouhait = new JComboBox<>();
    private JLabel VilleSouhaitée = new JLabel("Ville souhaitée");
    private JTextField villeTF = new JTextField();
    private JLabel langue = new JLabel("Langue");
    private JComboBox<Domaine> langueList = new JComboBox<>();
    private JLabel domaineSouhait = new JLabel("DomaineM souhaité");
    private JComboBox<Domaine> domaineListSouhait = new JComboBox<>();
    private JLabel ssDomaineSouhait = new JLabel("Sous domaine souhaité");
    private JComboBox<SousDomaine> ssDomaineListSouhait = new JComboBox<>();
    private JLabel duree = new JLabel("Durée");
    private JComboBox<SousDomaine> dureelist = new JComboBox<>();

    private JButton annuler = new JButton("Annuler");
    private JButton valider = new JButton("Valider");


    public FenetreAccueil() {
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(750,300);

        this.setJMenuBar(menu);
        menu.add(admin);    //TODO listener

        this.setLayout(new BorderLayout());

        JPanel ResearchPanel = new JPanel();
        ResearchPanel.setLayout(new GridLayout(1,2));

        JPanel panelLeft = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        panelLeft.setLayout(new GridBagLayout());


        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        // Labels
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelLeft.add(situationA, gbc);

        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelLeft.add(pays, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panelLeft.add(universite, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panelLeft.add(domaine, gbc);


        // Fields
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx = 2;
        gbc.gridy = 2;
        panelLeft.add(paysList, gbc); //TODO remplir

        gbc.gridx = 2;
        gbc.gridy = 4;
        panelLeft.add(ecoleList, gbc); //TODO remplir

        gbc.gridx = 2;
        gbc.gridy = 6;
        panelLeft.add(domaineList, gbc); //TODO remplir


        /*
        Panel Right
         */

        JPanel panelRight = new JPanel();
        GridBagConstraints gbcR = new GridBagConstraints();
        panelRight.setLayout(new GridBagLayout());

        gbcR.weightx = 0.5;
        gbcR.weighty = 0.5;
        gbcR.insets = new Insets(0,0,0,10);

        gbcR.anchor = GridBagConstraints.LINE_END;

        gbcR.gridx = 1;
        gbcR.gridy = 1;
        panelRight.add(souhait,gbcR);

        // Labels
        gbcR.weighty = 0.5;
        gbcR.gridx = 1;
        gbcR.gridy = 2;
        panelRight.add(paysSouhait,gbcR);

        gbcR.gridx = 1;
        gbcR.gridy = 3;
        panelRight.add(VilleSouhaitée,gbcR);

        gbcR.gridx = 1;
        gbcR.gridy = 4;
        panelRight.add(langue,gbcR);

        gbcR.gridx = 1;
        gbcR.gridy = 5;
        panelRight.add(domaineSouhait,gbcR);

        gbcR.gridx = 1;
        gbcR.gridy = 6;
        panelRight.add(ssDomaineSouhait,gbcR);

        gbcR.gridx = 1;
        gbcR.gridy = 7;
        panelRight.add(duree,gbcR);


        // Fields
        gbcR.fill = GridBagConstraints.HORIZONTAL;
        gbcR.anchor = GridBagConstraints.LINE_START;

        gbcR.gridx = 2;
        gbcR.gridy = 2;
        panelRight.add(paysListSouhait,gbcR);

        gbcR.gridx = 2;
        gbcR.gridy = 3;
        panelRight.add(villeTF,gbcR);

        gbcR.gridx = 2;
        gbcR.gridy = 4;
        panelRight.add(langueList,gbcR);

        gbcR.gridx = 2;
        gbcR.gridy = 5;
        panelRight.add(domaineListSouhait,gbcR);

        gbcR.gridx = 2;
        gbcR.gridy = 6;
        panelRight.add(ssDomaineListSouhait,gbcR);

        gbcR.gridx = 2;
        gbcR.gridy = 7;
        panelRight.add(dureelist,gbcR);

        ResearchPanel.add(panelLeft);
        ResearchPanel.add(panelRight);
        add(ResearchPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setSize(this.getWidth(), 50);
        bottomPanel.add(annuler);  //TODO listener
        bottomPanel.add(valider);  //TODO listener
        add(bottomPanel, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FenetreAccueil();
    }
}

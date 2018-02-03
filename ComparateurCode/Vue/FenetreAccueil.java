package ComparateurCode.Vue;

import ComparateurCode.Controleur.ControleurRecherche;
import ComparateurCode.Controleur.Echange.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class FenetreAccueil extends JFrame {

    private JMenuBar menu = new JMenuBar();
    private JButton admin = new JButton("Admin");

    private JLabel situationA = new JLabel("Situation actuelle");
    private JLabel pays = new JLabel("Pays *");
    private JComboBox<Pays> paysList = new JComboBox<>();
    private JLabel universite = new JLabel("Université de départ *");
    private JComboBox<Ecole> ecoleList = new JComboBox<>();
    private JLabel domaine = new JLabel("DomaineM");
    private JComboBox<Domaine> domaineList = new JComboBox<>();

    private JLabel souhait = new JLabel("Souhait");
    private JLabel paysSouhait = new JLabel("Pays souhaité *");
    private JComboBox<Pays> paysListSouhait = new JComboBox<>();
    private JLabel VilleSouhaitée = new JLabel("Ville souhaitée");
    private JTextField villeTF = new JTextField();
    private JLabel langue = new JLabel("Langue");
    private JComboBox<String> langueList = new JComboBox<>();
    private JLabel domaineSouhait = new JLabel("Domaine souhaité *");
    private JComboBox<Domaine> domaineListSouhait = new JComboBox<>();
    private JLabel ssDomaineSouhait = new JLabel("Sous domaine souhaité");
    private JComboBox<SousDomaine> ssDomaineListSouhait = new JComboBox<>();
    private JLabel duree = new JLabel("Durée (en mois)");
    private JComboBox<Integer> dureelist = new JComboBox<>();

    private JLabel info = new JLabel("Les champs avec une * sont obligatoires");

    private JButton annuler = new JButton("Annuler");
    private JButton valider = new JButton("Valider");

//TODO POUR LES JCOMBOBOX LAISSER LA POSSIBLE DE CHOISIR "VIDE" SI LUTILISATEUR NE SOUHAITE PAS RENSENGNER UN CHAMPS OBLIGATOIRE
//TODO signaler par une * les champs qui sont facultatifs
    public FenetreAccueil() {
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(850,350);

        this.setJMenuBar(menu);
        menu.add(admin);
        admin.addActionListener(new ClicAdmin(this));

        this.setLayout(new BorderLayout());

        /*______________Initialise le contenu des JComboBox______________*/
        // pour chaque JComboBox facultatives la premiere case est vide pour laisser le choix à l'utilisateur de ne pas renseigner le champs

        // Pays depart
        ArrayList<Pays> listPays = Pays.getPays();
        System.out.println(listPays);
        for(Pays p : listPays)
            paysList.addItem(p);
        paysList.addItemListener(new PaysListener()); // écoute le choix du pays pour déterminer les écoles à afficher

        // Universite depart
        ArrayList<Ecole> listEcoleDep = Ecole.getEcolesFromPays((Pays) paysList.getSelectedItem());
        for(Ecole e : listEcoleDep)
            ecoleList.addItem(e);

        // Domaine
        ArrayList<Domaine> listDomaine = Domaine.getListDomaine();
        domaineList.addItem(null);
        for(Domaine d : listDomaine)
            domaineList.addItem(d);

        // Pays souhaite
        ArrayList<Pays> listPaysUtilises = Pays.getPaysUtilises();
        paysListSouhait.addItem(null);
        for(Pays p : listPaysUtilises) {
            paysListSouhait.addItem(p);
        }
        paysList.addItemListener(new PaysListener()); // écoute le choix du pays pour déterminer les écoles à afficher


        // Ville souhaite
        //text field donc rien à faire

        // langue
        ArrayList<String> listLangue = Formation.getLangues();
        langueList.addItem(null);
        for(String l: listLangue)
            langueList.addItem(l);

        // Domaine souhaite
        for(Domaine d : listDomaine)
            domaineListSouhait.addItem(d);
        domaineListSouhait.addItemListener(new DomaineListener());

        // Sous domaine
        ArrayList<SousDomaine> listSousDomaine = SousDomaine.getListSousDomaineFromDomaine((Domaine) domaineListSouhait.getSelectedItem());
        ssDomaineListSouhait.addItem(null);
        for(SousDomaine d : listSousDomaine)
            ssDomaineListSouhait.addItem(d);

        // duree (celle de l'échange)
        dureelist.addItem(null);
        for(int i = 1; i < 25; ++i) {
            dureelist.addItem(i);
        }


        /*______________Action Bouton______________*/

        annuler.addActionListener(new AnnulerListener());
        valider.addActionListener(new ValiderListener());

        admin.addActionListener(new AdminListener(this));


        /*______________Creation Panel______________*/

        JPanel ResearchPanel = new JPanel();
        ResearchPanel.setLayout(new GridLayout(1,2));

        JPanel panelLeft = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        panelLeft.setLayout(new GridBagLayout());

        /*______________Panel Left______________*/

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
        panelLeft.add(paysList, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        panelLeft.add(ecoleList, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        panelLeft.add(domaineList, gbc);

        /* Info saisies */
        info.setForeground(Color.red);
        gbc.gridx = 2;
        gbc.gridy = 9;
        panelLeft.add(info,gbc);

        /*______________Panel Right______________*/

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

    private class ClicAdmin implements ActionListener {
        JFrame f;
        public ClicAdmin(JFrame f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            FenetreConnexion fen = new FenetreConnexion();
            f.dispose();
        }
    }

    private class DomaineListener implements ItemListener {
        ArrayList<SousDomaine> listSousDomaine;

        public DomaineListener() {
            super();
            listSousDomaine = new ArrayList<>();
        }
        @Override
        public void itemStateChanged(ItemEvent e) {
            // si on choisi un domaine permet de choisir un sous domaine qui en fait partie
            listSousDomaine.clear();
            ssDomaineListSouhait.removeAllItems();
            listSousDomaine = SousDomaine.getListSousDomaineFromDomaine((Domaine) domaineListSouhait.getSelectedItem());
            ssDomaineListSouhait.addItem(null);
            for(SousDomaine d : listSousDomaine) {
                ssDomaineListSouhait.addItem(d);
            }
        }
    }

    private class PaysListener implements ItemListener {
        ArrayList<Ecole> listEcolePays;

        public PaysListener() {
            super();
            this.listEcolePays = new ArrayList<>();
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            // si on choisi un pays permet de choisir une école qui en fait partie
            listEcolePays.clear();
            ssDomaineListSouhait.removeAllItems();
            listEcolePays = Ecole.getEcolesFromPays((Pays) e.getItem());
            for(Ecole ecole : listEcolePays)
                ecoleList.addItem(ecole);

        }
    }

    private class ValiderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // On vérifie les champs obligatoires
            if(paysList.getSelectedItem() != null && ecoleList.getSelectedItem() != null &&
                    paysListSouhait.getSelectedItem() != null && domaineListSouhait.getSelectedItem() != null) {
                // envoie de tous les champs au controleur

                ControleurRecherche.sendRequest((Pays) paysList.getSelectedItem(),
                    (Ecole) ecoleList.getSelectedItem(),
                    (Domaine) domaineList.getSelectedItem(),
                    (Pays) paysListSouhait.getSelectedItem(),
                    (String) villeTF.getText(),
                    (String) langueList.getSelectedItem(),
                    (Domaine) domaineListSouhait.getSelectedItem(),
                    (SousDomaine) ssDomaineListSouhait.getSelectedItem(),
                    (Integer) dureelist.getSelectedItem());
            }
            else {
                FenetreErreur fen = new FenetreErreur("Veuillez remplir les champs obligatoires (notés avec *)");
            }
        }
    }

    private class AnnulerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class AdminListener implements ActionListener {
        JFrame f;

        public AdminListener(JFrame f) {
            this.f = f;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
            new FenetreAdmin();
        }
    }

    public static void main(String[] args) {
        new FenetreAccueil();
    }
}

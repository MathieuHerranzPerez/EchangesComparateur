package ComparateurCode.Vue;


import ComparateurCode.Controleur.Echange.Echange;
import ComparateurCode.Controleur.Echange.Ecole;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;

public class FenetreAdmin extends JFrame{

    public FenetreAdmin() {
        super();

        this.setSize(450, 200);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.getContentPane().add(panel, BorderLayout.WEST);

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ClicRetour(this));
        panel.add(btnRetour);

        PanelAdmin panel_1 = new PanelAdmin(this);
        this.getContentPane().add(panel_1, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setVisible(true);

    }

    private class PanelAdmin extends JPanel {
        private JFrame f;

        public PanelAdmin(JFrame f) {

            this.f = f;
            GridBagLayout gbl1 = new GridBagLayout();
            this.setLayout(gbl1);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(0, 0, 5, 5);

            JButton btnAjouterUnecole = new JButton("Ajouter une école");
            btnAjouterUnecole.addActionListener(new ClicAjoutEcole());
            gbc.gridx = 2;
            gbc.gridy = 1;
            this.add(btnAjouterUnecole, gbc);

            JButton btnAjouterUnchange = new JButton("Ajouter un échange");
            btnAjouterUnchange.addActionListener(new ClicAjoutEchange());
            gbc.gridx = 3;
            gbc.gridy = 1;
            this.add(btnAjouterUnchange, gbc);

            JButton btnParcourirLescoles = new JButton("Parcourir les écoles");
            btnParcourirLescoles.addActionListener(new ClicParcourirEcole());
            gbc.gridx = 2;
            gbc.gridy = 3;
            this.add(btnParcourirLescoles, gbc);

            JButton btnParcourirLeschanges = new JButton("Parcourir les échanges");
            btnParcourirLeschanges.addActionListener(new ClicParcourirEchange());
            gbc.gridx = 3;
            gbc.gridy = 3;
            this.add(btnParcourirLeschanges, gbc);
        }

        private class ClicAjoutEcole implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreAjoutEcole fen = new FenetreAjoutEcole();
                f.dispose();
            }
        }

        private class ClicAjoutEchange implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreAjoutEchange fen = new FenetreAjoutEchange();
                f.dispose();
            }
        }

        private class ClicParcourirEcole implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Ecole.parcourirEcole();
                f.dispose();
            }
        }

        private class ClicParcourirEchange implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Echange.parcourirEchange(); //TODO
                f.dispose();
            }
        }
    }

    private class ClicRetour implements ActionListener {
        private JFrame f;
        public ClicRetour(JFrame f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            FenetreAccueil fen = new FenetreAccueil();
            f.dispose();
        }
    }

    public static void main(String[] args) {
        new FenetreAdmin();
    }
}

package ComparateurCode.Vue;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

public class FenetreAdmin extends JFrame{

    public FenetreAdmin() {
        super();

        this.setSize(450, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.getContentPane().add(panel, BorderLayout.WEST);

        JButton btnRetour = new JButton("Retour");
        panel.add(btnRetour);

        PanelAdmin panel_1 = new PanelAdmin();
        this.getContentPane().add(panel_1, BorderLayout.CENTER);

        this.setVisible(true);

    }

    private class PanelAdmin extends JPanel {
        public PanelAdmin() {

            GridBagLayout gbl1 = new GridBagLayout();
            this.setLayout(gbl1);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(0, 0, 5, 5);

            JButton btnAjouterUnecole = new JButton("Ajouter une école");
            gbc.gridx = 2;
            gbc.gridy = 1;
            this.add(btnAjouterUnecole, gbc);

            JButton btnAjouterUnchange = new JButton("Ajouter un échange");
            gbc.gridx = 3;
            gbc.gridy = 1;
            this.add(btnAjouterUnchange, gbc);

            JButton btnParcourirLescoles = new JButton("Parcourir les écoles");
            gbc.gridx = 2;
            gbc.gridy = 3;
            this.add(btnParcourirLescoles, gbc);

            JButton btnParcourirLeschanges = new JButton("Parcourir les échanges");
            gbc.gridx = 3;
            gbc.gridy = 3;
            this.add(btnParcourirLeschanges, gbc);
        }
    }

    public static void main(String[] args) {
        new FenetreAdmin();
    }

}

package ComparateurCode.Vue;

import ComparateurCode.Controleur.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenetre de connexion à l'interface d'administration
 */
public class FenetreConnexion extends JFrame {

    private Panneau p = new Panneau(this);

    public FenetreConnexion() {
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(500,150);

        this.add(p);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private class Panneau extends JPanel {
        private  JFrame f;
        private JLabel ident = new JLabel("Identifiant");
        private JLabel mdp = new JLabel("Mot de passe");
        private JTextField jtfIdent = new JTextField(20);
        private JPasswordField jpfMdp = new JPasswordField(20);

        private JButton valider = new JButton("Valider");
        private JButton annuler = new JButton("Annuler");

        private Panneau(JFrame f) {
            this.f = f;
            this.setLayout(new BorderLayout());

            JPanel panelConnexion = new JPanel();
            GridBagConstraints gbc = new GridBagConstraints();
            panelConnexion.setLayout(new GridBagLayout());

            gbc.weightx = 0.5;
            gbc.weighty = 0.5;

            // Labels
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.insets = new Insets(0,0,0,10);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panelConnexion.add(ident, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            panelConnexion.add(mdp, gbc);

            // Fields
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.LINE_START;

            gbc.gridx = 2;
            gbc.gridy = 1;
            panelConnexion.add(jtfIdent, gbc);

            gbc.gridx = 2;
            gbc.gridy = 2;
            panelConnexion.add(jpfMdp, gbc);

            this.add(panelConnexion, BorderLayout.CENTER);

            JPanel panelSouth = new JPanel();
            panelSouth.add(annuler);
            annuler.addActionListener(new ClicAnnuler(f));
            valider.addActionListener(new ClicValider(f));
            panelSouth.add(valider);
            add(panelSouth, BorderLayout.SOUTH);
        }

        private class ClicAnnuler implements ActionListener {
            private JFrame f;
            public ClicAnnuler(JFrame f) {
                this.f = f;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreAccueil fen = new FenetreAccueil();
                f.dispose();
            }
        }

        private class ClicValider implements ActionListener {
            private JFrame f;
            public ClicValider(JFrame f) {
                this.f = f;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Admin.isBonsIdentifiants(jtfIdent.getText(), String.valueOf(jpfMdp.getPassword()))) {
                    FenetreAdmin fen = new FenetreAdmin();
                    f.dispose();
                }
                else {
                    FenetreErreur fen = new FenetreErreur("Identifiants Incorrects");
                }
            }
        }
    }
}

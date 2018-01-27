package ComparateurCode.Vue;

import javax.swing.*;
import java.awt.*;

public class FenetreConnexion extends JFrame {

    private Panneau p = new Panneau();

    public FenetreConnexion() {
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(700,500);

        this.add(p);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class Panneau extends JPanel {
        private JLabel ident = new JLabel("Identifiant");
        private JLabel mdp = new JLabel("Mot de passe");
        private JTextField jtfIdent = new JTextField(20);
        private JPasswordField jpfMdp = new JPasswordField(20);

        private JButton valider = new JButton("Valider");
        private JButton annuler = new JButton("Annuler");

        private Panneau() {
            this.add(ident, BorderLayout.CENTER);
            this.add(jtfIdent, BorderLayout.CENTER);
            this.add(mdp, BorderLayout.CENTER);
            this.add(jpfMdp, BorderLayout.CENTER);

            this.add(annuler, BorderLayout.SOUTH);  //TODO listener
            this.add(valider, BorderLayout.SOUTH);  //TODO listener
        }
    }
}

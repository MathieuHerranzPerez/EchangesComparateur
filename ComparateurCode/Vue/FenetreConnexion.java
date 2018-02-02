package ComparateurCode.Vue;

import javax.swing.*;
import java.awt.*;

public class FenetreConnexion extends JFrame {

    private Panneau p = new Panneau();

    public FenetreConnexion() {
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(500,150);

        this.add(p);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
            this.setLayout(new BorderLayout());


            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(ident);
            panel.add(jtfIdent);
            panel.add(mdp);
            panel.add(jpfMdp);

            this.add(panel, BorderLayout.CENTER);

            JPanel panelSouth = new JPanel();
            panelSouth.add(annuler);  //TODO listener
            panelSouth.add(valider);  //TODO listener
            add(panelSouth, BorderLayout.SOUTH);
        }
    }

    public static void main(String[] args) {
        new FenetreConnexion();
    }
}

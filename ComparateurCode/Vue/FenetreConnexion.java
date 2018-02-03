package ComparateurCode.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(ident);
            panel.add(jtfIdent);
            panel.add(mdp);
            panel.add(jpfMdp);

            this.add(panel, BorderLayout.CENTER);

            JPanel panelSouth = new JPanel();
            panelSouth.add(annuler);
            annuler.addActionListener(new ClicAnnuler(f));
            panelSouth.add(valider);  //TODO listener
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
    }

    public static void main(String[] args) {
        new FenetreConnexion();
    }
}

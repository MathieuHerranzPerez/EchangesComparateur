package ComparateurCode.Vue;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class FenetreErreur extends JFrame {

    public FenetreErreur(String message) {
        this.setTitle("Erreur");
        //this.setSize(250,150);

        JPanel p = new PanneauMessage(message);
        this.add(p, BorderLayout.CENTER);

        JButton b = new JButton("Ok");
        b.addActionListener(new ClicFermer(this));
        this.add(b, BorderLayout.SOUTH);

        int largeur = message.length() * 6 + 80;

        this.setSize(largeur, 150);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private class PanneauMessage extends JPanel {
        private String message;
        public PanneauMessage(String message) {
            this.message = message;
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;

            g2D.drawString(message, 40, 40);
        }
    }

    private class ClicFermer implements ActionListener {

        private JFrame f;
        public ClicFermer(JFrame f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
        }
    }
}

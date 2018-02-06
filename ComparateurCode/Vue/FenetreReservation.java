package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Echange;
import ComparateurCode.Controleur.Echange.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Permet à l'utilisateur de réserver un échange, en exprimant ses coordonnées
 */
public class FenetreReservation extends JFrame {

    private Echange echange;

    private JLabel labelNom = new JLabel("Nom *");
    private JTextField jtfNom = new JTextField(20);
    private JLabel labelPrenom = new JLabel("Prenom *");
    private JTextField jtfPrenom = new JTextField(20);
    private JLabel labelTelephone = new JLabel("Telephone *");
    private JTextField jtfTelephone = new JTextField(10);

    private JLabel labelAutre = new JLabel("Informations complétaires");
    private JTextArea jtaAutre = new JTextArea(10, 30);

    private JButton reserver = new JButton("Reserver");
    private JButton retour = new JButton("Retour");

    public FenetreReservation(Echange e) {

        this.echange = e;
        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(700,300);
        this.setLayout(new BorderLayout());

        JPanel p = new JPanel();
        this.add(p, BorderLayout.CENTER);

        p.add(labelNom);
        p.add(jtfNom);
        p.add(labelPrenom);
        p.add(jtfPrenom);
        p.add(labelTelephone);
        p.add(jtfTelephone);

        p.add(labelAutre);
        p.add(jtaAutre);

        reserver.addActionListener(new ClicReserver(this));
        p.add(reserver, BorderLayout.CENTER);
        retour.addActionListener(new ClicRetour(this));
        this.add(retour, BorderLayout.SOUTH);


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Après vérification des champs obligatoires, ajoute la réservation en BD
     * Sinon, affiche FenetreErreur
     * @see FenetreErreur
     */
    private class ClicReserver implements ActionListener {

        private JFrame f;
        public ClicReserver(JFrame f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            // On vérifie que les champs obligatoires ne sont pas vide
            if(!jtfNom.getText().equals("") && !jtfPrenom.getText().equals("") && !jtfTelephone.getText().equals("")) {
                Reservation.ajouterReservation(echange, jtfNom.getText(), jtfPrenom.getText(), jtfTelephone.getText(), jtaAutre.getText());
                f.dispose();
            }
            else {
                FenetreErreur fen = new FenetreErreur("Veuillez saisir tous les champs requis");
            }
        }
    }

    /**
     * Ferme la fenetre
     */
    private class ClicRetour implements ActionListener {

        private JFrame f;
        public ClicRetour(JFrame f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
        }
    }
}

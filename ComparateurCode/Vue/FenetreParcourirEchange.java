package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Echange;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Affiche dans un tableau les échanges passés en parametre
 */
public class FenetreParcourirEchange extends JFrame {
    private Object[][] echanges;
    JFrame f;

    public FenetreParcourirEchange(ArrayList<Echange> e) {
        this.setLayout(new BorderLayout());

        JTable tableau = new JTable(new TableauEchange(e, this));
        tableau.getColumn("Modifier").setCellRenderer(new FenetreParcourirEchange.ButtonRenderer());
        tableau.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());

        tableau.addMouseListener(new TableauClic(tableau));

        JScrollPane scroll = new JScrollPane(tableau);
        this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(1200,500);

        JButton bRetour = new JButton("Retour");
        bRetour.addActionListener(new ClicRetour(this));
        this.add(bRetour, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        f = this;
        this.toBack();
        this.setVisible(true);
    }

    private class TableauEchange extends AbstractTableModel {
        private String[] entetes = {"EcoleDep", "EcoleArr", "Localisation", "Pays", "Formation", "Langue", "Sous Domaine", "Domaine", "Modifier", "Supprimer"};
        public TableauEchange(ArrayList<Echange> e, JFrame f) {
            echanges = new Object[e.size()][10];

            for(int i = 0; i < e.size(); ++i) {
                echanges[i][0] = e.get(i).getEcoleDepart();
                echanges[i][1] = e.get(i).getEcoleArrivee();
                echanges[i][2] = e.get(i).getEcoleArrivee().getLocalisation();
                echanges[i][3] = e.get(i).getEcoleArrivee().getLocalisation().getPays();

                echanges[i][4] = e.get(i).getFormation().toString();
                echanges[i][5] = e.get(i).getLangue();
                echanges[i][6] = e.get(i).getFormation().getSousDomaine();
                echanges[i][7] = e.get(i).getFormation().getSousDomaine().getDomaine();

                JButton bAdd = new JButton("Modifier");
                bAdd.addActionListener(new ClicModifier(e.get(i)));
                echanges[i][8] = bAdd;
                JButton bSupp = new JButton("Supprimer");
                bSupp.addActionListener(new ClicSuppr(e.get(i), f));
                echanges[i][9] = bSupp;
            }
        }

        @Override
        public int getRowCount() {
            return echanges.length;
        }

        @Override
        public int getColumnCount() {
            return entetes.length;
        }

        public String getColumnName(int columnIndex) {
            return entetes[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return echanges[rowIndex][columnIndex];
        }
    }

    public class ButtonRenderer extends JButton implements TableCellRenderer
    {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if(value instanceof JButton) {
                return (JButton) value;
            }
            return this;
        }
    }

    private class TableauClic extends MouseAdapter {
        private JTable tableau;

        public TableauClic(JTable tableau) {
            this.tableau = tableau;
        }
        @Override
        public void mouseClicked(MouseEvent evt) {
            int row = tableau.rowAtPoint(evt.getPoint());
            int col = tableau.columnAtPoint(evt.getPoint());
            Object value = tableau.getValueAt(row, col);
            if(value instanceof JButton) {
                ((JButton) value).doClick();
            }
        }
    }

    /**
     * Créer une FenetreModifierEchange avec l'échange en parametre, et ferme celle-ci
     */
    private class ClicModifier implements ActionListener {

        private Echange echange;

        public ClicModifier(Echange e) {
            this.echange = e;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            FenetreModifierEchange fen = new FenetreModifierEchange(echange);
            f.dispose();
        }
    }

    /**
     * Supprime l'échange, et actualise la Fenetre
     */
    private class ClicSuppr implements ActionListener {

        private Echange echange;
        private JFrame frame;

        public ClicSuppr(Echange e, JFrame f) {
            this.echange = e;
            this.frame = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Echange.supprimerEchange(echange);
            // mettre a jour la table (refaire la fenetre)
            rechargerPage();
            this.frame.dispose();
        }

        public void rechargerPage() {
            Echange.parcourirEchange();
        }
    }

    /**
     * Revient sur la FenetreAdmin et ferme celle-ci
     * @see FenetreAdmin
     */
    private class ClicRetour implements ActionListener {

        private JFrame f;
        public ClicRetour(JFrame f) {
            this.f = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            FenetreAdmin fen = new FenetreAdmin();
            this.f.dispose();
        }
    }
}

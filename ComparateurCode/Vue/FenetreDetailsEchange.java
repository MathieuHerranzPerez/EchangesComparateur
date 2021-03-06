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

/**
 * Fenetre qui donne les details sur un echange
 */
public class FenetreDetailsEchange extends JFrame {

    Object[] echangeTab;
    Echange echange;
    JFrame f;

    public FenetreDetailsEchange(Echange echange) {
        this.echange = echange;
        JTable tableau = new JTable(new TableauEchange(echange, this));
        tableau.getColumn("Réserver").setCellRenderer(new ButtonRenderer());

        tableau.addMouseListener(new TableauClic(tableau));

        JScrollPane scroll = new JScrollPane(tableau);
        this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(1200,100);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        f = this;
        this.toBack();
        this.setVisible(true);
    }

    private class TableauEchange extends AbstractTableModel {
        private String[] entetes = {"Ecole de départ", "Ecole d'arrivée", "Durée (mois)", "Localisation", "Pays", "Formation", "Langue", "Sous Domaine", "Domaine", "Réserver"};

        public TableauEchange(Echange e, JFrame f) {
            echangeTab = new Object[10];

            echangeTab[0] = e.getEcoleDepart();
            echangeTab[1] = e.getEcoleArrivee();
            echangeTab[2] = e.getDuree();
            echangeTab[3] = e.getEcoleArrivee().getLocalisation();
            echangeTab[4] = e.getEcoleArrivee().getLocalisation().getPays();
            echangeTab[5] = e.getFormation();
            echangeTab[6] = e.getLangue();

            echangeTab[7] = e.getFormation().getSousDomaine();
            echangeTab[8] = e.getFormation().getSousDomaine().getDomaine();

            JButton bReserver = new JButton("Réserver");
            bReserver.addActionListener(new ClicReserver(e));
            echangeTab[9] = bReserver;
        }

        @Override
        public int getRowCount() {
            return 1;
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
            return echangeTab[columnIndex];
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
     * Appelle la page FenetreReservation, et ferme celle-ci
     * @see FenetreReservation
     */
    private class ClicReserver implements ActionListener {

        private Echange echange;

        public ClicReserver(Echange e) {
            this.echange = e;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            FenetreReservation fen = new FenetreReservation(echange);
            f.dispose();
        }
    }
}

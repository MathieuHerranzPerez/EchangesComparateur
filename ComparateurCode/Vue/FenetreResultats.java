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

public class FenetreResultats extends JFrame {

    private JButton retour = new JButton("Retour aux critères");
    private Object[][] echanges;
    JFrame f;

    public FenetreResultats(ArrayList<Echange> resultatsEchange) {
        JTable tableau = new JTable(new TableauEchange(resultatsEchange, this));
        tableau.getColumn("Details").setCellRenderer(new ButtonRenderer());

        tableau.addMouseListener(new TableauClic(tableau));

        JScrollPane scroll = new JScrollPane(tableau);
        this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(1200,250);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        f = this;
        this.toBack();
        this.setVisible(true);
    }

    private class TableauEchange extends AbstractTableModel {
        private String[] entetes = {"Classement", "Ecole", "Localisation", "Pays", "Formation", "Langue", "Sous Domaine", "Domaine", "Details"};

        public TableauEchange(ArrayList<Echange> e, JFrame f) {
            echanges = new Object[e.size()][9];

            for (int i = 0; i < e.size(); ++i) {
                echanges[i][0] = i + 1;
                echanges[i][1] = e.get(i).getEcoleArrivee();
                echanges[i][2] = e.get(i).getEcoleArrivee().getLocalisation();
                echanges[i][3] = e.get(i).getEcoleArrivee().getLocalisation().getPays();

                echanges[i][4] = e.get(i).getFormation().toString();
                echanges[i][5] = e.get(i).getLangue();
                echanges[i][6] = e.get(i).getFormation().getSousDomaine();
                echanges[i][7] = e.get(i).getFormation().getSousDomaine().getDomaine();

                JButton bDetails = new JButton("Details");
                bDetails.addActionListener(new ClicDetails(e.get(i)));
                echanges[i][8] = bDetails;
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

    private class ClicDetails implements ActionListener {

        private Echange echange;

        public ClicDetails(Echange e) {
            this.echange = e;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            FenetreDetailsEchange fen = new FenetreDetailsEchange(echange);
        }
    }
}

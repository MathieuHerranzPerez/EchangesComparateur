package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Ecole;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FenetreParcourirEcole extends JFrame {

    private Object[][] ecoles;
    JFrame f;

    public FenetreParcourirEcole(ArrayList<Ecole> e) {

        JTable tableau = new JTable(new TableauEcole(e, this));
        tableau.getColumn("Modifier").setCellRenderer(new ButtonRenderer());
        tableau.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());

        tableau.addMouseListener(new TableauClic(tableau));

        JScrollPane scroll = new JScrollPane(tableau);
        this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        this.setTitle("Comparateur d'échanges universitaires");
        this.setSize(800,500);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        f = this;
        this.setVisible(true);
    }

    private class TableauEcole extends AbstractTableModel {
        private String[] entetes = {"Ecole", "Localisation", "Pays", "Modifier", "Supprimer"};
        public TableauEcole(ArrayList<Ecole> e, JFrame f) {
            ecoles = new Object[e.size()][5];

            for(int i = 0; i < e.size(); ++i) {
                ecoles[i][0] = e.get(i);
                ecoles[i][1] = e.get(i).getLocalisation();
                ecoles[i][2] = e.get(i).getLocalisation().getPays();

                JButton bAdd = new JButton("Modifier");
                bAdd.addActionListener(new ClicDetails(e.get(i)));
                ecoles[i][3] = bAdd;
                JButton bSupp = new JButton("Supprimer");
                bSupp.addActionListener(new ClicSuppr(e.get(i), f));
                ecoles[i][4] = bSupp;
            }
        }

        @Override
        public int getRowCount() {
            return ecoles.length;
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
            return ecoles[rowIndex][columnIndex];
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

        private Ecole ecole;

        public ClicDetails(Ecole e) {
            this.ecole = e;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            FenetreModifierEcole fen = new FenetreModifierEcole(ecole);
            f.dispose();
        }
    }

    private class ClicSuppr implements ActionListener {

        private Ecole ecole;
        private JFrame frame;

        public ClicSuppr(Ecole e, JFrame f) {
            this.ecole = e;
            this.frame = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Ecole.supprimerEcole(ecole);
            // mettre a jour la table (refaire la fenetre)
            rechargerPage();
            this.frame.dispose();
        }

        public void rechargerPage() {
            Ecole.parcourirEcole();
        }
    }

    public static void main(String[] argv) {
        Ecole.parcourirEcole();
    }
}

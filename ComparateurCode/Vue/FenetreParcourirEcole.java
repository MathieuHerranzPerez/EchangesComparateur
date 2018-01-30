package ComparateurCode.Vue;

import ComparateurCode.Controleur.Echange.Ecole;
import ComparateurCode.Controleur.Echange.Pays;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FenetreParcourirEcole extends JFrame {

    private Object[][] ecoles;
    JFrame f;

    public FenetreParcourirEcole(String[] ecole, String[] localisation, Pays[] pays) {

        JTable tableau = new JTable(new TableauEcole(ecole, localisation, pays));
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
        public TableauEcole(String[] ecole, String[] localisation, Pays[] pays) {
            ecoles = new Object[ecole.length][5];

            for(int i = 0; i < ecole.length; ++i) {
                ecoles[i][0] = ecole[i];
                ecoles[i][1] = localisation[i];
                ecoles[i][2] = pays[i];

                JButton bAdd = new JButton("Modifier");
                bAdd.addActionListener(new ClicDetails(ecole[i], localisation[i], pays[i]));
                ecoles[i][3] = bAdd;
                JButton bSupp = new JButton("Supprimer");
                bSupp.addActionListener(new ClicSuppr(ecole[i], localisation[i], pays[i]));
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

        private String ecole;
        private String localisation;
        private Pays pays;

        public ClicDetails(String ecole, String localisation, Pays pays) {
            this.ecole = ecole;
            this.localisation = localisation;
            this.pays = pays;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("ecole : " + ecole + " loc : " + localisation + " pays " + pays);    //TODO enlever
            FenetreModifierEcole fen = new FenetreModifierEcole(ecole, localisation, pays);
            f.dispose();
        }
    }

    private class ClicSuppr implements ActionListener {

        private String ecole;
        private String localisation;
        private Pays pays;

        public ClicSuppr(String ecole, String localisation, Pays pays) {
            this.ecole = ecole;
            this.localisation = localisation;
            this.pays = pays;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("ecole : " + ecole + " loc : " + localisation + " pays " + pays);    //TODO enlever
        }
    }

    public static void main(String[] argv) {
        Ecole.parcourirEcole();
    }
}

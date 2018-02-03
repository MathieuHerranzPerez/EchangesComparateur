package ComparateurCode.Modele;

import ComparateurCode.Controleur.Echange.Echange;
import ComparateurCode.Controleur.Echange.Ecole;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 * Peut-être à supprimer !
 */
public class EchangeM {
//    private int id;
//    private String type;
//    private Ecole ecolePrincipale;
//    private Ecole ecoleEchange;
//
//
//    public EchangeM(int id, String type, Ecole ecolePrincipale, Ecole ecoleEchange) {
//        this.id = id;
//        this.type = type;
//        this.ecolePrincipale = ecolePrincipale;
//        this.ecoleEchange = ecoleEchange;
//    }
//
//    public EchangeM(int id, String type, Ecole ecoleEchange) {
//        this.id = id;
//        this.type = type;
//        this.ecoleEchange = ecoleEchange;
//    }
//
//    public int getId() { return id; }
//
//    public Ecole getEcolePrincipale() {
//        return ecolePrincipale;
//    }
//
//    public Ecole getEcoleEchange() {
//        return ecoleEchange;
//    }

    private static TreeMap<Integer, Echange> treeMapEchange = new TreeMap<>();

    /**
     * Met à jour le treeMap graçe à la BD, return un arrayList de Location (ou null)
     * @return
     */
    public static ArrayList<Echange> getEchanges() {
        Statement state = null;
        try {
            state = ConnexionBD.getInstance().createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM ECHANGE");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            ArrayList<Echange> res = new ArrayList<>();
            while (result.next()) {
                Echange e = new Echange((Integer) result.getObject("Duree"),
                        EcoleM.getEcoleFromId(Integer.parseInt(result.getObject("EcoleDep").toString())),
                        EcoleM.getEcoleFromId(Integer.parseInt(result.getObject("EcoleArr").toString())),
                        FormationM.getFormationFromId(Integer.parseInt(result.getObject("Formation").toString())));
                res.add(e);
                treeMapEchange.put(Integer.parseInt(result.getObject(1).toString()), e);
            }
            result.close();
            state.close();

            return res;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEchangeInBD(Echange echange) {
        Statement state;
        boolean res = false;
        try {
            // On regarde si la formation et les écoles y sont
            if(FormationM.isFormationInBD(echange.getFormation())
                    && EcoleM.isEcoleInBD(echange.getEcoleDepart()) && EcoleM.isEcoleInBD(echange.getEcoleArrivee())) {

                // On regarde si l'echange y est
                state = ConnexionBD.getInstance().createStatement();
                String requete = "SELECT * FROM ECHANGE WHERE Duree = " + echange.getDuree() + " " +
                        "AND Formation = " + FormationM.getId(echange.getFormation()) + " " +
                        "AND EcoleDep = " + EcoleM.getId(echange.getEcoleDepart()) + " " +
                        "AND EcoleArr = " + EcoleM.getId(echange.getEcoleArrivee()) + ";";

                ResultSet result = state.executeQuery(requete);
                if (result.next()) {
                    res = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void ajouterEchange(Echange echange) {
        String requete = "INSERT INTO ECHANGE (Duree, Formation, EcoleDep, EcoleArr) VALUES (?, ?, ?, ?);";
        PreparedStatement prepare;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setString(1, Integer.toString(echange.getDuree()));
            prepare.setString(2, Integer.toString(FormationM.getId(echange.getFormation())));
            prepare.setString(3, Integer.toString(EcoleM.getId(echange.getEcoleDepart())));
            prepare.setString(4, Integer.toString(EcoleM.getId(echange.getEcoleArrivee())));

            prepare.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // On met à jour le TreeMap
        getEchanges();
    }

    public static void modifierEchange(Echange oldEchange, Echange nouvelEchange) {
        if(treeMapEchange.size() == 0) {
            getEchanges();
        }
        String requete = "UPDATE ECHANGE SET Duree = ?, Formation = ? , EcoleDep = ? , EcoleArr = ? WHERE Id = ?;";
        PreparedStatement prepare;
        try {
            prepare = ConnexionBD.getInstance().prepareStatement(requete);

            prepare.setInt(1, nouvelEchange.getDuree());
            // Si la formation n'existe pas on la creer
            if(FormationM.getId(nouvelEchange.getFormation()) == null) {
                FormationM.ajouterFormation(nouvelEchange.getFormation());
            }

            prepare.setInt(2, FormationM.getId(nouvelEchange.getFormation()));
            prepare.setInt(3, EcoleM.getId(nouvelEchange.getEcoleDepart()));
            prepare.setInt(4, EcoleM.getId(nouvelEchange.getEcoleArrivee()));

            prepare.setInt(5, getId(oldEchange));

            // On modifie
            prepare.executeUpdate();

            // On met à jour le treeMap
            int key = getId(oldEchange);
            treeMapEchange.remove(key);
            treeMapEchange.put(key, nouvelEchange);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer getId(Echange echange) {

        if(treeMapEchange.size() == 0) {
            getEchanges();
        }
        Set<Integer> ss = treeMapEchange.keySet();
        Integer res = null;
        for(Integer i : ss) {
            if(treeMapEchange.get(i).equals(echange)) {
                res = i;
            }
        }
        return res;
    }

    public static void supprimerEchange(Echange echange) {
        // TODO
    }
}

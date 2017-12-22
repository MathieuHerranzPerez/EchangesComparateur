package ComparateurCode.Modele;

public class ConnexionBD {
    private static ConnexionBD ourInstance = new ConnexionBD();

    public static ConnexionBD getInstance() {
        return ourInstance;
    }

    private ConnexionBD() {
    }
}

package ComparateurCode.Controleur;

import ComparateurCode.Modele.AdminM;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class non instanciable. Elle permet juste de vérifier si l'utilisateur est un administrateur de l'application
 */
public class Admin {
    /**
     * Vérifie la justesse des identifiants en parametre par rapport au résultat de la BD
     * Le mdp est sera chiffré MD5
     * @param id String, l'identifiant de connexion
     * @param mdp String, le mot de passe
     * @return boolean, si les identifiants id et mdp correspondent à leur équivalants en BD
     */
    public static boolean isBonsIdentifiants(String id, String mdp) {
        String idBD = AdminM.getId();
        String mdpBD = AdminM.getMDP();

        mdp = getMD5FromString(mdp);

        return id.equals(idBD) && mdp.equals(mdpBD);
    }

    /**
     * Chiffre le parametre key en MD5
     * @param key String, la chaine à chiffrer
     * @return String la chaine chiffrée
     */
    public static String getMD5FromString(String key) {
        byte[] uniqueKey = key.getBytes();
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        }
        catch (NoSuchAlgorithmException e) {
            throw new Error("La VM ne support pas MD5");
        }
        StringBuilder hashString = new StringBuilder();

        for(int i = 0; i < hash.length; ++i ) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1 ) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length()-1));
            } else {
                hashString.append(hex.substring(hex.length()-2));
            }
        }
        return hashString.toString();
    }
}

package ComparateurCode.Controleur;

import ComparateurCode.Modele.AdminM;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Admin {
    public static boolean isBonsIdentifiants(String id, String mdp) {
        String idBD = AdminM.getId();
        String mdpBD = AdminM.getMDP();

        mdp = getMD5FromString(mdp);

        return mdp.equals(mdpBD);
    }

    public static String getMD5FromString(String key) {
        byte[] uniqueKey = key.getBytes();
        byte[] hash = null;
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

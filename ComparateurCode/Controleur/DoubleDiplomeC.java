package ComparateurCode.Controleur;

public class DoubleDiplomeC extends DecorateurEchangeC {

    @Override
    public String getType() {
        return echange.getType() + "en double diplome";
    }
}

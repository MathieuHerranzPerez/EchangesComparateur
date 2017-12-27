package ComparateurCode.Controleur;

public class DoubleDiplome extends DecorateurEchangeAbs {

    @Override
    public String getType() {
        return echangeAbs.getType() + "en double diplome";
    }
}

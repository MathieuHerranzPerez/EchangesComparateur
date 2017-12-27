package ComparateurCode.Controleur;

public class Stage extends DecorateurEchangeAbs {

    @Override
    public String getType() {
        return echangeAbs.getType() + "en stage";
    }
}

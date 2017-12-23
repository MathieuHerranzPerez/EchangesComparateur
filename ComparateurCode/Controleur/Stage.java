package ComparateurCode.Controleur;

public class Stage extends DecorateurEchangeC {

    @Override
    public String getType() {
        return echange.getType() + "en stage";
    }
}

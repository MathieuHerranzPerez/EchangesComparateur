package ComparateurCode.Controleur;

public class Stage extends DecorateurEchange {

    @Override
    public String getType() {
        return echange.getType() + "en stage";
    }
}

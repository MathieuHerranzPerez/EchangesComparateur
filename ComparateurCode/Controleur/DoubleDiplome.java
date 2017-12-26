package ComparateurCode.Controleur;

public class DoubleDiplome extends DecorateurEchange {

    @Override
    public String getType() {
        return echange.getType() + "en double diplome";
    }
}

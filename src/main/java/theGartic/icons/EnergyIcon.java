package theGartic.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theGartic.util.TexLoader;
import static theGartic.GarticMod.makeID;

public class EnergyIcon extends AbstractCustomIcon {
    public static final String ID = makeID("Energy");
    private static EnergyIcon singleton;

    public EnergyIcon() {
        super(ID, TexLoader.getTexture("garticmodResources/images/icons/EnergyIcon.png"));
    }

    public static EnergyIcon get()
    {
        if (singleton == null) {
            singleton = new EnergyIcon();
        }
        return singleton;
    }
}
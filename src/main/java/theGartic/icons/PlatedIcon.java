package theGartic.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theGartic.util.TexLoader;
import static theGartic.GarticMod.makeID;

public class PlatedIcon extends AbstractCustomIcon {
    public static final String ID = makeID("Plated");
    private static PlatedIcon singleton;

    public PlatedIcon() {
        super(ID, TexLoader.getTexture("garticmodResources/images/icons/PlatedIcon.png"));
    }

    public static PlatedIcon get()
    {
        if (singleton == null) {
            singleton = new PlatedIcon();
        }
        return singleton;
    }
}
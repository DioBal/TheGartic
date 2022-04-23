package theGartic.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theGartic.util.TexLoader;
import static theGartic.GarticMod.makeID;

public class SpikyIcon extends AbstractCustomIcon {
    public static final String ID = makeID("Spiky");
    private static SpikyIcon singleton;

    public SpikyIcon() {
        super(ID, TexLoader.getTexture("garticmodResources/images/icons/SpikyIcon.png"));
    }

    public static SpikyIcon get()
    {
        if (singleton == null) {
            singleton = new SpikyIcon();
        }
        return singleton;
    }
}
package theGartic.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theGartic.util.TexLoader;
import static theGartic.GarticMod.makeID;

public class VoidIcon extends AbstractCustomIcon {
    public static final String ID = makeID("Void");
    private static VoidIcon singleton;

    public VoidIcon() {
        super(ID, TexLoader.getTexture("garticmodResources/images/icons/VoidIcon.png"));
    }

    public static VoidIcon get()
    {
        if (singleton == null) {
            singleton = new VoidIcon();
        }
        return singleton;
    }
}
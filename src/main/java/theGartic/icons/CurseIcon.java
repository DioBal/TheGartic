package theGartic.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theGartic.util.TexLoader;
import static theGartic.GarticMod.makeID;

public class CurseIcon extends AbstractCustomIcon {
    public static final String ID = makeID("Curse");
    private static CurseIcon singleton;

    public CurseIcon() {
        super(ID, TexLoader.getTexture("garticmodResources/images/icons/CurseIcon.png"));
    }

    public static CurseIcon get()
    {
        if (singleton == null) {
            singleton = new CurseIcon();
        }
        return singleton;
    }
}
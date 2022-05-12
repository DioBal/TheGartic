package theGartic.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theGartic.util.TexLoader;
import static theGartic.GarticMod.makeID;

public class AngryIcon extends AbstractCustomIcon {
    public static final String ID = makeID("Angry");
    private static AngryIcon singleton;

    public AngryIcon() {
        super(ID, TexLoader.getTexture("garticmodResources/images/icons/AngryIcon.png"));
    }

    public static AngryIcon get()
    {
        if (singleton == null) {
            singleton = new AngryIcon();
        }
        return singleton;
    }
}
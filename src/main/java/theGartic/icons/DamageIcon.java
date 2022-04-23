package theGartic.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theGartic.util.TexLoader;
import static theGartic.GarticMod.makeID;

public class DamageIcon extends AbstractCustomIcon {
    public static final String ID = makeID("Damage");
    private static DamageIcon singleton;

    public DamageIcon() {
        super(ID, TexLoader.getTexture("garticmodResources/images/icons/DamageIcon.png"));
    }

    public static DamageIcon get()
    {
        if (singleton == null) {
            singleton = new DamageIcon();
        }
        return singleton;
    }
}
package theGartic.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theGartic.util.TexLoader;
import static theGartic.GarticMod.makeID;

public class BlockingIcon extends AbstractCustomIcon {
    public static final String ID = makeID("Blocking");
    private static BlockingIcon singleton;

    public BlockingIcon() {
        super(ID, TexLoader.getTexture("garticmodResources/images/icons/BlockingIcon.png"));
    }

    public static BlockingIcon get()
    {
        if (singleton == null) {
            singleton = new BlockingIcon();
        }
        return singleton;
    }
}
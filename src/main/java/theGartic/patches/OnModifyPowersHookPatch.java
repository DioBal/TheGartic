package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.util.OnModifyPowersOrb;

import static theGartic.util.Wiz.adp;

@SpirePatch2(
        clz = AbstractDungeon.class,
        method = "onModifyPower"
)
public class OnModifyPowersHookPatch {
    @SpirePrefixPatch
    public static void Prefix() {
        for (AbstractOrb orb : adp().orbs)
            if (orb instanceof OnModifyPowersOrb)
                ((OnModifyPowersOrb) orb).OnPowersModified();
    }
}
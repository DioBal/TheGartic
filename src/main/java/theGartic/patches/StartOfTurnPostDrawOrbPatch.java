package theGartic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theGartic.summons.AbstractSummonOrb;

public class StartOfTurnPostDrawOrbPatch {

    @SpirePatch(clz = AbstractCreature.class, method = "applyStartOfTurnPostDrawPowers")
    public static class StartOfTurnPostDrawOrbs {
        @SpirePrefixPatch
        public static void Prefix(AbstractCreature __instance){
                if (__instance == AbstractDungeon.player){
                    for (AbstractOrb s : AbstractDungeon.player.orbs){
                        if (s instanceof AbstractSummonOrb){
                            ((AbstractSummonOrb) s).atStartOfTurnPostDraw();
                        }
                    }
                }
            }
        }
    }

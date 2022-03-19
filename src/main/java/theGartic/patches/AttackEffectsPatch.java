package theGartic.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theGartic.GarticMod;

public class AttackEffectsPatch {
    @SpirePatch(
            clz = FlashAtkImgEffect.class,
            method = "loadImage"
    )
    public static class VfxPatch {
        @SpirePrefixPatch
        public static SpireReturn<TextureAtlas.AtlasRegion> Prefix(FlashAtkImgEffect __instance) {
            AbstractGameAction.AttackEffect effect = ReflectionHacks.getPrivate(__instance, FlashAtkImgEffect.class, "effect");
            if (effect == GarticMod.Enums.GUNSHOT) {
                // Texture texture = TexLoader.getTexture(Path to your file);
                // TextureAtlas.AtlasRegion atReg = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
                // return SpireReturn.Return(atReg);
                return SpireReturn.Return(ImageMaster.ATK_SLASH_H);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = FlashAtkImgEffect.class,
            method = "playSound"
    )
    public static class SfxPatch {
        @SpirePrefixPatch
        public static SpireReturn Prefix(FlashAtkImgEffect __instance, AbstractGameAction.AttackEffect effect) {
            if (effect == GarticMod.Enums.GUNSHOT) {
                CardCrawlGame.sound.play(GarticMod.GUNSHOT_KEY);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
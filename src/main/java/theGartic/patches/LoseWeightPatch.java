package theGartic.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;
import theGartic.actions.LoseWeightAction;

public class LoseWeightPatch {
	@SpirePatch2(
		clz = HandCardSelectScreen.class,
		method = "open",
		paramtypez = {String.class, int.class, boolean.class, boolean.class, boolean.class, boolean.class, boolean.class}
	)
	@SpirePatch2(
		clz = HandCardSelectScreen.class,
		method = "open",
		paramtypez = {String.class, int.class, boolean.class, boolean.class}
	)
	public static class CheckIfActivated {
		@SpirePrefixPatch
		public static void Prefix(@ByRef String[] msg) {
			if (msg[0].startsWith(LoseWeightAction.magicPrefix)) {
				msg[0] = msg[0].substring(LoseWeightAction.magicPrefix.length());
				LoseWeightAction.activated = true;
			} else {
				LoseWeightAction.activated = false;
			}
		}
	}

	@SpirePatch2(
		clz = HandCardSelectScreen.class,
		method = "selectHoveredCard"
	)
	public static class DisableSelectingColoredCard {
		@SpirePrefixPatch
		public static SpireReturn<Void> Prefix(AbstractCard ___hoveredCard) {
			if (LoseWeightAction.activated && ___hoveredCard.color != AbstractCard.CardColor.COLORLESS) {
				InputHelper.moveCursorToNeutralPosition();
				return SpireReturn.Return();
			} else {
				return SpireReturn.Continue();
			}
		}
	}

	@SpirePatch2(
		clz = CardGroup.class,
		method = "render"
	)
	public static class GrayUnselectableCards {
		@SpirePrefixPatch
		public static SpireReturn<Void> Prefix(CardGroup __instance, SpriteBatch sb) {
			if (LoseWeightAction.activated && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT && __instance == AbstractDungeon.player.hand) {
				for (AbstractCard c : __instance.group) {
					Color origColor = Color.WHITE.cpy();
					if (c.color != AbstractCard.CardColor.COLORLESS) {
						origColor = ReflectionHacks.getPrivate(c, AbstractCard.class, "renderColor");
						Color newColor = origColor.cpy();
						newColor.r *= 0.5f;
						newColor.g *= 0.5f;
						newColor.b *= 0.5f;
						ReflectionHacks.setPrivate(c, AbstractCard.class, "renderColor", newColor);
					}
					c.render(sb);
					if (c.color != AbstractCard.CardColor.COLORLESS) {
						ReflectionHacks.setPrivate(c, AbstractCard.class, "renderColor", origColor);
					}
				}
				return SpireReturn.Return();
			} else {
				return SpireReturn.Continue();
			}
		}
	}
}

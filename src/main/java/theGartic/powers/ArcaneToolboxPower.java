package theGartic.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theGartic.util.Wiz.*;

public class ArcaneToolboxPower extends AbstractEasyPower {
    //Arcane Toolbox - 1 Cost Rare Power
    //Your first card each turn grants: 1 strength if it's a Skill, 1 dexterity if it's an Attack or 1 energy if it's a Power (Innate)

    public ArcaneToolboxPower(AbstractCreature owner, int amount) {
        super("Arcane Toolbox", PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.RED.cpy());
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        if (!card.purgeOnUse && this.amount > 0 && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= this.amount) {
            this.flash();
            if (card.type == AbstractCard.CardType.ATTACK) {
                atb(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
            }
            if (card.type == AbstractCard.CardType.SKILL) {
                atb(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            }
            if (card.type == AbstractCard.CardType.POWER) {
                atb(new GainEnergyAction(1));
            }
        }

    }

    @Override
    public void updateDescription() {
            description = "Your first " + amount + " card(s) each turn grants: 1 Strength if it's a Skill, 1 Dexterity if it's an Attack or 1 Energy if it's a Power";
    }
}

package theGartic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PaperBunnyAction extends AbstractGameAction {
    private AbstractCard funCard;

    public PaperBunnyAction(AbstractMonster target, AbstractCard card) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = (AbstractCreature) target;
        this.funCard = card;
    }

    public PaperBunnyAction(AbstractCard card) {
        this(null, card);
    }

    public void update() {
        int magicNumber = funCard.magicNumber;
        for (AbstractCard q : AbstractDungeon.player.hand.group) {
            q.baseDamage += magicNumber;
            q.damage += magicNumber;
            q.baseBlock += magicNumber;
            q.block += magicNumber;
            q.baseMagicNumber += magicNumber;
            q.magicNumber += magicNumber;
            q.superFlash();
        }
        for (AbstractCard q : AbstractDungeon.player.drawPile.group) {
            q.baseDamage += magicNumber;
            q.damage += magicNumber;
            q.baseBlock += magicNumber;
            q.block += magicNumber;
            q.baseMagicNumber += magicNumber;
            q.magicNumber += magicNumber;
            q.superFlash();
        }
        for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
            q.baseDamage += magicNumber;
            q.damage += magicNumber;
            q.baseBlock += magicNumber;
            q.block += magicNumber;
            q.baseMagicNumber += magicNumber;
            q.magicNumber += magicNumber;
            q.superFlash();
        }
        for (AbstractCard q : AbstractDungeon.player.exhaustPile.group) {
            q.baseDamage += magicNumber;
            q.damage += magicNumber;
            q.baseBlock += magicNumber;
            q.block += magicNumber;
            q.baseMagicNumber += magicNumber;
            q.magicNumber += magicNumber;
            q.superFlash();
        }
        this.isDone = true;
    }

}
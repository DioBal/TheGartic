package theGartic.fish;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractEasyCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class Orca extends AbstractEasyCard {
    public final static String ID = makeID("Orca");
    // intellij stuff skill, none, special, , , , , , 

    public Orca() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
    }

    public void upp() {
        selfRetain = true;
        uDesc();
    }
}
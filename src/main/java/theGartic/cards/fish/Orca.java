package theGartic.cards.fish;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractEasyCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class Orca extends AbstractFishCard {
    public final static String ID = makeID("Orca");
    // intellij stuff skill, none, special, , , , , , 

    public Orca() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            atb(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
    }

    @Override
    public void applyPowers() {
        if (magicNumber > 1) {
            if (!upgraded)
                rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            else
                rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
        }
        super.initializeDescription();
    }

    public void upp() {
        selfRetain = true;
        uDesc();
    }
}
package theGartic.cards.InariModal;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import theGartic.cards.EasyModalChoiceCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariProtection extends EasyModalChoiceCard {

    public static final String ID = makeID(InariProtection.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public InariProtection() {
        super(NAME, DESCRIPTION, () -> {
            atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 1));
        });

        baseMagicNumber = 1;
        initializeDescription();
    }

    public InariProtection(int magicNumber) {
        super(NAME, DESCRIPTION, () -> {
            atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber * 12));
        });

        baseMagicNumber = magicNumber * 12;
        initializeDescription();
    }
}

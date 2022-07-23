package theGartic.cards.InariModal;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.cards.EasyModalChoiceCard;
import theGartic.powers.InariDashPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariEndurance extends EasyModalChoiceCard {

    public static final String ID = makeID(InariEndurance.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public InariEndurance(){
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            atb(new AddTemporaryHPAction(p, p, 1));
        });

        baseMagicNumber = magicNumber;

        initializeDescription();
    }

    public InariEndurance(int magicNumber){
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            atb(new AddTemporaryHPAction(p, p, magicNumber*10));
        });

        baseMagicNumber = magicNumber*10;

        initializeDescription();
    }
}

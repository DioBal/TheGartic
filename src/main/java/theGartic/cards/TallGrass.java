package theGartic.cards;

import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.PermanentCostReduceAction;
import theGartic.actions.PickNewSummonToAddToPartyAction;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class TallGrass extends AbstractEasyCard implements CustomSavable<Integer> {
    public final static String ID = makeID(TallGrass.class.getSimpleName());
    private final static int COST = 10;
    private final static int CHOICES = 3;
    private final static int UPGRADED_COST = 0;

    public TallGrass()
    {
        super(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
        FleetingField.fleeting.set(this, true);
    }

    @Override
    public void triggerWhenDrawn() {
        addToTop(new PermanentCostReduceAction(uuid, magicNumber));
    }

    public void updateCost(int costChange){

    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new PickNewSummonToAddToPartyAction(CHOICES));
    }

    public void upp()
    {
        upgradeBaseCost(UPGRADED_COST);
    }

    @Override
    public Integer onSave() {
        return cost;
    }

    @Override
    public void onLoad(Integer integer) {
        if(integer != null && integer > 0)
            costForTurn = cost = integer;
        else
            cost = COST;
    }
}
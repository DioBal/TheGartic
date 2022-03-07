package theGartic.powers;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theGartic.GarticMod;

import java.util.ArrayList;

import static theGartic.GarticMod.makeID;

public class StolenCardPower extends AbstractEasyPower {
    public ArrayList<AbstractCard> cards = new ArrayList<>();
    public static final String POWER_ID = GarticMod.makeID("StolenCardPower");
    //private static Texture chain = TexLoader.getTexture("todomodResources/images/ui/chain.png");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String NAME = powerStrings.NAME;


    public StolenCardPower(AbstractCreature owner, ArrayList<AbstractCard> cardList, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        cards = cardList;
        System.out.println("cardsize"+cards.size());
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.RED.cpy());
    }

    public void stackPower(int stackAmount) {
        fontScale = 8.0F;
        amount = 0;
        updateDescription();
    }

    @Override
    public void onInitialApplication(){
        super.onInitialApplication();
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < this.cards.size(); i++) {
            if (AbstractDungeon.player.hand.size() != BaseMod.MAX_HAND_SIZE) {
                this.addToBot(new MakeTempCardInHandAction(this.cards.get(i), false, true));
                updateDescription();
            } else {
                this.addToBot(new MakeTempCardInDiscardAction(this.cards.get(i), true));
                updateDescription();
            }
        }
        this.cards.clear();
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    public void addNewCard(AbstractCard c2) {
        this.cards.add(c2);
    }

    public void updateDescription() {
        if (this.cards != null) {
            System.out.println(this.cards.size());
            this.description = DESCRIPTIONS[0];
            for (AbstractCard c : this.cards) {
                this.description += FontHelper.colorString(c.name, "y") + " ";
            }
            this.description += DESCRIPTIONS[1];
        }
    }
}

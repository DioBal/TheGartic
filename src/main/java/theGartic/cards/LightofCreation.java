package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;

public class LightofCreation extends AbstractEasyCard {
    public final static String ID = makeID(LightofCreation.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public LightofCreation() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true), AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        for (AbstractCard c : AbstractDungeon.player.hand.group){
            boolean nomatch = false;
            for (AbstractCard ca : AbstractDungeon.player.masterDeck.group){
                if (c.uuid == ca.uuid){
                    nomatch = true;
                }
            }
            if (!nomatch){
                dmg(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true), AbstractGameAction.AttackEffect.SLASH_VERTICAL);
            }
        }
    }

    public void upp() {
        upgradeBaseCost(0);
        initializeDescription();
    }
}
package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import theGartic.GarticMod;
import theGartic.actions.CreationOfCreationAction;
import theGartic.util.Wiz;

public class CreationOfCreation extends AbstractEasyCard {
    public final static String ID = GarticMod.makeID("CreationOfCreation");
    
    public CreationOfCreation() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CreationOfCreationAction(upgraded));
    }
    
    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}
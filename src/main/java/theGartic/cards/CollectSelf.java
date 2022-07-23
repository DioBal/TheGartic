package theGartic.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.DoppelgangerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.adp;
import static theGartic.util.Wiz.atb;

public class CollectSelf extends AbstractEasyCard {
    public final static String ID = makeID(CollectSelf.class.getSimpleName());
    public final static int COST = -1;

    public CollectSelf() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new NotStanceCheckAction(NeutralStance.STANCE_ID,
                new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
        atb(new ChangeStanceAction(NeutralStance.STANCE_ID));
        atb(new DoppelgangerAction(adp(), upgraded, freeToPlayOnce, energyOnUse));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
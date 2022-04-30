package theGartic.cardmods.block;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theGartic.powers.LosePowerPower;
import static theGartic.GarticMod.makeID;
import com.badlogic.gdx.graphics.Color;

public class SpikyBlockMod extends AbstractBlockModifier {
    
    public static final String ID = makeID("SpikyBlock");
    private Color c = Color.SLATE;
    
    public SpikyBlockMod () {};
    
    @Override
    public void onThisBlockDamaged (DamageInfo info, int loseAmount) {
        if (info.owner != null && loseAmount > 0) {
            AbstractPower power = new ThornsPower(owner, loseAmount);
            this.addToTop(new ApplyPowerAction(owner, owner, power));
            this.addToTop(new ApplyPowerAction(owner, owner, new LosePowerPower(owner, power, loseAmount)));
        }
    }
    
    @Override
    public String getName() { return BaseMod.getKeywordTitle(ID.toLowerCase()); }
    
    @Override
    public String getDescription() { return BaseMod.getKeywordDescription(ID.toLowerCase()); }
    
    @Override
    public Color blockImageColor() { return c; }
    
    @Override
    public Color blockTextColor() { return c; }
    
    @Override 
    public AbstractBlockModifier makeCopy() { 
        return new SpikyBlockMod();
    }

}
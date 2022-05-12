package theGartic.cardmods.block;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import static theGartic.GarticMod.makeID;
import com.badlogic.gdx.graphics.Color;

public class DamageBlockMod extends AbstractBlockModifier {
    
    public static final String ID = makeID("DamageBlock");
    private Color c = Color.MAROON;
    
    public DamageBlockMod () {};
    
    @Override
    public void onThisBlockDamaged (DamageInfo info, int loseAmount) {
        if (info.owner != null && loseAmount > 0) {
            this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, loseAmount)));
            this.addToBot(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, loseAmount)));
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
        return new DamageBlockMod();
    }

}
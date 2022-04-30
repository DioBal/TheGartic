package theGartic.cardmods.block;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.DamageInfo;
import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import static theGartic.GarticMod.makeID;
import com.badlogic.gdx.graphics.Color;

public class EnergyBlockMod extends AbstractBlockModifier {
    
    public static final String ID = makeID("EnergyBlock");
    
    public EnergyBlockMod () {};
    private Color c = Color.YELLOW;
    
    @Override
    public void onThisBlockDamaged(DamageInfo info, int lostAmount) {
        if (info.owner != null && lostAmount > 0) {
            if (info.owner != owner) {
                AbstractDungeon.actionManager.addToTurnStart(new GainEnergyAction(1));
            } else {
                addToBot(new GainEnergyAction(1));
            }
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
        return new EnergyBlockMod();
    }

}
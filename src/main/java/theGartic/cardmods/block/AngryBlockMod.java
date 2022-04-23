package theGartic.cardmods.block;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.DamageInfo;
import static theGartic.GarticMod.makeID;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.graphics.Color;
import theGartic.powers.LoseWrathStancePower;

public class AngryBlockMod extends AbstractBlockModifier {
    
    public static final String ID = makeID("AngryBlock");
    private Color c = Color.RED;
    
    public AngryBlockMod () {};

    public int onRemove(boolean lose, DamageInfo info, int rd) {
        if (owner == AbstractDungeon.player && !lose) {
            addToBot(new ChangeStanceAction("Wrath"));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseWrathStancePower(AbstractDungeon.player, 2)));
        }
        return rd;
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
        return new AngryBlockMod();
    }

}
package theGartic.cards.papers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.AbstractPaperCard;
import static theGartic.GarticMod.makeID;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockModContainer;
import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import theGartic.cardmods.block.*;
import static theGartic.util.Wiz.getRandomItem;

import java.util.ArrayList;
import java.util.Collections;

public class PaperShield extends AbstractPaperCard {
    public final static String ID = makeID("PaperShield");
    private ArrayList<AbstractBlockModifier> blocksList = new ArrayList<>();


    public PaperShield() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseBlock = block = 10;
        baseMagicNumber = magicNumber = 1;
        Collections.addAll(blocksList, new EnergyBlockMod(), new VoidBlockMod(), new CurseBlockMod(), new DamageBlockMod(), new PlatedBlockMod(), new SpikyBlockMod(), new AngryBlockMod(), new BlockingBlockMod());
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        //blocksList.clear();
        //Collections.addAll(blocksList, new EnergyBlockMod(), new VoidBlockMod(), new CurseBlockMod(), new DamageBlockMod(), new PlatedBlockMod(), new SpikyBlockMod(), new AngryBlockMod(), new BlockingBlockMod());

        addToBot(new GainCustomBlockAction(new BlockModContainer(this, getRandomItem(blocksList).makeCopy()), p, block));
        addToBot(new DamageAction(p, new DamageInfo(p, magicNumber, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new DamageAction(p, new DamageInfo(p, magicNumber, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
    
    public void upp() {
        upgradeBlock(4);
    }
}

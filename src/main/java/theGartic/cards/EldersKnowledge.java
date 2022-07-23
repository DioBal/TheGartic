package theGartic.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.stances.WisdomStance;

import static theGartic.GarticMod.makeID;

public class EldersKnowledge extends AbstractEasyCard{
        public final static String ID = makeID("EldersKnowledge");
        private final static int BLOCK = 8;
        private final static int UPGRADE_BLOCK = 4;
        private final static int COST = 2;

        public EldersKnowledge() {
            super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
            baseBlock = block = BLOCK;
        }

        public void use(AbstractPlayer p, AbstractMonster m) {
            this.addToBot(new GainBlockAction(p, p, block, false));
            this.addToBot(new ChangeStanceAction(new WisdomStance()));
        }

        public void upp() {
            upgradeBlock(UPGRADE_BLOCK);
            uDesc();
        }
}

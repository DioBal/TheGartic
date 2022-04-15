package theGartic.cards.InariModal;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import theGartic.cards.EasyModalChoiceCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariFlames extends InariCard {

    public static final String ID = makeID(InariFlames.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static Runnable theOtherRunnableOnUseOrChosen;

    public InariFlames() {
        super(NAME, DESCRIPTION, null);
        Runnable runnable = ()->{

            AbstractPlayer p = AbstractDungeon.player;
            AbstractGameAction.AttackEffect effect = AbstractGameAction.AttackEffect.FIRE;
            DamageInfo.DamageType damageType = DamageInfo.DamageType.THORNS;
            AbstractMonster m = GetAliveMonsterWithLessHealth();
            if (m != null){
                for (int i = 0; i < 10; i++){
                    atb(new DamageAction(m, new DamageInfo(p, 2, damageType), effect));
                }
            }
        };
        this.theOtherRunnableOnUseOrChosen = runnable;

        baseMagicNumber = magicNumber;
        initializeDescription();
    }

    public InariFlames(int magicNumber) {
        super(NAME, DESCRIPTION, null);
        Runnable runnable = ()->{
            AbstractPlayer p = AbstractDungeon.player;
            AbstractGameAction.AttackEffect effect = AbstractGameAction.AttackEffect.FIRE;
            DamageInfo.DamageType damageType = DamageInfo.DamageType.THORNS;
            AbstractMonster m = GetAliveMonsterWithLessHealth();
            if (m != null){
                for (int i = 0; i < 10; i++){
                    atb(new DamageAction(m, new DamageInfo(p, 1+magicNumber, damageType), effect));
                }
            }

        };

        baseMagicNumber = 1+magicNumber;
        initializeDescription();
    }

    @Override
    public void onChoseThisOption() {
        theOtherRunnableOnUseOrChosen.run();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        theOtherRunnableOnUseOrChosen.run();
    }

    @Override
    public AbstractCard makeCopy() {
        return new EasyModalChoiceCard(NAME, DESCRIPTION, theOtherRunnableOnUseOrChosen);
    }

    private AbstractMonster GetAliveMonsterWithLessHealth(){
        MonsterGroup monsterGroup = AbstractDungeon.getMonsters();
        AbstractMonster monsterWithLessHealth = null;

        for (int i = 0; i < monsterGroup.monsters.size(); i++){
            /*
            if monster is alive
            etc
             */
            AbstractMonster currentMonster = monsterGroup.monsters.get(i);
            if (!currentMonster.isDeadOrEscaped()){
                if (monsterWithLessHealth == null){
                    monsterWithLessHealth = currentMonster;
                }
                else if (currentMonster.currentHealth < monsterWithLessHealth.currentHealth)
                {
                    monsterWithLessHealth = currentMonster;
                }
            }
        }

        return monsterWithLessHealth;
    }

    @Override
    public void updateMagicNumber(int amount){
        magicNumber += amount;
        baseMagicNumber = 1+magicNumber;
    }
}

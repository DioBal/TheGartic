package theGartic.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.cards.papers.PaperSword;
import theGartic.cards.papers.PaperShield;
import theGartic.cards.papers.PaperBunny;
import theGartic.cards.AbstractEasyCard;
import theGartic.TheGartic;
import static theGartic.util.Wiz.getRandomItem;
import basemod.helpers.CardModifierManager;
import basemod.BaseMod;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractPaperCard extends AbstractEasyCard {
    
    protected ArrayList<AbstractCard> paperCardsList = new ArrayList<>();
    
    public AbstractPaperCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, TheGartic.Enums.GARTIC_COLOR);
    }
    
    public AbstractPaperCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }
    
    @Override
    public void atTurnStart() {
        paperCardsList.clear();
        paperCardsList.add(new PaperSword());
        paperCardsList.add(new PaperShield());
        paperCardsList.add(new PaperBunny());

        if (AbstractDungeon.player.drawPile.group.contains(this)) {
            replaceSelf(AbstractDungeon.player.drawPile);
        } else if (AbstractDungeon.player.discardPile.group.contains(this)) {
            replaceSelf(AbstractDungeon.player.discardPile);
        } else {
            replaceSelf(AbstractDungeon.player.exhaustPile);
        }
    }
    
    private void replaceSelf(CardGroup pile) {
        for (int i = 0; i < pile.group.size(); i++) {
            AbstractCard card = pile.group.get(i);
            if (card == this) {
                AbstractCard newcard = getRandomItem(paperCardsList).makeCopy();
                CardModifierManager.copyModifiers(card, newcard, true, false, false);
                pile.group.set(i, newcard);
            }
        }
    }
    
    @Override
    public List<String> getCardDescriptors() {
        List<String> tags = new ArrayList<>();
        tags.add(BaseMod.getKeywordTitle("garticmod:paper"));
        return tags;
    }
}
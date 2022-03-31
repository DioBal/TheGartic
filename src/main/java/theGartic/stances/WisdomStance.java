package theGartic.stances;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import theGartic.TheGartic;

public class WisdomStance extends AbstractStance {
    public static final String STANCE_ID = "garticmod:WisdomStance";
    private static long sfxId = -1L;
    private String[] strings = TheGartic.characterStrings.TEXT;

    public WisdomStance() {
        this.ID = STANCE_ID;
        this.name = strings[3];
    }

    public void updateDescription() {
        this.description = strings[4];
    }
}

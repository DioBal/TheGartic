package theGartic;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theGartic.cards.AbstractEasyCard;
import theGartic.cards.cardvars.SecondDamage;
import theGartic.patches.AllWillReturnPatch;
import theGartic.potions.DarklingMilk;
import theGartic.potions.PurpleStuff;
import theGartic.powers.PowerOfCreationPower;
import theGartic.relics.AbstractEasyRelic;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class GarticMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber
        {

    public static final String modID = "garticmod";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";
    public static Logger logger = LogManager.getLogger(GarticMod.class.getName());

    public static final String GUNSHOT_KEY = makeID("GunshotKey");
    private static final String GUNSHOT_PATH = "garticmodResources/audio/sfx/Gunshot.ogg";

    public GarticMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheGartic.Enums.GARTIC_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }
    
    public static class Enums {
        @SpireEnum
        public static AbstractCard.CardTags SUMMON;
        @SpireEnum
        public static AbstractGameAction.AttackEffect GUNSHOT;
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath)
    {
        return modID + "Resources/images/orbs/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        GarticMod thismod = new GarticMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheGartic(TheGartic.characterStrings.NAMES[1], TheGartic.Enums.THE_GARTIC),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheGartic.Enums.THE_GARTIC);
        receiveEditPotions();
    }

    public void receiveEditPotions() {
        BaseMod.addPotion(PurpleStuff.class, Color.PURPLE.cpy(), Color.PURPLE.cpy(), Color.PURPLE.cpy(), PurpleStuff.ID, TheGartic.Enums.THE_GARTIC);
        BaseMod.addPotion(DarklingMilk.class, Color.WHITE.cpy(), Color.WHITE.cpy(), Color.WHITE.cpy(), DarklingMilk.ID, TheGartic.Enums.THE_GARTIC);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID)
                .packageFilter(SecondDamage.class)
                .any(DynamicVariable.class, (info, dynamicVariable) -> BaseMod.addDynamicVariable(dynamicVariable));
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");

        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/eng/Potionstrings.json");

        BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/eng/Orbstrings.json");
        
        BaseMod.loadCustomStringsFile(StanceStrings.class, modID+ "Resources/localization/eng/Stancestrings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class, modID+ "Resources/localization/eng/UIstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(modID + ":GLASSARMOR", modID + "Resources/audio/sfx/glassarmor.ogg");
        BaseMod.addAudio(GUNSHOT_KEY, GUNSHOT_PATH);
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        AllWillReturnPatch.lastTurnBlock = AllWillReturnPatch.thisTurnBlock = 0;
        AllWillReturnPatch.lastTurnDamage = AllWillReturnPatch.thisTurnDamage = 0;
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        AllWillReturnPatch.lastTurnBlock = AllWillReturnPatch.thisTurnBlock = 0;
        AllWillReturnPatch.lastTurnDamage = AllWillReturnPatch.thisTurnDamage = 0;
    }
    
    public static void onCardCreation(AbstractCard card)
    {
        if(AbstractDungeon.player.hasPower(PowerOfCreationPower.POWER_ID))
        {
            ((PowerOfCreationPower)AbstractDungeon.player.getPower(PowerOfCreationPower.POWER_ID)).onCardCreation(card);
        }
    }
}

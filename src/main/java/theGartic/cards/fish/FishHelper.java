package theGartic.cards.fish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class FishHelper {
    public static AbstractCard returnRandomFish() {
        ArrayList<String> fishList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            fishList.add("guppy");
        }
        for (int i = 0; i < 21; i++) {
            fishList.add("piranha");
        }
        for (int i = 0; i < 10; i++) {
            fishList.add("shark");
        }
        for (int i = 0; i < 10; i++) {
            fishList.add("clownfish");
        }
        for (int i = 0; i < 8; i++) {
            fishList.add("octopus");
        }
        for (int i = 0; i < 2; i++) {
            fishList.add("orca");
        }
        for (int i = 0; i < 2; i++) {
            fishList.add("hammerhead");
        }
        for (int i = 0; i < 2; i++) {
            fishList.add("qwilfish");
        }
        for (int i = 0; i < 2; i++) {
            fishList.add("siren");
        }
        for (int i = 0; i < 2; i++) {
            fishList.add("boot");
        }
        fishList.add("seamonster");
        return getFishFromStr(fishList.get(AbstractDungeon.cardRandomRng.random(fishList.size() - 1)));
    }

    private static AbstractCard getFishFromStr(String input) {
        switch (input) {
            case "guppy":
                return new Guppy();
            case "piranha":
                return new Piranha();
            case "shark":
                return new Shark();
            case "clownfish":
                return new Clownfish();
            case "octopus":
                return new Octopus();
            case "orca":
                return new Orca();
            case "hammerhead":
                return new Hammerhead();
            case "qwilfish":
                return new Qwilfish();
            case "siren":
                return new Siren();
            case "seamonster":
                return new SeaMonster();
            default:
                return new Madness();
        }
    }
}

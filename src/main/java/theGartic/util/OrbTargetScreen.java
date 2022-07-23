package theGartic.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import theGartic.GarticMod;

import static theGartic.util.Wiz.adp;

public class OrbTargetScreen {
    public static OrbTargetScreen Inst = new OrbTargetScreen();

    public boolean isActive;
    private String tip;

    private float timer;

    public void open(OrbTargetArrow.OrbTargetArrowSubscriber sub, String tip) {
        timer = 0.1F;
        OrbTargetArrow.open(adp());
        OrbTargetArrow.register(sub);
        isActive = true;
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = GarticMod.Enums.ORB_TARGET_SCREEN;
        this.tip = tip;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.endTurnButton.disable();
    }

    public void reopen() {
        isActive = true;
        AbstractDungeon.screen = GarticMod.Enums.ORB_TARGET_SCREEN;
        AbstractDungeon.topPanel.unhoverHitboxes();
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.endTurnButton.disable();
    }

    public void close() {
        isActive = false;
        AbstractDungeon.overlayMenu.endTurnButton.enable();
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NONE;
        AbstractDungeon.isScreenUp = false;
        tip = null;
    }

    public void update() {
        OrbTargetArrow.update();
        OrbTargetArrow.from.x = AbstractDungeon.player.drawX;
        OrbTargetArrow.from.y = AbstractDungeon.player.drawY;
        AbstractDungeon.currMapNode.room.update();

    }

    public void render(SpriteBatch sb) {
        OrbTargetArrow.render(sb);
        if (isActive && tip != null)
            FontHelper.renderDeckViewTip(sb, tip, 96.0F * Settings.scale, Settings.CREAM_COLOR);
    }
}
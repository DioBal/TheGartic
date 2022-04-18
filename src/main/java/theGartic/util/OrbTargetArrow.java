package theGartic.util;

import basemod.interfaces.ISubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theGartic.summons.AbstractSummonOrb;

import static theGartic.util.Wiz.adp;

public class OrbTargetArrow {
    public static boolean isActive;

    public static Vector2 from;

    private static AbstractCreature source;

    private static AbstractSummonOrb hoveredOrb;
    private static Vector2 controlPoint;
    private static Vector2[] points = new Vector2[20];
    private static float arrowScaleTimer = 0.0F;

    public static OrbTargetArrowSubscriber subscriber = null;

    public static void register(OrbTargetArrowSubscriber sub) {
        subscriber = sub;
    }

    public static void unsubscribe(OrbTargetArrowSubscriber sub) {
        subscriber = null;
    }

    public static void open(AbstractCreature _source) {
        source = _source;
        from = new Vector2(source.drawX, source.drawY);
        isActive = true;
        GameCursor.hidden = true;
        for (int i = 0; i < points.length; i++) points[i] = new Vector2();
    }

    public static void close() {
        isActive = false;
        subscriber.end();
        OrbTargetScreen.Inst.close();
    }

    private static void use(AbstractCreature source, AbstractSummonOrb target) {
        subscriber.receiveTargetOrb(source, target);
    }

    public static void update() {
        if (isActive) {
            hoveredOrb = null;

            for (AbstractOrb orb : adp().orbs) {
                if (orb instanceof AbstractSummonOrb) {
                    AbstractSummonOrb sorb = (AbstractSummonOrb) orb;
                    if (sorb.hb.hovered) {
                        hoveredOrb = sorb;
                        break;
                    }
                }
            }

            if (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) {
                InputHelper.justClickedLeft = false;
                CInputActionSet.select.unpress();
                if (hoveredOrb != null && subscriber.isAcceptableTarget(hoveredOrb)) {
                    use(source, hoveredOrb);
                    if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)
                        AbstractDungeon.actionManager.addToBottom(new HandCheckAction());
                    close();
                    GameCursor.hidden = false;
                }
            }
        }
    }

    public static void render(SpriteBatch sb) {
        if (isActive) {
            if (hoveredOrb != null)
                hoveredOrb.renderReticle(sb);

            float x = (float) InputHelper.mX;
            float y = (float) InputHelper.mY;
            controlPoint = new Vector2(x - (x - from.x) / 4.0F, y + (y - from.y - 40.0F * Settings.scale) / 2.0F);
            float arrowScale;

            if (hoveredOrb == null || !subscriber.isAcceptableTarget(hoveredOrb)) {
                arrowScale = Settings.scale;
                arrowScaleTimer = 0.0F;
                sb.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
            } else {
                arrowScaleTimer += Gdx.graphics.getDeltaTime();
                if (arrowScaleTimer > 1.0F)
                    arrowScaleTimer = 1.0F;

                arrowScale = Interpolation.elasticOut.apply(Settings.scale, Settings.scale * 1.2F, arrowScaleTimer);
                sb.setColor(new Color(1.0F, 0.2F, 0.3F, 1.0F));
            }

            Vector2 tmp = new Vector2(controlPoint.x - x, controlPoint.y - y);
            tmp.nor();
            drawCurvedLine(sb, new Vector2(from.x, from.y - 40.0F * Settings.scale), new Vector2(x, y), controlPoint);
            sb.draw(ImageMaster.TARGET_UI_ARROW, x - 128.0F, y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, arrowScale, arrowScale, tmp.angle() + 90.0F, 0, 0, 256, 256, false, false);
        }
    }

    private static void drawCurvedLine(SpriteBatch sb, Vector2 start, Vector2 end, Vector2 control) {
        float radius = 7.0F * Settings.scale;

        for (int i = 0; i < points.length - 1; ++i) {
            points[i] = Bezier.quadratic(points[i], (float) i / 20.0F, start, control, end, new Vector2());
            radius += 0.4F * Settings.scale;
            Vector2 tmp;
            float angle;
            if (i != 0) {
                tmp = new Vector2(points[i - 1].x - points[i].x, points[i - 1].y - points[i].y);
                angle = tmp.nor().angle() + 90.0F;
            } else {
                tmp = new Vector2(controlPoint.x - points[i].x, controlPoint.y - points[i].y);
                angle = tmp.nor().angle() + 270.0F;
            }

            sb.draw(ImageMaster.TARGET_UI_CIRCLE, points[i].x - 64.0F, points[i].y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, radius / 18.0F, radius / 18.0F, angle, 0, 0, 128, 128, false, false);
        }

    }

    public interface OrbTargetArrowSubscriber extends ISubscriber {
        void receiveTargetOrb(AbstractCreature source, AbstractSummonOrb target);

        void end();

        boolean isAcceptableTarget(AbstractSummonOrb orb);
    }
}

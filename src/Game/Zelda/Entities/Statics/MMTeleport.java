package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaMMGameState;
import Game.Zelda.Entities.MMBaseEntity;
import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/14/2020
 */
public class MMTeleport extends MMBaseEntity {

    public int linkedX,linkedY;

    public MMTeleport(int x, int y, BufferedImage sprite, Handler handler) {
        super(x, y, sprite,handler);
        bounds = new Rectangle(x ,y ,width,height);

    }

    @Override
    public void tick() {
        if (handler.getState() instanceof ZeldaMMGameState && ((ZeldaMMGameState)handler.getState()).map.link.interactBounds.intersects(bounds) && handler.getKeyManager().keyJustPressed(KeyEvent.VK_SHIFT)){
            ((ZeldaMMGameState)handler.getState()).map.link.x = linkedX;
            ((ZeldaMMGameState)handler.getState()).map.link.y = linkedY;
            return;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,x ,y,width,height,null);
    }
}

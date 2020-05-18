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
public class MMmovingLink extends MMBaseEntity {

    public MMmovingLink(int x, int y, BufferedImage sprite, Handler handler) {
        super(x, y, sprite,handler);
        bounds = new Rectangle(x ,y ,width,height);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,x ,y,width,height,null);
    }
}

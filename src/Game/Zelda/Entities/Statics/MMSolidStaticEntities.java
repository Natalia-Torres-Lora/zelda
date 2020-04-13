package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.MMBaseEntity;
import Game.Zelda.World.MapBuilder;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/14/2020
 */
public class MMSolidStaticEntities extends MMBaseEntity {

    public MMSolidStaticEntities(int x, int y, BufferedImage sprite, Handler handler) {
        super(x, y, sprite,handler);
        bounds = new Rectangle(x ,y ,width,height);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,x ,y,width,height,null);
    }
}

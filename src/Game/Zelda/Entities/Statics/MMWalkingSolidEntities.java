package Game.Zelda.Entities.Statics;

import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.MMBaseEntity;
import Main.Handler;

import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/14/2020
 */
public class MMWalkingSolidEntities extends MMBaseEntity {
    public MMWalkingSolidEntities(int x, int y, BufferedImage sprite, Handler handler) {
        super(x, y, sprite,handler);
    }
}

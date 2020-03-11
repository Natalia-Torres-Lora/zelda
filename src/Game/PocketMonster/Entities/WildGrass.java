package Game.PocketMonster.Entities;

import Main.Handler;

import java.awt.image.BufferedImage;
import java.lang.ref.PhantomReference;
import java.util.Random;

/**
 * Created by AlexVR on 3/11/2020
 */
public class WildGrass extends BasePassThroughtEntity {
    boolean playerEntered = false;
    public WildGrass(BufferedImage sprite, int x, int y, String name, Handler handler) {
        super(sprite, x, y, name,handler);
    }

    @Override
    public void tick() {
        super.tick();
        if (bounds.intersects(handler.getPMGameState().entityManager.player.bounds) && !playerEntered){
            playerEntered = true;
            if (new Random().nextInt(100) < 3){
                //triggerFight
            }
        }
    }
}

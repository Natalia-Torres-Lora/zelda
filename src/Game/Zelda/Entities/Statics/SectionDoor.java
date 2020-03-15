package Game.Zelda.Entities.Statics;

import Main.Handler;

import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/15/2020
 */
public class SectionDoor extends SolidStaticEntities {
    String direction;
    public SectionDoor(int x, int y,int widht, int height, String direction, Handler handler) {
        super(x, y, null, handler);
        this.width = widht;
        this.height = height;
        bounds.width = width;
        bounds.height = height;
        this.direction = direction;
    }

}

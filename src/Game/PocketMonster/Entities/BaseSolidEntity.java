package Game.PocketMonster.Entities;

import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/11/2020
 */
public class BaseSolidEntity extends BaseEntity {

    public BaseSolidEntity(BufferedImage sprite, int x, int y, String name, Handler handler) {
        super(sprite, x, y, name, handler);
    }
    public BaseSolidEntity(BufferedImage sprite, int x, int y,int width, int height, String name, Handler handler) {
        super(sprite, x, y, name, handler);
        this.width = width;
        this.height = height;
        bounds.width = width;
        bounds.height = height;
    }

    @Override
    public void render(Graphics g, int scale, int xOffset, int yOffset) {
        super.render(g, scale, xOffset, yOffset);
        g.setColor(Color.BLUE);
        g.fillRect(x + xOffset,y + yOffset,width,height);
    }
}

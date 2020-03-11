package Game.PocketMonster.World;

import Main.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/11/2020
 */
public class BaseWorld {
    BufferedImage image;
    public int xOff,yOff;

    public BaseWorld(BufferedImage image,int xOff,int yXOff, Handler handler) {
        this.image = image;

    }

    public void render(Graphics g,int scale,int xOffset,int yOffset) {

        g.drawImage(image,  xOffset + xOff,  yOffset + yOff,image.getWidth()*scale,image.getHeight()*scale,null);
    }
}

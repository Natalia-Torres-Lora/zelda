package Game.PocketMonster.World;

import Game.Galaga.Entities.EntityManager;
import Game.PocketMonster.Entities.BaseSolidEntity;
import Main.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by AlexVR on 3/11/2020
 */
public class BaseWorld {
    public BufferedImage image;
    public int xOff,yOff;
    public ArrayList<BaseSolidEntity> walls;
    Handler handler;

    public BaseWorld(BufferedImage image,int xOff,int yXOff, Handler handler) {
        this.image = image;
        walls = new ArrayList<>();
        this.xOff = xOff;
        this.yOff = yXOff;
        this.handler = handler;

    }

    public void render(Graphics g,int scale,int xOffset,int yOffset) {

        g.drawImage(image,  xOffset + xOff,  yOffset + yOff,image.getWidth()*scale,image.getHeight()*scale,null);
        for (BaseSolidEntity bse:walls){
            bse.render(g,scale,xOffset,yOffset);
        }
    }
}

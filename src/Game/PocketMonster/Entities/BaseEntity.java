package Game.PocketMonster.Entities;

import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/11/2020
 */
public class BaseEntity {

    BufferedImage sprite;
    public int x,y,width,height;
    String name;
    Rectangle bounds;
    Handler handler;

    public BaseEntity(BufferedImage sprite, int x, int y, String name, Handler handler) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.name = name;
        width = sprite.getWidth();
        height = sprite.getHeight();
        bounds = new Rectangle(x,y,width,height);
        this.handler = handler;
    }

    public void tick(){

    }

    public void render(Graphics g,int scale,int xOffset,int yOffset) {
        g.drawImage(sprite,x + xOffset,y + yOffset,width*scale,height*scale,null);
    }


    }

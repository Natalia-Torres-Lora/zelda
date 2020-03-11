package Game.PocketMonster.Entities;

import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by AlexVR on 3/11/2020
 */
public class BaseInteractableEntity extends BaseEntity {

    ArrayList<String> dialogue;
    BufferedImage[] sprites = null;
    Direction facing;
    boolean interacting = false;
    Rectangle interactBounds;



    public BaseInteractableEntity(BufferedImage sprite, int x, int y, String name,Handler handler) {
        super(sprite, x, y, name,handler);
        facing = Direction.DOWN;
        changeIntersectingBounds();
    }



    public BaseInteractableEntity(BufferedImage[] sprite, int x, int y, String name, Handler handler) {
        super(sprite[0], x, y, name,handler);
        sprites = sprite;
        facing = Direction.DOWN;
        changeIntersectingBounds();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics g,int scale,int xOffset,int yOffset) {
        super.render(g,scale,xOffset,yOffset);
    }

    public void interact(){

    }

    public void changeIntersectingBounds() {
        switch (facing){
            case DOWN:
                interactBounds = (Rectangle) bounds.clone();
                interactBounds.y+=height;
                break;
            case UP:
                interactBounds = (Rectangle) bounds.clone();
                interactBounds.y-=height;
                break;
            case LEFT:
                interactBounds = (Rectangle) bounds.clone();
                interactBounds.x-=width;
                break;
            case RIGHT:
                interactBounds = (Rectangle) bounds.clone();
                interactBounds.x+=width;
                break;
        }
    }
}

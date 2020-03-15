package Game.Zelda.Entities.Dynamic;

import Game.Zelda.Entities.BaseEntity;
import Main.Handler;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.Zelda.Entities.Dynamic.Direction.*;

/**
 * Created by AlexVR on 3/15/2020
 */
public class BaseMovingEntity extends BaseEntity {

    int speed;
    Direction direction;
    Animation animation;
    BufferedImage[] sprites;
    boolean moving = false;
    Rectangle interactBounds;

    public BaseMovingEntity(int x, int y, BufferedImage[] sprite, Handler handler) {
        super(x, y, sprite[0], handler);
        animation = new Animation(160,sprite);
        speed=2;
        direction = UP;
        sprites = sprite;
        interactBounds = (Rectangle) bounds.clone();

        interactBounds.y-=(height/4);
    }

    @Override
    public void tick() {
        super.tick();

        move(direction);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    public void move(Direction direction){
        moving = true;
    }

    public void changeIntersectingBounds() {
        switch (direction){
            case DOWN:
                interactBounds = (Rectangle) bounds.clone();
                interactBounds.y+=(height/4);
                break;
            case UP:
                interactBounds = (Rectangle) bounds.clone();
                interactBounds.y-=(height/4);
                break;
            case LEFT:
                interactBounds = (Rectangle) bounds.clone();
                interactBounds.x-=(width/4);
                break;
            case RIGHT:
                interactBounds = (Rectangle) bounds.clone();
                interactBounds.x+=(width/4);
                break;
        }
    }
}

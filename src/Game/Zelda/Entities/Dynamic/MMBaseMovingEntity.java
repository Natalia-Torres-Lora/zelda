package Game.Zelda.Entities.Dynamic;

import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.MMBaseEntity;
import Main.Handler;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class MMBaseMovingEntity extends MMBaseEntity {

    int speed;
    Direction direction;
    Animation animation;
    BufferedImage[] sprites;
    boolean moving = false;
    public Rectangle interactBounds;

    public MMBaseMovingEntity(int x, int y, BufferedImage[] sprite, Handler handler) {
        super(x, y, sprite[0], handler);
        animation = new Animation(160,sprite);
        speed=2;
        direction = UP;
        sprites = sprite;
        interactBounds = (Rectangle) bounds.clone();
        interactBounds.y+=(height/2);
        interactBounds.height/=2;
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
        interactBounds = (Rectangle) bounds.clone();
        interactBounds.y+=(height/2);
        interactBounds.height/=2;
        switch (direction){
            case DOWN:
                interactBounds.y+=speed;
                break;
            case UP:
                interactBounds.y-=speed;
                break;
            case LEFT:
                interactBounds.x-=speed;
                break;
            case RIGHT:
                interactBounds.x+=speed;
                break;
        }
    }
}

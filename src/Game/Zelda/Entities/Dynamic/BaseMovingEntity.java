package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.BaseEntity;
import Main.Handler;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class BaseMovingEntity extends BaseEntity {

    int speed;
    Direction direction;
    Animation animation;
    BufferedImage[] sprites;
    boolean moving = false;
    boolean dead = false;


    Rectangle interactBounds;
    public int health = 1;

    public BaseMovingEntity(int x, int y, BufferedImage[] sprite, Handler handler) {
        super(x, y, sprite[0], handler);
        animation = new Animation(256,sprite);
        bounds = new Rectangle((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);
        speed=2;
        direction = UP;
        sprites = sprite;
        interactBounds = (Rectangle) bounds.clone();
        interactBounds.y+=(height/2);
        interactBounds.height/=2;
    }

    @Override
    public void tick() {
        if(!dead){
            super.tick();
            move(direction);
        }

    }

    @Override
    public void render(Graphics g) {
        if (!dead) {
            super.render(g);
        }
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

    public Rectangle getInteractBounds() {
        return interactBounds;
    }

    public void damage(int amount){
        health-=amount;
        if (health<=0){
            kill();
        }
    }

    public void kill(){
        dead=true;
    }

}

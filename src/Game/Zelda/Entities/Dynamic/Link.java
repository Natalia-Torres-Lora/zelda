package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/15/2020
 */
public class Link extends BaseMovingEntity {


    private final int animSpeed = 120;
    int newMapX=0,newMapY=0;
    boolean movingMap = false;

    public Link(int x, int y, BufferedImage[] sprite, Handler handler) {
        super(x, y, sprite, handler);
        speed = 4;
        BufferedImage[] animList = new BufferedImage[2];
        animList[0] = sprite[4];
        animList[1] = sprite[5];

        animation = new Animation(animSpeed,animList);
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().up ) {
            if (direction!=Direction.UP){
                BufferedImage[] animList = new BufferedImage[2];
                animList[0] = sprites[4];
                animList[1] = sprites[5];
                animation = new Animation(animSpeed, animList);
                direction = Direction.UP;
                sprite = sprites[4];
            }
            animation.tick();
            move(direction);

        } else if (handler.getKeyManager().down) {
            if (direction!=Direction.DOWN){
                BufferedImage[] animList = new BufferedImage[2];
                animList[0] = sprites[0];
                animList[1] = sprites[1];
                animation = new Animation(animSpeed, animList);
                direction = Direction.DOWN;
                sprite = sprites[0];
            }
            animation.tick();
            move(direction);
        } else if (handler.getKeyManager().left) {
            if (direction!=Direction.LEFT){
                BufferedImage[] animList = new BufferedImage[2];
                animList[0] = Images.flipHorizontal(sprites[2]);
                animList[1] = Images.flipHorizontal(sprites[3]);
                animation = new Animation(animSpeed, animList);
                direction = Direction.LEFT;
                sprite = Images.flipHorizontal(sprites[3]);
            }
            animation.tick();
            move(direction);
        } else if (handler.getKeyManager().right) {
            if (direction!=Direction.RIGHT){
                BufferedImage[] animList = new BufferedImage[2];
                animList[0] = (sprites[2]);
                animList[1] = (sprites[3]);
                animation = new Animation(animSpeed, animList);
                direction = Direction.RIGHT;
                sprite = (sprites[3]);
            }
            animation.tick();
            move(direction);
        }else{
            moving = false;
        }
    }

    @Override
    public void render(Graphics g) {
        if (moving) {
            g.drawImage(animation.getCurrentFrame(),x , y, width * ZeldaGameState.worldScale /2, height  * ZeldaGameState.worldScale/2, null);

        } else {
            g.drawImage(sprite, x , y, width  * ZeldaGameState.worldScale/2, height * ZeldaGameState.worldScale/2, null);
        }
    }

    @Override
    public void move(Direction direction) {
        moving = true;
        switch (direction) {
            case RIGHT:
                x += speed;
                break;
            case LEFT:
                x -= speed;

                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;

                break;
        }
        bounds.x = x;
        bounds.y = y;
        changeIntersectingBounds();
        for (SolidStaticEntities doors:handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)){
            if (doors instanceof SectionDoor && doors.bounds.intersects(bounds)){
                System.out.println("Move to next area to: " + ((SectionDoor) doors).direction);
            }
        }

    }
}

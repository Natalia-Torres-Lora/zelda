package Game.PocketMonster.Entities;

import Game.PocketMonster.World.BaseWorld;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/11/2020
 */
public class PokePlayer extends BaseWalkingEntity {

    Animation walkAnim;
    boolean transitioning = false;
    int speed = 2;
    int animSpeed = 384/speed;

    public PokePlayer(BufferedImage[] sprite, int x, int y,int scale, String name, Handler handler) {
        super(sprite, x, y-(sprite[0].getHeight()/3),scale, name, handler);
        facing = Direction.UP;
        BufferedImage[] animList = new BufferedImage[2];
        animList[0] = sprite[1];
        animList[1] = sprite[4];

        walkAnim = new Animation(animSpeed,animList);

    }

    @Override
    public void tick() {
        super.tick();
        if (transitioning){
            walk(facing);
            if (walkAnim.end){
                transitioning = false;
                walkAnim.reset();
            }else {
                walkAnim.tick();
            }
        }else {
            if (handler.getKeyManager().up && !transitioning) {
                BufferedImage[] animList = new BufferedImage[2];
                animList[0] = sprites[1];
                animList[1] = sprites[4];
                walkAnim = new Animation(animSpeed, animList);

                facing = Direction.UP;
                transitioning = true;
                sprite = sprites[1];
            } else if (handler.getKeyManager().down && !transitioning) {
                BufferedImage[] animList = new BufferedImage[2];
                animList[0] = sprites[0];
                animList[1] = sprites[3];
                walkAnim = new Animation(animSpeed, animList);


                facing = Direction.DOWN;
                sprite = sprites[0];
                transitioning = true;
            } else if (handler.getKeyManager().left && !transitioning) {
                BufferedImage[] animList = new BufferedImage[2];
                animList[0] = sprites[2];
                animList[1] = sprites[5];
                walkAnim = new Animation(animSpeed, animList);

                facing = Direction.LEFT;
                sprite = sprites[2];
                transitioning = true;
            } else if (handler.getKeyManager().right && !transitioning) {
                BufferedImage[] animList = new BufferedImage[2];
                animList[0] = Images.flipHorizontal(sprites[2]);
                animList[1] = Images.flipHorizontal(sprites[5]);
                walkAnim = new Animation(animSpeed, animList);

                facing = Direction.RIGHT;
                sprite = Images.flipHorizontal(sprites[2]);
                transitioning = true;
            }
        }
    }

    @Override
    public void render(Graphics g, int scale, int xOffset, int yOffset) {

        if (transitioning) {
            g.drawImage(walkAnim.getCurrentFrame(), x + xOffset, y + yOffset, width * scale, height * scale, null);

        } else {
            g.drawImage(sprite, x + xOffset, y + yOffset, width * scale, height * scale, null);
        }

    }

    @Override
    protected void walk(Direction direction) {

        switch (direction) {
            case RIGHT:
                x += speed;
                handler.getPMGameState().currentWorld.xOff -= speed;
                break;
            case LEFT:
                x -= speed;
                handler.getPMGameState().currentWorld.xOff += speed;
                break;
            case UP:
                y -= speed;
                handler.getPMGameState().currentWorld.yOff += speed;
                break;
            case DOWN:
                y += speed;
                handler.getPMGameState().currentWorld.yOff -= speed;
                break;
        }

    }
}

package Game.PacMan.entities.Dynamics;

import Game.PacMan.entities.Statics.BaseStatic;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;

public class Ghost extends BaseDynamic{

    protected double velX,velY;
    public String facing = "Left";
    public boolean moving = false;
    public boolean changeDirrection=false;
    int changeDirectionCounter=0;


    public Ghost(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.ghost);
    }

    @Override
    public void tick(){

        if (changeDirrection) {
            changeDirectionCounter++;
        }
        if(changeDirectionCounter>=3){
            changeDirrection=false;
            changeDirectionCounter=0;
        }

        if (facing.equals("Right") || facing.equals("Left")){
            checkMarioHorizontalCollision();
        }else{
            checkVerticalCollisions();
        }

    }

    public void checkVerticalCollisions() {
        Ghost mario = this;
        ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();

        boolean marioDies = false;
        boolean toUp = moving && facing.equals("Up");

        Rectangle marioBounds = toUp ? mario.getTopBounds() : mario.getBottomBounds();

        for (BaseStatic brick : bricks) {
            Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
            if (marioBounds.intersects(brickBounds)) {
                velY=0;
                if(toUp)
                    mario.setY(brick.getY() + mario.getDimension().height);
                else
                    mario.setY(brick.getY() - brick.getDimension().height);
            }
        }

        for(BaseDynamic enemy : enemies){
            Rectangle enemyBounds = !toUp ? enemy.getTopBounds() : enemy.getBottomBounds();
            if (marioBounds.intersects(enemyBounds)) {
                marioDies = true;
                break;
            }
        }

        if(marioDies) {
            handler.getMap().reset();
        }
    }



    public void checkMarioHorizontalCollision(){
        Ghost mario = this;
        ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();

        boolean marioDies = false;
        boolean toRight = moving && facing.equals("Right");

        Rectangle marioBounds = toRight ? mario.getRightBounds() : mario.getLeftBounds();

        for (BaseStatic brick : bricks) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (marioBounds.intersects(brickBounds)) {
                velX=0;
                if(toRight)
                    mario.setX(brick.getX() - mario.getDimension().width);
                else
                    mario.setX(brick.getX() + brick.getDimension().width);
            }
        }

        for(BaseDynamic enemy : enemies){
            Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
            if (marioBounds.intersects(enemyBounds)) {
                marioDies = true;
                break;
            }
        }

        if(marioDies) {
            handler.getMap().reset();
        }
    }


    public double getVelX() {
        return velX;
    }
    public double getVelY() {
        return velY;
    }


}

package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.Sword;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;
import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class Link extends BaseMovingEntity {


    private final int animSpeed = 120;
    int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
    public boolean movingMap = false;
    Direction movingTo;
    private int animationSpeed = 10;
    public BufferedImage fullHeart, halfHeart, emptyHeart;
    private int gotSwordCounter= 60*4;//4 seconds
    public boolean attacking=true;
    public Animation leftAnim,rightAnim,upAnim,downAnim;


    public Link(int x, int y, BufferedImage[] sprite, Handler handler) {
        super(x, y, sprite, handler);
        speed = 4;
        health = 6;
        BufferedImage[] animList = new BufferedImage[2];
        animList[0] = sprite[4];
        animList[1] = sprite[5];

        animation = new Animation(animSpeed,animList);
        
        fullHeart = Images.zeldaLife[0];
        halfHeart = Images.zeldaLife[1];
        emptyHeart = Images.zeldaLife[2];
 
        rightAnim = new Animation(128,Images.linkAttackingRight);
        upAnim = new Animation(128,Images.linkAttackingUp);
        downAnim = new Animation(128,Images.linkAttackingDown);
    }

    @Override
    public void tick() {
        if (movingMap){
            switch (movingTo) {
                case RIGHT:
                    handler.getZeldaGameState().cameraOffsetX+=animationSpeed;
                    newMapX+=animationSpeed;
                    if (xExtraCounter>0){
                        x+=2*animationSpeed;
                        xExtraCounter-=animationSpeed;
                        animation.tick();

                    }else{
                        x-=animationSpeed;
                    }
                    break;
                case LEFT:
                    handler.getZeldaGameState().cameraOffsetX-=animationSpeed;
                    
                    newMapX-=animationSpeed;
                    if (xExtraCounter>0){
                        x-=2*animationSpeed;
                        xExtraCounter-=animationSpeed;
                        animation.tick();

                    }else{
                        x+=animationSpeed;
                    }
                    break;
                case UP:
                    handler.getZeldaGameState().cameraOffsetY-=animationSpeed;
                    newMapY+=animationSpeed;
                    if (yExtraCounter>0){
                        y-=2*animationSpeed;
                        yExtraCounter-=animationSpeed;
                        animation.tick();

                    }else{
                        y+=animationSpeed;
                    }
                    break;
                case DOWN:
                    handler.getZeldaGameState().cameraOffsetY+=animationSpeed;
                    newMapY-=animationSpeed;
                    if (yExtraCounter>0){
                        y+=2*animationSpeed;
                        yExtraCounter-=animationSpeed;
                        animation.tick();
                    }else{
                        y-=animationSpeed;
                    }
                    break;
            }
            bounds = new Rectangle(x,y,width,height);
            changeIntersectingBounds();
            if (newMapX >=0 && newMapX <= animationSpeed && newMapY >= 0 && newMapY <= animationSpeed){
                movingMap = false;
                movingTo = null;
                newMapX = 0;
                newMapY = 0;
            }
        }else {
            if (handler.getKeyManager().up) {
                if (direction != UP) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = sprites[4];
                    animList[1] = sprites[5];
                    animation = new Animation(animSpeed, animList);
                    direction = UP;
                    sprite = sprites[4];
                }
                animation.tick();
                move(direction);

            } else if (handler.getKeyManager().down) {
                if (direction != DOWN) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = sprites[0];
                    animList[1] = sprites[1];
                    animation = new Animation(animSpeed, animList);
                    direction = DOWN;
                    sprite = sprites[0];
                }
                animation.tick();
                move(direction);
            } else if (handler.getKeyManager().left) {
                if (direction != Direction.LEFT) {
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
                if (direction != Direction.RIGHT) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = (sprites[2]);
                    animList[1] = (sprites[3]);
                    animation = new Animation(animSpeed, animList);
                    direction = Direction.RIGHT;
                    sprite = (sprites[3]);
                }
                animation.tick();
                move(direction);
            } else {
                moving = false;
            }
        }
        // gives Link one extra life
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)) {
        	if (handler.getZeldaGameState().link.health <= 8) {
        		handler.getZeldaGameState().link.health++;
        	}
        }
        // takes one life from Link
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_J)) {
        	if (handler.getZeldaGameState().link.health > 0) {
        		handler.getZeldaGameState().link.health--;
        	}
        }       
        ArrayList<SolidStaticEntities> toREmove = new ArrayList<>();
        for (SolidStaticEntities objects : handler.getZeldaGameState().caveObjects) {
        	if(ZeldaGameState.inCave) {
        		if(objects instanceof Sword) {
        			if(objects.bounds.intersects(interactBounds)) {
        				toREmove.add(objects);
        				handler.getMusicHandler().playEffect("zelda_Get_Item.wav");
        			}
        		}
        	}        	
        }
        for (SolidStaticEntities removing: toREmove){
        	handler.getZeldaGameState().caveObjects.remove(removing);
        }   


    }

    @Override
    public void render(Graphics g) {
        if (moving) {
            g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);

        } else {
        	if (movingMap){
        		g.drawImage(animation.getCurrentFrame(),x , y, width, height  , null);
        	}
        	g.drawImage(sprite, x , y, width , height , null);
        }

        // control the images of hearts of the lives that Link has at the moment
        if (handler.getZeldaGameState().link.health == 0) { // 0 lives
        	g.drawImage(emptyHeart, handler.getWidth()/2 - handler.getWidth()/4, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);	
        	g.drawImage(emptyHeart, handler.getWidth()/2 - handler.getWidth()/4 + 30, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        	g.drawImage(emptyHeart, handler.getWidth()/2 - handler.getWidth()/4 + 60, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        } else if (handler.getZeldaGameState().link.health == 1) { // 1 lives
        	g.drawImage(halfHeart, handler.getWidth()/2 - handler.getWidth()/4, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);	
        	g.drawImage(emptyHeart, handler.getWidth()/2 - handler.getWidth()/4 + 30, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        	g.drawImage(emptyHeart, handler.getWidth()/2 - handler.getWidth()/4 + 60, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        } else if (handler.getZeldaGameState().link.health == 2) { // 2 lives
        	g.drawImage(fullHeart, handler.getWidth()/2 - handler.getWidth()/4, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);	
        	g.drawImage(emptyHeart, handler.getWidth()/2 - handler.getWidth()/4 + 30, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        	g.drawImage(emptyHeart, handler.getWidth()/2 - handler.getWidth()/4 + 60, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        } else if (handler.getZeldaGameState().link.health == 3) {// 3 lives
        	g.drawImage(fullHeart, handler.getWidth()/2 - handler.getWidth()/4, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);	
        	g.drawImage(halfHeart, handler.getWidth()/2 - handler.getWidth()/4 + 30, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        	g.drawImage(emptyHeart, handler.getWidth()/2 - handler.getWidth()/4 + 60, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        } else if (handler.getZeldaGameState().link.health == 4) { // 4 lives
        	g.drawImage(fullHeart, handler.getWidth()/2 - handler.getWidth()/4, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);	
        	g.drawImage(fullHeart, handler.getWidth()/2 - handler.getWidth()/4 + 30, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        	g.drawImage(emptyHeart, handler.getWidth()/2 - handler.getWidth()/4 + 60, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        } else if (handler.getZeldaGameState().link.health == 5) {// 5 lives
        	g.drawImage(fullHeart, handler.getWidth()/2 - handler.getWidth()/4, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);	
        	g.drawImage(fullHeart, handler.getWidth()/2 - handler.getWidth()/4 + 30, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        	g.drawImage(halfHeart, handler.getWidth()/2 - handler.getWidth()/4 + 60, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        } else { // 6 lives
        	g.drawImage(fullHeart, handler.getWidth()/2 - handler.getWidth()/4, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);	
        	g.drawImage(fullHeart, handler.getWidth()/2 - handler.getWidth()/4 + 30, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);
        	g.drawImage(fullHeart, handler.getWidth()/2 - handler.getWidth()/4 + 60, handler.getHeight()/2 + handler.getHeight()/5 + 20, 30, 30, null);        		
        }

    }

    @Override
    public void move(Direction direction) {
        moving = true;
        changeIntersectingBounds();
        //check for collisions
        if (ZeldaGameState.inCave){
        	for (SolidStaticEntities objects : handler.getZeldaGameState().caveObjects) {
        		if ((objects instanceof DungeonDoor) && objects.bounds.intersects(bounds) && direction == ((DungeonDoor) objects).direction) {
        			if (((DungeonDoor) objects).name.equals("caveStartLeave")) {
        				ZeldaGameState.inCave = false;
        				x = ((DungeonDoor) objects).nLX;
        				y = ((DungeonDoor) objects).nLY;
        				direction = DOWN;
        			}
        		} else if (!(objects instanceof DungeonDoor) && objects.bounds.intersects(interactBounds)) {
        			//dont move
        			return;	
        		}
        	}
        }
        else {
        	for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
        		if ((objects instanceof SectionDoor) && objects.bounds.intersects(bounds) && direction == ((SectionDoor) objects).direction) {
        			if (!(objects instanceof DungeonDoor)) {
        				movingMap = true;
                        movingTo = ((SectionDoor) objects).direction;
                        switch (((SectionDoor) objects).direction) {
                            case RIGHT:
                                newMapX = -(((handler.getZeldaGameState().mapWidth) + 1) * worldScale);
                                newMapY = 0;
                                handler.getZeldaGameState().mapX++;
                                xExtraCounter = 8 * worldScale + (2 * worldScale);
                                break;
                            case LEFT:
                                newMapX = (((handler.getZeldaGameState().mapWidth) + 1) * worldScale);
                                newMapY = 0;
                                handler.getZeldaGameState().mapX--;
                                xExtraCounter = 8 * worldScale + (2 * worldScale);
                                break;
                            case UP:
                                newMapX = 0;
                                newMapY = -(((handler.getZeldaGameState().mapHeight) + 1) * worldScale);
                                handler.getZeldaGameState().mapY--;
                                yExtraCounter = 8 * worldScale + (2 * worldScale);
                                break;
                            case DOWN:
                                newMapX = 0;
                                newMapY = (((handler.getZeldaGameState().mapHeight) + 1) * worldScale);
                                handler.getZeldaGameState().mapY++;
                                yExtraCounter = 8 * worldScale + (2 * worldScale);
                                break;
                        }
                        return;
                    }
                    else {
                        if (((DungeonDoor) objects).name.equals("caveStartEnter")) {
                            ZeldaGameState.inCave = true;
                            x = ((DungeonDoor) objects).nLX;
                            y = ((DungeonDoor) objects).nLY;
                            direction = UP;
                        }
                    }
                }
                else if (!(objects instanceof SectionDoor) && objects.bounds.intersects(interactBounds)) {
                    //dont move
                    return;
                }
            }
        }
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

    }
}

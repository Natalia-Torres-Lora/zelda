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
	private final int atackAnimSpeed= 100;
	int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
	public boolean movingMap = false;
	Direction movingTo;
	private int animationSpeed = 10;
	public boolean linkGotSword =false;
	public boolean attacking=false;
	public Animation leftAnim,rightAnim,upAnim,downAnim;
	public Animation hurtRight, hurtLeft, hurtUp, hurtDown;
	public boolean hit=false;
	public int hitTimer = 15;
	private int pLX; //Previous link X before dungeon
	private int pLY; //Previous link Y before dungeon
	


	public Link(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed = 4;
		health = 6;
		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[4];
		animList[1] = sprite[5];

		animation = new Animation(animSpeed,animList);

		rightAnim = new Animation(atackAnimSpeed,Images.linkAttackingRight);
		upAnim = new Animation(atackAnimSpeed,Images.linkAttackingUp);
		downAnim = new Animation(atackAnimSpeed,Images.linkAttackingDown);
		leftAnim = new Animation(atackAnimSpeed, Images.linkAttackingLeft);
		
		//animation when link is hurt
		hurtUp = new Animation(30, Images.hurtUp);
		hurtDown = new Animation(30, Images.hurtDown);
		hurtRight = new Animation(30, Images.hurtRight);
		hurtLeft = new Animation(30, Images.hurtLeft);
		
		pLX = x;
		pLY = y;
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
			if(!hit) {
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
					sprite = Images.flipHorizontal(sprites[2]);
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
					sprite = (sprites[2]);
				}
				animation.tick();
				move(direction);
			} else {
				moving = false;
			}
			}
		}         
		if(attacking) {
			if(direction.equals(UP)) {
				upAnim.tick();
				if(upAnim.end) {
					attacking=false;
					upAnim.reset();
				}
			}else if(direction.equals(Direction.LEFT)) {
				leftAnim.tick();
				if(leftAnim.end) {
					attacking=false;
					leftAnim.reset();
				}
			}else if(direction.equals(DOWN)) {
				downAnim.tick();
				if(downAnim.end) {
					attacking=false;
					downAnim.reset();
				}
			}else if(direction.equals(Direction.RIGHT)) {
				rightAnim.tick();
				if(rightAnim.end) {
					attacking=false;
					rightAnim.reset();
				}
			}
		}		
		if(hit) {
			speed= -3;
			if(hitTimer<=0) {
				hitTimer=15;
				speed=4;
				hit = false;
			}else {
				if(direction.equals(UP)) {
					hurtUp.tick();
					if(hurtUp.end) {
						hurtUp.reset();
					}
				}else if(direction.equals(Direction.LEFT)) {
					hurtLeft.tick();
					if(hurtLeft.end) {
						hurtLeft.reset();
					}
				}else if(direction.equals(DOWN)) {
					hurtDown.tick();
					if(hurtDown.end) {
						hurtDown.reset();
					}
				}else if(direction.equals(Direction.RIGHT)) {
					hurtRight.tick();
					if(hurtRight.end) {
						hurtRight.reset();
					}
				}
				hitTimer--;	
				move(direction);
			}
		}
		
		// gives Link one extra life
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)) {
			if (handler.getZeldaGameState().link.health < 6) {
				handler.getZeldaGameState().link.health++;
			}
		}
		// takes one life from Link
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_J)) {
			if (handler.getZeldaGameState().link.health > 0) {
				handler.getZeldaGameState().link.health--;
			}
		} 
		if(linkGotSword) {
			if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)&& !attacking){
				attacking = true;
				moving=false;
			}
		}		
	}

	@Override
	public void render(Graphics g) {
		if (moving && !hit) {
			g.drawImage(animation.getCurrentFrame(),x ,y, width , height  , null);    		
		}
		else if (attacking && !hit) {
			if(direction.equals(UP)) {
				g.drawImage(upAnim.getCurrentFrame(), x, (y-15), width, (height +13), null);
			}else if(direction.equals(DOWN)) {
				g.drawImage(downAnim.getCurrentFrame(), x, y, width, (height +13), null);
			}else if(direction.equals(Direction.LEFT)) {
				g.drawImage(leftAnim.getCurrentFrame(), (x-15), y, (width +13), height, null);
			}else if(direction.equals(Direction.RIGHT)) {
				g.drawImage(rightAnim.getCurrentFrame(), x , y, (width+13), (height), null);
			}
		}
		else if (hit) {
			if(direction.equals(UP)) {
				g.drawImage(hurtUp.getCurrentFrame(), x, y, width, height, null);
			}else if(direction.equals(DOWN)) {
				g.drawImage(hurtDown.getCurrentFrame(), x, y, width, height, null);
			}else if(direction.equals(Direction.LEFT)) {
				g.drawImage(hurtLeft.getCurrentFrame(), x, y, width, height, null);
			}else if(direction.equals(Direction.RIGHT)) {
				g.drawImage(hurtRight.getCurrentFrame(), x , y, width, height, null);
			}
		}else if (handler.getZeldaGameState().linkGotItem){
			g.drawImage(Images.zeldaLinkFrames[7], x, y, width, height, null);
			g.drawImage(Images.items[handler.getZeldaGameState().selector], this.x+ 10, this.y -25, 20, 35, null);
		}else {
			if (movingMap){
				g.drawImage(animation.getCurrentFrame(),x , y, width, height  , null);
			}
			g.drawImage(sprite, x , y, width , height , null);
		}

	}

	@Override
	public void move(Direction direction) {
		moving = true;
		changeIntersectingBounds();
		//check for collisions
		if (ZeldaGameState.inCave){
			ArrayList<SolidStaticEntities> toREmove = new ArrayList<>();
			for (SolidStaticEntities objects : handler.getZeldaGameState().caveObjects) {
				if(objects instanceof Sword) {
					if(objects.bounds.intersects(interactBounds)) {
						toREmove.add(objects);
						handler.getMusicHandler().playEffect("zelda_Get_Item.wav");
						linkGotSword=true;
					}
					for (SolidStaticEntities removing: toREmove){
						handler.getZeldaGameState().caveObjects.remove(removing);
					} 
				}
				if ((objects instanceof DungeonDoor) && objects.bounds.intersects(bounds) && direction == ((DungeonDoor) objects).direction) {
					if (((DungeonDoor) objects).name.equals("caveStartLeave")) {
						ZeldaGameState.inCave = false;
						x = pLX;
						y = pLY;
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
							pLX = x;
							pLY = y;
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
			}for (BaseMovingEntity enemy : handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
				if(enemy.bounds.intersects(interactBounds)) {
					hit=true;
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

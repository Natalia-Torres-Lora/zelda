package Game.Zelda.Entities.Dynamic;

import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

public class Enemy1 extends BaseMovingEntity {

	private final int animSpeed = 150;
	public boolean newDirection= false;
	public int changeDirection;
	Random rand = new Random();
	public int newDirectionTimer=60;
	public int move=2;
	Animation startAnim;
	public boolean hit = false;
	public int hitTimer = 2;

	public Enemy1(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed=1;
		moving=false;
		direction = Direction.LEFT;
		startAnim = new Animation(300, Images.startEnemy);
		
		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[4];
		animList[1] = sprite[5];
		animation = new Animation(animSpeed,animList);

	}
	public void tick() {
		startAnim.tick();
		if(startAnim.end) {
			moving=true;

			switch(direction) {
			case UP:
				y-=speed;
				animation.tick();
				move(direction);
				break;
			case DOWN:
				y+=speed;
				animation.tick();
				move(direction);
				break;
			case LEFT:
				x-=speed;
				animation.tick();
				move(direction);
				break;
			case RIGHT:
				x+=speed;
				animation.tick();
				move(direction);
				break;
			}
			if(newDirection) {
				move = rand.nextInt(5);
				if(!(changeDirection==move)) {
					changeDirection=move;
				}
				if (changeDirection == 0 && direction != UP) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[0];
					animList[1] = sprites[1];
					animation = new Animation(animSpeed, animList);					
					direction = UP;				

				}else if (changeDirection == 1 && direction != DOWN) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[2];
					animList[1] = sprites[3];
					animation = new Animation(animSpeed, animList);					
					direction = DOWN;
				}else if (changeDirection == 2 && direction != Direction.LEFT) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[4];
					animList[1] = sprites[5];
					animation = new Animation(animSpeed, animList);									
					direction = Direction.LEFT;
				}else if (changeDirection == 3 && direction != Direction.RIGHT) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[6];
					animList[1] = sprites[7];
					animation = new Animation(animSpeed, animList);				
					direction = Direction.RIGHT;
				}else if(changeDirection > 3) {
					speed=0;
				}else {
					speed=1;
				}
			}
			if(newDirectionTimer<=0) {
				newDirection=true;
				newDirectionTimer=60;
			}
			else {
				newDirection =false;
				newDirectionTimer--;
			}
		}
		if(hit) {
			speed= -1;
			if(hitTimer<=0) {
				hit=false;
				hitTimer=2;
				speed=1;
				newDirection=true;
			}else {
				hitTimer--;	
				move(direction);
			}
		}
	}

	public void render(Graphics g) {
		if(moving) {
			g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);

		}else {
			g.drawImage(startAnim.getCurrentFrame(),x , y, width , height  , null);
			//			g.drawImage(sprite, x , y, width , height , null);
		}			
		
	}

	public void move(Direction direction) {
		moving=true;
		changeIntersectingBounds();

		for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
			if(objects.bounds.intersects(interactBounds)) {
				hit=true;				
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

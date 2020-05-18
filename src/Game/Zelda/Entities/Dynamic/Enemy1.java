package Game.Zelda.Entities.Dynamic;

import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Main.Handler;
import Resources.Animation;

import java.awt.*;
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
	public boolean hit = false;
	public int hitCooldown= 60*2;
	Link link;
	public boolean collision  = false;
	public int cooldown = 60*3;
	

	public Enemy1(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed=0;
		moving=true;
		direction = Direction.LEFT;

		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[4];
		animList[1] = sprite[5];
		animation = new Animation(animSpeed,animList);		
		

	}
	public void tick() {
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
			
			move = rand.nextInt(4);
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
				direction = UP;
				
				
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
			}else {
				newDirection=false;
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

	public void render(Graphics g) {
		if(moving) {
			g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);

		}else {
			g.drawImage(sprite, x , y, width , height , null);
		}
	}

	public void move(Direction direction) {
		moving=true;
		changeIntersectingBounds();

		for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
			if(objects.bounds.intersects(interactBounds)) {				
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

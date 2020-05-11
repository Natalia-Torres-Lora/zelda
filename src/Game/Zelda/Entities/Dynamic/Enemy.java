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
import java.util.Random;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;
import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class Enemy extends BaseMovingEntity {

	private final int animSpeed =120;
	//Direction facing;
	public String facing = "Left";
	private int animationSpeed = 10;
	public int move=2;
	public int change=0;
	public boolean newDirection= false;
	Random rand = new Random();
	public int newDirectionTimer=60;
	

	public Enemy(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed =1;
		//bounds = new Rectangle(x,y,width,height);

		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[4];
		animList[1] = sprite[5];

		animation = new Animation(animSpeed,animList);
//		facing = Direction.LEFT;
		moving = true;

	}
	public void tick() {
		if(moving) {
			switch (facing) {
			case "Up":
				y-=speed;
				animation.tick();
				break;
			case "Down":
				y+=speed;
				animation.tick();
				break;
			case "Left":
				x-=speed;
				animation.tick();
				break;
			case "Right":
				x+=speed;
				animation.tick();
				break;
			}
			 bounds = new Rectangle(x,y,width,height);
	         changeIntersectingBounds();
		}

		if(newDirection) {
			change = rand.nextInt(5);

			if(!(move == change)) {
				move=change;
				if(move==0 /*&& direction !=UP*/ ) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[0];
					animList[1] = sprites[1];
					animation = new Animation(animSpeed, animList);
					facing="Up";

				}else if(move==1 /*&& direction !=DOWN*/ ) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[2];
					animList[1] = sprites[3];
					animation = new Animation(animSpeed, animList);
					facing="Down";

				}else if(move==2 /*&& direction != Direction.LEFT */) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[4];
					animList[1] = sprites[5];
					animation = new Animation(animSpeed, animList);
					facing="Left";

				}else if(move==4 /*&& direction != Direction.RIGHT*/ ) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[6];
					animList[1] = sprites[7];
					animation = new Animation(animSpeed, animList);
					facing="Right";

				}else {
					newDirection=false;
				}
			}
		}
		
		if(newDirectionTimer<=0) {
			newDirection =true;
			newDirectionTimer=60;
		}else {
			newDirection =false;
			newDirectionTimer--;
		}

	}

	public void render(Graphics g) {
		if(moving) {
			g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);
		}
	}
	public void move(Direction direction) {
		moving=true;
		changeIntersectingBounds();

//		for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
//			if (objects.bounds.intersects(interactBounds)) {
//				newDirection=true;
//
//			}
//		}
//		switch (direction) {
//		case RIGHT:
//			x += speed;
//			break;
//		case LEFT:
//			x -= speed;
//
//			break;
//		case UP:
//			y -= speed;
//			break;
//		case DOWN:
//			y += speed;
//
//			break;
//		}
		bounds.x = x;
		bounds.y = y;
		changeIntersectingBounds();	
	}	
}

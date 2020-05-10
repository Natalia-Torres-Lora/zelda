package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.PacMan.entities.Statics.BaseStatic;
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
	
	//protected double velX,velY,speed = 1;
	public int speed=1;
	public String facing = "Left";
	public boolean moving = true,turnFlag = false;
	public Animation leftAnim,rightAnim,upAnim,downAnim;

	boolean colission = false;
	String colissionSide = "";
	//Random rand = new Random(chan);
	public int direction = 2;
	public int move = 0;
	
	public int change = 80;

	public Enemy(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		// TODO Auto-generated constructor stub

		leftAnim = new Animation(128,Images.enemy1left);
		rightAnim = new Animation(128,Images.enemy1right);
		upAnim = new Animation(128,Images.enemy1up);
		downAnim = new Animation(128,Images.enemy1down);


	}
	public void tick(){
		switch(facing) {
		case "UP":
			y-=speed;
			upAnim.tick();
			break;
		case "Down":
			x-=speed;
			downAnim.tick();
			break;
		case "Left":
			y-=speed;
			leftAnim.tick();
			break;
		case "Right":
			y+=speed;
			rightAnim.tick();
			break;
		
		}
		
		
		
		

	}
}



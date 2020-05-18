package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.World.MapBuilder;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/15/2020
 */
public class Sword extends SolidStaticEntities {
	public Sword(int x, int y,int width, int height, Handler handler) {
		super(x, y, Images.sword, handler);
		this.width = width;
		this.height = height;

		bounds = new Rectangle(x,y,width,height);
	}

	@Override
	public void tick() {
		if(ZeldaGameState.inCave) {
			super.tick();
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.sword, x,y,width, height, null);

	}
}

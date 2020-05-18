package Game.Zelda.Entities.Statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Main.Handler;
import Resources.Images;

public class Items extends SolidStaticEntities {

	public BufferedImage heart, diamond, bottle, ring, key1, key2;
//	public int selector = new Random().nextInt(11);

	public Items(int x, int y,int width, int height, Handler handler) {
		super(x, y, Images.items[0], handler);
		this.width = width;
		this.height = height;

		heart = Images.items[0];
		diamond = Images.items[1];
		bottle = Images.items[2];
		ring = Images.items[3];
		key1 = Images.items[4];
		key2 = Images.items[5];

		bounds = new Rectangle(x,y,width,height);
	}

	@Override
	public void tick() {
//		super.tick();

	}

	@Override
	public void render(Graphics g) {
		// this is to randomly choose an item as a gift when link kills an enemy
		switch(handler.getZeldaGameState().selector) {
		case 0: 
			g.drawImage(heart, x,y,width, height, null);
			break;
		case 1:
			g.drawImage(diamond, x,y,width, height, null);
			break;
		case 2:
			g.drawImage(bottle, x,y,width, height, null);
			break;
		case 3:
			g.drawImage(ring, x,y,width, height, null);
			break;
		case 4:
			g.drawImage(key1, x,y,width, height, null);
			break;
		case 5: 
			g.drawImage(key2, x,y,width, height, null);
			break;
		}

	}
}
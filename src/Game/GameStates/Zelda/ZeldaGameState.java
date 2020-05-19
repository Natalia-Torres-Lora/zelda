package Game.GameStates.Zelda;

import Game.GameStates.State;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.Entities.Dynamic.Enemy1;
import Game.Zelda.Entities.Dynamic.Link;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.Items;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.Sword;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaGameState extends State {
	private Animation caveFireAnim;

	public static int xOffset, yOffset, stageWidth, stageHeight, worldScale;
	public int cameraOffsetX, cameraOffsetY;
	public BufferedImage fullHeart, halfHeart, emptyHeart;
	public int enemyX, enemyY, selector;
	public int enemyKill = new Random().nextInt(5);
	public Items items;
	public int amountHeart = 0, amountDiamond = 0, amountBottle = 0, 
			amountRing = 0, amountKey1 = 0, amountKey2 = 0, count = 70;
	public boolean itemRemoved = false, linkGotItem = false; // check if Link took the item
	// map is 16 by 7 squares, you start at x=7,y=7 starts counting at 0
	public int mapX, mapY, mapWidth, mapHeight;

	public ArrayList<ArrayList<ArrayList<SolidStaticEntities>>> objects;
	public ArrayList<ArrayList<ArrayList<BaseMovingEntity>>> enemies;
	public Link link;
	public static boolean inCave = false;
	public ArrayList<SolidStaticEntities> caveObjects;

    public ZeldaGameState(Handler handler) {
        super(handler);
        caveFireAnim = new Animation(150,Images.caveFire);
        xOffset = handler.getWidth()/4;
        yOffset = handler.getHeight()/4;
        stageWidth = handler.getWidth()/3 + (handler.getWidth()/15);
        stageHeight = handler.getHeight()/2;
        worldScale = 3;
        mapX = 7;
        mapY = 7;
        mapWidth = 256;
        mapHeight = 176;
        cameraOffsetX =  ((mapWidth*mapX) + mapX + 1)*worldScale;
        cameraOffsetY = ((mapHeight*mapY) + mapY + 1)*worldScale;
        objects = new ArrayList<>();
        enemies = new ArrayList<>();
        caveObjects = new ArrayList<>();
        for (int i =0;i<16;i++){
            objects.add(new ArrayList<>());
            enemies.add(new ArrayList<>());
            for (int j =0;j<8;j++) {
                objects.get(i).add(new ArrayList<>());
                enemies.get(i).add(new ArrayList<>());
            }
        }

        addWorldObjects();

        link = new Link(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.zeldaLinkFrames,handler);  
        
        fullHeart = Images.zeldaLife[0];
		halfHeart = Images.zeldaLife[1];
		emptyHeart = Images.zeldaLife[2];
		
		selector = new Random().nextInt(6);

	}

	@Override
	public void tick() {
		link.tick();
		if (inCave) {
			caveFireAnim.tick();
			handler.getMusicHandler().stopMusic();
		} else {
			if (!link.movingMap) {
				for (SolidStaticEntities entity : objects.get(mapX).get(mapY)) {
					entity.tick();
				}
				ArrayList<BaseMovingEntity> enemiesToRemove = new ArrayList<BaseMovingEntity>();
				for (BaseMovingEntity entity : enemies.get(mapX).get(mapY)) {
					entity.tick();
					if (entity.getInteractBounds().intersects(link.getInteractBounds())) {
						if (link.attacking) {
							enemiesToRemove.add(entity);
							if (enemyKill == selector) {
								objects.get(mapX).get(mapY).add(new Items(entity.x + 80, entity.y + 80, 25, 40, handler));
							}
						} else {
							link.damage(1);
						}
					}
				}
				for (BaseMovingEntity enemy : enemiesToRemove) {
					enemies.get(mapX).get(mapY).remove(enemy);
				}
				// if Link kills an enemy, get a gift
				ArrayList<SolidStaticEntities> itemsToRemove = new ArrayList<SolidStaticEntities>();
				for (SolidStaticEntities items : objects.get(mapX).get(mapY)) {
					// check if the object is an instance of Items class
					if (items instanceof Items) {
						if (items.bounds.intersects(link.getInteractBounds())) {
							itemsToRemove.add(items); //when Link touches the item, it is removed
							itemRemoved = true;
							linkGotItem = true;
						}
					}
				}
				for (SolidStaticEntities item : itemsToRemove) {
					objects.get(mapX).get(mapY).remove(item);
				}
				//this is to add the amount to the item that Link takes
				if (itemRemoved) {
					switch(selector) {
					case 0:
						link.health++;
						itemRemoved = false;
						break;
					case 1:
						amountDiamond++;
						itemRemoved = false;
						break;
					case 2:
						amountBottle++;
						itemRemoved = false;
						break;
					case 3:
						amountRing++;
						itemRemoved = false;
						break;
					case 4:
						amountKey1++;
						itemRemoved = false;
						break;
					case 5:
						amountKey2++;
						itemRemoved = false;
						break;
					}
				}
				if (linkGotItem) {
					count--;
					if (count <= 0) {
						linkGotItem = false;
//						selector = new Random().nextInt(6);
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (inCave) {
			g.drawImage(caveFireAnim.getCurrentFrame(), xOffset + (stageWidth / 4), yOffset + (stageHeight / 3), 32, 32,
					null);
			g.drawImage(caveFireAnim.getCurrentFrame(), xOffset + stageWidth - stageWidth / 4 - 32,
					yOffset + (stageHeight / 3), 32, 32, null);
			g.drawImage(Images.oldMan, xOffset + (stageWidth / 2) - 20, yOffset + (stageHeight / 3), 32, 32, null);
			for (SolidStaticEntities entity : caveObjects) {
				entity.render(g);
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 28));
			g.drawString("  IT ' S  DANGEROUS  TO  GO", (3 * (ZeldaGameState.stageWidth / 16)) + ZeldaGameState.xOffset,
					(2 * (ZeldaGameState.stageHeight / 11)) + ZeldaGameState.yOffset + ((16 * worldScale)));
			g.drawString("  ALONE !   TAKE  THIS", (4 * (ZeldaGameState.stageWidth / 16)) + ZeldaGameState.xOffset,
					(4 * (ZeldaGameState.stageHeight / 11)) + ZeldaGameState.yOffset - ((16 * worldScale) / 2));
			link.render(g);
		} else {
			g.drawImage(Images.zeldaMap, -cameraOffsetX + xOffset, -cameraOffsetY + yOffset,
					Images.zeldaMap.getWidth() * worldScale, Images.zeldaMap.getHeight() * worldScale, null);
			if (!link.movingMap) {
				for (SolidStaticEntities entity : objects.get(mapX).get(mapY)) {
						entity.render(g);
				}
				for (BaseMovingEntity entity : enemies.get(mapX).get(mapY)) {
					entity.render(g);
				}
			}

			link.render(g);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, xOffset, handler.getHeight());
			g.fillRect(xOffset + stageWidth, 0, handler.getWidth(), handler.getHeight());
			g.fillRect(0, 0, handler.getWidth(), yOffset);
			g.fillRect(0, yOffset + stageHeight, handler.getWidth(), handler.getHeight());

			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 28));
			//Diamond item
			g.drawImage(Images.items[1], 52, handler.getHeight()/2 -140, 30, 45, null);
			g.drawString("x" + amountDiamond, 90, handler.getHeight()/2 -100);
			//Bottle item
			g.drawImage(Images.items[2], 55, handler.getHeight()/2 -70, 25, 45, null);
			g.drawString("x" + amountBottle, 90, handler.getHeight()/2 -30);
			//Ring item
			g.drawImage(Images.items[3], 55, handler.getHeight()/2 -10, 25, 45, null);
			g.drawString("x" + amountRing, 90, handler.getHeight()/2 +30);
			//Key1 item
			g.drawImage(Images.items[4], 55, handler.getHeight()/2 +60, 25, 45, null);
			g.drawString("x" + amountKey1, 90, handler.getHeight()/2 +100);
			//Key2 item
			g.drawImage(Images.items[5], 55, handler.getHeight()/2 + 125, 25, 45, null);
			g.drawString("x" + amountKey2, 90, handler.getHeight()/2 + 165);

			// control the images of hearts of the lives that Link has at the moment
			if (handler.getZeldaGameState().link.health <= 0) { // 0 lives
				g.drawImage(emptyHeart, handler.getWidth() / 2 + 130, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(emptyHeart, handler.getWidth() / 2 + 160, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(emptyHeart, handler.getWidth() / 2 + 190, handler.getHeight() / 8 + 80, 30, 30, null);
			} else if (handler.getZeldaGameState().link.health == 1) { // 1 lives
				g.drawImage(halfHeart, handler.getWidth() / 2 + 130, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(emptyHeart, handler.getWidth() / 2 + 160, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(emptyHeart, handler.getWidth() / 2 + 190, handler.getHeight() / 8 + 80, 30, 30, null);
			} else if (handler.getZeldaGameState().link.health == 2) { // 2 lives
				g.drawImage(fullHeart, handler.getWidth() / 2 + 130, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(emptyHeart, handler.getWidth() / 2 + 160, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(emptyHeart, handler.getWidth() / 2 + 190, handler.getHeight() / 8 + 80, 30, 30, null);
			} else if (handler.getZeldaGameState().link.health == 3) {// 3 lives
				g.drawImage(fullHeart, handler.getWidth() / 2 + 130, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(halfHeart, handler.getWidth() / 2 + 160, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(emptyHeart, handler.getWidth() / 2 + 190, handler.getHeight() / 8 + 80, 30, 30, null);
			} else if (handler.getZeldaGameState().link.health == 4) { // 4 lives
				g.drawImage(fullHeart, handler.getWidth() / 2 + 130, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(fullHeart, handler.getWidth() / 2 + 160, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(emptyHeart, handler.getWidth() / 2 + 190, handler.getHeight() / 8 + 80, 30, 30, null);
			} else if (handler.getZeldaGameState().link.health == 5) {// 5 lives
				g.drawImage(fullHeart, handler.getWidth() / 2 + 130, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(fullHeart, handler.getWidth() / 2 + 160, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(halfHeart, handler.getWidth() / 2 + 190, handler.getHeight() / 8 + 80, 30, 30, null);
			} else if (handler.getZeldaGameState().link.health >= 6) { // 6 lives
				g.drawImage(fullHeart, handler.getWidth() / 2 + 130, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(fullHeart, handler.getWidth() / 2 + 160, handler.getHeight() / 8 + 80, 30, 30, null);
				g.drawImage(fullHeart, handler.getWidth() / 2 + 190, handler.getHeight() / 8 + 80, 30, 30, null);
			}
		}
	}
	private void addWorldObjects() {
		// cave
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 11; j++) {
				if (i >= 2 && i <= 13 && j >= 2 && j < 9) {
					continue;
				} else {
					if (j >= 9) {
						if (i > 1 && i < 14) {
							if ((i == 7 || i == 8)) {
								continue;
							} else {
								caveObjects.add(new SolidStaticEntities(i, j, Images.caveTiles.get(2), handler));
							}
						} else {
							caveObjects.add(new SolidStaticEntities(i, j, Images.caveTiles.get(5), handler));
						}
					} else {
						caveObjects.add(new SolidStaticEntities(i, j, Images.caveTiles.get(5), handler));
					}
				}
			}
		}
			
		
		caveObjects.add(new DungeonDoor(7, 9, 16 * worldScale * 2, 16 * worldScale * 2, Direction.DOWN,
				"caveStartLeave", handler, (4 * (ZeldaGameState.stageWidth / 16)) + ZeldaGameState.xOffset,
				(2 * (ZeldaGameState.stageHeight / 11)) + ZeldaGameState.yOffset));
		caveObjects.add(new Sword(xOffset + (stageWidth / 2) - 15, yOffset + (stageHeight / 2), 15, 30, handler));

		// 7,7
		ArrayList<SolidStaticEntities> solids = new ArrayList<>();
		ArrayList<BaseMovingEntity> monster = new ArrayList<>();
		solids.add(new SectionDoor(0, 5, 16 * worldScale, 16 * worldScale, Direction.LEFT, handler));
		solids.add(new SectionDoor(7, 0, 16 * worldScale * 2, 16 * worldScale, Direction.UP, handler));
		solids.add(new DungeonDoor(4, 1, 16 * worldScale, 16 * worldScale, Direction.UP, "caveStartEnter", handler,
				(7 * (ZeldaGameState.stageWidth / 16)) + ZeldaGameState.xOffset,
				(9 * (ZeldaGameState.stageHeight / 11)) + ZeldaGameState.yOffset));
		solids.add(new SectionDoor(15, 5, 16 * worldScale, 16 * worldScale, Direction.RIGHT, handler));
		solids.add(new SolidStaticEntities(6, 0, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(5, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(6, 1, Images.forestTiles.get(6), handler));
		solids.add(new SolidStaticEntities(3, 2, Images.forestTiles.get(6), handler));
		solids.add(new SolidStaticEntities(2, 3, Images.forestTiles.get(6), handler));
		solids.add(new SolidStaticEntities(1, 4, Images.forestTiles.get(6), handler));
		solids.add(new SolidStaticEntities(1, 6, Images.forestTiles.get(3), handler));
		solids.add(new SolidStaticEntities(1, 7, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(1, 8, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(2, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(3, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(4, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(5, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(6, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(7, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(8, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(9, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(10, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(11, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(12, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(13, 9, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(14, 8, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(14, 7, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(14, 6, Images.forestTiles.get(2), handler));
		solids.add(new SolidStaticEntities(14, 4, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(13, 4, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(12, 4, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(11, 4, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(10, 4, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(9, 4, Images.forestTiles.get(4), handler));
		solids.add(new SolidStaticEntities(9, 3, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(9, 2, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(9, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(9, 0, Images.forestTiles.get(5), handler));
		monster.add(new Enemy1(xOffset + (stageWidth / 4), yOffset + (stageHeight / 2), Images.enemy1, handler));
		monster.add(new Enemy1(xOffset + (stageWidth / 4), yOffset + (stageHeight / 3), Images.enemy1, handler));
		objects.get(7).set(7, solids);
		enemies.get(7).set(7, monster);
		
		//6,7
        monster = new ArrayList<>();
        solids = new ArrayList<>();
        solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*7, Direction.LEFT,handler));
        solids.add(new SectionDoor( 12,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SolidStaticEntities(1,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(2,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(3,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(5,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(7,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(8,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(10,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(11,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,0,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,2,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,3,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(1,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(3,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(5,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(7,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(9,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(11,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(13,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,9,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,8,Images.forestTiles.get(5),handler));
        objects.get(6).set(7,solids);
        
        //7,6
        monster = new ArrayList<>();
        solids = new ArrayList<>();
        solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
        solids.add(new SectionDoor( 7,10,16*worldScale * 2,16*worldScale,Direction.DOWN,handler));
        solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));
        solids.add(new SolidStaticEntities(1,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(0,3,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(2,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(3,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(4,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(5,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(6,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(7,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(8,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(9,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(10,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(9,2,Images.forestTiles.get(4),handler));
        solids.add(new SolidStaticEntities(10,2,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(11,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(12,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(13,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(14,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(14,2,Images.forestTiles.get(4),handler));
        solids.add(new SolidStaticEntities(15,2,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(0,8,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(0,9,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(1,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(2,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(4,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(5,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(6,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(10,9,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(12,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(14,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,9,Images.forestTiles.get(5),handler));
        objects.get(7).set(6,solids);

		// 8,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0, 5, 16 * worldScale, 16 * worldScale, Direction.LEFT, handler));
		solids.add(new SectionDoor(2, 0, 16 * worldScale * 13, 16 * worldScale, Direction.UP, handler));
		solids.add(new SectionDoor(15, 2, 16 * worldScale, 16 * worldScale * 7, Direction.RIGHT, handler));
		solids.add(new SolidStaticEntities(0, 0, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 2, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 3, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 7, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 8, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 9, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 10, Images.forestTiles.get(5), handler));
		objects.get(8).set(7, solids);
        
        //5,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(15, 2, 16 * worldScale, 16 * worldScale * 7, Direction.RIGHT, handler));
		solids.add(new SectionDoor(4, 0, 16 * worldScale * 2, 16 * worldScale, Direction.UP, handler));
		solids.add(new DungeonDoor(2, 1, 10 * worldScale, 10 * worldScale, Direction.UP, "caveStartEnter", handler,
				(7 * (ZeldaGameState.stageWidth / 16)) + ZeldaGameState.xOffset,
				(9 * (ZeldaGameState.stageHeight / 11)) + ZeldaGameState.yOffset));
		solids.add(new SolidStaticEntities(2, 0, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 3, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 4, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 5, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 6, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 7, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 8, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(0, 9, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(3, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(2, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(4, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(6, 2, Images.forestTiles.get(11), handler));
		solids.add(new SolidStaticEntities(6, 3, Images.forestTiles.get(11), handler));
		solids.add(new SolidStaticEntities(6, 4, Images.forestTiles.get(11), handler));
		solids.add(new SolidStaticEntities(6, 5, Images.forestTiles.get(11), handler));
		solids.add(new SolidStaticEntities(6, 6, Images.forestTiles.get(11), handler));
		solids.add(new SolidStaticEntities(6, 7, Images.forestTiles.get(11), handler));
		solids.add(new SolidStaticEntities(6, 8, Images.forestTiles.get(11), handler));
		solids.add(new SolidStaticEntities(6, 9, Images.forestTiles.get(11), handler));
		solids.add(new SolidStaticEntities(6, 10, Images.forestTiles.get(11), handler));
		solids.add(new SolidStaticEntities(8, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(9, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(10, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(11, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(12, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(13, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(14, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(8, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(9, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(10, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(11, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(12, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(13, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(14, 10, Images.forestTiles.get(5), handler));
		objects.get(5).set(7, solids);
        objects.get(5).set(7,solids);
        
        //6,6
        monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor(0, 2, 16 * worldScale, 16 * worldScale * 7, Direction.LEFT, handler));
		solids.add(new SectionDoor(12, 11, 16 * worldScale * 2, 16 * worldScale, Direction.DOWN, handler));
		solids.add(new SectionDoor(15, 4, 16 * worldScale, 16 * worldScale * 3, Direction.RIGHT, handler));
		solids.add(new DungeonDoor(8, 1, 10 * worldScale, 10 * worldScale, Direction.UP, "caveStartEnter", handler,
				(7 * (ZeldaGameState.stageWidth / 16)) + ZeldaGameState.xOffset,
				(9 * (ZeldaGameState.stageHeight / 11)) + ZeldaGameState.yOffset));
		solids.add(new SolidStaticEntities(0, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(1, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(2, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(3, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(4, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(5, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(6, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(10, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(12, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(13, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(14, 1, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(15, 3, Images.forestTiles.get(4), handler));
		solids.add(new SolidStaticEntities(0, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(1, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(3, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(4, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(5, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(7, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(8, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(10, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(11, 10, Images.forestTiles.get(5), handler));
		solids.add(new SolidStaticEntities(15, 10, Images.forestTiles.get(5), handler));
		objects.get(6).set(6, solids);
        
        //8,6
        monster = new ArrayList<>();
        solids = new ArrayList<>();
        solids.add(new SectionDoor( 2,10,16*worldScale * 13,16*worldScale,Direction.DOWN,handler));
        solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale*3,Direction.LEFT,handler));
        solids.add(new SolidStaticEntities(0,0,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(0,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(0,2,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(0,10,Images.forestTiles.get(5),handler));
        objects.get(8).set(6,solids);
        
        //5,6
        monster = new ArrayList<>();
        solids = new ArrayList<>();
        solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*7, Direction.RIGHT,handler));
        solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*7, Direction.LEFT,handler));
        solids.add(new SectionDoor( 4,10,16*worldScale * 2,16*worldScale,Direction.DOWN,handler));
        solids.add(new SolidStaticEntities(0,0,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(0,1,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(1,0,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(1,1,Images.forestTiles.get(6),handler));
        solids.add(new SolidStaticEntities(0,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(1,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(2,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(6,0,Images.forestTiles.get(11),handler));
        solids.add(new SolidStaticEntities(6,2,Images.forestTiles.get(11),handler));
        solids.add(new SolidStaticEntities(6,3,Images.forestTiles.get(11),handler));
        solids.add(new SolidStaticEntities(6,4,Images.forestTiles.get(11),handler));
        solids.add(new SolidStaticEntities(6,7,Images.forestTiles.get(11),handler));
        solids.add(new SolidStaticEntities(6,8,Images.forestTiles.get(11),handler));
        solids.add(new SolidStaticEntities(6,9,Images.forestTiles.get(11),handler));
        solids.add(new SolidStaticEntities(8,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(9,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(10,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(12,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(14,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,10,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,0,Images.forestTiles.get(5),handler));
        solids.add(new SolidStaticEntities(15,1,Images.forestTiles.get(5),handler));
        objects.get(5).set(6,solids);
    }

    @Override
    public void refresh() {

    }
}

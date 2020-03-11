package Game.GameStates.PocketStates;

import Game.GameStates.State;
import Game.PocketMonster.Entities.BaseSolidEntity;
import Game.PocketMonster.Entities.EntityManager;
import Game.PocketMonster.Entities.PokePlayer;
import Game.PocketMonster.World.BaseWorld;
import Game.PocketMonster.World.Camera;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

/**
 * Created by AlexVR on 3/9/2020
 */
public class PocketMonsterGameState extends State {

    public EntityManager entityManager;
    public BaseWorld pallet;
    public BaseWorld currentWorld;

    int startX,startY,stageWidth,stageHeight;
    int scale = 6;
    int selector = 0;


    public PocketMonsterGameState(Handler handler) {
        super(handler);
        pallet = new BaseWorld(Images.PokePallet[0],0,0,handler);
        entityManager = new EntityManager(handler,new PokePlayer(Images.player,(3*16*scale)-(1*scale),(6*16*scale),scale,"Player",handler));
        startX =((handler.getWidth()/2)-((Images.PokeCopyRight.getWidth()/2)*scale));
        startY = (handler.getHeight()/2)-((Images.PokeCopyRight.getHeight()/2)*scale);
        stageWidth = Images.Pokelogo[0].getWidth()*scale;
        stageHeight = Images.Pokelogo[0].getHeight()*scale;
        currentWorld = pallet;
//        pallet.walls.add(new BaseSolidEntity(Images.pocketMonsterPalletSpriteSheet.crop(0,0,1,1),startX+ (16*scale)-(16*scale),startY + (-16*scale*2),(16*scale),(stageHeight),"WallLeft",handler));
//        pallet.walls.add(new BaseSolidEntity(Images.pocketMonsterPalletSpriteSheet.crop(0,0,1,1),startX+ (16*scale)-(16*scale) + pallet.image.getWidth(),startY + (-16*scale*2),(16*scale),(stageHeight),"WallRight",handler));
//        pallet.walls.add(new BaseSolidEntity(Images.pocketMonsterPalletSpriteSheet.crop(0,0,1,1),startX+ (16*scale) ,startY + (-16*scale*2),(stageWidth),(16*scale),"WallTop",handler));
//        pallet.walls.add(new BaseSolidEntity(Images.pocketMonsterPalletSpriteSheet.crop(0,0,1,1),startX+ (16*scale),startY + (-16*scale*2)+pallet.image.getHeight(),stageWidth,(16*scale),"WallBot",handler));


    }

    @Override
    public void tick() {
        entityManager.tick();
    }

    @Override
    public void render(Graphics g) {

        currentWorld.render(g,scale,startX + (16*scale) ,startY + (-16*scale*2) );
        entityManager.render(g,scale,startX + (16*scale),startY+ (-16*scale*2));

        g.fillRect(0,0,startX,handler.getHeight());
        g.fillRect(startX+stageWidth,0,handler.getWidth(),handler.getHeight());
        g.fillRect(0,0,handler.getWidth(),startY);
        g.fillRect(0,startY+stageHeight,handler.getWidth(),handler.getHeight());
}

    @Override
    public void refresh() {

    }
}

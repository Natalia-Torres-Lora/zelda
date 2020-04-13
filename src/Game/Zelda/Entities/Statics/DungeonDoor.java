package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.Direction;
import Main.Handler;

import java.awt.*;

/**
 * Created by AlexVR on 3/15/2020
 */
public class DungeonDoor extends SectionDoor {
    public Direction direction;
    public String name;
    public int nLX,nLY;
    public DungeonDoor(int x, int y, int widht, int height, Direction direction,String name, Handler handler,int newLinkX,int newLinkY) {
        super(x, y,widht,height,direction, handler);
        this.width = widht;
        this.height = height;
        bounds = new Rectangle((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);
        this.direction = direction;
        this.name = name;
        nLX = newLinkX;
        nLY = newLinkY;
    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    public void render(Graphics g) {

//        g.setColor(Color.YELLOW);
//        g.fillRect((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);

    }
}

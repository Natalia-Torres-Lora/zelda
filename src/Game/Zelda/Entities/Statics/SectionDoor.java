package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.World.MapBuilder;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/15/2020
 */
public class SectionDoor extends SolidStaticEntities {
    public Direction direction;
    public SectionDoor(int x, int y,int widht, int height, Direction direction, Handler handler) {
        super(x, y, null, handler);
        this.width = widht;
        this.height = height;
        bounds = new Rectangle((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);
        this.direction = direction;
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

package Game.GameStates.Zelda;

import Game.GameStates.State;
import Game.Zelda.World.Map;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaLGameState extends State {


    static int xOffset,yOffset,stageWidth,stageHeight,worldScale;
    Map map;



    public ZeldaLGameState(Handler handler, Map map) {
        super(handler);
        xOffset = handler.getWidth()/3;
        yOffset = handler.getHeight()/4;
        stageWidth = handler.getWidth()/2;
        stageHeight = handler.getHeight()/2;
        worldScale = 4;
        this.map = map;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        map.drawMap(((Graphics2D)g));
        g.setColor(Color.BLACK);
        g.fillRect(0,0,xOffset,handler.getHeight());
        g.fillRect(xOffset+stageWidth,0,handler.getWidth(),handler.getHeight());
        g.fillRect(0,0,handler.getWidth(),yOffset);
        g.fillRect(0,yOffset+stageHeight,handler.getWidth(),handler.getHeight());

    }

    @Override
    public void refresh() {

    }
}

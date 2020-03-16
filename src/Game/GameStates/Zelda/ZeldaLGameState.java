package Game.GameStates.Zelda;

import Game.GameStates.State;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.World.Map;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaLGameState extends State {


    public static int worldScale;
    Map map;



    public ZeldaLGameState(Handler handler, Map map) {
        super(handler);
        worldScale = 4;
        this.map = map;

    }

    @Override
    public void tick() {
        for (BaseMovingEntity entity: map.getEnemiesOnMap()){
            entity.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        map.drawMap(((Graphics2D)g));


    }

    @Override
    public void refresh() {

    }
}

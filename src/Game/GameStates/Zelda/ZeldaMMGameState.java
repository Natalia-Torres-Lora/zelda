package Game.GameStates.Zelda;

import Game.GameStates.State;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.MMBaseMovingEntity;
import Game.Zelda.Entities.MMBaseEntity;
import Game.Zelda.World.Map;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaMMGameState extends State {


    public Map map;



    public ZeldaMMGameState(Handler handler, Map map) {
        super(handler);
        this.map = map;

    }

    @Override
    public void tick() {
        for (MMBaseMovingEntity entity: map.getEnemiesOnMap()){
            entity.tick();
        }
        for (MMBaseEntity entity: map.getBlocksOnMap()){
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

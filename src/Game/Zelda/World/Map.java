package Game.Zelda.World;

import Game.GameStates.Zelda.ZeldaLGameState;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.LLink;
import Game.Zelda.Entities.Dynamic.Link;
import Main.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Map {

    ArrayList<BaseEntity> blocksOnMap;
    ArrayList<BaseMovingEntity> enemiesOnMap;
    Handler handler;
    public LLink link;
    public int xOffset =0,yOffset = 0;

    public Map(Handler handler) {
        this.handler=handler;
        this.blocksOnMap = new ArrayList<>();
        this.enemiesOnMap = new ArrayList<>();
    }

    public void addBlock(BaseEntity block){
        blocksOnMap.add(block);
    }

    public void addEnemy(BaseMovingEntity entity){
        enemiesOnMap.add(entity);
    }

    public void drawMap(Graphics2D g2) {
        for (BaseEntity block:blocksOnMap) {
            g2.drawImage(block.sprite, block.x + xOffset, block.y + yOffset, block.width * ZeldaLGameState.worldScale, block.height * ZeldaLGameState.worldScale, null);
        }
        for (BaseMovingEntity entity:enemiesOnMap) {
            if (entity instanceof LLink){
                entity.render(g2);
            }else {
                g2.drawImage(entity.sprite, entity.x + xOffset, entity.y + yOffset, entity.width * ZeldaLGameState.worldScale, entity.height * ZeldaLGameState.worldScale, null);
            }

        }

    }

    public ArrayList<BaseEntity> getBlocksOnMap() {
        return blocksOnMap;
    }

    public ArrayList<BaseMovingEntity> getEnemiesOnMap() {
        return enemiesOnMap;
    }

    public void reset() {
    }
}

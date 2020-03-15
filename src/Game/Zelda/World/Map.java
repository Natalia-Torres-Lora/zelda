package Game.Zelda.World;

import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.Zelda.Entities.BaseEntity;
import Main.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Map {

    ArrayList<BaseEntity> blocksOnMap;
    ArrayList<BaseDynamic> enemiesOnMap;
    Handler handler;

    public Map(Handler handler) {
        this.handler=handler;
        this.blocksOnMap = new ArrayList<>();
        this.enemiesOnMap = new ArrayList<>();
    }

    public void addBlock(BaseEntity block){
        blocksOnMap.add(block);
    }

    public void addEnemy(BaseDynamic entity){
        enemiesOnMap.add(entity);
    }

    public void drawMap(Graphics2D g2) {
        for (BaseEntity block:blocksOnMap) {
            g2.drawImage(block.sprite, block.x, block.y, block.width, block.height, null);
        }
        for (BaseDynamic entity:enemiesOnMap) {

            g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);

        }

    }

    public ArrayList<BaseEntity> getBlocksOnMap() {
        return blocksOnMap;
    }

    public ArrayList<BaseDynamic> getEnemiesOnMap() {
        return enemiesOnMap;
    }

    public void reset() {
    }
}

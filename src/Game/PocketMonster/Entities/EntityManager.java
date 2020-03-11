package Game.PocketMonster.Entities;

import Main.Handler;

import java.awt.*;

/**
 * Created by AlexVR on 3/11/2020
 */
public class EntityManager {

    Handler handler;
    public PokePlayer player;

    public EntityManager(Handler handler, PokePlayer player) {
        this.handler = handler;
        this.player = player;
    }

    public void tick(){
        player.tick();
    }

    public void render(Graphics g,int scale,int xOffset,int yOffset) {
        player.render(g,scale,xOffset,yOffset);
    }
}

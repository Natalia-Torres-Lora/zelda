package Game.Zelda.Entities;

import Game.GameStates.Zelda.ZeldaMapMakerState;
import Game.Zelda.World.MapBuilder;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;

/**
 * Created by AlexVR on 3/14/2020
 */
public class MMBaseEntity {
    public int x,y,width,height;
    public BufferedImage sprite;
    public Rectangle bounds;
    public Handler handler;

    public MMBaseEntity(int x, int y, BufferedImage sprite, Handler handler) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.handler = handler;
        if (sprite!=null) {
            width = sprite.getWidth() * ZeldaMapMakerState.scale;
            height = sprite.getHeight()  * ZeldaMapMakerState.scale;
        }else{
            width = 1;
            height = 1;
        }
        bounds = new Rectangle(x,y,width,height);

    }

    public void tick(){

    }

    public void render(Graphics g) {

    }
}

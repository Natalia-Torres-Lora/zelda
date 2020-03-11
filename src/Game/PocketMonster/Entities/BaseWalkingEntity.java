package Game.PocketMonster.Entities;

import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/11/2020
 */
public class BaseWalkingEntity extends BaseInteractableEntity {

    int scale;

    public BaseWalkingEntity(BufferedImage[] sprite, int x, int y,int scale, String name, Handler handler) {
        super(sprite, x, y, name, handler);
        this.scale = scale;
        this.sprite = sprite[0];
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics g,int scale,int xOffset,int yOffset) {
        super.render(g,scale,xOffset,yOffset);
    }

    protected void walk(Direction direction) {
    }
}

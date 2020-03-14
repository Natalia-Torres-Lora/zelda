package Game.GameStates.Zelda;

import Game.GameStates.State;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaGameCobyStates extends State {

    int counter = 0;

    public ZeldaGameCobyStates(Handler handler) {
        super(handler);


    }

    @Override
    public void tick() {



    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Images.zeldaTiles.get(counter),0,0,Images.zeldaTiles.get(counter).getWidth(),Images.zeldaTiles.get(counter).getHeight(),null);

        //size check
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)){
            counter++;
        }

    }

    @Override
    public void refresh() {

    }
}

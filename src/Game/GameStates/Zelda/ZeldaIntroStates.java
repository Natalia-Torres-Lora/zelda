package Game.GameStates.Zelda;

import Game.GameStates.State;
import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaIntroStates extends State {


    public ZeldaIntroStates(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            handler.changeState(handler.getZeldaGameState());
        }

    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawString("Title WIP, press enter to continue",handler.getWidth()/2 - handler.getWidth()/6,handler.getHeight()/2);
    }

    @Override
    public void refresh() {

    }
}

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

    }

    @Override
    public void refresh() {

    }
}

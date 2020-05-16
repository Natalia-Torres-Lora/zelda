package Game.GameStates.Zelda;

import Game.GameStates.PacManState;
import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ZeldaGameOverState extends State {
	public State zeldaGameState;


	public ZeldaGameOverState(Handler handler) {
		super(handler);
		refresh();

	}
	public void tick() {
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
			zeldaGameState= new ZeldaGameState(handler);
			handler.changeState(handler.getZeldaGameState());
			
		}

	}

	public void render(Graphics g) {
		

	}

	public void refresh() {

	}
}

package Game.GameStates.Zelda;


import Game.GameStates.GameState;
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
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			handler.changeState(new ZeldaGameState(handler));
			handler.getMusicHandler().changeMusic("zelda_.Overworld_theme.wav");
		}

	}

	public void render(Graphics g) {
		g.drawImage(Images.zeldaGameOverBackground,0,0,handler.getWidth(),handler.getHeight(),null);
	}

	public void refresh() {

	}
}

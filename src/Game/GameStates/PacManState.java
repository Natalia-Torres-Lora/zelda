package Game.GameStates;

import Display.UI.UIManager;
import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Main.Handler;
import Resources.Images;

import java.awt.*;

public class PacManState extends State {

    private UIManager uiManager;

    public PacManState(Handler handler){
        super(handler);
        handler.setMap(MapBuilder.createMap(Images.map1, handler));

    }


    @Override
    public void tick() {
        for (BaseDynamic entity:handler.getMap().getEnemiesOnMap()) {
            entity.tick();
        }

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        handler.getMap().drawMap(g2);
    }

    @Override
    public void refresh() {

    }


}

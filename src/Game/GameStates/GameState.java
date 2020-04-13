package Game.GameStates;

import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Game.GameStates.Zelda.ZeldaMMGameState;
import Game.Zelda.World.MapBuilder;
import Main.Handler;
import Resources.Images;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class GameState extends State {

    private UIManager uiManager;

    public GameState(Handler handler){
        super(handler);
        refresh();
    }


    @Override
    public void tick() {

        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)){
            handler.getDisplayScreen().confirm("You can press 'H' for help once in the map maker. Continue will take you there.\n" +
                                                        "Note: Some keys will require you to press them multiple times, not just why tbh.\n" +
                                                        "Also, if sharing the map made, and if it has teleport pads, make sure to share the '.txt' file crated alongside the '.png' or they wont work");
                handler.getMouseManager().setUimanager(null);
                State.setState(handler.getZeldaMMState());
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)){
            handler.getDisplayScreen().confirm("If youre loading a world from someone else, make sure you also have the '.txt' file\n" +
                                                        "that was created along side the map if it has teleport pads or tehy wont work.\n" +
                                                        "This file is named exactly like the map but its '.txt' instead of '.png' ");
            handler.getMouseManager().setUimanager(null);
            String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
            String path2 = path.substring(0,path.indexOf("/out/"))+"/res/Edited";
            JFileChooser chooser = new JFileChooser(path2.replaceAll("%20"," "));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG, & PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
                try {
                State.setState(new ZeldaMMGameState(handler,MapBuilder.createMap(ImageIO.read(chooser.getSelectedFile()), handler,chooser.getSelectedFile().getName())));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.selectionBackground,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.Render(g);
    }

    @Override
    public void refresh() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);


        uiManager.addObjects(new UIImageButton((handler.getWidth() / 2) - (handler.getWidth() /3) + 24, (handler.getHeight() /2)-(handler.getHeight() /32), handler.getWidth()/7, handler.getHeight()/6, Images.galagaLogo, new ClickListlener() {
            @Override
            public void onClick() {
                if (handler.getState() == handler.getGameState()) {

                    handler.getMouseManager().setUimanager(null);
                    handler.getMusicHandler().triggerGalaga();
                    State.setState(handler.getGalagaState());
                }
            }
        }));

        uiManager.addObjects(new UIImageButton(((handler.getWidth() / 2)) - ((handler.getWidth() / 14)) , (handler.getHeight() /2)-(handler.getHeight() /32), handler.getWidth()/8, handler.getHeight()/8, Images.pacmanRight, new ClickListlener() {
            @Override
            public void onClick() {
                if (handler.getState() == handler.getGameState()) {
                    handler.getMouseManager().setUimanager(null);
                    handler.getMusicHandler().stopMusic();
                    State.setState(handler.getPacManState());
                }
            }
        }));

        uiManager.addObjects(new UIImageButton(((handler.getWidth() / 2)) + ((handler.getWidth() / 5)) - ((handler.getWidth() / 64)), (handler.getHeight() /2)-(handler.getHeight() /32), handler.getWidth()/8, handler.getHeight()/8, Images.zeldaTriforceLogo, new ClickListlener() {
            @Override
            public void onClick() {
                if (handler.getState() == handler.getGameState()) {
                    handler.getMouseManager().setUimanager(null);
                    handler.getMusicHandler().stopMusic();
                    State.setState(handler.getZeldaIntroState());
                }
            }
        }));


    }
}

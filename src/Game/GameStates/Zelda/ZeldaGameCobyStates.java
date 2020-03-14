package Game.GameStates.Zelda;

import Game.GameStates.State;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaGameCobyStates extends State {
    ArrayList<ArrayList<BufferedImage>> grid;

    int counter = 0;
    static int pixelsPerSquare;
    static int pixelTotalWidth,pixelTotalHeight;
    static int xOffset,yOffset,stageWidth,stageHeight;


    public ZeldaGameCobyStates(Handler handler) {
        super(handler);
        grid = new ArrayList<>();
        xOffset = handler.getWidth()/3;
        yOffset = handler.getHeight()/4;
        stageWidth = handler.getWidth()/2;
        stageHeight = handler.getHeight()/2;
        pixelsPerSquare = Images.zeldaTiles.get(22).getWidth();
        pixelTotalWidth = stageWidth/pixelsPerSquare;
        pixelTotalHeight = stageHeight / pixelsPerSquare;



    }

    @Override
    public void tick() {



    }

    @Override
    public void render(Graphics g) {

g.setColor(Color.WHITE);
        for (int i = xOffset;i < xOffset+stageWidth;i++){
            g.drawLine(i*pixelsPerSquare,yOffset,i*pixelsPerSquare,yOffset+stageHeight);
        }
        for (int i = yOffset;i < yOffset+stageHeight;i++){
            g.drawLine(xOffset,i*pixelsPerSquare,xOffset+stageWidth,i*stageHeight);
        }
        g.setColor(Color.BLACK);



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

package Game.GameStates.Zelda;

import Game.GameStates.State;
import Game.Zelda.Entities.Dynamic.Link;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaGameState extends State {


    public static int xOffset,yOffset,stageWidth,stageHeight,worldScale;
    int cameraOffsetX,cameraOffsetY;
    //map is 16 by 7 squares, you start at x=7,y=7 starts counting at 0
    public int mapX,mapY,mapWidth,mapHeight;

    public ArrayList<ArrayList<ArrayList<SolidStaticEntities>>> objects;
    public Link link;



    public ZeldaGameState(Handler handler) {
        super(handler);
        xOffset = handler.getWidth()/4;
        yOffset = handler.getHeight()/4;
        stageWidth = handler.getWidth()/3 + (handler.getWidth()/15);
        stageHeight = handler.getHeight()/2;
         worldScale = 3;
         mapX = 7;
         mapY = 7;
         mapWidth = 256;
         mapHeight = 176;
         cameraOffsetX =  ((mapWidth*mapX) + mapX + 1)*worldScale;
         cameraOffsetY = ((mapHeight*mapY) + mapY + 1)*worldScale;
         objects = new ArrayList<>();
         for (int i =0;i<16;i++){
             objects.add(new ArrayList<>());
             for (int j =0;j<8;j++) {
                 objects.get(i).add(new ArrayList<>());
             }
         }
         ArrayList<SolidStaticEntities> doors = new ArrayList<>();
        doors.add(new SectionDoor( 0,5,16*worldScale,16*worldScale,"Left",handler));
        doors.add(new SectionDoor( 7,0,16*worldScale * 2,16*worldScale,"Up",handler));
        doors.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,"Left",handler));
        objects.get(7).set(7,doors);
         link = new Link(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.zeldaLinkFrames,handler);


    }

    @Override
    public void tick() {
        link.tick();
        for (SolidStaticEntities entity : objects.get(mapX).get(mapY)){
            entity.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.zeldaMap,-cameraOffsetX + xOffset,-cameraOffsetY + yOffset,Images.zeldaMap.getWidth()*worldScale,Images.zeldaMap.getHeight()*worldScale,null );
        for (SolidStaticEntities entity : objects.get(mapX).get(mapY)){
            entity.render(g);
        }
        link.render(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,xOffset,handler.getHeight());
        g.fillRect(xOffset+stageWidth ,0,handler.getWidth(),handler.getHeight());
        g.fillRect(0,0,handler.getWidth(),yOffset);
        g.fillRect(0,yOffset+stageHeight,handler.getWidth(),handler.getHeight());

    }

    @Override
    public void refresh() {

    }
}

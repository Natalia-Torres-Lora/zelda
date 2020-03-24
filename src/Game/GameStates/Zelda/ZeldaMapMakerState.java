package Game.GameStates.Zelda;

import Game.GameStates.State;
import Game.Zelda.World.Map;
import Game.Zelda.World.MapBuilder;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaMapMakerState extends State {
    ArrayList<ArrayList<BufferedImage>> grid;

    int counter = 0;
    int selector = 0;
    boolean showingTiles= false;
    ArrayList<BufferedImage> selectedList;

    public static int scale =2;
    public static int pixelsPerSquare = Images.zeldaTiles.get(22).getWidth()*scale;
    public static int pixelTotalWidth,pixelTotalHeight;
    public static final BufferedImage unChange = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    ArrayList<int[]> drawStack = new ArrayList<>();

    public ZeldaMapMakerState(Handler handler) {
        super(handler);
        grid = new ArrayList<>();

        pixelTotalWidth = handler.getWidth()/pixelsPerSquare;
        pixelTotalHeight = handler.getHeight() / pixelsPerSquare;
        for (int x = 0 ; x<= pixelTotalWidth;x++){
            grid.add(new ArrayList<BufferedImage>());
            for (int y = 0; y <= pixelTotalHeight;y++){
                grid.get(x).add(null);
            }
        }
        selectedList = Images.zeldaTiles;



    }

    @Override
    public void tick() {

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_G)){

            selector++;
            if (selector>4){
                selector = 0;
            }
            counter = 0;
            switch (selector){
                case 0:
                    selectedList = Images.zeldaTiles;
                    break;
                case 1:
                    selectedList = Images.forestTiles;
                    break;
                case 2:
                    selectedList = Images.caveTiles;
                    break;
                case 3:
                    selectedList = Images.mountainTiles;
                    break;
                case 4:
                    selectedList = Images.graveTiles;
                    break;
            }
        }

        if (handler.getMouseManager().isLeftPressed()){
            int xCoords = (handler.getMouseManager().getMouseX()/(pixelsPerSquare));
            int yCoords =  (handler.getMouseManager().getMouseY()/(pixelsPerSquare));
            if (grid.get(xCoords).get(yCoords) == null || !grid.get(xCoords).get(yCoords).equals(unChange)) {
                if (handler.getKeyManager().shift){
                    grid.get(xCoords).set(yCoords, Images.zeldaLinkFrames[0]);
                    int[] l = new int[2];
                    l[0] = xCoords;
                    l[1] = yCoords;
                    drawStack.add(l);
                }else {
                    grid.get(xCoords).set(yCoords, selectedList.get(counter));
                    int[] l = new int[2];
                    l[0] = xCoords;
                    l[1] = yCoords;
                    drawStack.add(l);
                    if (xCoords < pixelTotalWidth - 1 && yCoords < pixelTotalHeight - 1 && selectedList.get(counter).getHeight() * scale > pixelsPerSquare) {
                        grid.get(xCoords).set(yCoords + 1, unChange);
                        grid.get(xCoords + 1).set(yCoords, unChange);
                        grid.get(xCoords + 1).set(yCoords + 1, unChange);
                    }
                }
            }
        }
        if (handler.getMouseManager().isRightPressed()){
            int xCoords = (handler.getMouseManager().getMouseX()/(pixelsPerSquare));
            int yCoords =  (handler.getMouseManager().getMouseY()/(pixelsPerSquare));
            if (grid.get(xCoords).get(yCoords) != null) {
                if ( grid.get(xCoords).get(yCoords).getHeight() *scale > pixelsPerSquare) {
                    grid.get(xCoords).set(yCoords + 1, null);
                    grid.get(xCoords + 1).set(yCoords, null);
                    grid.get(xCoords + 1).set(yCoords + 1, null);
                }
                grid.get(xCoords).set(yCoords, null);
                int[] toRemove = new int[2];
                for (int[] list:drawStack){
                    if (list[0]==xCoords && list[1] == yCoords){
                        toRemove = list;
                    }
                }
                drawStack.remove(toRemove);

            }
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_U) && drawStack.size()>0){
            if(grid.get(drawStack.get(drawStack.size()-1)[0]).get(drawStack.get(drawStack.size()-1)[1]) !=null&&(grid.get(drawStack.get(drawStack.size()-1)[0]).get(drawStack.get(drawStack.size()-1)[1]).getHeight()*scale)>pixelsPerSquare){
                grid.get(drawStack.get(drawStack.size()-1)[0]).set(drawStack.get(drawStack.size()-1)[1]+1,null);
                grid.get(drawStack.get(drawStack.size()-1)[0]+1).set(drawStack.get(drawStack.size()-1)[1],null);
                grid.get(drawStack.get(drawStack.size()-1)[0]+1).set(drawStack.get(drawStack.size()-1)[1]+1,null);
            }
            grid.get(drawStack.get(drawStack.size()-1)[0]).set(drawStack.get(drawStack.size()-1)[1],null);
            drawStack.remove(drawStack.size()-1);
        }

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)){
            switch (selector){
                case 0:
                    if (counter==29){
                        counter = 0;
                    }else {
                        counter++;
                    }
                    break;
                default:
                    if (counter==41){
                        counter = 0;
                    }else {
                        counter++;
                    }
                    break;
            }
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)){
            switch (selector){
                case 0:
                    if (counter==0){
                        counter = 29;
                    }else {
                        counter--;
                    }
                    break;
                default:
                    if (counter==0){
                        counter = 41;
                    }else {
                        counter--;
                    }
                    break;
            }
        }

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_J)){
            showingTiles = !showingTiles;
        }


        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            Map map = MapBuilder.createMap(Objects.requireNonNull(MapBuilder.arrayToRGBImage(grid, handler.getDisplayScreen().stringInputPopUp("Enter File Name", "testName1"))),handler);
            if (map.link == null){
                handler.getDisplayScreen().confirm("Must have a Link, hold shift and click where you wiah to add him");
            }else {
                handler.changeState(new ZeldaMMGameState(handler, map));
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        g.setColor(Color.WHITE);
        for (int i = 0;i <= pixelTotalWidth;i++){
            g.drawLine(i*pixelsPerSquare,0,i*pixelsPerSquare ,handler.getHeight());
        }
        for (int i = 0;i <= pixelTotalHeight;i++){
            g.drawLine(0,i*pixelsPerSquare ,handler.getWidth(),i*pixelsPerSquare);
        }

        for (int x = 0 ; x<= pixelTotalWidth;x++){
            for (int y = 0; y <= pixelTotalHeight;y++){

                g.setFont(new Font("TimesRoman", Font.PLAIN, 10));

                if (grid.get(x).get(y) != null || grid.get(x).get(y) == unChange){
                    if (grid.get(x).get(y) == Images.zeldaLinkFrames[0]){
                        g.drawImage(Images.forestTiles.get(0),x*pixelsPerSquare,y*pixelsPerSquare,grid.get(x).get(y).getWidth()*scale,grid.get(x).get(y).getHeight()*scale,null);

                    }
                    g.drawImage(grid.get(x).get(y),x*pixelsPerSquare,y*pixelsPerSquare,grid.get(x).get(y).getWidth()*scale,grid.get(x).get(y).getHeight()*scale,null);

                }else{
                    if (x%2==0){
                        g.drawString("(" + x + "," + y + ")", (x * pixelsPerSquare),  (y * pixelsPerSquare) + (2*pixelsPerSquare) / 3);
                    }else {
                        g.drawString("(" + x + "," + y + ")", (x * pixelsPerSquare), (y * pixelsPerSquare) + pixelsPerSquare / 3);
                    }
                }
            }
        }

        if (handler.getKeyManager().shift){
            g.drawImage(Images.zeldaLinkFrames[0],handler.getMouseManager().getMouseX(),handler.getMouseManager().getMouseY(),selectedList.get(counter).getWidth()*scale,selectedList.get(counter).getHeight()*scale,null);

        }else{
            g.drawImage(selectedList.get(counter),handler.getMouseManager().getMouseX(),handler.getMouseManager().getMouseY(),selectedList.get(counter).getWidth()*scale,selectedList.get(counter).getHeight()*scale,null);
        }


        if (showingTiles){
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(handler.getMouseManager().getMouseX(),handler.getMouseManager().getMouseY(),6*16*scale+(3*scale),8*16*scale+(3*scale));
            int x=0,y=0;
            int counterSelector = 0;
            for (BufferedImage image: selectedList){
                if (x==5){
                    x=0;
                    y++;
                }
                if (counterSelector == counter){
                    g.setColor(Color.YELLOW);
                    g.fillRect(handler.getMouseManager().getMouseX()+(x*16*scale)+ 4,handler.getMouseManager().getMouseY()+(y*16*scale)+ 4,16*scale +4,16*scale+4);
                }

                g.drawImage(image,handler.getMouseManager().getMouseX()+(x*16*scale)+ 8,handler.getMouseManager().getMouseY()+(y*16*scale)+ 8,16*scale,16*scale,null);
                counterSelector++;

                x++;

            }
        }

    }

    @Override
    public void refresh() {

    }
}

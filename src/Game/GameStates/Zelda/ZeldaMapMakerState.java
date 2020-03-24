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
    ArrayList<ArrayList<int[]>> linkedTeleports = new ArrayList<>();
    boolean linking = false;
    boolean linkingStarted = false;
    boolean rightClicked = false;
    boolean leftClicked = false;
    boolean linkPlaced = false;

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
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)){
            handler.getDisplayScreen().confirm(
                    "Note: Some keys will require you to press them multiple times, not just why tbh.\n" +
                            "Also teleport pad works by pressing shift while one the teleport pad or near it while in facing it" +
                            "H ==> Help Menu.\n" +
                            "U ==> Undo last placed tile (will store in a stack all tiles drawn).\n" +
                            "T ==> Changes the tile set.\n" +
                            "S ==> Shows the list of tiles available and the one selected from the current tileSet.\n" +
                            "+ ==> Move to the next tile.\n" +
                            "- ==> Moves to the previous tile.\n" +
                            "ENTER ==> Finished the map and saves it to the the 'Edited' folder.\n" +
                            "LMB ==> Place the currently selected tile\n" +
                            "RMB ==> Will Erase the selected tile and remove it from undo stack.\n\n" +
                            "The block time in the last 4 tile sets are teleport pads, once placed\nyou'll be required to place another one to be linked with before you can do anything else.\n\nPressing U will cancel out of this and erase the teleport pad.\n\n" +
                            "Hold Shift and Click LMB to place down Link. Exactly one Link per map is needed, \nhe will always be placed on the tile 0 from tileSet 2.");
        }


        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_T)){
            if (linking){
                handler.getDisplayScreen().confirm("Please click where the last tile will teleport too.");
            }else {

                selector++;
                if (selector > 4) {
                    selector = 0;
                }
                counter = 0;
                switch (selector) {
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
        }
        if (linking && !handler.getMouseManager().isLeftPressed()){
            linkingStarted = true;
        }
        if (leftClicked && !handler.getMouseManager().isLeftPressed()){
            leftClicked = false;
        }
        if (handler.getMouseManager().isLeftPressed() && !leftClicked){
            leftClicked = true;
            if (!linking) {
                int xCoords = (handler.getMouseManager().getMouseX() / (pixelsPerSquare));
                int yCoords = (handler.getMouseManager().getMouseY() / (pixelsPerSquare));
                if (grid.get(xCoords).get(yCoords) == null || !grid.get(xCoords).get(yCoords).equals(unChange)) {
                    if (handler.getKeyManager().shift && ! linkPlaced) {
                        grid.get(xCoords).set(yCoords, Images.zeldaLinkFrames[0]);
                        int[] l = new int[2];
                        l[0] = xCoords;
                        l[1] = yCoords;
                        drawStack.add(l);
                        linkPlaced = true;
                    } else {
                        if (linkPlaced && handler.getKeyManager().shift){
                            handler.getDisplayScreen().confirm("You cant place more than one Link per map, I mean, why would you?");
                            return;
                        }
                        if (grid.get(xCoords).get(yCoords) != null && grid.get(xCoords).get(yCoords).equals(Images.zeldaLinkFrames[0])){
                            linkPlaced = false;
                        }
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
                        if ((counter == 25 && selector == 0) || (selector != 0 && counter == 41)) {
                            handler.getDisplayScreen().confirm("Started linking a teleport, click where you want to teleport to.");
                            linking = true;
                            showingTiles = false;
                            linkedTeleports.add(new ArrayList<>());
                            linkedTeleports.get(linkedTeleports.size() - 1).add(l);
                        }
                    }
                }
            }else  if (linkingStarted) {
                int xCoords = (handler.getMouseManager().getMouseX() / (pixelsPerSquare));
                int yCoords = (handler.getMouseManager().getMouseY() / (pixelsPerSquare));
                if (grid.get(xCoords).get(yCoords) == null || !grid.get(xCoords).get(yCoords).equals(unChange)) {
                    if (grid.get(xCoords).get(yCoords) != null && grid.get(xCoords).get(yCoords).equals(Images.zeldaLinkFrames[0])) {
                        linkPlaced = false;
                    }
                    grid.get(xCoords).set(yCoords, selectedList.get(counter));
                    int[] l = new int[2];
                    l[0] = xCoords;
                    l[1] = yCoords;
                    drawStack.add(l);
                    handler.getDisplayScreen().confirm("Linked last tile succesfully.");
                    linking = false;
                    linkingStarted = false;
                    linkedTeleports.get(linkedTeleports.size() - 1).add(l);
                }else{
                    handler.getDisplayScreen().confirm("Cant Overwrite Tiles with teleporter");
                }
            }
        }
        if (rightClicked && !handler.getMouseManager().isRightPressed()){
            rightClicked = false;
        }
        if (handler.getMouseManager().isRightPressed() && ! rightClicked){
            if (linking){
                handler.getDisplayScreen().confirm("Please click where the last tile will teleport too. 'U' will undo the last pressed and stop the linking.");
                rightClicked = true;
                return;
            }else {
                int xCoords = (handler.getMouseManager().getMouseX() / (pixelsPerSquare));
                int yCoords = (handler.getMouseManager().getMouseY() / (pixelsPerSquare));
                if (grid.get(xCoords).get(yCoords) != null) {
                    if (grid.get(xCoords).get(yCoords).getHeight() * scale > pixelsPerSquare) {
                        grid.get(xCoords).set(yCoords + 1, null);
                        grid.get(xCoords + 1).set(yCoords, null);
                        grid.get(xCoords + 1).set(yCoords + 1, null);
                    }
                    if ( grid.get(xCoords).get(yCoords).equals(Images.zeldaLinkFrames[0])){
                        linkPlaced = false;
                    }
                    grid.get(xCoords).set(yCoords, null);
                    int[] toRemove = new int[2];
                    for (int[] list : drawStack) {
                        if (list[0] == xCoords && list[1] == yCoords) {
                            toRemove = list;
                        }
                    }
                    drawStack.remove(toRemove);
                }
            }
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_U) && drawStack.size()>0){

            if(grid.get(drawStack.get(drawStack.size()-1)[0]).get(drawStack.get(drawStack.size()-1)[1]) !=null&&(grid.get(drawStack.get(drawStack.size()-1)[0]).get(drawStack.get(drawStack.size()-1)[1]).getHeight()*scale)>pixelsPerSquare){
                grid.get(drawStack.get(drawStack.size()-1)[0]).set(drawStack.get(drawStack.size()-1)[1]+1,null);
                grid.get(drawStack.get(drawStack.size()-1)[0]+1).set(drawStack.get(drawStack.size()-1)[1],null);
                grid.get(drawStack.get(drawStack.size()-1)[0]+1).set(drawStack.get(drawStack.size()-1)[1]+1,null);
            }
            if (grid.get(drawStack.get(drawStack.size()-1)[0]).get(drawStack.get(drawStack.size()-1)[1]).equals(Images.zeldaLinkFrames[0])){
                linkPlaced = false;
            }
            grid.get(drawStack.get(drawStack.size()-1)[0]).set(drawStack.get(drawStack.size()-1)[1],null);
            drawStack.remove(drawStack.size()-1);
            if (linking){
                handler.getDisplayScreen().confirm("Stopped Linking");
                linking = false;
            }
        }

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)){
            if (linking){
                handler.getDisplayScreen().confirm("Please click where the last tile will teleport too.");
            }else {
                switch (selector) {
                    case 0:
                        if (counter == 29) {
                            counter = 0;
                        } else {
                            counter++;
                        }
                        break;
                    default:
                        if (counter == 41) {
                            counter = 0;
                        } else {
                            counter++;
                        }
                        break;
                }
            }
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)){
            if (linking){
                handler.getDisplayScreen().confirm("Please click where the last tile will teleport too.");
            }else {
                switch (selector) {
                    case 0:
                        if (counter == 0) {
                            counter = 29;
                        } else {
                            counter--;
                        }
                        break;
                    default:
                        if (counter == 0) {
                            counter = 41;
                        } else {
                            counter--;
                        }
                        break;
                }
            }
        }

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)){
            if (linking){
                handler.getDisplayScreen().confirm("Please click where the last tile will teleport too.");
            }else {
                showingTiles = !showingTiles;
            }
        }


        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            if (linking){
                handler.getDisplayScreen().confirm("Please click where the last tile will teleport too.");
            }else {
                String name = handler.getDisplayScreen().stringInputPopUp("Enter File Name", "testName1");
                Map map = MapBuilder.createMap(Objects.requireNonNull(MapBuilder.arrayToRGBImage(grid, name,linkedTeleports)), handler, name);
                if (map.link == null) {
                    handler.getDisplayScreen().confirm("Must have a Link, hold shift and click where you wiah to add him");
                } else {
                    handler.changeState(new ZeldaMMGameState(handler, map));
                }
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

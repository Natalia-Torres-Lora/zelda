package Game.GameStates.PocketStates;

import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

/**
 * Created by AlexVR on 3/9/2020
 */
public class PocketMonsterOptionState extends State {

    public static int textSpeed = 1;
    public static boolean battleAnimations = true;
    public static boolean battleStyle = true;

    int startX,startY,stageWidth,stageHeight;
    int scale = 6;
    int selector = 0;

    public PocketMonsterOptionState(Handler handler) {
        super(handler);
        startX =((handler.getWidth()/2)-((Images.PokeCopyRight.getWidth()/2)*scale));
        startY = (handler.getHeight()/2)-((Images.PokeCopyRight.getHeight()/2)*scale);
        stageWidth = Images.Pokelogo[0].getWidth()*scale;
        stageHeight = Images.Pokelogo[0].getHeight()*scale;


    }

    @Override
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            handler.changeState(handler.getPMIntroState());
        }

        if (selector == 0){
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
                textSpeed++;
                if (textSpeed>3){
                    textSpeed = 3;
                }
            }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
                textSpeed--;
                if (textSpeed<1){
                    textSpeed = 1;
                }
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
                selector++;
            }
        }else if (selector == 1){
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
                battleAnimations = true;
            }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
                battleAnimations = false;
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
                selector++;
            }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
                selector--;
            }
        }else if (selector == 2){
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
                battleStyle=true;
            }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
                battleStyle = false;
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
                selector++;
            }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
                selector--;
            }
        }else{
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
                selector--;
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                handler.changeState(handler.getPMIntroState());
            }
        }
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(startX,startY,stageWidth,stageHeight);
        g.drawImage(Images.PokeSelectingSquarePane,startX+ (2*scale),startY+ (scale),(stageWidth) - (4*scale),stageHeight/4,null);
        g.drawImage(Images.PokeSelectingSquarePane,startX+ (2*scale),startY+ (scale) + (stageHeight/4) + (3*scale),(stageWidth) - (4*scale),stageHeight/4,null);
        g.drawImage(Images.PokeSelectingSquarePane,startX+ (2*scale),startY+ (scale) + 2*(stageHeight/4) + (3*scale),(stageWidth) - (4*scale),stageHeight/4,null);


        if (selector==0){
            if (textSpeed == 3){
                g.drawImage(Images.arrow,startX+(8*scale), startY + (24*scale),5*scale,7*scale,null );
            }else if (textSpeed ==2){
                g.drawImage(Images.arrow,startX+(60*scale), startY + (24*scale),5*scale,7*scale,null );
            }else {
                g.drawImage(Images.arrow, startX + (120 * scale ), startY + (24 * scale), 5 * scale, 7 * scale, null);
            }

            if (battleAnimations) {
                g.drawImage(Images.earrow, startX + (8 * scale), startY + (64 * scale), 5 * scale, 7 * scale, null);
            }else{
                g.drawImage(Images.earrow, startX + (68 * scale), startY + (64 * scale), 5 * scale, 7 * scale, null);
            }

            if (battleStyle) {
                g.drawImage(Images.earrow, startX + (8 * scale), startY + (100 * scale), 5 * scale, 7 * scale, null);
            }else{
                g.drawImage(Images.earrow, startX + (68 * scale), startY + (100 * scale), 5 * scale, 7 * scale, null);
            }

            g.drawImage(Images.earrow,startX+(8*scale), startY + (121*scale),5*scale,7*scale,null );
        }else if (selector==1){
            if (textSpeed == 3){
                g.drawImage(Images.earrow,startX+(8*scale), startY + (24*scale),5*scale,7*scale,null );
            }else if (textSpeed ==2){
                g.drawImage(Images.earrow,startX+(60*scale), startY + (24*scale),5*scale,7*scale,null );
            }else {
                g.drawImage(Images.earrow, startX + (120 * scale ), startY + (24 * scale), 5 * scale, 7 * scale, null);
            }

            if (battleAnimations) {
                g.drawImage(Images.arrow, startX + (8 * scale), startY + (64 * scale), 5 * scale, 7 * scale, null);
            }else{
                g.drawImage(Images.arrow, startX + (68 * scale), startY + (64 * scale), 5 * scale, 7 * scale, null);
            }

            if (battleStyle) {
                g.drawImage(Images.earrow, startX + (8 * scale), startY + (100 * scale), 5 * scale, 7 * scale, null);
            }else{
                g.drawImage(Images.earrow, startX + (68 * scale), startY + (100 * scale), 5 * scale, 7 * scale, null);
            }

            g.drawImage(Images.earrow,startX+(8*scale), startY + (121*scale),5*scale,7*scale,null );
        }else if (selector==2){
            if (textSpeed == 3){
                g.drawImage(Images.earrow,startX+(8*scale), startY + (24*scale),5*scale,7*scale,null );
            }else if (textSpeed ==2){
                g.drawImage(Images.earrow,startX+(60*scale), startY + (24*scale),5*scale,7*scale,null );
            }else {
                g.drawImage(Images.earrow, startX + (120 * scale ), startY + (24 * scale), 5 * scale, 7 * scale, null);
            }

            if (battleAnimations) {
                g.drawImage(Images.earrow, startX + (8 * scale), startY + (64 * scale), 5 * scale, 7 * scale, null);
            }else{
                g.drawImage(Images.earrow, startX + (68 * scale), startY + (64 * scale), 5 * scale, 7 * scale, null);
            }

            if (battleStyle) {
                g.drawImage(Images.arrow, startX + (8 * scale), startY + (100 * scale), 5 * scale, 7 * scale, null);
            }else{
                g.drawImage(Images.arrow, startX + (68 * scale), startY + (100 * scale), 5 * scale, 7 * scale, null);
            }

            g.drawImage(Images.earrow,startX+(8*scale), startY + (121*scale),5*scale,7*scale,null );
        }else{
            if (textSpeed == 3){
                g.drawImage(Images.earrow,startX+(8*scale), startY + (24*scale),5*scale,7*scale,null );
            }else if (textSpeed ==2){
                g.drawImage(Images.earrow,startX+(60*scale), startY + (24*scale),5*scale,7*scale,null );
            }else {
                g.drawImage(Images.earrow, startX + (120 * scale ), startY + (24 * scale), 5 * scale, 7 * scale, null);
            }

            if (battleAnimations) {
                g.drawImage(Images.earrow, startX + (8 * scale), startY + (64 * scale), 5 * scale, 7 * scale, null);
            }else{
                g.drawImage(Images.earrow, startX + (68 * scale), startY + (64 * scale), 5 * scale, 7 * scale, null);
            }

            if (battleStyle) {
                g.drawImage(Images.earrow, startX + (8 * scale), startY + (100 * scale), 5 * scale, 7 * scale, null);
            }else{
                g.drawImage(Images.earrow, startX + (68 * scale), startY + (100 * scale), 5 * scale, 7 * scale, null);
            }

            g.drawImage(Images.arrow,startX+(8*scale), startY + (121*scale),5*scale,7*scale,null );
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 54));
        g.drawString("TEXT      SPEED" , startX + (6*scale), startY + (12*scale));
        g.drawString("FAST" , startX + (15*scale), startY + (31*scale));
        g.drawString("MEDIUM" , startX + (66*scale), startY + (31*scale));
        g.drawString("SLOW" , startX + (126*scale), startY + (31*scale));
        g.drawString("BATTLE      ANIMATION" , startX + (6*scale), startY + (51*scale));
        g.drawString("ON" , startX + (15*scale), startY + (71*scale));
        g.drawString("OFF" , startX + (74*scale), startY + (71*scale));
        g.drawString("BATTLE      STYLE" , startX + (6*scale), startY + (87*scale));
        g.drawString("SHIFT" , startX + (15*scale), startY + (107*scale));
        g.drawString("SET" , startX + (74*scale), startY + (107*scale));
        g.drawString("CANCEL" , startX + (15*scale), startY + (128*scale));

    }

    @Override
    public void refresh() {

    }
}

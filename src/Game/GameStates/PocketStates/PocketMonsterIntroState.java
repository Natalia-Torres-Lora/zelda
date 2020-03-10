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
public class PocketMonsterIntroState extends State {

    int stage = 1;
    int stage1Counter = 120;
    Animation GFlogoAnim;
    int starStage = 0,starX,starY;
    int startX,startY,stageWidth,stageHeight;
    int scale = 6;
    private int starSpeed = 16;
    int miniStar1X,miniStar2X,miniStar3X,miniStar1Y,miniStar2Y,miniStar3Y,miniStar4Y,miniStar4X,miniWidth,miniHeight;
    int gengarFinish,gengarX,gengarY;
    int fighterFinish,fighterX,fighterY;
    private int fightersSpeed = 4;
    boolean attack1=false,attack2=false;
    int attack1Counter = 37;
    int attack2Counter = 65;
    int attack3Counter = 33;
    private int fade = 0;
    private int tileY,finishTitleY,bounce = 0 ;
    private int versionX,finishVersionX ;
    Animation redAnim;

    public PocketMonsterIntroState(Handler handler) {
        super(handler);
        GFlogoAnim = new Animation(128,Images.Pokelogo);
        startX =((handler.getWidth()/2)-((Images.PokeCopyRight.getWidth()/2)*scale));
        startY = (handler.getHeight()/2)-((Images.PokeCopyRight.getHeight()/2)*scale);
        stageWidth = Images.Pokelogo[0].getWidth()*scale;
        stageHeight = Images.Pokelogo[0].getHeight()*scale;
        starX = (handler.getWidth()/2)+((Images.PokeCopyRight.getWidth()/2)*scale) - 16;
        starY = startY - 16;
        miniStar1Y = startY+(91*scale);//91 is how many pixels down in the original image it should be
        miniStar2Y = startY+(91*scale);
        miniStar3Y = startY+(91*scale);
        miniStar4Y = startY+(91*scale);

        miniStar1X = startX+(40*scale);
        miniStar4X = startX+(40*scale);
        miniStar2X = startX+(44*scale);
        miniStar3X = startX+(48*scale);

        miniWidth = 80 * scale;
        miniHeight = 21 * scale;

        //fight
        gengarFinish = startX + (24*scale);
        gengarY=startY + (56*scale);
        fighterFinish = startX+(79*scale);
        fighterY = startY +(69*scale);
        fighterX=startX-(Images.PokeGigly[0].getWidth()/2);
        gengarX=startX+stageWidth+(Images.PokeGengar[0].getWidth()/2);

        //idleMenu
        tileY = startY - ((Images.PokeMonLogo.getHeight()*scale)/2);
        finishTitleY = startY + (8*scale);
        versionX = startX + stageWidth +((Images.version.getWidth()*scale)/2);
        finishVersionX = startX + (48*scale);
        redAnim = new Animation(1024,Images.PokeRed);


    }

    @Override
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && stage < 4  ){
            stage = 4;
        }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && stage == 4 ){
            stage = 5;
        }
        switch (stage){
            case 1:
                if (stage1Counter <=0){
                    stage++;
                    handler.getMusicHandler().playEffect("/pocketMonster/star.wav");
                }
                stage1Counter--;
                break;
            case 2:
                if (starStage>0 && !GFlogoAnim.end){
                    GFlogoAnim.tick();
                }
                if (starX <= startX + 2*(stageWidth/3) && starX > startX + (stageWidth/3)){
                    starStage = 1;
                }else if ( starX <= startX + (stageWidth/3)){
                    starStage = 2;
                }
                if (starX>startX-5){
                    starX-=starSpeed;
                    starY+=starSpeed;
                }
                if (starX<=startX-8){
                    starStage = 3;
                }
                if (starStage == 3) {
                    miniStar1Y++;
                    if (miniStar1Y > (miniHeight/3) + (startY + (91*scale))){
                        miniStar2Y++;
                    }
                    if (miniStar1Y > (2*(miniHeight/3)) + (startY + (91*scale))){
                        miniStar3Y++;
                        miniStar4Y++;
                    }
                    if (miniStar4Y>=miniHeight + (startY + (91*scale))){
                        stage++ ;
                        handler.getMusicHandler().playEffect("/pocketMonster/01 Opening Movie.wav");

                    }
                }
                break;
            case 3:
                if (Point2D.distance(gengarX,gengarY,gengarFinish,gengarY) > fightersSpeed&& attack1Counter==37){
                    gengarX-=fightersSpeed;
                }
                if (Point2D.distance(fighterX,fighterY,fighterFinish,fighterY) > fightersSpeed && attack1Counter==37){
                    fighterX+=fightersSpeed;
                }
                if (Point2D.distance(gengarX,gengarY,gengarFinish,gengarY) <= fightersSpeed &&  Point2D.distance(fighterX,fighterY,fighterFinish,fighterY) <= fightersSpeed ) {

                    if (!attack1 && !attack2) {
                        attack1 = true;
                        attack1Counter--;
                    }
                }
                if (attack1 && !attack2){
                    if (attack1Counter>0) {
                        if (attack1Counter <= 36 && attack1Counter > 27) {
                            fighterX++;
                            fighterY--;
                        } else if (attack1Counter <= 27 && attack1Counter > 18) {
                            fighterX++;
                            fighterY++;
                        } else if (attack1Counter <= 18 && attack1Counter > 9) {
                            fighterX--;
                            fighterY--;
                        } else if (attack1Counter <= 9) {
                            fighterX--;
                            fighterY++;
                        }
                        attack1Counter--;

                    }else{
                        attack1=false;
                        attack2=true;
                        attack1Counter=35;
                    }
                }
                else if (!attack1 && attack2){
                    attack2Counter--;
                }
                else if (attack1 && attack2){
                    if (attack3Counter <=98 && attack3Counter >= 38){
                        if (attack3Counter > 90){
                            fighterY++;
                        }else{
                            fighterX-=8;
                            fighterY-=4;
                        }
                    } else if(attack3Counter==37){
                        attack3Counter = -1;
                    } else if (attack3Counter <= 36 && attack3Counter > 27) {
                        fighterX++;
                        fighterY--;
                    } else if (attack3Counter <= 27 && attack3Counter > 18) {
                        fighterX++;
                        fighterY++;
                    } else if (attack3Counter <= 18 && attack3Counter > 9) {
                        fighterX--;
                        fighterY--;
                    } else if (attack3Counter <= 9 && attack3Counter > 0) {
                        fighterX--;
                        fighterY++;
                    }else if (attack3Counter == 0){
                        attack3Counter = 128;
                    }
                    attack3Counter--;
                }
                break;

            case 4:
                if (bounce==0 || bounce == 3){
                    if (Point2D.distance(startX+ (16*scale),tileY,startX+ (16*scale),finishTitleY)> 8){
                        tileY+=4;
                    }else {
                        if (bounce ==3){
                            bounce = 4;
                            handler.getMusicHandler().startMusic("/pocketMonster/02 Title Screen.wav");

                            break;
                        }else {
                            bounce = 1;
                        }
                    }
                }else if (bounce==1){
                    if (Point2D.distance(startX+ (16*scale),tileY,startX+ (16*scale),finishTitleY-(finishTitleY/4.0))> 8){
                        tileY-=4;
                    }else {
                        bounce = 3;
                    }
                }else if (bounce ==4){
                    if (Point2D.distance(versionX,startY+(64*scale),finishVersionX,startY+(64*scale))> 2){
                        versionX-=4;
                    }else{
                        stage++;
                    }
                }
                break;
            default:
                redAnim.tick();
                break;
        }
    }

    @Override
    public void render(Graphics g) {

        switch (stage){
            case 1:
                g.drawImage(Images.PokeCopyRight,startX,startY,stageWidth,stageHeight,null);
                break;
            case 2:
                if (starStage == 0) {
                    g.drawImage(Images.Pokelogo[0], startX, startY, stageWidth, stageHeight, null);
                }
                else{
                    g.drawImage(GFlogoAnim.getCurrentFrame(), startX, startY, stageWidth, stageHeight, null);
                }
                if (starStage == 0){
                    g.drawImage(Images.Pokestar[0], starX, starY, Images.Pokestar[0].getWidth()*scale, Images.Pokestar[0].getHeight()*scale, null);
                }
                else if (starStage == 1){
                    g.drawImage(Images.Pokestar[1], starX, starY, Images.Pokestar[0].getWidth()*scale, Images.Pokestar[0].getHeight()*scale, null);
                }
                else if (starStage == 2){
                    g.drawImage(Images.Pokestar[2], starX, starY, Images.Pokestar[0].getWidth()*scale, Images.Pokestar[0].getHeight()*scale, null);

                }
                else{
                    g.drawImage(Images.PokeStars[0], miniStar1X  , miniStar1Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                    g.drawImage(Images.PokeStars[1], miniStar1X+ 3*(miniWidth/9), miniStar1Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                    g.drawImage(Images.PokeStars[2], miniStar1X+ 5*(miniWidth/9), miniStar1Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                    g.drawImage(Images.PokeStars[3], miniStar1X+ 7*(miniWidth/9), miniStar1Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);

                    if (miniStar1Y > (startY + (91*scale))/3){
                        g.drawImage(Images.PokeStars[0], miniStar2X  , miniStar1Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                        g.drawImage(Images.PokeStars[1], miniStar2X+ 3*(miniWidth/9), miniStar2Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                        g.drawImage(Images.PokeStars[2], miniStar2X+ 5*(miniWidth/9), miniStar2Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                        g.drawImage(Images.PokeStars[3], miniStar2X+ 7*(miniWidth/9), miniStar2Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);

                    }
                    if (miniStar1Y > 2*((startY + (91*scale))/3)){
                        g.drawImage(Images.PokeStars[0], miniStar1X  , miniStar1Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                        g.drawImage(Images.PokeStars[1], miniStar3X+ 3*(miniWidth/9), miniStar3Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                        g.drawImage(Images.PokeStars[2], miniStar3X+ 5*(miniWidth/9), miniStar3Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                        g.drawImage(Images.PokeStars[3], miniStar3X+ 7*(miniWidth/9), miniStar3Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);

                        g.drawImage(Images.PokeStars[0], miniStar4X  , miniStar4Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                        g.drawImage(Images.PokeStars[1], miniStar4X+ 3*(miniWidth/9), miniStar4Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                        g.drawImage(Images.PokeStars[2], miniStar4X+ 5*(miniWidth/9), miniStar4Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);
                        g.drawImage(Images.PokeStars[3], miniStar4X+ 7*(miniWidth/9), miniStar4Y, Images.PokeStars[0].getWidth()*scale, Images.PokeStars[0].getHeight()*scale, null);

                    }
                }
                break;
            case 3:
                g.drawImage(Images.PokeBlank,startX,startY,stageWidth,stageHeight,null);
                if ((!attack1 && !attack2) || (attack1 && !attack2)) {
                    g.drawImage(Images.PokeGigly[0], fighterX, fighterY, Images.PokeGigly[0].getWidth() * scale, Images.PokeGigly[0].getHeight() * scale, null);
                    g.drawImage(Images.PokeGengar[0], gengarX, gengarY, Images.PokeGengar[0].getWidth() * scale, Images.PokeGengar[0].getHeight() * scale, null);
                }
                else if (!attack1 && attack2){
                    if (attack2Counter<=65 && attack2Counter>52){
                        if (attack2Counter>63){
                            gengarX--;
                        }
                        g.drawImage(Images.PokeGigly[0], fighterX, fighterY, Images.PokeGigly[0].getWidth() * scale, Images.PokeGigly[0].getHeight() * scale, null);
                        g.drawImage(Images.PokeGengar[1], gengarX, gengarY, Images.PokeGengar[0].getWidth() * scale, Images.PokeGengar[0].getHeight() * scale, null);
                    }else if (attack2Counter<=52 && attack2Counter>40){
                        if (attack2Counter>49){
                            gengarX+=24;
                        }
                        g.drawImage(Images.PokeGigly[0], fighterX, fighterY, Images.PokeGigly[0].getWidth() * scale, Images.PokeGigly[0].getHeight() * scale, null);
                        g.drawImage(Images.PokeGengar[2], gengarX, gengarY, Images.PokeGengar[0].getWidth() * scale, Images.PokeGengar[0].getHeight() * scale, null);
                    }else if (attack2Counter<=40 && attack2Counter>19){
                        if (attack2Counter>30){
                            fighterY-= ((24*scale)/10);
                            fighterX+=6;
                        }else{
                            fighterY+= ((24*scale)/10);
                            fighterX+=6;
                        }
                        g.drawImage(Images.PokeGigly[1], fighterX, fighterY, Images.PokeGigly[0].getWidth() * scale, Images.PokeGigly[0].getHeight() * scale, null);
                        g.drawImage(Images.PokeGengar[2], gengarX, gengarY, Images.PokeGengar[0].getWidth() * scale, Images.PokeGengar[0].getHeight() * scale, null);

                    }else if (attack2Counter<=19 && attack2Counter>13){
                        gengarX-=12;

                        g.drawImage(Images.PokeGigly[1], fighterX, fighterY, Images.PokeGigly[0].getWidth() * scale, Images.PokeGigly[0].getHeight() * scale, null);
                        g.drawImage(Images.PokeGengar[2], gengarX, gengarY, Images.PokeGengar[0].getWidth() * scale, Images.PokeGengar[0].getHeight() * scale, null);
                    }else{
                        attack1 = true;
                        attack2 = true;
                    }


                }
                else if (attack1 && attack2){
                    if (attack3Counter <=98 && attack3Counter > 36){
                        if (attack3Counter > 90){
                            g.drawImage(Images.PokeGigly[1], fighterX, fighterY, Images.PokeGigly[0].getWidth() * scale, Images.PokeGigly[0].getHeight() * scale, null);
                        }else{
                            g.drawImage(Images.PokeGigly[2], fighterX, fighterY, Images.PokeGigly[0].getWidth() * scale, Images.PokeGigly[0].getHeight() * scale, null);
                        }
                        g.drawImage(Images.PokeGengar[0], gengarX, gengarY, Images.PokeGengar[0].getWidth() * scale, Images.PokeGengar[0].getHeight() * scale, null);
                        if (attack3Counter<90){
                            g.setColor(new Color(255,255,255,fade));
                            fade += (254.0/54.0);
                            g.fillRect(startX,startY ,stageWidth,stageHeight);
                            if (fade > 253){
                                stage++;
                                break;
                            }
                        }
                    }else{
                        if (fade > 100){
                            stage=4;
                            break;
                        }
                        g.drawImage(Images.PokeGigly[0], fighterX, fighterY, Images.PokeGigly[0].getWidth() * scale, Images.PokeGigly[0].getHeight() * scale, null);
                        g.drawImage(Images.PokeGengar[0], gengarX, gengarY, Images.PokeGengar[0].getWidth() * scale, Images.PokeGengar[0].getHeight() * scale, null);
                    }



                }
                break;
            case 4:
                g.drawImage(Images.PokeTitle,startX,startY,stageWidth,stageHeight,null);
                g.setColor(new Color(Images.pinkColor));
                g.fillRect(startX,startY,stageWidth,72*scale);
                g.drawImage(Images.PokeMonLogo,startX+ (16*scale),tileY,Images.PokeMonLogo.getWidth()*scale,Images.PokeMonLogo.getHeight()*scale,null);
                g.drawImage(Images.version,versionX,startY+(64*scale),Images.version.getWidth()*scale,Images.version.getHeight()*scale,null);
                g.drawImage(Images.PokeRed[0],startX+(82*scale),startY+(80*scale),Images.PokeRed[0].getWidth()*scale,Images.PokeRed[0].getHeight()*scale,null);
                g.drawImage(Images.Pokemons.get(6),startX+(52*scale),startY+(104*scale),Images.Pokemons.get(6).getWidth()*scale,Images.Pokemons.get(6).getHeight()*scale,null);

                break;
            case 5:
                g.setColor(Color.white);
                g.fillRect(startX,startY,stageWidth,stageHeight);
                g.drawImage(Images.PokeSelectingSquarePane,startX+ (2*scale),startY+ (2*scale),2*(stageWidth/3),stageHeight/3,null);

                break;
            default:
                g.drawImage(Images.PokeTitle,startX,startY,stageWidth,stageHeight,null);
                g.drawImage(redAnim.getCurrentFrame(),startX+(82*scale),startY+(80*scale),Images.PokeRed[0].getWidth()*scale,Images.PokeRed[0].getHeight()*scale,null);
                g.drawImage(Images.Pokemons.get(6),startX+(52*scale),startY+(104*scale),Images.Pokemons.get(6).getWidth()*scale,Images.Pokemons.get(6).getHeight()*scale,null);

                break;
        }

        //fill the surrounding with black
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,startX,handler.getHeight());
        g.fillRect(startX+stageWidth,0,handler.getWidth(),handler.getHeight());
        g.fillRect(startX+stageWidth,0,handler.getWidth(),handler.getHeight());
        g.fillRect(0,0,handler.getWidth(),startY);
        g.fillRect(0,startY+stageHeight,handler.getWidth(),handler.getHeight());
        if (starStage == 3 && stage == 2 || stage == 3){//cover mini stars and other things
            g.setColor(Color.BLACK);
            g.fillRect(startX,startY,stageWidth, (32*scale));
            g.fillRect(startX,startY+stageHeight - (32*scale),stageWidth,(32*scale));
        }

    }

    @Override
    public void refresh() {

    }
}

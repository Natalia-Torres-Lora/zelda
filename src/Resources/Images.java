package Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class Images {


    public static BufferedImage titleScreenBackground;
    public static BufferedImage pauseBackground;
    public static BufferedImage selectionBackground;
    public static BufferedImage galagaCopyright;
    public static BufferedImage galagaSelect;
    public static BufferedImage muteIcon;
    public static BufferedImage galagaPlayerLaser;
    public static BufferedImage[] startGameButton;
    public static BufferedImage[] galagaLogo;
    public static BufferedImage[] pauseResumeButton;
    public static BufferedImage[] pauseToTitleButton;
    public static BufferedImage[] pauseOptionsButton;
    public static BufferedImage[] galagaPlayer;
    public static BufferedImage[] galagaPlayerDeath;
    public static BufferedImage[] galagaEnemyDeath;
    public static BufferedImage[] galagaEnemyBee;

    public static BufferedImage map1;
    public static BufferedImage ghost;
    public static BufferedImage[] pacmanDots;
    public static BufferedImage[] pacmanRight;
    public static BufferedImage[] pacmanLeft;
    public static BufferedImage[] pacmanUp;
    public static BufferedImage[] pacmanDown;
    public static BufferedImage[] bound;
    public static BufferedImage intro;
    public static BufferedImage start;

    public static BufferedImage galagaImageSheet;
    public SpriteSheet galagaSpriteSheet;

    public static BufferedImage pacmanImageSheet;
    public SpriteSheet pacmanSpriteSheet;

    public static BufferedImage zeldaImageSheet;
    public static BufferedImage storyImageSheet;

    public SpriteSheet zeldaSpriteSheet;
    public SpriteSheet storySpriteSheet;
    public static BufferedImage zeldaTriforceLogo;
    public static BufferedImage zeldaMap;

    public static ArrayList<BufferedImage> zeldaTiles;
    public static BufferedImage[] zeldaTitleFrames;
    public static BufferedImage[] zeldaStoryFrames;
    public static BufferedImage zeldaWorldLayoutTileImage;
    public SpriteSheet zeldaWorldLayoutTileSpriteSheet;
    public static ArrayList<BufferedImage> zeldaWorldLayoutTiles;

    public static BufferedImage zeldaLinkImage;
    public SpriteSheet zeldaLinkSpriteSheet;
    public static BufferedImage[] zeldaLinkFrames;

    public static ArrayList<BufferedImage> forestTiles;
    public static ArrayList<BufferedImage> caveTiles;
    public static ArrayList<BufferedImage> mountainTiles;
    public static ArrayList<BufferedImage> graveTiles;

    public static BufferedImage EnemyOverwoldImage;
    public SpriteSheet EnemyOverwoldSpriteSheet;
    public static BufferedImage[] bouncyEnemyFrames;

    public Images() {

        startGameButton = new BufferedImage[3];
        pauseResumeButton = new BufferedImage[2];
        pauseToTitleButton = new BufferedImage[2];
        pauseOptionsButton = new BufferedImage[2];
        galagaLogo = new BufferedImage[3];
        galagaPlayer = new BufferedImage[8];//not full yet, only has second to last image on sprite sheet
        galagaPlayerDeath = new BufferedImage[8];
        galagaEnemyDeath = new BufferedImage[5];
        galagaEnemyBee = new BufferedImage[8];

        pacmanDots = new BufferedImage[2];
        pacmanRight = new BufferedImage[2];
        pacmanLeft = new BufferedImage[2];
        pacmanUp = new BufferedImage[2];
        pacmanDown = new BufferedImage[2];
        bound = new BufferedImage[16];

        zeldaTiles = new ArrayList<>();
        zeldaTitleFrames = new BufferedImage[6];
        zeldaStoryFrames = new BufferedImage[8];
        zeldaWorldLayoutTiles = new ArrayList<>();

        forestTiles = new ArrayList<>();
        caveTiles = new ArrayList<>();
        graveTiles = new ArrayList<>();
        mountainTiles = new ArrayList<>();

        zeldaLinkFrames = new BufferedImage[8];

        bouncyEnemyFrames = new BufferedImage[2];



        try {

            startGameButton[0]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/NormalStartButton.png"));
            startGameButton[1]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/HoverStartButton.png"));
            startGameButton[2]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/ClickedStartButton.png"));

            titleScreenBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Title.png"));

            pauseBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Pause.png"));

            selectionBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Selection.png"));

            galagaCopyright = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/Copyright.png"));

            galagaSelect = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_select.png"));

            muteIcon = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/mute.png"));

            galagaLogo[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_logo.png"));
            galagaLogo[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/hover_galaga_logo.png"));
            galagaLogo[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pressed_galaga_logo.png"));

            pauseResumeButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/NormalHoverResume.png"));
            pauseResumeButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/PressedResume.png"));

            pauseToTitleButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/NormalHoverToTitleButton.png"));
            pauseToTitleButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/PressedToTitleButton.png"));

            pauseOptionsButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/NormalHoverToOptionsButton.png"));
            pauseOptionsButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/PressedToOptionsButton.png"));

            galagaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/Galaga.png"));
            galagaSpriteSheet = new SpriteSheet(galagaImageSheet);

            galagaPlayer[0] = galagaSpriteSheet.crop(160,55,15,16);

            galagaPlayerDeath[0] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[1] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[2] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[3] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[4] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[5] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[6] = galagaSpriteSheet.crop(327,47,32,32);
            galagaPlayerDeath[7] = galagaSpriteSheet.crop(327,47,32,32);

            galagaEnemyDeath[0] = galagaSpriteSheet.crop(201,191,32,32);
            galagaEnemyDeath[1] = galagaSpriteSheet.crop(223,191,32,32);
            galagaEnemyDeath[2] = galagaSpriteSheet.crop(247,191,32,32);
            galagaEnemyDeath[3] = galagaSpriteSheet.crop(280,191,32,32);
            galagaEnemyDeath[4] = galagaSpriteSheet.crop(320,191,32,32);

            galagaEnemyBee[0] = galagaSpriteSheet.crop(188,178,9,10);
            galagaEnemyBee[1] = galagaSpriteSheet.crop(162,178,13,10);
            galagaEnemyBee[2] = galagaSpriteSheet.crop(139,177,11,12);
            galagaEnemyBee[3] = galagaSpriteSheet.crop(113,176,14,13);
            galagaEnemyBee[4] = galagaSpriteSheet.crop(90,177,13,13);
            galagaEnemyBee[5] = galagaSpriteSheet.crop(65,176,13,14);
            galagaEnemyBee[6] = galagaSpriteSheet.crop(42,178,12,11);
            galagaEnemyBee[7] = galagaSpriteSheet.crop(19,177,10,13);


            galagaPlayerLaser = galagaSpriteSheet.crop(365 ,219,3,8);

            pacmanImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Background.png"));
            pacmanSpriteSheet = new SpriteSheet(pacmanImageSheet);
            map1 = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map1.png"));
            ghost = pacmanSpriteSheet.crop(456,64,16,16);
            pacmanDots[0] = pacmanSpriteSheet.crop(643,18,16,16);
            pacmanDots[1] = pacmanSpriteSheet.crop(623,18,16,16);

            bound[0] = pacmanSpriteSheet.crop(603,18,16,16);//single
            bound[1] = pacmanSpriteSheet.crop(615,37,16,16);//right open
            bound[2] = pacmanSpriteSheet.crop(635,37,16,16);//down open
            bound[3] = pacmanSpriteSheet.crop(655,37,16,16);//left open
            bound[4] = pacmanSpriteSheet.crop(655,57,16,16);//up open
            bound[5] = pacmanSpriteSheet.crop(655,75,16,16);//up/down
            bound[6] = pacmanSpriteSheet.crop(656,116,16,16);//left/Right
            bound[7] = pacmanSpriteSheet.crop(656,136,16,16);//up/Right
            bound[8] = pacmanSpriteSheet.crop(655,174,16,16);//up/left
            bound[9] = pacmanSpriteSheet.crop(655,155,16,16);//down/Right
            bound[10] = pacmanSpriteSheet.crop(655,192,16,16);//down/left
            bound[11] = pacmanSpriteSheet.crop(664,232,16,16);//all
            bound[12] = pacmanSpriteSheet.crop(479,191,16,16);//left
            bound[13] = pacmanSpriteSheet.crop(494,191,16,16);//right
            bound[14] = pacmanSpriteSheet.crop(479,208,16,16);//top
            bound[15] = pacmanSpriteSheet.crop(479,223,16,16);//bottom

            pacmanRight[0] = pacmanSpriteSheet.crop(473,1,12,13);
            pacmanRight[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanLeft[0] = pacmanSpriteSheet.crop(474,17,12,13);
            pacmanLeft[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanUp[0] = pacmanSpriteSheet.crop(473,34,13,12);
            pacmanUp[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanDown[0] = pacmanSpriteSheet.crop(473,48,13,12);
            pacmanDown[1] = pacmanSpriteSheet.crop(489,1,13,13);

            intro = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/intro.png"));
            start = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/startScreen.png"));

            zeldaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/tileSet.png"));
            zeldaTriforceLogo = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/triforceLogo.png"));
            zeldaMap = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/map.png"));
            zeldaMap = createImageTransparent(zeldaMap,"zelddaMap_0,128,0,green",new Color(0,128,0).getRGB());
            zeldaImageSheet = createImageTransparent(zeldaImageSheet,"tileSets_0,120,0,green",new Color(0,128,0).getRGB());
            zeldaSpriteSheet = new SpriteSheet(zeldaImageSheet);

            storyImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/title.png"));
            storySpriteSheet = new SpriteSheet(storyImageSheet);
            zeldaTitleFrames[5] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_0.gif"));
            zeldaTitleFrames[4] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_1.gif"));
            zeldaTitleFrames[3] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_2.gif"));
            zeldaTitleFrames[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_3.gif"));
            zeldaTitleFrames[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_4.gif"));
            zeldaTitleFrames[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_5.gif"));

            zeldaStoryFrames[0] = storySpriteSheet.crop(1, 250, 256,223);
            zeldaStoryFrames[1] = storySpriteSheet.crop(258, 250, 256,223);
            zeldaStoryFrames[2] = storySpriteSheet.crop(515, 250, 256,223);
            zeldaStoryFrames[3] = storySpriteSheet.crop(772, 250, 256,223);
            zeldaStoryFrames[4] = storySpriteSheet.crop(1, 475, 256,223);
            zeldaStoryFrames[5] = storySpriteSheet.crop(258, 475, 256,223);
            zeldaStoryFrames[6] = storySpriteSheet.crop(515, 475, 256,223);
            zeldaStoryFrames[7] = storySpriteSheet.crop(772, 475, 256,64);

            zeldaLinkImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/link.png"));
            zeldaLinkImage = createImageTransparent(zeldaLinkImage,"link_0,128,0_green",new Color(0,128,0).getRGB());
            zeldaLinkSpriteSheet = new SpriteSheet(createImageTransparent(zeldaLinkImage,"link_116,116,116_gray",new Color(116,116,116).getRGB()));
            zeldaLinkFrames[0] = zeldaLinkSpriteSheet.crop(1,11,16,16);
            zeldaLinkFrames[1] = zeldaLinkSpriteSheet.crop(18,11,16,16);
            zeldaLinkFrames[2] = zeldaLinkSpriteSheet.crop(35,11,16,16);
            zeldaLinkFrames[3] = zeldaLinkSpriteSheet.crop(52,11,16,16);
            zeldaLinkFrames[4] = zeldaLinkSpriteSheet.crop(69,11,16,16);
            zeldaLinkFrames[5] = zeldaLinkSpriteSheet.crop(86,11,16,16);
            zeldaLinkFrames[6] = zeldaLinkSpriteSheet.crop(213,11,16,16);
            zeldaLinkFrames[7] = zeldaLinkSpriteSheet.crop(230,11,16,16);

            zeldaWorldLayoutTileImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/layout.png"));
            zeldaWorldLayoutTileSpriteSheet = new SpriteSheet( createImageTransparent(zeldaWorldLayoutTileImage,"layout_0,128,0_green",new Color(0,128,0).getRGB()));
            zeldaWorldLayoutTiles.add(zeldaWorldLayoutTileSpriteSheet.crop(1,154,152,84));
            zeldaWorldLayoutTiles.add(createImage(zeldaWorldLayoutTiles.get(0),"forest_brown4greeen",brown.getRGB(),new Color(0,168,0).getRGB()));
            zeldaWorldLayoutTiles.add(createImage(zeldaWorldLayoutTiles.get(0),"cave_brown4greeen",brown.getRGB(),new Color(124,8,0).getRGB()));
            zeldaWorldLayoutTiles.add(createImage(zeldaWorldLayoutTiles.get(0),"grave_brown4greeen",brown.getRGB(),new Color(252,252,252).getRGB()));


            EnemyOverwoldImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/enemy3.png"));
            EnemyOverwoldImage = createImageTransparent(EnemyOverwoldImage,"enemies_overworld_116,116,116_green",new Color(116,116,116).getRGB());
            EnemyOverwoldSpriteSheet = new SpriteSheet( createImageTransparent(EnemyOverwoldImage,"enemies_overworld_0,128,0_green",new Color(0,128,0).getRGB()));

            bouncyEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(162,90,16,16);
            bouncyEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(179,90,16,16);

            //dungeon one tiles
            zeldaTiles.add(zeldaSpriteSheet.crop(815,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(848,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(881,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(914,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(947,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(848,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(815,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(881,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(914,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(947,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(815,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(848,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(881,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(914,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(947,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(815,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(848,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(881,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(914,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(947,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(984,11,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1001,11,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1018,11,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1035,11,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1001,28,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(984,28,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1018,28,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1035,28,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(984,45,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1001,45,16,16));

            //main world tiles
            SpriteSheet mountain = new SpriteSheet(zeldaWorldLayoutTiles.get(0));
            SpriteSheet forest = new SpriteSheet(zeldaWorldLayoutTiles.get(1));
            SpriteSheet cave = new SpriteSheet(createImageTransparent(zeldaWorldLayoutTiles.get(2),"caveTransparent_252,216,168_crema", new Color(252,216,168).getRGB()));
            SpriteSheet grave = new SpriteSheet(zeldaWorldLayoutTiles.get(3));

            mountainTiles.add(mountain.crop(0,0,16,16));
            mountainTiles.add(mountain.crop(17,0,16,16));
            mountainTiles.add(mountain.crop(34,0,16,16));
            mountainTiles.add(mountain.crop(51,0,16,16));
            mountainTiles.add(mountain.crop(17,17,16,16));
            mountainTiles.add(mountain.crop(34,17,16,16));
            mountainTiles.add(mountain.crop(51,17,16,16));
            mountainTiles.add(mountain.crop(68,0,16,16));
            mountainTiles.add(mountain.crop(85,0,16,16));
            mountainTiles.add(mountain.crop(102,0,16,16));
            mountainTiles.add(mountain.crop(68,17,16,16));
            mountainTiles.add(mountain.crop(85,17,16,16));
            mountainTiles.add(mountain.crop(102,17,16,16));
            mountainTiles.add(mountain.crop(68,34,16,16));
            mountainTiles.add(mountain.crop(85,34,16,16));
            mountainTiles.add(mountain.crop(102,34,16,16));
            mountainTiles.add(mountain.crop(119,0,16,16));
            mountainTiles.add(mountain.crop(136,0,16,16));
            mountainTiles.add(mountain.crop(119,17,16,16));
            mountainTiles.add(mountain.crop(136,17,16,16));
            mountainTiles.add(mountain.crop(119,34,16,16));
            mountainTiles.add(mountain.crop(136,34,16,16));
            mountainTiles.add(mountain.crop(0,51,16,16));
            mountainTiles.add(mountain.crop(17,51,16,16));
            mountainTiles.add(mountain.crop(34,51,16,16));
            mountainTiles.add(mountain.crop(0,68,16,16));
            mountainTiles.add(mountain.crop(34,68,16,16));
            mountainTiles.add(mountain.crop(51,51,16,16));
            mountainTiles.add(mountain.crop(68,51,16,16));
            mountainTiles.add(mountain.crop(85,51,16,16));
            mountainTiles.add(mountain.crop(51,68,16,16));
            mountainTiles.add(mountain.crop(85,68,16,16));
            mountainTiles.add(mountain.crop(0,17,16,16));
            mountainTiles.add(mountain.crop(0,34,16,16));
            mountainTiles.add(mountain.crop(17,34,16,16));
            mountainTiles.add(mountain.crop(34,34,16,16));
            mountainTiles.add(mountain.crop(51,34,16,16));
            mountainTiles.add(mountain.crop(17,68,16,16));
            mountainTiles.add(mountain.crop(68,68,16,16));
            mountainTiles.add(mountain.crop(102,51,16,16));
            mountainTiles.add(mountain.crop(119,51,16,16));
            mountainTiles.add(mountain.crop(136,51,16,16));

            forestTiles.add(forest.crop(0,0,16,16));
            forestTiles.add(forest.crop(17,0,16,16));
            forestTiles.add(forest.crop(34,0,16,16));
            forestTiles.add(forest.crop(51,0,16,16));
            forestTiles.add(forest.crop(17,17,16,16));
            forestTiles.add(forest.crop(34,17,16,16));
            forestTiles.add(forest.crop(51,17,16,16));
            forestTiles.add(forest.crop(68,0,16,16));
            forestTiles.add(forest.crop(85,0,16,16));
            forestTiles.add(forest.crop(102,0,16,16));
            forestTiles.add(forest.crop(68,17,16,16));
            forestTiles.add(forest.crop(85,17,16,16));
            forestTiles.add(forest.crop(102,17,16,16));
            forestTiles.add(forest.crop(68,34,16,16));
            forestTiles.add(forest.crop(85,34,16,16));
            forestTiles.add(forest.crop(102,34,16,16));
            forestTiles.add(forest.crop(119,0,16,16));
            forestTiles.add(forest.crop(136,0,16,16));
            forestTiles.add(forest.crop(119,17,16,16));
            forestTiles.add(forest.crop(136,17,16,16));
            forestTiles.add(forest.crop(119,34,16,16));
            forestTiles.add(forest.crop(136,34,16,16));
            forestTiles.add(forest.crop(0,51,16,16));
            forestTiles.add(forest.crop(17,51,16,16));
            forestTiles.add(forest.crop(34,51,16,16));
            forestTiles.add(forest.crop(0,68,16,16));
            forestTiles.add(forest.crop(34,68,16,16));
            forestTiles.add(forest.crop(51,51,16,16));
            forestTiles.add(forest.crop(68,51,16,16));
            forestTiles.add(forest.crop(85,51,16,16));
            forestTiles.add(forest.crop(51,68,16,16));
            forestTiles.add(forest.crop(85,68,16,16));
            forestTiles.add(forest.crop(0,17,16,16));
            forestTiles.add(forest.crop(0,34,16,16));
            forestTiles.add(forest.crop(17,34,16,16));
            forestTiles.add(forest.crop(34,34,16,16));
            forestTiles.add(forest.crop(51,34,16,16));
            forestTiles.add(forest.crop(17,68,16,16));
            forestTiles.add(forest.crop(68,68,16,16));
            forestTiles.add(forest.crop(102,51,16,16));
            forestTiles.add(forest.crop(119,51,16,16));
            forestTiles.add(forest.crop(136,51,16,16));

            caveTiles.add(cave.crop(0,0,16,16));
            caveTiles.add(cave.crop(17,0,16,16));
            caveTiles.add(cave.crop(34,0,16,16));
            caveTiles.add(cave.crop(51,0,16,16));
            caveTiles.add(cave.crop(17,17,16,16));
            caveTiles.add(cave.crop(34,17,16,16));
            caveTiles.add(cave.crop(51,17,16,16));
            caveTiles.add(cave.crop(68,0,16,16));
            caveTiles.add(cave.crop(85,0,16,16));
            caveTiles.add(cave.crop(102,0,16,16));
            caveTiles.add(cave.crop(68,17,16,16));
            caveTiles.add(cave.crop(85,17,16,16));
            caveTiles.add(cave.crop(102,17,16,16));
            caveTiles.add(cave.crop(68,34,16,16));
            caveTiles.add(cave.crop(85,34,16,16));
            caveTiles.add(cave.crop(102,34,16,16));
            caveTiles.add(cave.crop(119,0,16,16));
            caveTiles.add(cave.crop(136,0,16,16));
            caveTiles.add(cave.crop(119,17,16,16));
            caveTiles.add(cave.crop(136,17,16,16));
            caveTiles.add(cave.crop(119,34,16,16));
            caveTiles.add(cave.crop(136,34,16,16));
            caveTiles.add(cave.crop(0,51,16,16));
            caveTiles.add(cave.crop(17,51,16,16));
            caveTiles.add(cave.crop(34,51,16,16));
            caveTiles.add(cave.crop(0,68,16,16));
            caveTiles.add(cave.crop(34,68,16,16));
            caveTiles.add(cave.crop(51,51,16,16));
            caveTiles.add(cave.crop(68,51,16,16));
            caveTiles.add(cave.crop(85,51,16,16));
            caveTiles.add(cave.crop(51,68,16,16));
            caveTiles.add(cave.crop(85,68,16,16));
            caveTiles.add(cave.crop(0,17,16,16));
            caveTiles.add(cave.crop(0,34,16,16));
            caveTiles.add(cave.crop(17,34,16,16));
            caveTiles.add(cave.crop(34,34,16,16));
            caveTiles.add(cave.crop(51,34,16,16));
            caveTiles.add(cave.crop(17,68,16,16));
            caveTiles.add(cave.crop(68,68,16,16));
            caveTiles.add(cave.crop(102,51,16,16));
            caveTiles.add(cave.crop(119,51,16,16));
            caveTiles.add(cave.crop(136,51,16,16));

            graveTiles.add(grave.crop(0,0,16,16));
            graveTiles.add(grave.crop(17,0,16,16));
            graveTiles.add(grave.crop(34,0,16,16));
            graveTiles.add(grave.crop(51,0,16,16));
            graveTiles.add(grave.crop(17,17,16,16));
            graveTiles.add(grave.crop(34,17,16,16));
            graveTiles.add(grave.crop(51,17,16,16));
            graveTiles.add(grave.crop(68,0,16,16));
            graveTiles.add(grave.crop(85,0,16,16));
            graveTiles.add(grave.crop(102,0,16,16));
            graveTiles.add(grave.crop(68,17,16,16));
            graveTiles.add(grave.crop(85,17,16,16));
            graveTiles.add(grave.crop(102,17,16,16));
            graveTiles.add(grave.crop(68,34,16,16));
            graveTiles.add(grave.crop(85,34,16,16));
            graveTiles.add(grave.crop(102,34,16,16));
            graveTiles.add(grave.crop(119,0,16,16));
            graveTiles.add(grave.crop(136,0,16,16));
            graveTiles.add(grave.crop(119,17,16,16));
            graveTiles.add(grave.crop(136,17,16,16));
            graveTiles.add(grave.crop(119,34,16,16));
            graveTiles.add(grave.crop(136,34,16,16));
            graveTiles.add(grave.crop(0,51,16,16));
            graveTiles.add(grave.crop(17,51,16,16));
            graveTiles.add(grave.crop(34,51,16,16));
            graveTiles.add(grave.crop(0,68,16,16));
            graveTiles.add(grave.crop(34,68,16,16));
            graveTiles.add(grave.crop(51,51,16,16));
            graveTiles.add(grave.crop(68,51,16,16));
            graveTiles.add(grave.crop(85,51,16,16));
            graveTiles.add(grave.crop(51,68,16,16));
            graveTiles.add(grave.crop(85,68,16,16));
            graveTiles.add(grave.crop(0,17,16,16));
            graveTiles.add(grave.crop(0,34,16,16));
            graveTiles.add(grave.crop(17,34,16,16));
            graveTiles.add(grave.crop(34,34,16,16));
            graveTiles.add(grave.crop(51,34,16,16));
            graveTiles.add(grave.crop(17,68,16,16));
            graveTiles.add(grave.crop(68,68,16,16));
            graveTiles.add(grave.crop(102,51,16,16));
            graveTiles.add(grave.crop(119,51,16,16));
            graveTiles.add(grave.crop(136,51,16,16));


        }catch (IOException e) {
        e.printStackTrace();
    }


    }


    public BufferedImage invertImage(BufferedImage bufferedImage, String name) {
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
        String path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
        File imagess = new File(path2.replaceAll("%20"," "));
        if (imagess.exists()){
            try {
                return ImageIO.read(imagess.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int rgba = bufferedImage.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                        255 - col.getGreen(),
                        255 - col.getBlue());
                bufferedImage.setRGB(x, y, col.getRGB());
            }
        }
        File f = null;

        try
        {
            path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
            path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
            f = new File(path2.replaceAll("%20"," "));
            System.out.println("File saved in: "+path2);
            ImageIO.write(bufferedImage, "png", f);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        return bufferedImage;
    }

    public static Color transparant = new Color(255, 255, 255, 0);
    public static Color brown = new Color(200,76,12);

    public BufferedImage createImageTransparent(BufferedImage image, String name, int RGBToReplace){


        return createImage(image,name,RGBToReplace,transparant.getRGB());
    }

    public BufferedImage createImage(BufferedImage image, String name, int RGBToReplace,int RGBReplaicing){

        int width = image.getWidth();
        int height = image.getHeight();
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
        String path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
        File imagess = new File(path2.replaceAll("%20"," "));
        if (imagess.exists()){
            try {
                return ImageIO.read(imagess.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        // Create buffered image object
        BufferedImage img = null;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // file object
        File f = null;

        // create random values pixel by pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (image.getRGB(x, y) == RGBToReplace) {
                    img.setRGB(x, y, RGBReplaicing);
                } else {
                    img.setRGB(x, y, image.getRGB(x, y));
                }


            }
        }

        // write image, AKA save it to pc
        try
        {
            path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
            path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
            f = new File(path2.replaceAll("%20"," "));
            System.out.println("File saved in: "+path2);
            ImageIO.write(img, "png", f);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        return img;
    }


    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

        // Copy image
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }

    public static BufferedImage flipHorizontal(BufferedImage image){
        // Flip the image horizontally
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }

}

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
    public static final int pinkColor = new Color(248,232,248).getRGB();
    public SpriteSheet galagaSpriteSheet;

    public static BufferedImage pacmanImageSheet;
    public SpriteSheet pacmanSpriteSheet;



    //intro
    public static BufferedImage pocketMonsterIntroSheet;
    public SpriteSheet pocketMonsterIntroSpriteSheet;
    public static BufferedImage PokeCopyRight,PokeBlank,PokeTitle,PokeMonLogo,version;
    public static BufferedImage[] Pokelogo,Pokestar,PokeGengar,PokeNido,PokeGigly,PokeRed,PokeStars;

    public static BufferedImage pocketMonsterPokemonSheet;
    public SpriteSheet pocketMonsterPokemonSpriteSheet;
    public static ArrayList<BufferedImage> Pokemons;
    public static BufferedImage pocketMonsterPokemonsFightsSheet;
    public SpriteSheet pocketMonsterPokemonsFightsSpriteSheet;
    public static ArrayList<BufferedImage> PokemonsFights;
    public static BufferedImage PokeSelectingSquarePane,earrow,arrow;

    public static BufferedImage pocketMonsterPalletSheet;
    public SpriteSheet pocketMonsterPalletSpriteSheet;
    public static BufferedImage pocketMonsterNPCSheet;
    public SpriteSheet pocketMonsterNPCSpriteSheet;
    public static BufferedImage[] PokePallet,player;

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

        //intro
        Pokelogo = new BufferedImage[4];
        Pokestar = new BufferedImage[3];
        PokeGengar = new BufferedImage[3];
        PokeNido = new BufferedImage[3];
        PokeGigly = new BufferedImage[3];
        PokeRed = new BufferedImage[2];
        PokeStars = new BufferedImage[4];

        Pokemons = new ArrayList<>(152);
        PokemonsFights = new ArrayList<>(152);

        PokePallet = new BufferedImage[5];
        player = new BufferedImage[6];


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

            pocketMonsterIntroSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/pocketMonster/intro.png"));
            pocketMonsterIntroSpriteSheet = new SpriteSheet(pocketMonsterIntroSheet);

            //intro
            PokeCopyRight = pocketMonsterIntroSpriteSheet.crop(768,8,160,144);
            PokeBlank = pocketMonsterIntroSpriteSheet.crop(930,8,160,144);

            Pokelogo[0] = pocketMonsterIntroSpriteSheet.crop(768,162,160,144);
            Pokelogo[1] = pocketMonsterIntroSpriteSheet.crop(930,162,160,144);
            Pokelogo[2] = pocketMonsterIntroSpriteSheet.crop(1092,162,160,144);
            Pokelogo[3] = pocketMonsterIntroSpriteSheet.crop(1254,162,160,144);

            Pokestar[0] = pocketMonsterIntroSpriteSheet.crop(1416,162,16,16);
            Pokestar[0] = createImage(Pokestar[0].getWidth(),Pokestar[0].getHeight(),Pokestar[0],"star[0]_248,232,248_lightPink",pinkColor);
            Pokestar[1] = pocketMonsterIntroSpriteSheet.crop(1416,179,16,16);
            Pokestar[1] = createImage(Pokestar[0].getWidth(),Pokestar[0].getHeight(),Pokestar[1],"star[1]_248,232,248_lightPink",pinkColor);
            Pokestar[2] = pocketMonsterIntroSpriteSheet.crop(1416,196,16,16);
            Pokestar[2] = createImage(Pokestar[0].getWidth(),Pokestar[0].getHeight(),Pokestar[2],"star[2]_248,232,248_lightPink",pinkColor);

            PokeGengar[0] = pocketMonsterIntroSpriteSheet.crop(930,470,56,56);
            PokeGengar[0] = createImage(PokeGengar[0].getWidth(),PokeGengar[0].getHeight(),PokeGengar[0],"gengar[0]_248,232,248_lightPink",pinkColor);
            PokeGengar[1] = pocketMonsterIntroSpriteSheet.crop(988,470,56,56);
            PokeGengar[1] = createImage(PokeGengar[0].getWidth(),PokeGengar[0].getHeight(),PokeGengar[1],"gengar[1]_248,232,248_lightPink",pinkColor);
            PokeGengar[2] = pocketMonsterIntroSpriteSheet.crop(1046,470,56,56);
            PokeGengar[2] = createImage(PokeGengar[0].getWidth(),PokeGengar[0].getHeight(),PokeGengar[2],"gengar[2]_248,232,248_lightPink",pinkColor);

//            PokeNido[0] = pocketMonsterIntroSpriteSheet.crop(930,528,48,48);
//            PokeNido[1] = pocketMonsterIntroSpriteSheet.crop(988,528,48,48);
//            PokeNido[2] = pocketMonsterIntroSpriteSheet.crop(1046,528,48,48); //red

            PokeGigly[0] = pocketMonsterIntroSpriteSheet.crop(930,578,48,48);
            PokeGigly[1] = pocketMonsterIntroSpriteSheet.crop(988,578,48,48);
            PokeGigly[2] = pocketMonsterIntroSpriteSheet.crop(1046,578,48,48);

            PokeTitle = pocketMonsterIntroSpriteSheet.crop(930,640,160,144);
//            PokeTitle = pocketMonsterIntroSpriteSheet.crop(768,640,160,144); //red

            PokeMonLogo = pocketMonsterIntroSpriteSheet.crop(1092,640,128,56);
            PokeMonLogo = createImage(PokeMonLogo.getWidth(),PokeMonLogo.getHeight(),PokeMonLogo,"Logo_248,232,248_lightPink",pinkColor);

            version = pocketMonsterIntroSpriteSheet.crop(1092,708,72,8);
//            version = pocketMonsterIntroSpriteSheet.crop(1092,698,72,8);//red


            PokeRed[0] = pocketMonsterIntroSpriteSheet.crop(1092,720,40,56);
            PokeRed[0] = createImage(PokeRed[0].getWidth(),PokeRed[0].getHeight(),PokeRed[0],"Red_0_248,232,248_lightPink",pinkColor);
            PokeRed[1] = pocketMonsterIntroSpriteSheet.crop(1134,720,40,56);
            PokeRed[1] = createImage(PokeRed[1].getWidth(),PokeRed[1].getHeight(),PokeRed[1],"Red_1_248,232,248_lightPink",pinkColor);

            PokeStars[0] = pocketMonsterIntroSpriteSheet.crop(1092,316,8,8);
            PokeStars[0] = createImage(PokeStars[0].getWidth(),PokeStars[0].getHeight(),PokeStars[0],"miniStar_0_248,232,248_lightPink",pinkColor);
            PokeStars[1] = pocketMonsterIntroSpriteSheet.crop(1102,316,8,8);
            PokeStars[1] = createImage(PokeStars[0].getWidth(),PokeStars[0].getHeight(),PokeStars[1],"miniStar_1_248,232,248_lightPink",pinkColor);
            PokeStars[2] = pocketMonsterIntroSpriteSheet.crop(1112,316,8,8);
            PokeStars[2] = createImage(PokeStars[0].getWidth(),PokeStars[0].getHeight(),PokeStars[2],"miniStar_2_248,232,248_lightPink",pinkColor);
            PokeStars[3] = pocketMonsterIntroSpriteSheet.crop(1122,316,8,8);
            PokeStars[3] = createImage(PokeStars[0].getWidth(),PokeStars[0].getHeight(),PokeStars[3],"miniStar_3_248,232,248_lightPink",pinkColor);

            PokeSelectingSquarePane = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/pocketMonster/Font.png"))).crop(187,170,318,75);
            arrow = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/pocketMonster/Font.png"))).crop(110,60,5,7);
            earrow = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/pocketMonster/Font.png"))).crop(94,60,5,7);

            pocketMonsterPalletSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/pocketMonster/palletTown.png"));
            pocketMonsterPalletSpriteSheet = new SpriteSheet(pocketMonsterPalletSheet);

            PokePallet[0] = pocketMonsterPalletSpriteSheet.crop(336,24,128,128);//room
            PokePallet[1] = pocketMonsterPalletSpriteSheet.crop(472,24,128,128);//sala
            PokePallet[2] = pocketMonsterPalletSpriteSheet.crop(336,160,128,128);//neighboor
            PokePallet[3] = pocketMonsterPalletSpriteSheet.crop(472,160,160,192);//lab
            PokePallet[4] = pocketMonsterPalletSpriteSheet.crop(7,24,321,288);//city

            pocketMonsterNPCSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/pocketMonster/npc.png"));
            pocketMonsterNPCSpriteSheet = new SpriteSheet(createImage(pocketMonsterNPCSheet.getWidth(),pocketMonsterNPCSheet.getHeight(),pocketMonsterNPCSheet,"npc_0,255,0_green",new Color(0,255,0).getRGB()));

            player[0] = pocketMonsterNPCSpriteSheet.crop(113,269,14,16);//front
            player[1] = pocketMonsterNPCSpriteSheet.crop(132,269,14,16);//back
            player[2] = pocketMonsterNPCSpriteSheet.crop(7,288,13,16);//side
            player[3] = pocketMonsterNPCSpriteSheet.crop(23,289,14,15);//runningF
            player[4] = pocketMonsterNPCSpriteSheet.crop(39,289,14,15);//runningB
            player[5] = pocketMonsterNPCSpriteSheet.crop(55,289,13,15);//runningS




            //pokemons
            pocketMonsterPokemonSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/pocketMonster/pokedexColor.png"));
            pocketMonsterPokemonSpriteSheet = new SpriteSheet(createImage(pocketMonsterPokemonSheet.getWidth(),pocketMonsterPokemonSheet.getHeight(),pocketMonsterPokemonSheet,"pokemons_255,0,255_violet", new Color(255,0,255).getRGB()));

            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,23,34,36));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(53,19,40,40));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(108,9,53,50));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(176,20,35,39));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(226,14,48,45));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(289,5,55,54));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(359,27,35,32));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(409,15,39,44));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(463,5,54,54));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(532,33,26,26));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(573,27,28,32));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(616,6,54,53));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(685,24,24,35));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(724,21,27,38));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(766,4,52,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,101,33,30));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(52,86,47,45));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(114,77,53,54));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(182,98,32,33));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(229,84,48,47));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(292,99,32,32));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(339,83,53,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(407,96,34,35));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(456,75,55,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(527,91,37,40));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(580,77,54,54));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(649,96,37,35));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(701,83,47,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(763,105,31,26));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,168,46,39));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(65,151,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(136,174,36,33));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(187,164,44,43));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(246,151,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(317,172,37,35));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(369,161,48,46));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(432,160,44,47));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(491,152,56,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(562,174,37,33));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(614,159,47,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(676,170,39,37));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(730,152,56,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,251,29,33));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(51,245,38,39));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(104,236,48,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(167,258,36,26));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(218,228,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(289,244,39,40));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(343,229,50,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(408,254,38,30));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(461,243,48,41));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(524,249,38,35));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(577,229,53,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(645,244,38,40));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(698,228,52,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(765,247,40,37));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,307,49,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(68,324,34,39));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(117,309,53,54));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(185,331,36,32));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(236,317,45,46));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(296,310,52,53));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(363,326,34,37));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(412,315,46,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(473,307,54,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(542,324,39,39));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(596,312,51,51));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(662,308,56,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(733,325,32,38));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(780,321,46,42));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,387,50,54));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(69,409,38,32));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(122,394,47,47));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(184,408,39,33));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(238,393,48,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(301,397,47,44));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(363,396,44,45));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(422,386,55,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(492,404,40,37));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(547,385,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(618,418,26,23));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(659,393,48,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(722,394,47,47));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(784,402,36,39));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,461,50,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(69,469,47,47));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(131,468,47,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(193,476,40,40));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(248,460,55,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(318,478,36,38));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(369,464,53,52));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(437,467,50,46));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(506,468,48,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(569,469,47,47));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(631,461,50,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(696,468,48,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(759,461,54,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,554,40,39));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(59,543,56,50));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(130,568,25,25));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(170,558,36,35));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(221,541,53,52));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(289,540,56,53));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(360,555,35,38));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(410,545,48,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(473,537,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(544,545,47,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(606,548,55,45));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(676,546,46,46));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(738,542,54,51));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,616,56,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(75,618,55,53));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(145,623,47,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(207,627,45,44));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(267,616,54,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(336,639,31,32));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(382,623,45,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(442,624,36,47));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(493,617,56,54));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(564,629,39,42));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(618,624,47,47));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(680,629,48,42));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(743,615,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,701,47,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(66,704,48,45));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(129,706,46,43));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(190,695,53,54));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(258,693,55,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(328,701,48,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(391,693,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(462,693,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(533,719,34,30));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(582,711,38,38));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(635,705,46,44));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(696,706,48,43));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(759,702,47,47));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,782,47,45));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(66,795,31,32));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(112,779,47,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(174,791,40,36));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(229,781,47,46));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(291,777,56,50));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(362,772,54,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(431,771,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(502,772,53,55));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(570,771,55,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(640,788,32,39));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(687,783,46,44));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(748,774,52,53));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(4,847,56,56));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(75,863,36,40));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(126,858,45,45));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(186,851,51,52));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(252,855,48,48));
            Pokemons.add(pocketMonsterPokemonSpriteSheet.crop(315,847,24,56));

            pocketMonsterPokemonsFightsSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/pocketMonster/pokemonsFight.png"));
            pocketMonsterPokemonsFightsSpriteSheet = new SpriteSheet(createImage(pocketMonsterPokemonsFightsSheet.getWidth(),pocketMonsterPokemonsFightsSheet.getHeight(),pocketMonsterPokemonsFightsSheet,"pokemonsFight_36,71,124_BLUE", new Color(36,71,124).getRGB()));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(6,11,28,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(37,7,24,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(66,2,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(98,13,22,17));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(127,7,27,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(156,4,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(187,6,22,24));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(216,4,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(246,9,28,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(280,12,20,18));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(307,5,23,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(6,36,26,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(47,36,15,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(73,41,16,18));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(96,32,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(131,44,19,15));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(159,38,25,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(186,33,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(223,44,17,15));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(247,37,26,22));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(280,42,21,17));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(306,31,26,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(12,70,17,18));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(36,62,25,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(67,68,26,20));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(96,60,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(128,70,25,18));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(157,65,27,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(192,75,19,13));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(216,65,28,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(246,60,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(279,69,20,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(306,60,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(6,89,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(41,101,21,16));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(66,89,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(101,93,19,24));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(126,89,27,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(160,101,20,16));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(186,96,28,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(220,100,21,17));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(246,99,27,18));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(277,90,27,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(306,94,27,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(6,124,27,22));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(38,129,25,17));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(66,127,28,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(97,127,24,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(126,128,27,18));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(157,124,26,20));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(186,123,28,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(220,127,20,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(246,119,28,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(279,125,24,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(306,121,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(10,154,21,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(36,150,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(69,155,22,20));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(96,150,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(128,157,24,18));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(157,153,27,22));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(187,154,27,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(216,152,28,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(246,155,28,20));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(276,149,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(308,152,25,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(6,179,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(36,180,28,24));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(66,179,24,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(96,181,27,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(127,176,23,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(159,189,22,15));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(186,177,28,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(220,188,22,15));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(246,179,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(276,187,27,17));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(309,179,23,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(6,207,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(39,211,25,22));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(66,208,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(99,214,22,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(126,212,28,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(156,206,27,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(186,210,28,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(216,205,27,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(248,214,25,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(276,207,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(306,205,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(6,237,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(37,246,22,16));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(66,234,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(97,237,27,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(126,234,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(156,238,28,24));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(189,234,24,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(216,240,28,22));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(246,241,28,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(277,240,24,22));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(306,235,28,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(11,272,19,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(39,269,22,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(66,271,28,20));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(96,267,28,24));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(127,269,26,22));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(156,267,28,24));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(186,271,24,20));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(216,266,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(246,264,27,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(276,264,28,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(308,264,26,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(5,294,26,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(33,295,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(63,302,28,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(93,304,28,17));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(123,297,27,24));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(158,304,17,17));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(183,293,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(213,300,26,20));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(243,293,27,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(274,305,25,16));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(303,307,28,14));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(5,325,25,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(33,322,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(66,326,24,24));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(93,328,28,22));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(123,322,27,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(153,325,27,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(183,324,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(213,325,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(243,322,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(274,322,26,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(305,336,25,14));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(6,360,22,19));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(33,353,26,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(63,352,28,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(93,353,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(123,363,28,16));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(158,362,21,17));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(183,351,28,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(217,363,23,16));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(243,353,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(273,353,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(303,355,27,24));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(5,387,21,23));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(32,385,28,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(62,382,27,28));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(95,389,25,21));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(122,385,29,25));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(152,384,28,26));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(182,383,28,27));
            PokemonsFights.add(pocketMonsterPokemonsFightsSpriteSheet.crop(216,390,20,20));

        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static Color transparant = new Color(255, 255, 255, 0);

    public BufferedImage createImage(int width,int height,BufferedImage image,String name, int RGBToReplace){

        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
        String path2 = path.substring(0,path.indexOf("/out/"))+"/res/Edited/"+name+".png";
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
                    img.setRGB(x, y, transparant.getRGB());
                } else {
                    img.setRGB(x, y, image.getRGB(x, y));
                }


            }
        }

        // write image, AKA save it to pc
        try
        {
            path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
            path2 = path.substring(0,path.indexOf("/out/"))+"/res/Edited/"+name+".png";
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

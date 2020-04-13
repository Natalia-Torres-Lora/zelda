package Game.Zelda.World;

import Game.GameStates.Zelda.ZeldaMapMakerState;
import Game.Zelda.Entities.Dynamic.MMLink;
import Game.Zelda.Entities.Statics.MMSolidStaticEntities;
import Game.Zelda.Entities.Statics.MMTeleport;
import Game.Zelda.Entities.Statics.MMWalkingSolidEntities;
import Main.Handler;
import Resources.Images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class MapBuilder {



	public static Map createMap(BufferedImage mapImage, Handler handler,String name){
		if (name.contains(".png")){
			name = name.replace(".png","");
		}
		name+=".txt";
		String path = Objects.requireNonNull(MapBuilder.class.getClassLoader().getResource(".")).getPath();
		String path2 = path.substring(0,path.indexOf("/out/"))+"/res/Edited/"+name;
		ArrayList<ArrayList<int[]>> linkedTeli = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader( new File(path2)));
			String line;
			// read through the first two lines to get to the data
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				System.out.println("Parsing: " + line);

				String xy1 = (line.substring(line.indexOf("[") + 1,line.indexOf("]")));
				String xy2 = (line.substring(line.lastIndexOf("[") + 1,line.lastIndexOf("]")));
				System.out.println("xy1: " + xy1);
				System.out.println("xy2: " + xy2);

				ArrayList<int[]> coords = new ArrayList<>();
				int[] xy = new int[2];
				xy[0] = Integer.parseInt(xy1.substring(0,xy1.indexOf(",")));
				xy[1] = Integer.parseInt(xy1.substring(xy1.indexOf(",") + 2));
				coords.add(xy);
				xy = new int[2];
				xy[0] = Integer.parseInt(xy2.substring(0,xy2.indexOf(",")));
				xy[1] = Integer.parseInt(xy2.substring(xy2.indexOf(",") + 2));
				coords.add(xy);
				linkedTeli.add(coords);
				System.out.println(Arrays.toString(coords.get(0)) + " , " + Arrays.toString(coords.get(1)));
				System.out.println();
			}
			br.close();
		} catch (Exception e) {
			handler.getDisplayScreen().confirm("There was an issue parsing the file:\n" +
					path2 + "\n" +
					"It was not found, assuming there is no teleport pads in map\n" +
					"If there is, they wont work.");
			System.out.println("There was an issue parsing the file. Teleport file not found, assuming there is no teleport pads in map\n" +
					"If there is, they wont work.");
		}
		Map mapInCreation = new Map(handler);
		for (int i = 0; i < mapImage.getWidth(); i++) {
			for (int j = 0; j < mapImage.getHeight(); j++) {
				int currentPixel = mapImage.getRGB(i, j);
				int xPos = i*pixelMultiplier;
				int yPos = j*pixelMultiplier;
				if(currentPixel == DDDoor){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(0),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DDDoor1){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(1),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DDDoor2){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(2),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DDDoor3){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(3),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DDDoor4){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(4),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DRDoor){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(5),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DRDoor1){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(6),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DRDoor2){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(7),handler);
					mapInCreation.addBlock(ghost);
				}if(currentPixel == DRDoor3){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(8),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DRDoor4){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(9),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DLDoor){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(10),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DLDoor1){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(11),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DLDoor2){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(12),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DLDoor3){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(13),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DLDoor4){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(14),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DUDoor){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(15),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DUDoor1){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(16),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DUDoor2){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(17),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DUDoor3){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(18),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DUDoor4){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(19),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dfloor){
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos,yPos,Images.zeldaTiles.get(20),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dwall){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(21),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DRShooter){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(22),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DLShooter){
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(23),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dhole){
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos,yPos,Images.zeldaTiles.get(24),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dsand){
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos,yPos,Images.zeldaTiles.get(25),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dwater){
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos,yPos,Images.zeldaTiles.get(26),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dstairs){
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos,yPos,Images.zeldaTiles.get(27),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(0), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest1) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(1), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest2) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(2), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest3) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(3), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest4) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(4), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest5) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(5), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest6) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(6), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest7) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(7), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest8) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(8), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest9) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(9), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest10) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(10), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest11) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(11), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest12) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(12), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest13) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(13), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest14) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(14), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest15) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(15), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest16) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(16), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest17) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(17), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest18) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(18), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest19) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(19), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest20) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(20), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest21) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(21), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest22) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(22), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest23) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(23), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest24) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(24), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest25) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(25), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest26) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(26), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest27) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(27), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest28) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(28), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest29) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(29), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest30) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(30), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest31) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(31), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest32) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(32), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest33) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(33), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest34) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(34), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest35) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(35), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest36) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(36), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest37) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(37), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest38) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.forestTiles.get(38), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest39) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(39), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest40) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(40), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == forest41) {
					MMTeleport ghost = new MMTeleport(xPos, yPos, Images.forestTiles.get(41), handler);
					mapInCreation.addBlock(ghost);
					checkForTeli(ghost,xPos,yPos,linkedTeli);
				}else if(currentPixel == cave) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.caveTiles.get(0), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave1) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(1), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave2) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(2), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave3) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(3), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave4) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(4), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave5) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(5), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave6) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(6), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave7) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(7), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave8) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(8), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave9) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(9), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave10) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(10), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave11) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(11), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave12) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(12), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave13) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(13), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave14) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(14), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave15) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(15), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave16) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.caveTiles.get(16), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave17) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.caveTiles.get(17), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave18) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.caveTiles.get(18), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave19) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.caveTiles.get(19), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave20) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(20), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave21) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(21), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave22) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(22), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave23) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(23), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave24) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(24), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave25) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(25), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave26) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(26), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave27) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(27), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave28) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(28), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave29) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(29), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave30) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(30), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave31) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(31), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave32) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.caveTiles.get(32), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave33) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(33), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave34) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(34), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave35) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(35), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave36) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(36), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave37) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.caveTiles.get(37), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave38) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.caveTiles.get(38), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave39) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.caveTiles.get(39), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave40) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.caveTiles.get(40), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == cave41) {
					MMTeleport ghost = new MMTeleport(xPos, yPos, Images.caveTiles.get(41), handler);
					mapInCreation.addBlock(ghost);
					checkForTeli(ghost,xPos,yPos,linkedTeli);
				}else if(currentPixel == mountain) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.mountainTiles.get(0), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain1) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(1), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain2) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(2), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain3) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(3), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain4) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(4), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain5) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(5), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain6) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(6), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain7) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(7), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain8) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(8), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain9) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(9), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain10) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(10), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain11) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(11), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain12) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(12), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain13) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(13), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain14) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(14), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain15) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(15), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain16) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.mountainTiles.get(16), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain17) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.mountainTiles.get(17), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain18) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.mountainTiles.get(18), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain19) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.mountainTiles.get(19), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain20) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(20), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain21) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(21), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain22) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(22), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain23) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(23), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain24) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(24), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain25) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(25), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain26) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(26), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain27) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(27), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain28) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(28), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain29) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(29), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain30) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(30), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain31) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(31), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain32) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.mountainTiles.get(32), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain33) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(33), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain34) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(34), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain35) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(35), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain36) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(36), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain37) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.mountainTiles.get(37), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain38) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.mountainTiles.get(38), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain39) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.mountainTiles.get(39), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain40) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.mountainTiles.get(40), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == mountain41) {
					MMTeleport ghost = new MMTeleport(xPos, yPos, Images.mountainTiles.get(41), handler);
					mapInCreation.addBlock(ghost);
					checkForTeli(ghost,xPos,yPos,linkedTeli);
				}else if(currentPixel == grave) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.graveTiles.get(0), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave1) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(1), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave2) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(2), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave3) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(3), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave4) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(4), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave5) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(5), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave6) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(6), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave7) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(7), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave8) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(8), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave9) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(9), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave10) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(10), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave11) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(11), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave12) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(12), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave13) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(13), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave14) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(14), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave15) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(15), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave16) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.graveTiles.get(16), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave17) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.graveTiles.get(17), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave18) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.graveTiles.get(18), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave19) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.graveTiles.get(19), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave20) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(20), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave21) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(21), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave22) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(22), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave23) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(23), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave24) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(24), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave25) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(25), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave26) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(26), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave27) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(27), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave28) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(28), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave29) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(29), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave30) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(30), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave31) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(31), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave32) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.graveTiles.get(32), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave33) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(33), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave34) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(34), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave35) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(35), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave36) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(36), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave37) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.graveTiles.get(37), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave38) {
					MMSolidStaticEntities ghost = new MMSolidStaticEntities(xPos, yPos, Images.graveTiles.get(38), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave39) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.graveTiles.get(39), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave40) {
					MMWalkingSolidEntities ghost = new MMWalkingSolidEntities(xPos, yPos, Images.graveTiles.get(40), handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == grave41) {
					MMTeleport ghost = new MMTeleport(xPos, yPos, Images.graveTiles.get(41), handler);
					mapInCreation.addBlock(ghost);
					checkForTeli(ghost,xPos,yPos,linkedTeli);
				}else if(currentPixel == Link) {
					MMLink ghost = new MMLink(xPos, yPos, Images.zeldaLinkFrames, handler);
					mapInCreation.addEnemy(ghost);
					mapInCreation.link = ghost;
					MMWalkingSolidEntities ghost1 = new MMWalkingSolidEntities(xPos, yPos, Images.forestTiles.get(0), handler);
					mapInCreation.addBlock(ghost1);
				}


			}
		}
		if (mapInCreation.link != null) {
			mapInCreation.link.map = mapInCreation;
		}
		return mapInCreation;
	}

	public static BufferedImage arrayToRGBImage(ArrayList<ArrayList<BufferedImage>> info,String name,ArrayList<ArrayList<int[]>> teleportList){

		String path = Objects.requireNonNull(MapBuilder.class.getClassLoader().getResource(".")).getPath();
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
		BufferedImage image = new BufferedImage(info.size(),info.get(0).size(), BufferedImage.TYPE_INT_ARGB);
		// file object
		File f = null;
		File f2 = null;
		for (int y = 0; y < info.get(0).size(); y++) {
			for (int x = 0; x < info.size(); x++) {
				if (Images.zeldaTiles.get(0).equals(info.get(x).get(y))){
					image.setRGB(x,y,DDDoor);
				}else if (Images.zeldaTiles.get(1).equals(info.get(x).get(y))){
					image.setRGB(x,y,DDDoor1);
				}else if (Images.zeldaTiles.get(2).equals(info.get(x).get(y))){
					image.setRGB(x,y,DDDoor2);
				}else if (Images.zeldaTiles.get(3).equals(info.get(x).get(y))){
					image.setRGB(x,y,DDDoor3);
				}else if (Images.zeldaTiles.get(4).equals(info.get(x).get(y))){
					image.setRGB(x,y,DDDoor4);
				}else if (Images.zeldaTiles.get(5).equals(info.get(x).get(y))){
					image.setRGB(x,y,DRDoor);
				}else if (Images.zeldaTiles.get(6).equals(info.get(x).get(y))){
					image.setRGB(x,y,DRDoor1);
				}else if (Images.zeldaTiles.get(7).equals(info.get(x).get(y))){
					image.setRGB(x,y,DRDoor2);
				}else if (Images.zeldaTiles.get(8).equals(info.get(x).get(y))){
					image.setRGB(x,y,DRDoor3);
				}else if (Images.zeldaTiles.get(9).equals(info.get(x).get(y))){
					image.setRGB(x,y,DRDoor4);
				}else if (Images.zeldaTiles.get(10).equals(info.get(x).get(y))){
					image.setRGB(x,y,DLDoor);
				}else if (Images.zeldaTiles.get(11).equals(info.get(x).get(y))){
					image.setRGB(x,y,DLDoor1);
				}else if (Images.zeldaTiles.get(12).equals(info.get(x).get(y))){
					image.setRGB(x,y,DLDoor2);
				}else if (Images.zeldaTiles.get(13).equals(info.get(x).get(y))){
					image.setRGB(x,y,DLDoor3);
				}else if (Images.zeldaTiles.get(14).equals(info.get(x).get(y))){
					image.setRGB(x,y,DLDoor4);
				}else if (Images.zeldaTiles.get(15).equals(info.get(x).get(y))){
					image.setRGB(x,y,DUDoor);
				}else if (Images.zeldaTiles.get(16).equals(info.get(x).get(y))){
					image.setRGB(x,y,DUDoor1);
				}else if (Images.zeldaTiles.get(17).equals(info.get(x).get(y))){
					image.setRGB(x,y,DUDoor2);
				}else if (Images.zeldaTiles.get(18).equals(info.get(x).get(y))){
					image.setRGB(x,y,DUDoor3);
				}else if (Images.zeldaTiles.get(19).equals(info.get(x).get(y))){
					image.setRGB(x,y,DUDoor4);
				}else if (Images.zeldaTiles.get(20).equals(info.get(x).get(y))){
					image.setRGB(x,y,Dfloor);
				}else if (Images.zeldaTiles.get(21).equals(info.get(x).get(y))){
					image.setRGB(x,y,Dwall);
				}else if (Images.zeldaTiles.get(22).equals(info.get(x).get(y))){
					image.setRGB(x,y,DRShooter);
				}else if (Images.zeldaTiles.get(23).equals(info.get(x).get(y))){
					image.setRGB(x,y,DLShooter);
				}else if (Images.zeldaTiles.get(24).equals(info.get(x).get(y))){
					image.setRGB(x,y,Dhole);
				}else if (Images.zeldaTiles.get(25).equals(info.get(x).get(y))){
					image.setRGB(x,y,Dsand);
				}else if (Images.zeldaTiles.get(26).equals(info.get(x).get(y))){
					image.setRGB(x,y,Dwater);
				}else if (Images.zeldaTiles.get(27).equals(info.get(x).get(y))){
					image.setRGB(x,y,Dstairs);
				}else if (Images.forestTiles.get(0).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest);
				}else if (Images.forestTiles.get(1).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest1);
				}else if (Images.forestTiles.get(2).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest2);
				}else if (Images.forestTiles.get(3).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest3);
				}else if (Images.forestTiles.get(4).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest4);
				}else if (Images.forestTiles.get(5).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest5);
				}else if (Images.forestTiles.get(6).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest6);
				}else if (Images.forestTiles.get(7).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest7);
				}else if (Images.forestTiles.get(8).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest8);
				}else if (Images.forestTiles.get(9).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest9);
				}else if (Images.forestTiles.get(10).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest10);
				}else if (Images.forestTiles.get(11).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest11);
				}else if (Images.forestTiles.get(12).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest12);
				}else if (Images.forestTiles.get(13).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest13);
				}else if (Images.forestTiles.get(14).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest14);
				}else if (Images.forestTiles.get(15).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest15);
				}else if (Images.forestTiles.get(16).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest16);
				}else if (Images.forestTiles.get(17).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest17);
				}else if (Images.forestTiles.get(18).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest18);
				}else if (Images.forestTiles.get(19).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest19);
				}else if (Images.forestTiles.get(20).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest20);
				}else if (Images.forestTiles.get(21).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest21);
				}else if (Images.forestTiles.get(22).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest22);
				}else if (Images.forestTiles.get(23).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest23);
				}else if (Images.forestTiles.get(24).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest24);
				}else if (Images.forestTiles.get(25).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest25);
				}else if (Images.forestTiles.get(26).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest26);
				}else if (Images.forestTiles.get(27).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest27);
				}else if (Images.forestTiles.get(28).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest28);
				}else if (Images.forestTiles.get(29).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest29);
				}else if (Images.forestTiles.get(30).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest30);
				}else if (Images.forestTiles.get(31).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest31);
				}else if (Images.forestTiles.get(32).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest32);
				}else if (Images.forestTiles.get(33).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest33);
				}else if (Images.forestTiles.get(34).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest34);
				}else if (Images.forestTiles.get(35).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest35);
				}else if (Images.forestTiles.get(36).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest36);
				}else if (Images.forestTiles.get(37).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest37);
				}else if (Images.forestTiles.get(38).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest38);
				}else if (Images.forestTiles.get(39).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest39);
				}else if (Images.forestTiles.get(40).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest40);
				}else if (Images.forestTiles.get(41).equals(info.get(x).get(y))){
					image.setRGB(x,y,forest41);
				}else if (Images.caveTiles.get(0).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave);
				}else if (Images.caveTiles.get(1).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave1);
				}else if (Images.caveTiles.get(2).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave2);
				}else if (Images.caveTiles.get(3).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave3);
				}else if (Images.caveTiles.get(4).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave4);
				}else if (Images.caveTiles.get(5).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave5);
				}else if (Images.caveTiles.get(6).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave6);
				}else if (Images.caveTiles.get(7).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave7);
				}else if (Images.caveTiles.get(8).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave8);
				}else if (Images.caveTiles.get(9).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave9);
				}else if (Images.caveTiles.get(10).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave10);
				}else if (Images.caveTiles.get(11).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave11);
				}else if (Images.caveTiles.get(12).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave12);
				}else if (Images.caveTiles.get(13).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave13);
				}else if (Images.caveTiles.get(14).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave14);
				}else if (Images.caveTiles.get(15).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave15);
				}else if (Images.caveTiles.get(16).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave16);
				}else if (Images.caveTiles.get(17).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave17);
				}else if (Images.caveTiles.get(18).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave18);
				}else if (Images.caveTiles.get(19).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave19);
				}else if (Images.caveTiles.get(20).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave20);
				}else if (Images.caveTiles.get(21).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave21);
				}else if (Images.caveTiles.get(22).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave22);
				}else if (Images.caveTiles.get(23).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave23);
				}else if (Images.caveTiles.get(24).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave24);
				}else if (Images.caveTiles.get(25).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave25);
				}else if (Images.caveTiles.get(26).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave26);
				}else if (Images.caveTiles.get(27).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave27);
				}else if (Images.caveTiles.get(28).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave28);
				}else if (Images.caveTiles.get(29).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave29);
				}else if (Images.caveTiles.get(30).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave30);
				}else if (Images.caveTiles.get(31).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave31);
				}else if (Images.caveTiles.get(32).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave32);
				}else if (Images.caveTiles.get(33).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave33);
				}else if (Images.caveTiles.get(34).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave34);
				}else if (Images.caveTiles.get(35).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave35);
				}else if (Images.caveTiles.get(36).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave36);
				}else if (Images.caveTiles.get(37).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave37);
				}else if (Images.caveTiles.get(38).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave38);
				}else if (Images.caveTiles.get(39).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave39);
				}else if (Images.caveTiles.get(40).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave40);
				}else if (Images.caveTiles.get(41).equals(info.get(x).get(y))){
					image.setRGB(x,y,cave41);
				}else if (Images.mountainTiles.get(0).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain);
				}else if (Images.mountainTiles.get(1).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain1);
				}else if (Images.mountainTiles.get(2).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain2);
				}else if (Images.mountainTiles.get(3).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain3);
				}else if (Images.mountainTiles.get(4).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain4);
				}else if (Images.mountainTiles.get(5).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain5);
				}else if (Images.mountainTiles.get(6).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain6);
				}else if (Images.mountainTiles.get(7).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain7);
				}else if (Images.mountainTiles.get(8).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain8);
				}else if (Images.mountainTiles.get(9).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain9);
				}else if (Images.mountainTiles.get(10).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain10);
				}else if (Images.mountainTiles.get(11).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain11);
				}else if (Images.mountainTiles.get(12).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain12);
				}else if (Images.mountainTiles.get(13).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain13);
				}else if (Images.mountainTiles.get(14).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain14);
				}else if (Images.mountainTiles.get(15).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain15);
				}else if (Images.mountainTiles.get(16).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain16);
				}else if (Images.mountainTiles.get(17).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain17);
				}else if (Images.mountainTiles.get(18).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain18);
				}else if (Images.mountainTiles.get(19).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain19);
				}else if (Images.mountainTiles.get(20).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain20);
				}else if (Images.mountainTiles.get(21).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain21);
				}else if (Images.mountainTiles.get(22).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain22);
				}else if (Images.mountainTiles.get(23).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain23);
				}else if (Images.mountainTiles.get(24).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain24);
				}else if (Images.mountainTiles.get(25).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain25);
				}else if (Images.mountainTiles.get(26).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain26);
				}else if (Images.mountainTiles.get(27).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain27);
				}else if (Images.mountainTiles.get(28).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain28);
				}else if (Images.mountainTiles.get(29).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain29);
				}else if (Images.mountainTiles.get(30).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain30);
				}else if (Images.mountainTiles.get(31).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain31);
				}else if (Images.mountainTiles.get(32).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain32);
				}else if (Images.mountainTiles.get(33).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain33);
				}else if (Images.mountainTiles.get(34).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain34);
				}else if (Images.mountainTiles.get(35).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain35);
				}else if (Images.mountainTiles.get(36).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain36);
				}else if (Images.mountainTiles.get(37).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain37);
				}else if (Images.mountainTiles.get(38).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain38);
				}else if (Images.mountainTiles.get(39).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain39);
				}else if (Images.mountainTiles.get(40).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain40);
				}else if (Images.mountainTiles.get(41).equals(info.get(x).get(y))){
					image.setRGB(x,y,mountain41);
				}else if (Images.graveTiles.get(0).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave);
				}else if (Images.graveTiles.get(1).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave1);
				}else if (Images.graveTiles.get(2).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave2);
				}else if (Images.graveTiles.get(3).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave3);
				}else if (Images.graveTiles.get(4).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave4);
				}else if (Images.graveTiles.get(5).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave5);
				}else if (Images.graveTiles.get(6).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave6);
				}else if (Images.graveTiles.get(7).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave7);
				}else if (Images.graveTiles.get(8).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave8);
				}else if (Images.graveTiles.get(9).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave9);
				}else if (Images.graveTiles.get(10).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave10);
				}else if (Images.graveTiles.get(11).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave11);
				}else if (Images.graveTiles.get(12).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave12);
				}else if (Images.graveTiles.get(13).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave13);
				}else if (Images.graveTiles.get(14).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave14);
				}else if (Images.graveTiles.get(15).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave15);
				}else if (Images.graveTiles.get(16).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave16);
				}else if (Images.graveTiles.get(17).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave17);
				}else if (Images.graveTiles.get(18).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave18);
				}else if (Images.graveTiles.get(19).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave19);
				}else if (Images.graveTiles.get(20).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave20);
				}else if (Images.graveTiles.get(21).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave21);
				}else if (Images.graveTiles.get(22).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave22);
				}else if (Images.graveTiles.get(23).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave23);
				}else if (Images.graveTiles.get(24).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave24);
				}else if (Images.graveTiles.get(25).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave25);
				}else if (Images.graveTiles.get(26).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave26);
				}else if (Images.graveTiles.get(27).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave27);
				}else if (Images.graveTiles.get(28).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave28);
				}else if (Images.graveTiles.get(29).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave29);
				}else if (Images.graveTiles.get(30).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave30);
				}else if (Images.graveTiles.get(31).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave31);
				}else if (Images.graveTiles.get(32).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave32);
				}else if (Images.graveTiles.get(33).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave33);
				}else if (Images.graveTiles.get(34).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave34);
				}else if (Images.graveTiles.get(35).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave35);
				}else if (Images.graveTiles.get(36).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave36);
				}else if (Images.graveTiles.get(37).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave37);
				}else if (Images.graveTiles.get(38).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave38);
				}else if (Images.graveTiles.get(39).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave39);
				}else if (Images.graveTiles.get(40).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave40);
				}else if (Images.graveTiles.get(41).equals(info.get(x).get(y))){
					image.setRGB(x,y,grave41);
				}else if (Images.zeldaLinkFrames[0].equals(info.get(x).get(y))){
					image.setRGB(x,y,Link);
				}


			}
		}

		try {
			path = Objects.requireNonNull(MapBuilder.class.getClassLoader().getResource(".")).getPath();
			path2 = path.substring(0, path.indexOf("/out/")) + "/res/Edited/" + name + ".png";
			String path3 = path.substring(0, path.indexOf("/out/")) + "/res/Edited/" + name + ".txt";
			f = new File(path2.replaceAll("%20", " "));
			System.out.println("File saved in: " + path2);
			ImageIO.write(image, "png", f);

			if (!teleportList.isEmpty()) {
				f2 = new File(path3);
				FileOutputStream fos = new FileOutputStream(f2);

				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				bw.write("List of linked teleports");
				bw.newLine();
				for (int i = 0; i < teleportList.size(); i++) {
					bw.write("Teleporter at " + Arrays.toString(teleportList.get(i).get(0)) + " linked to " + Arrays.toString(teleportList.get(i).get(1)));
					bw.newLine();
				}

				bw.close();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}

		return image;

	}

	private static void checkForTeli(MMTeleport ghost, int x, int y,ArrayList<ArrayList<int[]>> linkedTeleport) {
		x=x/pixelMultiplier;
		y=y/pixelMultiplier;
		if (linkedTeleport.isEmpty()){
			System.out.println("List is empty");
			return;
		}
		for (ArrayList<int[]> linkedTelis: linkedTeleport){
			System.out.println("Linking " + x + " , " + y + " to "+ Arrays.toString(linkedTelis.get(0)) + " or " + Arrays.toString(linkedTelis.get(1)));

			if (linkedTelis.get(0)[0] == x && linkedTelis.get(0)[1] == y){
				System.out.println("Linked " + x + " , " + y + " to "+ Arrays.toString(linkedTelis.get(1)));
				ghost.linkedX = linkedTelis.get(1)[0]*pixelMultiplier;
				ghost.linkedY = linkedTelis.get(1)[1]*pixelMultiplier;
				System.out.println();
				return;
			}
			else if (linkedTelis.get(1)[0] == x && linkedTelis.get(1)[1] == y){
				System.out.println("Linked " + x + " , " + y + " to "+ Arrays.toString(linkedTelis.get(0)));

				ghost.linkedX = linkedTelis.get(0)[0]*pixelMultiplier;
				ghost.linkedY = linkedTelis.get(0)[1]*pixelMultiplier;
				System.out.println();
				return;
			}

		}
	}

	public static int pixelMultiplier = ZeldaMapMakerState.pixelsPerSquare;//change this for size of blocks

	public static int Link = new Color(0, 255, 0).getRGB();


	//dungeons
	public static int Dhole = new Color(0, 0, 0).getRGB();//smalls
	public static int Dwall = new Color(4, 225, 209).getRGB();
	public static int Dsand = new Color(8, 124, 132).getRGB();
	public static int Dwater = new Color(12, 60, 92).getRGB();
	public static int Dfloor = new Color(16, 180, 179).getRGB();
	public static int Dstairs = new Color(18, 117, 167).getRGB();
	public static int DRShooter = new Color(20, 167, 139).getRGB();
	public static int DLShooter = new Color(22, 167, 123).getRGB();
	public static int DDDoor = new Color(24, 76, 92).getRGB();//bigs
	public static int DDDoor1 = new Color(26, 180, 179).getRGB();
	public static int DDDoor2 = new Color(28, 117, 167).getRGB();
	public static int DDDoor3 = new Color(32, 167, 139).getRGB();
	public static int DDDoor4 = new Color(34, 167, 123).getRGB();
	public static int DRDoor = new Color(36, 76, 92).getRGB();//bigs
	public static int DRDoor1 = new Color(38, 180, 179).getRGB();
	public static int DRDoor2 = new Color(40, 117, 167).getRGB();
	public static int DRDoor3 = new Color(42, 167, 139).getRGB();
	public static int DRDoor4 = new Color(44, 167, 123).getRGB();
	public static int DLDoor = new Color(46, 76, 92).getRGB();//bigs
	public static int DLDoor1 = new Color(48, 180, 179).getRGB();
	public static int DLDoor2 = new Color(50, 117, 167).getRGB();
	public static int DLDoor3 = new Color(52, 167, 139).getRGB();
	public static int DLDoor4 = new Color(54, 167, 123).getRGB();
	public static int DUDoor = new Color(58, 76, 92).getRGB();//bigs
	public static int DUDoor1 = new Color(60, 180, 179).getRGB();
	public static int DUDoor2 = new Color(62, 117, 167).getRGB();
	public static int DUDoor3 = new Color(64, 167, 139).getRGB();
	public static int DUDoor4 = new Color(68, 167, 123).getRGB();

	//next 42 forest
	public static int forest = new Color(0, 2, 90).getRGB();//bigs
	public static int forest1 = new Color(0, 4, 79).getRGB();
	public static int forest2 = new Color(0, 8, 67).getRGB();
	public static int forest3 = new Color(0, 12, 39).getRGB();
	public static int forest4 = new Color(0, 14, 23).getRGB();
	public static int forest5 = new Color(0, 16, 90).getRGB();//bigs
	public static int forest6 = new Color(0, 18, 79).getRGB();
	public static int forest7 = new Color(0, 20, 67).getRGB();
	public static int forest8 = new Color(0, 22, 39).getRGB();
	public static int forest9 = new Color(0, 24, 23).getRGB();
	public static int forest10 = new Color(0, 26, 90).getRGB();//bigs
	public static int forest11 = new Color(0, 28, 79).getRGB();
	public static int forest12 = new Color(0, 30, 67).getRGB();
	public static int forest13 = new Color(0, 32, 39).getRGB();
	public static int forest14 = new Color(0, 34, 23).getRGB();
	public static int forest15 = new Color(0, 36, 90).getRGB();//bigs
	public static int forest16 = new Color(0, 38, 79).getRGB();
	public static int forest17 = new Color(0, 39, 67).getRGB();
	public static int forest18 = new Color(0, 42, 39).getRGB();
	public static int forest19 = new Color(0, 44, 23).getRGB();
	public static int forest20 = new Color(0, 46, 90).getRGB();//bigs
	public static int forest21 = new Color(0, 48, 79).getRGB();
	public static int forest22 = new Color(0, 60, 67).getRGB();
	public static int forest23 = new Color(0, 64, 39).getRGB();
	public static int forest24 = new Color(0, 66, 23).getRGB();
	public static int forest25 = new Color(0, 68, 90).getRGB();//bigs
	public static int forest26 = new Color(0, 70, 79).getRGB();
	public static int forest27 = new Color(0, 72, 67).getRGB();
	public static int forest28 = new Color(0, 74, 39).getRGB();
	public static int forest29 = new Color(0, 76, 23).getRGB();
	public static int forest30 = new Color(0, 78, 90).getRGB();//bigs
	public static int forest31 = new Color(0, 80, 79).getRGB();
	public static int forest32 = new Color(0, 82, 67).getRGB();
	public static int forest33 = new Color(0, 84, 39).getRGB();
	public static int forest34 = new Color(0, 86, 23).getRGB();
	public static int forest35 = new Color(0, 88, 90).getRGB();//bigs
	public static int forest36 = new Color(0, 90, 79).getRGB();
	public static int forest37 = new Color(0, 92, 67).getRGB();
	public static int forest38 = new Color(0, 94, 39).getRGB();
	public static int forest39 = new Color(0, 96, 23).getRGB();
	public static int forest40 = new Color(0, 98, 23).getRGB();
	public static int forest41 = new Color(0, 100, 23).getRGB();

	//next 42 cave
	public static int cave = new Color(90, 90, 2).getRGB();//bigs
	public static int cave1 = new Color(79, 79, 4).getRGB();
	public static int cave2 = new Color(67, 67, 8).getRGB();
	public static int cave3 = new Color(39, 39, 12).getRGB();
	public static int cave4 = new Color(23, 23, 14).getRGB();
	public static int cave5 = new Color(90, 90, 16).getRGB();//bigs
	public static int cave6 = new Color(79, 79, 18).getRGB();
	public static int cave7 = new Color(67, 67, 20).getRGB();
	public static int cave8 = new Color(39, 39, 22).getRGB();
	public static int cave9 = new Color(23, 23, 24).getRGB();
	public static int cave10 = new Color(90, 90, 26).getRGB();//bigs
	public static int cave11 = new Color(79, 79, 28).getRGB();
	public static int cave12 = new Color(67, 67, 30).getRGB();
	public static int cave13 = new Color(39, 39, 32).getRGB();
	public static int cave14 = new Color(23, 23, 34).getRGB();
	public static int cave15 = new Color(90, 90, 36).getRGB();//bigs
	public static int cave16 = new Color(79, 79, 38).getRGB();
	public static int cave17 = new Color(67, 67, 39).getRGB();
	public static int cave18 = new Color(39, 39, 42).getRGB();
	public static int cave19 = new Color(23, 23, 44).getRGB();
	public static int cave20 = new Color(90, 90, 46).getRGB();//bigs
	public static int cave21 = new Color(79, 79, 48).getRGB();
	public static int cave22 = new Color(67, 67, 60).getRGB();
	public static int cave23 = new Color(39, 39, 64).getRGB();
	public static int cave24 = new Color(23, 23, 66).getRGB();
	public static int cave25 = new Color(90, 90, 68).getRGB();//bigs
	public static int cave26 = new Color(79, 79, 70).getRGB();
	public static int cave27 = new Color(67, 67, 72).getRGB();
	public static int cave28 = new Color(39, 39, 74).getRGB();
	public static int cave29 = new Color(23, 23, 76).getRGB();
	public static int cave30 = new Color(90, 90, 78).getRGB();//bigs
	public static int cave31 = new Color(79, 79, 80).getRGB();
	public static int cave32 = new Color(67, 67, 82).getRGB();
	public static int cave33 = new Color(39, 39, 84).getRGB();
	public static int cave34 = new Color(23, 23, 86).getRGB();
	public static int cave35 = new Color(90, 90, 88).getRGB();//bigs
	public static int cave36 = new Color(79, 79, 90).getRGB();
	public static int cave37 = new Color(67, 67, 92).getRGB();
	public static int cave38 = new Color(39, 39, 94).getRGB();
	public static int cave39 = new Color(23, 23, 96).getRGB();
	public static int cave40 = new Color(23, 23, 98).getRGB();
	public static int cave41 = new Color(23, 23 ,100).getRGB();

	//next 42 mountain
	public static int mountain = new Color(2, 2, 90).getRGB();//bigs
	public static int mountain1 = new Color(4, 4, 79).getRGB();
	public static int mountain2 = new Color(8, 8, 67).getRGB();
	public static int mountain3 = new Color(12, 12, 39).getRGB();
	public static int mountain4 = new Color(14, 14, 23).getRGB();
	public static int mountain5 = new Color(16, 16, 90).getRGB();//bigs
	public static int mountain6 = new Color(18, 18, 79).getRGB();
	public static int mountain7 = new Color(20, 20, 67).getRGB();
	public static int mountain8 = new Color(22, 22, 39).getRGB();
	public static int mountain9 = new Color(24, 24, 23).getRGB();
	public static int mountain10 = new Color(26, 26, 90).getRGB();//bigs
	public static int mountain11 = new Color(28, 28, 79).getRGB();
	public static int mountain12 = new Color(30, 30, 67).getRGB();
	public static int mountain13 = new Color(32, 32, 39).getRGB();
	public static int mountain14 = new Color(34, 34, 23).getRGB();
	public static int mountain15 = new Color(36, 36, 90).getRGB();//bigs
	public static int mountain16 = new Color(38, 38, 79).getRGB();
	public static int mountain17 = new Color(39, 39, 67).getRGB();
	public static int mountain18 = new Color(42, 42, 39).getRGB();
	public static int mountain19 = new Color(44, 44, 23).getRGB();
	public static int mountain20 = new Color(46, 46, 90).getRGB();//bigs
	public static int mountain21 = new Color(48, 48, 79).getRGB();
	public static int mountain22 = new Color(60, 60, 67).getRGB();
	public static int mountain23 = new Color(64, 64, 39).getRGB();
	public static int mountain24 = new Color(66, 66, 23).getRGB();
	public static int mountain25 = new Color(68, 68, 90).getRGB();//bigs
	public static int mountain26 = new Color(70, 70, 79).getRGB();
	public static int mountain27 = new Color(72, 72, 67).getRGB();
	public static int mountain28 = new Color(74, 74, 39).getRGB();
	public static int mountain29 = new Color(76, 76, 23).getRGB();
	public static int mountain30 = new Color(78, 78, 90).getRGB();//bigs
	public static int mountain31 = new Color(80, 80, 79).getRGB();
	public static int mountain32 = new Color(82, 82, 67).getRGB();
	public static int mountain33 = new Color(84, 84, 39).getRGB();
	public static int mountain34 = new Color(86, 86, 23).getRGB();
	public static int mountain35 = new Color(88, 88, 90).getRGB();//bigs
	public static int mountain36 = new Color(90, 90, 79).getRGB();
	public static int mountain37 = new Color(92, 92, 67).getRGB();
	public static int mountain38 = new Color(94, 94, 39).getRGB();
	public static int mountain39 = new Color(96, 96, 23).getRGB();
	public static int mountain40 = new Color(98, 98, 23).getRGB();
	public static int mountain41 = new Color(100, 100, 23).getRGB();

	//next 42 grave
	public static int grave = new Color(21, 21, 21).getRGB();//bigs
	public static int grave1 = new Color(41, 41, 41).getRGB();
	public static int grave2 = new Color(81, 81, 81).getRGB();
	public static int grave3 = new Color(112, 112, 112).getRGB();
	public static int grave4 = new Color(114, 114, 114).getRGB();
	public static int grave5 = new Color(116, 116, 116).getRGB();//bigs
	public static int grave6 = new Color(118, 118, 118).getRGB();
	public static int grave7 = new Color(210, 210, 210).getRGB();
	public static int grave8 = new Color(212, 212, 212).getRGB();
	public static int grave9 = new Color(214, 214, 214).getRGB();
	public static int grave10 = new Color(126, 126, 126).getRGB();//bigs
	public static int grave11 = new Color(128, 128, 128).getRGB();
	public static int grave12 = new Color(130, 130, 130).getRGB();
	public static int grave13 = new Color(132, 132, 132).getRGB();
	public static int grave14 = new Color(134, 134, 134).getRGB();
	public static int grave15 = new Color(136, 136, 136).getRGB();//bigs
	public static int grave16 = new Color(138, 138, 138).getRGB();
	public static int grave17 = new Color(139, 139, 139).getRGB();
	public static int grave18 = new Color(142, 142, 142).getRGB();
	public static int grave19 = new Color(144, 144, 144).getRGB();
	public static int grave20 = new Color(146, 146, 146).getRGB();//bigs
	public static int grave21 = new Color(148, 148, 148).getRGB();
	public static int grave22 = new Color(160, 160, 160).getRGB();
	public static int grave23 = new Color(164, 164, 164).getRGB();
	public static int grave24 = new Color(166, 166, 166).getRGB();
	public static int grave25 = new Color(168, 168, 168).getRGB();//bigs
	public static int grave26 = new Color(170, 170, 170).getRGB();
	public static int grave27 = new Color(172, 172, 172).getRGB();
	public static int grave28 = new Color(174, 174, 174).getRGB();
	public static int grave29 = new Color(176, 176, 176).getRGB();
	public static int grave30 = new Color(178, 178, 178).getRGB();//bigs
	public static int grave31 = new Color(180, 180, 180).getRGB();
	public static int grave32 = new Color(182, 182, 182).getRGB();
	public static int grave33 = new Color(184, 184, 184).getRGB();
	public static int grave34 = new Color(186, 186, 186).getRGB();
	public static int grave35 = new Color(188, 188, 188).getRGB();//bigs
	public static int grave36 = new Color(190, 190, 190).getRGB();
	public static int grave37 = new Color(192, 192, 192).getRGB();
	public static int grave38 = new Color(194, 194, 194).getRGB();
	public static int grave39 = new Color(196, 196, 196).getRGB();
	public static int grave40 = new Color(198, 198, 198).getRGB();
	public static int grave41 = new Color(100,100 ,100).getRGB();


}

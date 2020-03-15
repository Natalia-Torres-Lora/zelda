package Game.Zelda.World;

import Game.GameStates.State;
import Game.GameStates.Zelda.ZeldaMapMakerState;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Main.Handler;
import Resources.Images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class MapBuilder {

	public static int pixelMultiplier = ZeldaMapMakerState.pixelsPerSquare;//change this for size of blocks
	public static int Dhole = new Color(0,0,0).getRGB();
	public static int Dwall = new Color(0, 225, 209).getRGB();
	public static int Dsand = new Color(0, 124, 132).getRGB();
	public static int Dwater = new Color(24, 60, 92).getRGB();
	public static int Dfloor = new Color(14, 180, 179).getRGB();
	public static int Dstairs = new Color(79, 117, 167).getRGB();
	public static int DRShooter = new Color(69, 167, 139).getRGB();
	public static int DLShooter = new Color(86, 167, 123).getRGB();

	public static Map createMap(BufferedImage mapImage, Handler handler){
		Map mapInCreation = new Map(handler);
		for (int i = 0; i < mapImage.getWidth(); i++) {
			for (int j = 0; j < mapImage.getHeight(); j++) {
				int currentPixel = mapImage.getRGB(i, j);
				int xPos = i*pixelMultiplier;
				int yPos = j*pixelMultiplier;
				if(currentPixel == Dhole){
					SolidStaticEntities ghost = new SolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(24),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DLShooter){
					SolidStaticEntities ghost = new SolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(23),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == DRShooter){
					SolidStaticEntities ghost = new SolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(22),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dstairs){
					SolidStaticEntities ghost = new SolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(27),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dfloor){
					SolidStaticEntities ghost = new SolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(20),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dwall){
					SolidStaticEntities ghost = new SolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(21),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dsand){
					SolidStaticEntities ghost = new SolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(25),handler);
					mapInCreation.addBlock(ghost);
				}else if(currentPixel == Dwater){
					SolidStaticEntities ghost = new SolidStaticEntities(xPos,yPos,Images.zeldaTiles.get(26),handler);
					mapInCreation.addBlock(ghost);
				}
			}

		}
		return mapInCreation;
	}

	public static BufferedImage
	arrayToRGBImage(ArrayList<ArrayList<BufferedImage>> info,String name){

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
		java.util.Map<BufferedImage, Integer> mapping = new HashMap<BufferedImage, Integer>();
		for (int y = 0; y < info.get(0).size(); y++) {
			for (int x = 0; x < info.size(); x++) {
				if (Images.zeldaTiles.get(20).equals(info.get(x).get(y))){
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
				}


			}

		}

		try
		{
			path = Objects.requireNonNull(MapBuilder.class.getClassLoader().getResource(".")).getPath();
			path2 = path.substring(0,path.indexOf("/out/"))+"/res/Edited/"+name+".png";
			f = new File(path2.replaceAll("%20"," "));
			System.out.println("File saved in: "+path2);
			ImageIO.write(image, "png", f);
		}
		catch(IOException e)
		{
			System.out.println("Error: " + e);
		}
		return image;

	}



}

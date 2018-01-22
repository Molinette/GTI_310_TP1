package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageBMP extends Image {

	
	BufferedImage BuffImg;
	
	public ImageBMP (File file) throws IOException{
		
		super._file = file;	
		BuffImg = ImageIO.read(file);
		
	}

	public BufferedImage draw() {
		return BuffImg;
	}

	public int getHeight() {
		
		return BuffImg.getHeight();
	}

	public int getWidth() {
		
	  return BuffImg.getWidth();
	}
}
	

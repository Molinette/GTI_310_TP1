package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.Image;
import model.ImageBMP;


public class ConcreteFactory implements ImageFactory {

	static ImageFactory instance;
	
	public static ImageFactory getInstance(){
		return instance;
	}
	
	public Image build(File file) {
			
		Image BmpImg = null;
		try {
			BmpImg = new ImageBMP(file).draw();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return BmpImg;

	}

}

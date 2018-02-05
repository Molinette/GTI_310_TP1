package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.Image;
import model.ImageBMP;
/**
 * 
 * @author Benjamin Brular
 *
 */

public class ConcreteFactory implements ImageFactory {

	static ImageFactory _instance;
	
	public static ImageFactory getInstance(){
		if(_instance == null)
			_instance = new ConcreteFactory();
		return _instance;
	}
	
	public Image build(File file) {
		ImageBMP bmpImg = null;
		try {
			bmpImg = new ImageBMP(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bmpImg;

	}

}

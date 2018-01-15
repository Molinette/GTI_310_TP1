package controller;

import java.io.File;

import model.Image;

public class ConcreteFactory implements ImageFactory {

	ImageFactory instance;
	@Override
	
	public ImageFactory getInstance(){
		return instance;
	}
	
	public Image build(File file) {
		// TODO Auto-generated method stub
		Image image = null;
		
		return image;
	}

}

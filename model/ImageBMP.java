package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

public class ImageBMP extends Image {

	
	FileInputStream input;
	DataInputStream dataReader;
		
	public ImageBMP (File file) throws FileNotFoundException {
		
		super._file = file;	
		input = new FileInputStream(file);
		
		
	}

	public BufferedImage draw() {
		byte[] dataT = new byte[4];
		
		
		try {
			input.read(dataT, 34, 4);
			int tailleData = ByteBuffer.wrap(dataT).getInt();
			byte[] data = new byte[tailleData];
			
			InputStream in = new ByteArrayInputStream(data);
			BufferedImage bImageFromConvert = ImageIO.read(in);

			ImageIO.write(bImageFromConvert, "jpg", new File(
					"c:/new-darksouls.jpg"));
			
			
			
			for(int i = 0; i<tailleData; i++){
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//return buffImg;
	}

	public int getHeight() {
		byte[] data = new byte[4];
		
		try {
			input.read(data, 22, 4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ByteBuffer.wrap(data).getInt();
	}

	public int getWidth() {
		byte[] data = new byte[4];
		
		try {
			input.read(data, 18, 4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  return ByteBuffer.wrap(data).getInt();
	}
}
	

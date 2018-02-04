package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
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
		
		
		 
		byte[] dataT = new byte[4];
		
		
	
		
		
		
		try {
			input.read(dataT, 34, 4);
			int tailleData = ByteBuffer.wrap(dataT).getInt();
			byte[] data = new byte[tailleData];
			
			InputStream in = new ByteArrayInputStream(data);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			int height = this.getHeight();
			int width = this.getWidth();
			
			//byte[][] dataPix = new byte[height][width];
			
			 for (int h = 1; h < height; h++) {
			      for (int w = 1; w < width; w++) {
			        int rgb = bImageFromConvert.getRGB(w, h);
			        int red = (rgb >> 16) & 0x000000FF;
			        int green = (rgb >> 8) & 0x000000FF;
			        int blue = (rgb) & 0x000000FF;
			        System.out.println("Pixel h"+h+" w"+w+" Value: "+red + green + blue);
			      }
			    }
			
			//ByteBuffer byteBuffer = ByteBuffer.allocate(tailleData);
            //input.copyPixelsToBuffer(byteBuffer);
            //byte[] byteArray = byteBuffer.array();
			
            
            
			
			//InputStream in = new ByteArrayInputStream(data);
			//bImageFromConvert = ImageIO.read(in);
		
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public BufferedImage draw() {
		
		BufferedImage bImageFromConvert=null;
		
		Bitmap.Config configBmp = Bitmap.Config.valueOf(bitmap.getConfig().name());
        Bitmap bitmap_tmp = Bitmap.createBitmap(width, height, configBmp);
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        bitmap_tmp.copyPixelsFromBuffer(buffer);
	
		return bImageFromConvert;
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
	

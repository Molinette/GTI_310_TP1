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

/**
 * Contains the data of the bmp image and creates a buffered image from it
 * 
 * @author Marc-Alexandre Monette Molina
 * @author Benjamin Brular
 *
 */
public class ImageBMP extends Image {

	private FileInputStream fileInputStream;
	private DataInputStream dataInputStream;
	private int _height = 100;
	private int _width = 100;
	private int[][] rgbPixelMatrix;
	
		
	public ImageBMP (File file) throws FileNotFoundException {
		
		super._file = file;	
		byte[] dataBuffer = new byte[4];
		
		try {
			fileInputStream = new FileInputStream(file);
			dataInputStream = new DataInputStream(fileInputStream);
			
			//Skip bytes to width
			dataInputStream.skipBytes(18);
			
			//Width
			dataInputStream.read(dataBuffer);
			_width = ByteArrayToInt((dataBuffer));
			
			//Height
			dataInputStream.read(dataBuffer);
			_height = ByteArrayToInt((dataBuffer));
			
			//Skip bytes to dataSize
			dataInputStream.skipBytes(28);
			
			//RGB Pixel matrix using bmp data
			rgbPixelMatrix = new int[_width][_height];
			
			dataBuffer = new byte[3];
			int padding = (_width * 3) % 4; //know how many bytes of zeros is the end of line padding
			for(int h = _height-1; h >= 0; h--) {
				for(int w = 0; w < _width; w++) {
					dataInputStream.read(dataBuffer);
					
					//Get the int representing the rgb values by adding each color components in the right order (BGR)
					int red = dataBuffer[2] << 16 & 0xFF0000;
					int green = dataBuffer[1] << 8 & 0x00FF00;
					int blue = dataBuffer[1] & 0xFF;
					int rgb = red + green + blue;
					rgbPixelMatrix[w][h] = rgb;
				}
				dataInputStream.skipBytes(padding); //skip the bmp padding
			}
			fileInputStream.close();
			dataInputStream.close();
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Creates a buffered image from the image rgb pixel matrix
	 *
	 * @return      the buffered image created from the rgb pixel matrix
	 */
	public BufferedImage draw() {
		BufferedImage bufferedImage = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
		for(int h = rgbPixelMatrix[0].length - 1; h >= 0; h--) {
			for(int w = 0; w < rgbPixelMatrix.length; w++) {
				bufferedImage.setRGB(w, h, rgbPixelMatrix[w][h]);
			}
			
		}
		
		return bufferedImage;
	}
	
	
	public int getHeight() {
		return _height;
	}

	public int getWidth() {		
		return _width;
	}
	
	/**
	 * Get an int from a byte array
	 *
	 * @param  byteArray  the byte array to convert
	 * @return      the integer equivalent to the byte array
	 */
	public int ByteArrayToInt(byte[] byteArray){
		int integer = 0;
		for(int i = 0; i < byteArray.length-1 ; i++){
			//adds each byte one after the other using bit shifting and addition ex. :(000000ff + 00005500 + 00ee0000 + 22000000)
			integer += (byteArray[i] & 0xff) << (8 * i); //since integers are signed in java, need to do & 0xff
		}
		return integer;
	}
}
	


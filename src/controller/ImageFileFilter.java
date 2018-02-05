package controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;

/**
 * Strict file filter for a JFileChooser that only accepts specific files.
 * 
 * @author François Caron <francois.caron.7@ens.etsmtl.ca>
 * @author Marc-Alexandre Monette Molina
 * 
 */
public class ImageFileFilter extends FileFilter {
	/* unique file extension allowed */
	private static final String EXTENSION = "bmp";
	
	
	public boolean accept(File arg0) {
		/* accept directories */
		if(arg0.isDirectory()) 
			return true;
		
		/* check file extensions */
		String name = arg0.getName();
		int pos = name.lastIndexOf(".") + 1;
		
		if(name.substring(pos).equals(EXTENSION)){
			try{
				
				FileInputStream fileInputStream = new FileInputStream(arg0);
				DataInputStream dataInputStream = new DataInputStream(fileInputStream);
				dataInputStream.skipBytes(28);
				byte[] dataBuffer = new byte[2];
				dataInputStream.read(dataBuffer);
				fileInputStream.close();
				dataInputStream.close();
				
				/* return TRUE if the file's extension matches the awaited extension and is 24 bpp */
				if(ByteArrayToInt(dataBuffer) == 24){
					return true;
				}
			}
			catch(IOException e){
				
			}
		}
		
		return false;
	}

	
	public String getDescription() {
		/* This String will be displayed in the file type combobox */
		return "24 BPP BMP (*.bmp)";
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
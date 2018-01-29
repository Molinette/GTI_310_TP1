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
 * @author ran�ois Caron <francois.caron.7@ens.etsmtl.ca>
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
				DataInputStream dataInputStream = new DataInputStream(new FileInputStream(arg0));
				dataInputStream.skipBytes(28);
				byte[] data = new byte[2];
				dataInputStream.read(data);
				dataInputStream.close();
				
				/* return TRUE if the file's extension matches the awaited extension and is 24 bpp */
				if(ByteArrayToInt(data) == 24){
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
	
	public int ByteArrayToInt(byte[] byteArray){
		int integer = 0;
		for(int i = 0; i < byteArray.length-1 ; i++){
			integer += (byteArray[i] & 0xff) << (8 * i); 
		}
		return integer;
	}

}

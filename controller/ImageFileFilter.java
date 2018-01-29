package controller;

import java.io.File;

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
		
		/* return TRUE if the file's extension matches the awaited extension */
		return name.substring(pos).equals(EXTENSION);
	}

	
	public String getDescription() {
		/* This String will be displayed in the file type combobox */
		return "BMP (*.bmp)";
	}

}

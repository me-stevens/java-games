/*
 * In charge of file input output operations
 */
package MadMax;

import java.io.File;

public class FileManager {
	
	public static void delete(String file) {
		File f = new File(file);

		if (f.isDirectory()) {			
			String[] files = f.list();
			
			if (files.length > 0)
				throw new IllegalArgumentException("ERROR: + " + file + " is a directory, and it is not empty.");
		}
		
		else if (f.exists() && f.canWrite()) {
			boolean success = f.delete();
			
			if (!success)
				throw new IllegalArgumentException("ERROR: I couldn't delete the file.");
		}
	}

}

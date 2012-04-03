import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class TextFile {

	private String buffer;		
	
	public TextFile( String filePath ) throws IOException {		
		
		// Parse a file into buf
		byte[] buf = new byte[(int) new File(filePath).length()];
	    BufferedInputStream f = null;
	    try {
	        f = new BufferedInputStream(new FileInputStream(filePath));
	        f.read(buf);
	    } finally {
	        if (f != null) try { f.close(); } catch (IOException ignored) { }
	    }
			
	    // Make a buffer with the read in buf
	    buffer = new String(buf);
	}
	
	public String getBuffer() {
		return buffer;		
	}
	
}

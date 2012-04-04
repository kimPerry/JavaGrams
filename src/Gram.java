
public class Gram {

	// Gram has a string and a count
	private String gram;
	private int count;
	
	// Constructor creates gram with passed in value, g
	public Gram( String g ) {
		gram = g;
		count = 1;
	}
	
	// Getter for gram 
	public String getGram() {
		return gram;
	}
	
	// Getter for count
	public int getCount() {
		return count;
	}
	
	// Increment count function
	public void addCount() { 
		count++;
	}
}

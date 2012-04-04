import java.util.ArrayList;


public class Utils {

	public static void generateGram( int index, String[] buffer, int n, ArrayList<Gram> g ) {
		
		// Temporary string to hold the current working n-gram
			String tempGram = new String();
			Boolean search = false;	
			
		// Loop until nConstant to create temporary n-gram
			for( int j = index; j < index + n; j++ ) {

				// Create the n-gram of size n
					tempGram += buffer[j] + " ";								
			}
			
		// Remove trailing white space from ngram
			tempGram = tempGram.replaceAll("\\s+$", "");
			
			if( index == 0 ) {
				// If bufIndex is 0, then it's the first n-gram so don't worry about checking for counts
					Gram gram = new Gram(tempGram);
					g.add( gram );					
			}

			else {
				Gram gram = new Gram(tempGram);
				// Check if the n-gram already exists and increment the count if it does
					for( int i = 0; i < g.size(); i++ ) {
						
						if( g.get(i).getGram().compareTo(tempGram) == 0 ) {
							g.get(i).addCount();
							search = true;
						}
					}

				// If the n-gram doesn't exist, make a new one and push onto list
					if( !search ) {
						g.add( gram );
					}

			}
		}
}

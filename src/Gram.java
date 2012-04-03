import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.*;

import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;


public class Gram {

	private String gram;
	private int count;
	
	public Gram() {
		
		setGram(new String());
		count = 1;

	}

	public String getGram() {
		return gram;
	}

	public void setGram(String gram) {
		this.gram = gram;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount() {
		count = 1;
	}
	
	public static void generateGram( int index ) {
		
		
	}
	
	public static void main(String[] args) throws MongoException, IOException {
	
	// Constants 
	int nConstant = Integer.parseInt(args[0]);	
	
	String filename = args[1];
	
	// Connection manager 
	Mongo m = new Mongo( "127.0.0.1" );		
	DB db = m.getDB( "test" );		
	DBCollection collection = db.getCollection("myCol");
	
	
	ArrayList<String> list = new ArrayList<String>();	
	list.add("stuff");
	list.add("more");
	list.add("here");
	
	// Create an object
		BasicDBObject doc = new BasicDBObject();
		BasicDBObject gram = new BasicDBObject();
		doc.put("filename", "in.txt" );
		
		
		ArrayList<BasicDBObject> grams = new ArrayList<BasicDBObject>();
		
		Gram tempGram = new Gram();

		tempGram.setGram("nitish");
		tempGram.setCount();
		
		gram.put( tempGram.getGram(), tempGram.getCount());
		grams.add(gram);		
		
		doc.put("counts", grams);
		
	// Insert collection into db
	collection.insert(doc);
	
	// Parse and split a file into arrays of strings
		TextFile f = new TextFile( "resources/in.txt" );			
		String line = new String(f.getBuffer() ); 		
		String[] splits = line.split("[.,?!;\n ]+");
	
		

	}


}

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
		
	}
	
	public static void generateGram( int index ) {
		
		
	}
	
	public static void main(String[] args) throws MongoException, IOException {
	
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
		doc.put("counts", list);
		
		
	collection.insert(doc);
	
	DBObject getDoc = collection.findOne();
	System.out.println(getDoc);
	
		
		
	
	// Parse and split a file into arrays of strings
		TextFile file = new TextFile( "resources/in.txt" );			
		String line = new String(file.getBuffer() ); 		
		String[] splits = line.split("[.,?!\n ]+");
	
		

		
		

	}


}

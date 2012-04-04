import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class Driver {
	
	// Buffer holds splits of the text file
	static String[] buffer;
		
	// nConstant is n number of grams
	static int nConstant;		
	
	// Magic happens in main function 
	public static void main(String[] args) throws MongoException, IOException {
	
	// Constants 
		int nConstant = Integer.parseInt(args[0]);				
		File n = new File(args[1]);
		String filename = new String( args[1] );		
	
	// JavaScript map and reduce functions
		String map = new String( "function map() { this.counts.forEach( function(z){ var c = z.count; emit( z.gram, { count : c } ); } ); };" );
		String reduce = new String ("function reduce( key, values ) {	var total = 0;	for( var i = 0; i < values.length; i++ ) { total += values[i].count;	} return {count : total }; };");
		
	// Parse and split a file into arrays of strings
		TextFile f = new TextFile( filename );			
		String line = new String(f.getBuffer() ); 		
		buffer = line.split("[.,?!;\n ]+");					
	
	// Create an array of Grams	
		ArrayList<Gram> grams = new ArrayList<Gram>();
		
	// Loop over array of words and make ngrams
		for( int i = 0; i < buffer.length - nConstant + 1; i++) {
			Utils.generateGram( i, buffer, nConstant, grams );	
		}	
	
	// Mongo connection manager
		Mongo m = new Mongo( "127.0.0.1" );		
		DB db = m.getDB( "test" );		
		DBCollection collection = db.getCollection("myCol");	
		
	// Create document which is inserted into collection
		BasicDBObject doc = new BasicDBObject();
		doc.put( "filename", n.getName() );
		
	// Create an array of document Grams
		ArrayList<BasicDBObject> gramDoc = new ArrayList<BasicDBObject>();
		
	// Loop over grams array list and create a document for each gram	
		for(int i = 0; i < grams.size(); i++ ) {
			
			// Make a temporary doc for one ngram
				BasicDBObject tempDoc = new BasicDBObject();
				tempDoc.put( "gram", grams.get(i).getGram() );
				tempDoc.put( "count", grams.get(i).getCount() );							
			
			// Add doc to a list of documents
				gramDoc.add( tempDoc );		
		}
		
	// Add array of grams to doc
		doc.put( "counts", gramDoc );
	
	// Insert collection into db
		collection.insert(doc);
		
		MapReduceOutput out = collection.mapReduce( map, reduce, null, MapReduceCommand.OutputType.INLINE, null );
				
		for ( DBObject obj : out.results() ) {
		    System.out.println( obj );
		}
		
		//collection.drop();
	}
}

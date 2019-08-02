package hello;

import java.util.*;
import org.bson.Document;
//import com.github.fakemongo.Fongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;



import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;







public class Model {
	
	MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/lab3"); 
    MongoClient client = new MongoClient();
    
	//Fongo fongo = new Fongo("Mongo");
	
	public void addDocuments(Document doc){
		MongoDatabase db = client.getDatabase(uri.getDatabase());
		//MongoDatabase db = fongo.getDatabase("inpe");
		MongoCollection<Document> researches = db.getCollection("researches");
    	researches.insertOne(doc);
	}
	
      
	public Document searchByResearchYearCity(String nome, String ano, String geocodigo) {
		MongoDatabase db = client.getDatabase(uri.getDatabase());
		//MongoDatabase db = fongo.getDatabase("inpe");
    	MongoCollection<Document> researches = db.getCollection("researches");
		   
		Document doc = researches.aggregate(Arrays.asList(unwind("$municipio"),
					   match(and(eq("nome", nome),eq("ano", ano),eq("municipio.geocodigo", geocodigo))),project(fields(include("municipio")))
					   )).first();
		return doc;
	}
	
	
   
    public List<Document> searchByResearchYearCities(String nome, String ano, String[] geocodigo) {
    	MongoDatabase db = client.getDatabase(uri.getDatabase());
	   //MongoDatabase db = fongo.getDatabase("app");
	   MongoCollection<Document> collection = db.getCollection("users");
	   List<Document> docs = new LinkedList<Document>();

	   for(String geo:geocodigo){
		   Document findOne = collection.aggregate(Arrays.asList(unwind("$municipio"),
				   match(and(eq("nome", nome),eq("ano", ano),eq("municipio.geocodigo", geo))),project(fields(include("municipio")))
				   )).first();
	   docs.add(findOne);
	   
	   }
	   return docs;
    }
   
}
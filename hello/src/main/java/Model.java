

import org.bson.Document;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Model {
	
	//start mongod - database
	//start mongo  - shell
	//https://docs.mongodb.com/manual/reference/mongo-shell/
	
	MongoClientURI uri  = new MongoClientURI("mongodb://localhost:27017/telegram"); 
    MongoClient client = new MongoClient();

    
    public void addUser(Document user){
    	
    	MongoDatabase db = client.getDatabase(uri.getDatabase());
    	MongoCollection<Document> users = db.getCollection("users");
    	users.insertOne(user); //insere no BD Telegram na Collection Users este Document (que eh o usuario)
    	
    	//no shell vc pode ver fazendo
    	//use telegram
    	//db.users.find() - busca todos os documentos da Collection Users do BD Telegram
    	
    }
    
    public FindIterable<Document> searchByName(String name){
    	MongoDatabase db = client.getDatabase(uri.getDatabase());
    	MongoCollection<Document> users = db.getCollection("users");
    	FindIterable<Document> found = users.find(new Document("name", name));
    	return found;
    }
    
}

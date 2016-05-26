

import org.bson.Document;

import com.mongodb.client.FindIterable;

public class Test {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		Document joao = new Document().append("name", "Joao").append("number", 12345);
		
		model.addUser(joao);
		
		FindIterable<Document> found = model.searchByName("Joao");
		
		for(Document doc:found){
			System.out.println("name: "+doc.getString("name"));
			System.out.println("number: "+doc.getInteger("number"));
		}
		
	}
	
}

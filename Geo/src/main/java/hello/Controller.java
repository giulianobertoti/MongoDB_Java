package hello;

import static spark.Spark.get;



import org.bson.Document;



public class Controller {
	
	private Model model;
	
	
	public Controller(Model model){
		this.model = model;
	}
	

	public void searchCity(){
		get("/:nome/:ano/:geocodigo",(req,res) -> {
			Document doc = model.searchByResearchYearCity(req.params(":nome"), req.params(":ano"), req.params(":geocodigo"));
			if(doc != null){
				return doc.toJson();
			} else {
				return "null";
			}
			
		});
	}
	
	

}

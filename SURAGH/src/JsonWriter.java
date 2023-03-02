import java.io.FileWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonWriter {

	public static void jsonWrite(List<List<Object>> list, String fileSchema, String resultIndicies, List<Integer> parsed_rows_indicies_LIST, List<Integer> possible_Outlier_Rows_indicies_LIST)
	{
		
		JSONArray jsonArray = new JSONArray();
        for (int i = 0;i < list.size() ; i++) {
         
            JSONObject objItem =  new JSONObject();
            	            
            objItem.put("Dominant Patterns",  list.get(i).get(0).toString().replace("<PD>", "")); 
            objItem.put("Complying Row Indices",  list.get(i).get(1));
            objItem.put("Size",  list.get(i).get(2));
           
            jsonArray.put(objItem);
        }

        try (FileWriter file = new FileWriter(fileSchema)) {    
            file.write(jsonArray.toString());
           
        } catch(Exception e){
            System.out.println(e);

        }
		
		
		
		
		
		JSONArray jsonArray_new = new JSONArray();
       
        JSONObject objItem_well =  new JSONObject();
        JSONObject objItem_ill =  new JSONObject();
        
        objItem_well.put("Well-Formed Records Indices",  parsed_rows_indicies_LIST);
          
        jsonArray_new.put(objItem_well);
      
        objItem_ill.put("Ill-Formed Records Indices",  possible_Outlier_Rows_indicies_LIST);
           
        jsonArray_new.put(objItem_ill);
        

        try (FileWriter file = new FileWriter(resultIndicies)) {    
            file.write(jsonArray_new.toString());
          
        } catch(Exception e){
            System.out.println(e);

        }
        
        System.out.println("Program successfully executed!");
	}
	
}

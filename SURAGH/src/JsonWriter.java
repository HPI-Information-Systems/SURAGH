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
            	            
            objItem.put("Dominant Patterns",  list.get(i).get(0));
            objItem.put("Complying Row Indices",  list.get(i).get(1));
            objItem.put("Size",  list.get(i).get(2));
           
            jsonArray.put(objItem);
        }

        try (FileWriter file = new FileWriter(fileSchema)) {    // add "true" for appending e.g., FileWriter file = new FileWriter(fileSchema, true)
            file.write(jsonArray.toString());
            //System.out.println("Successfully Copied JSON Object to File...");
            //System.out.println("\nJSON Object: " + jsonArray);
        } catch(Exception e){
            System.out.println(e);

        }
		
		
		
		
		
		JSONArray jsonArray_new = new JSONArray();
       
        JSONObject objItem_well =  new JSONObject();
        JSONObject objItem_ill =  new JSONObject();
        
        objItem_well.put("Well-Formed Records Indices",  parsed_rows_indicies_LIST);
        //objItem_well.put("Size",  parsed_rows_indicies_LIST.size());
          
        jsonArray_new.put(objItem_well);
      
        objItem_ill.put("Ill-Formed Records Indices",  possible_Outlier_Rows_indicies_LIST);
       // objItem_ill.put("Size",  possible_Outlier_Rows_indicies_LIST.size());
           
        jsonArray_new.put(objItem_ill);
        

        try (FileWriter file = new FileWriter(resultIndicies)) {    // add "true" for appending e.g., FileWriter file = new FileWriter(resultIndicies, true)
            file.write(jsonArray_new.toString());
            //System.out.println("Successfully Copied JSON Object to File...");
            //System.out.println("\nJSON Object: " + jsonArray);
        } catch(Exception e){
            System.out.println(e);

        }
        
        System.out.println("Program successfully executed!");
	}
	
}

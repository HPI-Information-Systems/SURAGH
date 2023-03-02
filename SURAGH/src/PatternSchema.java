import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;



public class PatternSchema {

    static Set<Integer> already_parsed_rows = new HashSet<Integer>();
	
	public List<List<Object>> schemaGeneration (int row_T, List<Integer> all_rows_list, List<List<Object>> optimal_pattern, String fileSchema, String resultIndicies)
	{
		
		int threshold = (int)((float)(row_T*all_rows_list.size()/100.0));  // Threshold to allow intersection 
		if(threshold == 0)
			threshold = 1;
			
			ArrayList<Object> temp = new ArrayList<Object>();
			if(!(optimal_pattern.isEmpty()))
			{
			 temp= (ArrayList<Object>) optimal_pattern.get(0);
			}
			
			try
			{
				for (int i = 1; i < optimal_pattern.size();i++)
		        {

		            temp = compare(temp, (ArrayList<Object>) optimal_pattern.get(i), threshold);   
		        }	
			}
	        catch(Exception ex)
			{
	        	System.out.println("Memory");
			}
			
			List<List<Object>> clean_PLI_results = new ArrayList<List<Object>>(); 
			for(int i = 0 ; i <temp.size();i++)
			{ 
				List<Object> obj = (List<Object>) temp.get(i);
				List sub_test = new ArrayList<>();
				sub_test.add(obj.get(1));
	
				Collections.sort((List<Integer>) obj.get(2));
				sub_test.add(obj.get(2));
				sub_test.add(((List<Integer>) obj.get(2)).size());
				clean_PLI_results.add(sub_test);
			}
			
			class ListComparatorPLI<T extends Comparable<T>> implements Comparator<List<T>> {

				  @Override
				  public int compare(List<T> o1, List<T> o2) {					
				      int c = o2.get(2).compareTo(o1.get(2));
				      if (c != 0 ) {
				        return c;
				    }
				    return Integer.compare(o1.size(), o2.size());
				  }

				}
			
			
			Collections.sort(clean_PLI_results, new ListComparatorPLI());
            
			for(int i = 0; i< clean_PLI_results.size(); i++)
			{
				if(!(clean_PLI_results.get(i).get(0).equals("DuplicatePattern")))
				 {
					List outer = (List) ((List)(clean_PLI_results.get(i))).get(1);
					for (int j = i+1; j < clean_PLI_results.size(); j++) 
					{
						List inner = (List) ((List)(clean_PLI_results.get(j))).get(1);
						if(j < clean_PLI_results.size() && outer.containsAll(inner) && !(clean_PLI_results.get(j).get(0).equals("DuplicatePattern")))
						{
							clean_PLI_results.get(j).set(0, "DuplicatePattern");
						}
					}	
				 }
				
			}
 			
			clean_PLI_results.removeIf(t ->t.contains("DuplicatePattern") );
			
			for(int i = 0; i<clean_PLI_results.size();i++)
			{
				already_parsed_rows.addAll((ArrayList)clean_PLI_results.get(i).get(1));
			}
			
			List<Integer> parsed_rows_indicies_LIST = new ArrayList<Integer>(already_parsed_rows);
			Collections.sort(parsed_rows_indicies_LIST);
			
			List<Integer> possible_Outlier_Rows_indicies_LIST = new ArrayList<Integer>();
			for (Integer item : all_rows_list) {
			    if (parsed_rows_indicies_LIST.contains(item)) {
			    } else {
			    	possible_Outlier_Rows_indicies_LIST.add(item);
			    }
			}
			
			// write results on disk as JSON
			//JsonWriter.jsonWrite(clean_PLI_results, fileSchema, resultIndicies, parsed_rows_indicies_LIST, possible_Outlier_Rows_indicies_LIST);
			
			// write results on disk as CSV
			Csv_Writer.csvWrite(clean_PLI_results, fileSchema, resultIndicies, parsed_rows_indicies_LIST, possible_Outlier_Rows_indicies_LIST);
			
		return clean_PLI_results;			
	}
	
	
	public static ArrayList<Object> compare(ArrayList<Object> list1, ArrayList<Object> list2, float threshold) {
	    ArrayList<Object> result = new ArrayList<Object>();
	    for (int i = 0; i < list1.size(); i++) {
	        for (int j = 0; j < list2.size(); j++) {
	            ArrayList<Object>
	                    item1 = (ArrayList<Object>) list1.get(i),
	                    item2 = (ArrayList<Object>) list2.get(j);
	            ArrayList<Integer>
	                    itemList1 = (ArrayList<Integer>) item1.get(item1.size() - 1),
	                    itemList2 = (ArrayList<Integer>) item2.get(item2.size() - 1);
	            ArrayList<Integer> intersection = intersection_Guava(itemList1, itemList2);
	            if (intersection.size() >= threshold) {
	                ArrayList<Object> temp = new ArrayList<Object>();
	                temp.add(item1.get(0) + "," + item2.get(0));             
	           temp.add(item1.get(1) + "" + item2.get(1));            

	                temp.add(intersection);
	                result.add(temp);
	            }
	        }
	    }
	    return result;
	}

	
	public static ArrayList<Integer> intersection_Guava(ArrayList<Integer> list1, ArrayList<Integer> list2 ){
		Set<Integer> result = Sets.intersection(Sets.newHashSet(list1), Sets.newHashSet(list2));
		ArrayList<Integer> result1 = new ArrayList<Integer>(result);    
	    return result1;
		
	}
	public static ArrayList<Integer> intersection(ArrayList<Integer> list1, ArrayList<Integer> list2 ){
	        Set<Integer> result = list1.stream()
	            		  .distinct()
	            		  .filter(list2::contains)
	            		  .collect(Collectors.toSet());
	            ArrayList<Integer> result1 = new ArrayList<Integer>(result);    
	    return result1;
	}
}

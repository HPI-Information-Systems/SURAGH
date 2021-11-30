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

		            temp = compare(temp, (ArrayList<Object>) optimal_pattern.get(i), threshold);   // incremental pattern generation 
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
			
			
			
			List<String> list_string = new ArrayList<String>();
			
			for(List in : clean_PLI_results)
			{
				StringBuilder strBld = new StringBuilder();
				String[] pattern_clean = in.get(0).toString().split("<DEL>");
				
				for(int i = 0; i<pattern_clean.length; i++)
				{
					int stringMatchCount = StringUtils.countMatches(pattern_clean[i], "<EV>"); 
				    if(i == pattern_clean.length-1 && pattern_clean[i].matches("^<EV>[\\r\\n]"))
				    {
					  
					   strBld.append(pattern_clean[i]);
				    }
				    else if(i == pattern_clean.length-1 && !(pattern_clean[i].matches("<EV>")) && stringMatchCount>1)
					{
						strBld.append(StringUtils.substringBefore(pattern_clean[i], "<EV>"));
						strBld.append("\n");
					}
				    else if(i == pattern_clean.length-1 && !(pattern_clean[i].matches("<EV>")))
					{
						strBld.append(StringUtils.substringBefore(pattern_clean[i], "<EV>"));
						
					}
					else
					{
						strBld.append(pattern_clean[i]);
					    strBld.append("<DEL>");
					}	
				}
				list_string.add(strBld.toString());
			}
			
			 
			for(int i = 0; i< list_string.size(); i++)
			{
				clean_PLI_results.get(i).set(0, list_string.get(i));
			}
			
			
			
			   List<List<Object>> sanitized_output = new ArrayList<List<Object>>();
			
			   List sub_list_ = new ArrayList<>();
			   sub_list_.add("#");
			   sub_list_.add("Dominant Patterns");
			   sub_list_.add("Complying Row Indices");
			   sub_list_.add("Size");
			   sanitized_output.add(sub_list_);
			   
			   int incremental= 0;
			   
			   for(int i = 0; i< clean_PLI_results.size(); i++)
			   	{
				   incremental++;
				   List l = new ArrayList<>();
				   l.add(incremental);
				   for(int j = 0; j< clean_PLI_results.get(i).size(); j++)
				   	{
					   
					   if(j==0)
					   {
						   String splitString[] = clean_PLI_results.get(i).get(j).toString().split("<DEL>");
						   StringBuilder combineString = new StringBuilder();
						   for(int k=0; k < splitString.length; k++)
						   {
							   
							   boolean flag_quotation = true;
							   boolean flag_delimiter = false;
							   
							   for (int m = 0; m < splitString[k].length(); m++)
							   {
								   if (splitString[k].charAt(m) == 34)
								   {
									   flag_quotation = !flag_quotation;
								   }
								   if (splitString[k].charAt(m) == 44)
								   {
									   flag_delimiter = true;
								   }
									   
								  if(flag_delimiter == true && flag_quotation == true)
								   {
									  splitString[k] = splitString[k].replace("\"", "\"\"");
									   break;
								   }
							   }
							   combineString.append(splitString[k]);
							   if(k != splitString.length-1)
							   {
								   combineString.append("<DEL>");
							   }
						   }
						   
						   
						     
						     String updated = combineString.toString();
						   
							 String step1 = StringUtils.join(updated, "\"");
							 String step2 = StringUtils.wrap(updated, "\"");
						     l.add(step2);  
						     
					   }
					   else if(j == 1)
					   {
							   String s = clean_PLI_results.get(i).get(j).toString().substring(1,clean_PLI_results.get(i).get(j).toString().length()-1);
							   
							    String step1 = StringUtils.join(s, "\"");
							    String step2 = StringUtils.wrap(s, "\"");
								 l.add(step2);  
						 
					   }
					   else
						   l.add(clean_PLI_results.get(i).get(j));
						
				   	}
				   sanitized_output.add(l);
			   	}
			   
			
			String write_PLI_output_results = fileSchema;
			try
				{
				FileWriter writer_to_buffer = new FileWriter(write_PLI_output_results, true);
				BufferedWriter writeHashMapToCsv_csvWriter = new BufferedWriter(writer_to_buffer);
				for(List indexx: sanitized_output)
			 	{
					for(int i = 0; i <indexx.size(); i++)
					{
						writeHashMapToCsv_csvWriter.write(indexx.get(i).toString());
						if(i!=indexx.size()-1)
						{
							writeHashMapToCsv_csvWriter.write(",");		
						}
						
					}
					writeHashMapToCsv_csvWriter.append('\n');
				}
				writeHashMapToCsv_csvWriter.flush();
				writeHashMapToCsv_csvWriter.close();
				}
						
			catch(Exception ex)
				{
				System.out.println(ex);
				}

					
			

			
			// start reading ground truth ..........................................................................................................
			
//			   CsvParserSettings annotation_settings = new CsvParserSettings();
//			   annotation_settings.detectFormatAutomatically();
//			   
//			   annotation_settings.setIgnoreLeadingWhitespaces(false);
//			   annotation_settings.setIgnoreTrailingWhitespaces(false);
//			   annotation_settings.setKeepQuotes(false);
//			   annotation_settings.setQuoteDetectionEnabled(true);
//			   annotation_settings.setSkipEmptyLines(false);
//			   annotation_settings.setCommentProcessingEnabled(false);
//			   annotation_settings.setMaxCharsPerColumn(1000000);
//			   annotation_settings.setDelimiterDetectionEnabled(true, ',');  
//			   
//			   CsvParser parser = new CsvParser(annotation_settings);
//			   
//			  
//			   List<Record> allRecords = parser.parseAllRecords(new File(groundTruth));
//			   
//			   List<Integer> illFormed_annoatated_data = new ArrayList<Integer>();
//			   List<Integer> wellFormed_annoatated_data = new ArrayList<Integer>();
//			   
//			   for(Record record : allRecords){
//				 
//				   String[] values = record.getValues();
//				   if(!(values.toString().isEmpty()) && values[0].toString().matches("ill formed rows indices"))
//					{
//					   String[] arr = values[1].split(",");
//					   for(String in : arr)
//					   {
//						   illFormed_annoatated_data.add(Integer.parseInt(in)); 
//					   }
//					   
//					}
//				   else if(!(values.toString().isEmpty()) && values[0].toString().matches("well formed rows indices"))
//					{
//					   String[] arr = values[1].split(",");
//					   for(String in : arr)
//					   {
//						   wellFormed_annoatated_data.add(Integer.parseInt(in)); 
//					   }
//					  
//					}
//				  
//			   }
			  
			for(int i = 0; i<clean_PLI_results.size();i++)
			{
				already_parsed_rows.addAll((ArrayList)clean_PLI_results.get(i).get(1));
			}
			
			List<Integer> parsed_rows_indicies_LIST = new ArrayList<Integer>(already_parsed_rows);
			Collections.sort(parsed_rows_indicies_LIST);
//			for (int i = 0; i < sort_set_list.size(); i ++) {
//			    int oldVal = sort_set_list.get(i);
//			    int newVal = oldVal + 1;
//			    sort_set_list.set(i, newVal);
//			}
			
			List<List<Object>> combined_Parsed_and_Outlier_Incdicies_LIST = new ArrayList<List<Object>>();
			
			List<Integer> possible_Outlier_Rows_indicies_LIST = new ArrayList<Integer>();
			for (Integer item : all_rows_list) {
			    if (parsed_rows_indicies_LIST.contains(item)) {
			       // duplicateList.add(item);
			    } else {
			    	possible_Outlier_Rows_indicies_LIST.add(item);
			    }
			}
			// confusion matrix
//			float true_Positive = 0;
//			float false_Negative = 0;
//			float false_Positive = 0;
//			float true_Negative = 0;
//			List<Integer> false_Negative_LIST = new ArrayList<Integer>();
//			List<Integer> false_Positive_LIST = new ArrayList<Integer>();
//			
//			
//			for(Integer in: illFormed_annoatated_data)
//			{
//				if(possible_Outlier_Rows_indicies_LIST.contains(in))
//				{
//					true_Positive++;
//				}
//				else if(parsed_rows_indicies_LIST.contains(in))
//				{
//					false_Negative++;
//					false_Negative_LIST.add(in);
//				}
//			}
//			
//			for(Integer in: wellFormed_annoatated_data)
//			{
//				if(possible_Outlier_Rows_indicies_LIST.contains(in))
//				{
//					false_Positive++;
//					false_Positive_LIST.add(in);
//				}
//				else if(parsed_rows_indicies_LIST.contains(in))
//				{
//					true_Negative++;
//				}
//			}
//			
//			float precision = 0;
//			float recall = 0;
//			float fMeasure = 0;
//			DecimalFormat df = new DecimalFormat("#.#####");
//			
//			precision = true_Positive/ (true_Positive + false_Positive);
//			recall = true_Positive / (true_Positive+ false_Negative);
//			fMeasure = 2*precision*recall/(precision+recall);
//			
//			if(Float.isNaN(precision))
//				precision=0;
//			if(Float.isNaN(recall))
//				recall=0;
//			if(Float.isNaN(fMeasure))
//				fMeasure=0;
//			
//			precision = Float.valueOf(df.format(precision));
//			recall = Float.valueOf(df.format(recall));
//			fMeasure = Float.valueOf(df.format(fMeasure));
			
//			
//			System.out.println("Precision     " + precision);
//			System.out.println("Recall     "+ recall);
//			System.out.println("F-1     "+ fMeasure);
//			
			// write parsed and possible outlier rows combined for analysis
			List parsed_rows_SUBLIST = new ArrayList<>();
			parsed_rows_SUBLIST.add("Well-Formed Records");
			parsed_rows_SUBLIST.add(parsed_rows_indicies_LIST);
			parsed_rows_SUBLIST.add(parsed_rows_indicies_LIST.size());
			combined_Parsed_and_Outlier_Incdicies_LIST.add(parsed_rows_SUBLIST);
			
			
			List outlier_rows_SUBLIST = new ArrayList<>();
			outlier_rows_SUBLIST.add("Ill-Formed Records");
			outlier_rows_SUBLIST.add(possible_Outlier_Rows_indicies_LIST);
			outlier_rows_SUBLIST.add(possible_Outlier_Rows_indicies_LIST.size());
			combined_Parsed_and_Outlier_Incdicies_LIST.add(outlier_rows_SUBLIST);
			
//			List false_negative_SUBLIST = new ArrayList<>();
//			false_negative_SUBLIST.add("False Negatives");
//			false_negative_SUBLIST.add(false_Negative_LIST);
//			false_negative_SUBLIST.add(false_Negative_LIST.size());
//			combined_Parsed_and_Outlier_Incdicies_LIST.add(false_negative_SUBLIST);
//			
//			
//			List false_positive_SUBLIST = new ArrayList<>();
//			false_positive_SUBLIST.add("False Positives");
//			false_positive_SUBLIST.add(false_Positive_LIST);
//			false_positive_SUBLIST.add(false_Positive_LIST.size());
//			combined_Parsed_and_Outlier_Incdicies_LIST.add(false_positive_SUBLIST);
//			
//			
//			List confiusion_matrix_result = new ArrayList<>();
//			confiusion_matrix_result.add("Precision   "+precision);
//			confiusion_matrix_result.add("Recall   "+recall);
//			confiusion_matrix_result.add("F Measure   "+fMeasure);
//			combined_Parsed_and_Outlier_Incdicies_LIST.add(confiusion_matrix_result);
			
			
			 List<List<Object>> sanitized__indices_output = new ArrayList<List<Object>>();
			
			 List sub_list_indicies_ = new ArrayList<>();
			 sub_list_indicies_.add("Output Type");
			 sub_list_indicies_.add("Row Indices");
			 sub_list_indicies_.add("Size");
			 sanitized__indices_output.add(sub_list_indicies_);
			   
			   
			   for(int i = 0; i< combined_Parsed_and_Outlier_Incdicies_LIST.size(); i++)
			   	{
				   
				   List l = new ArrayList<>();
				   for(int j = 0; j< combined_Parsed_and_Outlier_Incdicies_LIST.get(i).size(); j++)
				   	{
					  
					   if(j==0)
					   {
						     l.add(combined_Parsed_and_Outlier_Incdicies_LIST.get(i).get(j));  
						     
					   }
					   else if(j == 1)
					   {
						   
							   String s = combined_Parsed_and_Outlier_Incdicies_LIST.get(i).get(j).toString().substring(1,combined_Parsed_and_Outlier_Incdicies_LIST.get(i).get(j).toString().length()-1);
							   
							    String step1 = StringUtils.join(s, "\"");
							    String step2 = StringUtils.wrap(s, "\"");
								l.add(step2);  
						   
							   
						 
					   }
					   else
						   l.add(combined_Parsed_and_Outlier_Incdicies_LIST.get(i).get(j));
						
				   	}
				   sanitized__indices_output.add(l);
			   	}
			   
			   
			   
			   
           String write_all_parsed_AND_outlier = resultIndicies;
			
			try
			{
			FileWriter writer_to_buffer = new FileWriter(write_all_parsed_AND_outlier, true);
		 	BufferedWriter writeHashMapToCsv_csvWriter = new BufferedWriter(writer_to_buffer);
		 	for(List indexx: sanitized__indices_output)
		 	{
				for(int i = 0; i <indexx.size(); i++)
				{
					writeHashMapToCsv_csvWriter.write(indexx.get(i).toString());
					if(i!=indexx.size()-1)
					{
						writeHashMapToCsv_csvWriter.write(",");		
					}
					
				}
				writeHashMapToCsv_csvWriter.append('\n');
			}
			writeHashMapToCsv_csvWriter.flush();
			writeHashMapToCsv_csvWriter.close();
			}
			
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			
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
	            ArrayList<Integer> intersection = intersection(itemList1, itemList2);
	            if (intersection.size() > threshold) {
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

	public static ArrayList<Integer> intersection(ArrayList<Integer> list1, ArrayList<Integer> list2 ){
	        Set<Integer> result = list1.stream()
	            		  .distinct()
	            		  .filter(list2::contains)
	            		  .collect(Collectors.toSet());
	            ArrayList<Integer> result1 = new ArrayList<Integer>(result);    
	    return result1;
	}
}

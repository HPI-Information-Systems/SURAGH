import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import javax.accessibility.AccessibleBundle;

import abstractions.Arithmetic_Oprt_Class;
import abstractions.Brackets_Class;
import abstractions.Candidate_Delimiter_Class;
import abstractions.Date_Class;
import abstractions.Delimiter_Class;
import abstractions.Digit_Class;
import abstractions.EmptyValues_Class;
import abstractions.Full_Text_Class;
import abstractions.Line_Break_Class;
import abstractions.Lower_Letter_Class;
import abstractions.MissingValues_Class;
import abstractions.NotASCII_Class;
import abstractions.Number_Class;
import abstractions.Quotation_Class;
import abstractions.Sequence_Digit_Class;
import abstractions.Sequence_LowerLetter_Class;
import abstractions.Sequence_UpperLetter_Class;
import abstractions.Space_Class;
import abstractions.Symbol_Class;
import abstractions.Text_Class;
import abstractions.Upper_Letter_Class;
import abstractions.WhiteSpace_Class;

public class PruningPatterns {
	
	private static final Line_Break_Class LINE_BREAK_CLASS = new Line_Break_Class();
	private static final Delimiter_Class DELIMITER_CLASS = new Delimiter_Class();
	private static final Full_Text_Class FULL_TEXT_CLASS = new Full_Text_Class();
	private static final Text_Class TEXT_CLASS = new Text_Class();
	private static final Number_Class NUMBER_CLASS = new Number_Class();
	private static final Symbol_Class SYMBOL_CLASS = new Symbol_Class();
	private static final Brackets_Class BRACKETS_CLASS = new Brackets_Class();
	private static final Quotation_Class QUOTATION_CLASS = new Quotation_Class();
	private static final WhiteSpace_Class WHITE_SPACE_CLASS = new WhiteSpace_Class();
	private static final Space_Class SPACE_CLASS = new Space_Class();
	private static final Sequence_LowerLetter_Class SEQUENCE_LOWER_LETTER_CLASS = new Sequence_LowerLetter_Class();
	private static final Lower_Letter_Class LOWER_LETTER_CLASS = new Lower_Letter_Class();
	private static final Sequence_UpperLetter_Class SEQUENCE_UPPER_LETTER_CLASS = new Sequence_UpperLetter_Class();
	private static final Upper_Letter_Class UPPER_LETTER_CLASS = new Upper_Letter_Class();
	private static final Sequence_Digit_Class SEQUENCE_DIGIT_CLASS = new Sequence_Digit_Class();
	private static final Date_Class DATE_CLASS = new Date_Class();
	private static final Arithmetic_Oprt_Class ARITHMETIC_OPRT_CLASS = new Arithmetic_Oprt_Class();
	private static final Digit_Class DIGIT_CLASS = new Digit_Class();
	private static final MissingValues_Class MISSING_VALUES_CLASS = new MissingValues_Class();
	private static final EmptyValues_Class EMPTY_VALUES_CLASS = new EmptyValues_Class();
	
	public List<List<Object>> patternWeights_patternPruning( Map<Integer, List<List<String>>> map_Combined_listoflist_results, int col_T, int row_T, float rowCount)
	{
		
		   Map<Map<Integer,String>, Integer> counting_map = new LinkedHashMap<>();
	       Map<Map<Integer,String>,List<Integer>> map_PLI_Input = new LinkedHashMap<>();	
			
			for(int outer = 0; outer < map_Combined_listoflist_results.size(); outer++)
			{
				for(int inner = 0; inner < map_Combined_listoflist_results.get(outer).size(); inner++)
				{
					
					for(int innerMost = 0; innerMost< map_Combined_listoflist_results.get(outer).get(inner).size() ; innerMost++)
					{
						
						if(counting_map.containsKey(Stream.of(new Object[][] { 
					        { outer, map_Combined_listoflist_results.get(outer).get(inner).get(innerMost) }  //check if map contains key or not where key is Map<Integer,String>
					        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]))))
						{

						   counting_map.put(Stream.of(new Object[][] { 
						        { outer, map_Combined_listoflist_results.get(outer).get(inner).get(innerMost) } 
						    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1])), 
								   (int)counting_map.get(Stream.of(new Object[][] { 
								        { outer, map_Combined_listoflist_results.get(outer).get(inner).get(innerMost) }  
								    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1])) )+1 );
						   
						}
						else
						{
							
							counting_map.put(Stream.of(new Object[][] { 
						        { outer, map_Combined_listoflist_results.get(outer).get(inner).get(innerMost) } 
						    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1])), 1);
						}
						
						
						
						if(map_PLI_Input.containsKey(Stream.of(new Object[][] { 
					        { outer, map_Combined_listoflist_results.get(outer).get(inner).get(innerMost) }  
					        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]))))
						{
	                      
	                    	   		   
							map_PLI_Input.get(Stream.of(new Object[][] { 
						        { outer, map_Combined_listoflist_results.get(outer).get(inner).get(innerMost) } 
						    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]))).add(inner+1);
						   
						}
						else
						{
							
							map_PLI_Input.computeIfAbsent(Stream.of(new Object[][] { 
						        { outer, map_Combined_listoflist_results.get(outer).get(inner).get(innerMost) } 
						    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1])), k -> new ArrayList()).add(inner+1);
							
						}
						
					}	
				} 
			}
			
		
			List<List<Object>> map_values_with_weights = new ArrayList<List<Object>>();
			for(Entry entry: counting_map.entrySet())
			{
				int count_weight = 0;
				int counter = 0;
				float weight_average = 0;
	            int stringMatchCount = 0;
	            int stringLength = 0;
	            List<String> list_abstraction_classes = new ArrayList<String>();
	            // to get the size of key inside entry.getKey() where entry.getKey() is comprised of Map<Integer, String>
	            Map<Integer, String> map_test = (Map<Integer, String>) entry.getKey();
				for(Entry inner_entry : map_test.entrySet())
				{													
					stringLength =inner_entry.getValue().toString().length();		
				}
	            
				if(entry.getKey().toString().contains(DIGIT_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), DIGIT_CLASS.toString()); 
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(DIGIT_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(DIGIT_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(ARITHMETIC_OPRT_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), ARITHMETIC_OPRT_CLASS.toString()); 
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(ARITHMETIC_OPRT_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(ARITHMETIC_OPRT_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(DATE_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), DATE_CLASS.toString()); 
					count_weight+=3*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(DATE_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(DATE_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(SEQUENCE_DIGIT_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), SEQUENCE_DIGIT_CLASS.toString()); 
					count_weight+=3*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(SEQUENCE_DIGIT_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(SEQUENCE_DIGIT_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(UPPER_LETTER_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), UPPER_LETTER_CLASS.toString()); 
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(UPPER_LETTER_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(UPPER_LETTER_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), SEQUENCE_UPPER_LETTER_CLASS.toString()); 
					count_weight+=3*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(SEQUENCE_UPPER_LETTER_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(SEQUENCE_UPPER_LETTER_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(LOWER_LETTER_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), LOWER_LETTER_CLASS.toString()); 
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(LOWER_LETTER_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(LOWER_LETTER_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), SEQUENCE_LOWER_LETTER_CLASS.toString()); 
					count_weight+=3*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(SEQUENCE_LOWER_LETTER_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(SEQUENCE_LOWER_LETTER_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(SPACE_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), SPACE_CLASS.toString()); 
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(SPACE_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(SPACE_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(WHITE_SPACE_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), WHITE_SPACE_CLASS.toString()); 
					count_weight+=3*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(WHITE_SPACE_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(WHITE_SPACE_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(QUOTATION_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(),QUOTATION_CLASS.toString()); 
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(QUOTATION_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(QUOTATION_CLASS.toString(), ",",stringMatchCount));
					
				}
			
				if(entry.getKey().toString().contains(BRACKETS_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), BRACKETS_CLASS.toString()); 
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(BRACKETS_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(BRACKETS_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(SYMBOL_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), SYMBOL_CLASS.toString()); 
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(SYMBOL_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(SYMBOL_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(NUMBER_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), NUMBER_CLASS.toString());  
					count_weight+=2*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(NUMBER_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(NUMBER_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(TEXT_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), TEXT_CLASS.toString()); 
					count_weight+=2*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(TEXT_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(TEXT_CLASS.toString(), ",",stringMatchCount));
					
				}
				if(entry.getKey().toString().contains(FULL_TEXT_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), FULL_TEXT_CLASS.toString());
					count_weight+=1*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(FULL_TEXT_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(FULL_TEXT_CLASS.toString(), ",",stringMatchCount));
					
				}
				
				if(entry.getKey().toString().contains(DELIMITER_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), DELIMITER_CLASS.toString());
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(DELIMITER_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(DELIMITER_CLASS.toString(), ",",stringMatchCount));
				}
				if(entry.getKey().toString().contains(EMPTY_VALUES_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), EMPTY_VALUES_CLASS.toString());
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(EMPTY_VALUES_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(EMPTY_VALUES_CLASS.toString(), ",",stringMatchCount));
				}
				if(entry.getKey().toString().contains(MISSING_VALUES_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), MISSING_VALUES_CLASS.toString());
					count_weight+=2*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(MISSING_VALUES_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(MISSING_VALUES_CLASS.toString(), ",",stringMatchCount));
				}
				if(entry.getKey().toString().contains(LINE_BREAK_CLASS.toString()))
				{
					stringMatchCount = StringUtils.countMatches(entry.getKey().toString(), LINE_BREAK_CLASS.toString());
					count_weight+=4*stringMatchCount;
					counter = counter+ stringMatchCount;
					stringLength = stringLength - StringUtils.length(LINE_BREAK_CLASS.toString())*stringMatchCount;
					list_abstraction_classes.add(StringUtils.repeat(LINE_BREAK_CLASS.toString(), ",",stringMatchCount));
				}
				
				if(stringLength != 0)
				{
					count_weight+=5*stringLength;
					counter = counter+ stringLength;
				}

				
				try {
					weight_average = (float)count_weight/(float)counter; 
					if(Float.isNaN(weight_average))
						weight_average=0;
					DecimalFormat df = new DecimalFormat("#.##");
					weight_average = Float.valueOf(df.format(weight_average));
				}
				catch(Exception ex)
				{
					
				}
				
				List subList = new ArrayList<Object>();
				Map<Integer, String> map = (Map<Integer, String>) entry.getKey();
				for(Entry inner_entry : map.entrySet())
				{													
					subList.add(inner_entry.getKey());
					subList.add(inner_entry.getValue());	
				}
				subList.add(entry.getValue());
				subList.add((int)entry.getValue()*weight_average);
				subList.add(list_abstraction_classes);
				
			    float pattern_average = 0;
			    DecimalFormat df1 = new DecimalFormat("#.##");
			    pattern_average = Float.valueOf(df1.format((int)entry.getValue()*100/rowCount));    
			    subList.add(pattern_average);
			    
			    
			    if(map_PLI_Input.containsKey(entry.getKey()))
			    	subList.add(map_PLI_Input.get(entry.getKey()));
				map_values_with_weights.add(subList);  
			}
			
			
			class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {

				  @Override
				  public int compare(List<T> o1, List<T> o2) {					
				      int c = o2.get(3).compareTo(o1.get(3));
				      if (c != 0 && o1.get(0)==o2.get(0)) {
				        return c;
				    }
				    return Integer.compare(o1.size(), o2.size());
				  }

				}
			try
			{
				Collections.sort(map_values_with_weights, new ListComparator());
			}
			catch(Exception ex)
			{
				
			}
		
			
			
			for(int i = 0; i< map_values_with_weights.size(); i++)
		   	{
				if(Float.parseFloat(map_values_with_weights.get(i).get(5).toString()) <= col_T)
				 {
					map_values_with_weights.get(i).set(3, 100000000);
				 }
		   	}
			
			
			map_values_with_weights.removeIf(t ->t.contains(100000000) );
			
			
			Set<Integer> count_prunning_elements = new HashSet<Integer>();
			for(int i = 0; i< map_values_with_weights.size(); i++)
		   	{
				int inner = i+1;
					while(inner < map_values_with_weights.size() && map_values_with_weights.get(i).get(0) == map_values_with_weights.get(inner).get(0))
					  {	
						if( Float.parseFloat(map_values_with_weights.get(i).get(2).toString()) == Float.parseFloat(map_values_with_weights.get(inner).get(2).toString()) 
								&& Float.parseFloat(map_values_with_weights.get(i).get(3).toString() )!= 100000000 )
						{
							count_prunning_elements.add(i); 
							if( Float.parseFloat(map_values_with_weights.get(i).get(3).toString()) > Float.parseFloat(map_values_with_weights.get(inner).get(3).toString())  )
							{
								List outerList = (List) map_values_with_weights.get(i).get(4);
								List innerList = (List)map_values_with_weights.get(inner).get(4);
								
								if( outerList.isEmpty())
								{
									StringBuilder list_to_String = new StringBuilder();

									for (String s : (List<String>)map_values_with_weights.get(inner).get(4))
									{
										list_to_String.append(s);
									}
									StringBuilder stringDifference = new StringBuilder();
									
									for(int k = 0; k<map_values_with_weights.get(inner).get(1).toString().length(); k++)
									{
										
										char ch = map_values_with_weights.get(inner).get(1).toString().charAt(k) ;
										if(list_to_String.toString().indexOf(ch) == -1)
										{
								        	stringDifference.append(ch);
										}
									}
																			
									if( !(map_values_with_weights.get(inner).get(1).toString().contains(LINE_BREAK_CLASS.toString()) )  
											&& ifcontainsAll(map_values_with_weights.get(i).get(1).toString(), stringDifference.toString()) )
									{
										map_values_with_weights.get(inner).set(3, 100000000);	
									}
									
								}
								
								else if( innerList.containsAll(outerList)
										|| ( map_values_with_weights.get(inner).get(4).toString().contains(SEQUENCE_DIGIT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(DIGIT_CLASS.toString()) ) 
										||  ( map_values_with_weights.get(inner).get(4).toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(UPPER_LETTER_CLASS.toString()) )
										|| ( map_values_with_weights.get(inner).get(4).toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(LOWER_LETTER_CLASS.toString()) )
										||  ( map_values_with_weights.get(inner).get(4).toString().contains(WHITE_SPACE_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(SPACE_CLASS.toString() ))
										||  ( map_values_with_weights.get(inner).get(4).toString().contains(TEXT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(UPPER_LETTER_CLASS.toString())  )
									    ||  ( map_values_with_weights.get(inner).get(4).toString().contains(TEXT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(LOWER_LETTER_CLASS.toString()) )
										||  ( map_values_with_weights.get(inner).get(4).toString().contains(TEXT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()) )
										||  ( map_values_with_weights.get(inner).get(4).toString().contains(TEXT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString()) )
										)
								{
									
								   map_values_with_weights.get(inner).set(3, 100000000);	
								}
			                    
								else   
								
								{
									if(( map_values_with_weights.get(i).get(4).toString().contains(SEQUENCE_DIGIT_CLASS.toString()) &&  map_values_with_weights.get(inner).get(4).toString().contains(DIGIT_CLASS.toString()) ) 
										 ||  ( map_values_with_weights.get(i).get(4).toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()) &&  map_values_with_weights.get(inner).get(4).toString().contains(UPPER_LETTER_CLASS.toString()) )
										 || ( map_values_with_weights.get(i).get(4).toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString()) &&  map_values_with_weights.get(inner).get(4).toString().contains(LOWER_LETTER_CLASS.toString()) )
										 ||  ( map_values_with_weights.get(i).get(4).toString().contains(WHITE_SPACE_CLASS.toString()) &&  map_values_with_weights.get(inner).get(4).toString().contains(SPACE_CLASS.toString() )))
										
									{
										 map_values_with_weights.get(i).set(3, 100000000);	
										
									}
								}
							}
							else
							{
								if(( map_values_with_weights.get(inner).get(4).toString().contains(SEQUENCE_DIGIT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(DIGIT_CLASS.toString()) ) 
										 ||  ( map_values_with_weights.get(inner).get(4).toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(UPPER_LETTER_CLASS.toString()) )
										 ||  ( map_values_with_weights.get(inner).get(4).toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(LOWER_LETTER_CLASS.toString()) )
										 ||  ( map_values_with_weights.get(inner).get(4).toString().contains(WHITE_SPACE_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(SPACE_CLASS.toString()) )
									     ||  ( map_values_with_weights.get(inner).get(4).toString().contains(TEXT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(UPPER_LETTER_CLASS.toString())  )
									     ||  ( map_values_with_weights.get(inner).get(4).toString().contains(TEXT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(LOWER_LETTER_CLASS.toString()) )
									     ||  ( map_values_with_weights.get(inner).get(4).toString().contains(TEXT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()) )
									     ||  ( map_values_with_weights.get(inner).get(4).toString().contains(TEXT_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(SEQUENCE_LOWER_LETTER_CLASS.toString()) )
									     ||  ( map_values_with_weights.get(inner).get(4).toString().contains(NUMBER_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(DIGIT_CLASS.toString()) )   // added on 18 January 2021 --please double check these lines
									     ||  ( map_values_with_weights.get(inner).get(4).toString().contains(NUMBER_CLASS.toString()) &&  map_values_with_weights.get(i).get(4).toString().contains(SEQUENCE_DIGIT_CLASS.toString()) )

									     )
									{
										 map_values_with_weights.get(inner).set(3, 100000000);	
										
									}
							}
					    }
					inner++;
				}
					if(i+1 < map_values_with_weights.size() && map_values_with_weights.get(i).get(0) != map_values_with_weights.get(i+1).get(0)
							&& !(map_values_with_weights.get(i+1).get(1).equals(DELIMITER_CLASS.toString())))
					{
					count_prunning_elements.clear();		
					}		
		   	}
					
			map_values_with_weights.removeIf(t ->t.contains(100000000) );
							
					
			
			map_values_with_weights.removeIf(t ->t.contains(100000000) );
		   	
			
			
			
			//to pick top 5 records from weighted list
			List<List<Object>> maximum_weighted_List = new ArrayList<List<Object>>(); 
			boolean flag_for_weighted_list = true ;
			boolean flag_foFullText = false ;
			for(int i = 0; i< map_values_with_weights.size(); i++)
		   	{
				
				if(flag_for_weighted_list)
				{
				 for(int j = i; j< i+5; j++)
				  {	
					 if(j+1 < map_values_with_weights.size() && map_values_with_weights.get(j).get(0) == map_values_with_weights.get(j+1).get(0) )
					    {
						 
							 maximum_weighted_List.add(map_values_with_weights.get(j));		
						}
					 else
					    {
						 maximum_weighted_List.add(map_values_with_weights.get(j));
						 break;															// to add delimiter record
					    }
				  }	
			   }
				
			 flag_for_weighted_list = false;
			 if(i+1 < map_values_with_weights.size() && map_values_with_weights.get(i).get(0) != map_values_with_weights.get(i+1).get(0))
				{
				flag_for_weighted_list = true;
				}
			 
		   	}
			
			maximum_weighted_List.clear();
			maximum_weighted_List.addAll(map_values_with_weights);			
			
			
			
			
				boolean flag_for_weighted_list_maximum = true ;
				List<List<Object>> optimal_pattern = new ArrayList<List<Object>>();
			
				for(int i = 0; i< maximum_weighted_List.size(); i++)
					 {
					 int index = (int) maximum_weighted_List.get(i).get(0);
						if(flag_for_weighted_list_maximum)
						{
							float checkPatternAverage = Float.parseFloat(maximum_weighted_List.get(i).get(5).toString());
							if(checkPatternAverage >=95)  	
							{
						      if(i+1 < maximum_weighted_List.size() && maximum_weighted_List.get(i).get(0) == maximum_weighted_List.get(i+1).get(0) )
								 {  
						    	    
						    	     ArrayList<Object> optimal_patter_subList;
							            if(optimal_pattern.size() > index)
							            	optimal_patter_subList = (ArrayList<Object>) optimal_pattern.get(index);
							            else
							            	optimal_patter_subList = new ArrayList<Object>();
							            ArrayList<Object>  subList= new ArrayList<Object>();
							            subList.add(index);
							            subList.add(maximum_weighted_List.get(i).get(1));
							            subList.add(maximum_weighted_List.get(i).get(6));
							            optimal_patter_subList.add(subList);
							            if(optimal_pattern.size() > index)
							            	optimal_pattern.set(index,optimal_patter_subList);
							            else
							            	optimal_pattern.add(optimal_patter_subList);
							            
						    	     
//						    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(0));
//						    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(1));    
//						    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(6));
//							    	 optimal_pattern.add(optimal_patter_subList); 
							                                                             
								 }
							   else
								  { 
								   
								   ArrayList<Object> optimal_patter_subList;
						            if(optimal_pattern.size() > index)
						            	optimal_patter_subList = (ArrayList<Object>) optimal_pattern.get(index);
						            else
						            	optimal_patter_subList = new ArrayList<Object>();
						            ArrayList<Object>  subList= new ArrayList<Object>();
						            subList.add(index);
						            subList.add(maximum_weighted_List.get(i).get(1));
						            subList.add(maximum_weighted_List.get(i).get(6));
						            optimal_patter_subList.add(subList);
						            if(optimal_pattern.size() > index)
						            	optimal_pattern.set(index,optimal_patter_subList);
						            else
						            	optimal_pattern.add(optimal_patter_subList);
						            
								   
								   
//								     List optimal_patter_subList = new ArrayList<>() ;
//								     optimal_patter_subList.add(maximum_weighted_List.get(i).get(0));
//						    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(1));
//						    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(6));
//							    	 optimal_pattern.add(optimal_patter_subList); 	                                             // to add delimiter record 
								  }
							}
							else
							{
								ArrayList<Object> optimal_patter_subList = new ArrayList<Object>();
								for(int j = i; j< maximum_weighted_List.size(); j++)
								  {	
									 if(j+1 < maximum_weighted_List.size() && maximum_weighted_List.get(j).get(0) == maximum_weighted_List.get(j+1).get(0) )
									    {
									            if(optimal_pattern.size() > index)
									            	optimal_patter_subList = (ArrayList<Object>) optimal_pattern.get(index);
									            else
									            	optimal_patter_subList = new ArrayList<Object>();
									            ArrayList<Object>  subList= new ArrayList<Object>();
									            subList.add(index);
									            subList.add(maximum_weighted_List.get(j).get(1));
									            subList.add(maximum_weighted_List.get(j).get(6));
									            optimal_patter_subList.add(subList);
											  if(optimal_pattern.size() > index)
								            	optimal_pattern.set(index,optimal_patter_subList);
									            else
									            	optimal_pattern.add(optimal_patter_subList);
//											 optimal_patter_subList.add(maximum_weighted_List.get(i).get(0));
//											 optimal_patter_subList.add(maximum_weighted_List.get(j).get(1));
//											 optimal_patter_subList.add(maximum_weighted_List.get(i).get(6));     
										
										}
									 
									 else
									    {
										 if(optimal_pattern.size() > index)
								            	optimal_patter_subList = (ArrayList<Object>) optimal_pattern.get(index);
								            else
								            	optimal_patter_subList = new ArrayList<Object>();
								            ArrayList<Object>  subList= new ArrayList<Object>();
								            subList.add(index);
								            subList.add(maximum_weighted_List.get(j).get(1));
								            subList.add(maximum_weighted_List.get(j).get(6));
								            optimal_patter_subList.add(subList);
								            if(optimal_pattern.size() > index)
								            	optimal_pattern.set(index,optimal_patter_subList);
								            else
								            	optimal_pattern.add(optimal_patter_subList);
										 
//										 optimal_patter_subList.add(maximum_weighted_List.get(i).get(0));
//							    	     optimal_patter_subList.add(maximum_weighted_List.get(j).get(1));
//							    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(6));
										 break;															                  // to add delimiter record
									    }
								  }	
								
						            
								//optimal_pattern.add(optimal_patter_subList); 
							}
						}	
							
						flag_for_weighted_list_maximum = false;
						if(i+1 < maximum_weighted_List.size() && maximum_weighted_List.get(i).get(0) != maximum_weighted_List.get(i+1).get(0))
						 {
							flag_for_weighted_list_maximum = true;
						 }
						 
					   }
				
			return optimal_pattern;	
	}
	
	public static boolean ifcontainsAll(String parent, String child)
	{
		for(int i = 0; i<child.length(); i++)
		{
			char ch = child.charAt(i);
			if(parent.indexOf(ch) == -1)
			{
				return false;
			}
		}
		
		return true;
	}

	
}

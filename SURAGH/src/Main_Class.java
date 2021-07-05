import java.awt.Stroke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;



public class Main_Class {
	
static int row_T =1;
static int col_T = 20;
	
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

static Map<String, List<Object>> final_result_map_with_time = new LinkedHashMap<String, List<Object>>();

private static final Padded_Class PADDED_CLASS = new Padded_Class();
static int listSize = 0;
static List<List<Object>> final_pattern_list = new ArrayList<List<Object>>();
static Set<Integer> already_parsed_rows = new HashSet<Integer>();
static List<Integer> all_rows_list = new ArrayList<Integer>();
static float average_for_optimalPattern = 0;
private static final MissingValues_Class MISSING_VALUES_CLASS = new MissingValues_Class();
private static final EmptyValues_Class EMPTY_VALUES_CLASS = new EmptyValues_Class();
final static Abstraction_Primitives abstraction_primitive_object_csvtesting = new Abstraction_Primitives();
static File path_file_writer;
static List<String> listItems_tempHolds_group_rows_permutation = new ArrayList<String>();
static List<String> list_parsedTestItems_individual_rows_permutation = new ArrayList<>();
static List<String> list_parsedTestItems_group_rows_permutation = new ArrayList<>(); 
static Map<String, List<Integer>> map_set_list = new HashMap<String, List<Integer>>();
static int delimiter_type_counter;
static float rowCount;
static String univocityDetetced_delimiter;

static List<Dependency_Class> dependencies;
static {
	dependencies  = new ArrayList<Dependency_Class>();
    
    dependencies.add(new Dependency_Class(1, Arrays.asList(1,2,3,4,5,6,7,8,9,10,12,13,14,15,16)));
    dependencies.add(new Dependency_Class(2, Arrays.asList(3)));
    dependencies.add(new Dependency_Class(3, Arrays.asList(15)));
    dependencies.add(new Dependency_Class(4, Arrays.asList(5)));
    dependencies.add(new Dependency_Class(5, Arrays.asList(15)));	
    dependencies.add(new Dependency_Class(6, Arrays.asList(7,8)));
    dependencies.add(new Dependency_Class(7, Arrays.asList(16)));  
    dependencies.add(new Dependency_Class(8, Arrays.asList(7)));
    dependencies.add(new Dependency_Class(9, Arrays.asList(10)));
    dependencies.add(new Dependency_Class(10, Arrays.asList(15)));
    dependencies.add(new Dependency_Class(11, Arrays.asList(1,16)));   
    dependencies.add(new Dependency_Class(12, Arrays.asList(16)));
    dependencies.add(new Dependency_Class(13, Arrays.asList(8,16)));
    dependencies.add(new Dependency_Class(14, Arrays.asList(16)));
    dependencies.add(new Dependency_Class(15, Arrays.asList(16)));
   
 
}


static List<Dependency_Class> paired_dependencies;
static {
	paired_dependencies  = new ArrayList<Dependency_Class>();
	paired_dependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	paired_dependencies.add(new Dependency_Class(2, Arrays.asList(4)));
	paired_dependencies.add(new Dependency_Class(3, Arrays.asList(4)));   
}

static List<Dependency_Class> missingValues_dependencies;
static {
	missingValues_dependencies  = new ArrayList<Dependency_Class>();
	missingValues_dependencies.add(new Dependency_Class(1, Arrays.asList(9)));
	missingValues_dependencies.add(new Dependency_Class(2, Arrays.asList(3)));
	missingValues_dependencies.add(new Dependency_Class(3, Arrays.asList(9))); 
	missingValues_dependencies.add(new Dependency_Class(4, Arrays.asList(5)));
	missingValues_dependencies.add(new Dependency_Class(5, Arrays.asList(9)));
	missingValues_dependencies.add(new Dependency_Class(6, Arrays.asList(7)));
	missingValues_dependencies.add(new Dependency_Class(7, Arrays.asList(9)));
	missingValues_dependencies.add(new Dependency_Class(8, Arrays.asList(9)));
	 
}


static List<Dependency_Class> text_class_dependencies;
static {
	text_class_dependencies  = new ArrayList<Dependency_Class>();
	text_class_dependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	text_class_dependencies.add(new Dependency_Class(2, Arrays.asList(8)));     
	text_class_dependencies.add(new Dependency_Class(3, Arrays.asList(4)));
	text_class_dependencies.add(new Dependency_Class(4, Arrays.asList(8)));  
	text_class_dependencies.add(new Dependency_Class(5, Arrays.asList(6)));
	text_class_dependencies.add(new Dependency_Class(6, Arrays.asList(8)));  
	text_class_dependencies.add(new Dependency_Class(7, Arrays.asList(9))); 
	text_class_dependencies.add(new Dependency_Class(8, Arrays.asList(9)));
}

static List<Dependency_Class> alphaNumeric_class_dependencies;
static {
	alphaNumeric_class_dependencies  = new ArrayList<Dependency_Class>();
	alphaNumeric_class_dependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(2, Arrays.asList(10)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(3, Arrays.asList(4)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(4, Arrays.asList(10)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(5, Arrays.asList(6)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(6, Arrays.asList(10)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(7, Arrays.asList(8)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(8, Arrays.asList(10)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(9, Arrays.asList(10)));  
}

static List<Dependency_Class> number_class_dependencies;
static {
	number_class_dependencies  = new ArrayList<Dependency_Class>();
	number_class_dependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	number_class_dependencies.add(new Dependency_Class(2, Arrays.asList(4)));
}

static List<Dependency_Class> allASCIIdependencies;
static {
	allASCIIdependencies  = new ArrayList<Dependency_Class>();
    
	allASCIIdependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	allASCIIdependencies.add(new Dependency_Class(2, Arrays.asList(13)));
	allASCIIdependencies.add(new Dependency_Class(3, Arrays.asList(4)));
	allASCIIdependencies.add(new Dependency_Class(4, Arrays.asList(13)));
	allASCIIdependencies.add(new Dependency_Class(5, Arrays.asList(6)));
	allASCIIdependencies.add(new Dependency_Class(6, Arrays.asList(15,17)));
	allASCIIdependencies.add(new Dependency_Class(7, Arrays.asList(8)));	
	allASCIIdependencies.add(new Dependency_Class(8, Arrays.asList(13)));
	allASCIIdependencies.add(new Dependency_Class(10, Arrays.asList(15)));
	allASCIIdependencies.add(new Dependency_Class(11, Arrays.asList(15)));
	allASCIIdependencies.add(new Dependency_Class(12, Arrays.asList(15)));
	allASCIIdependencies.add(new Dependency_Class(13, Arrays.asList(15,16)));
	allASCIIdependencies.add(new Dependency_Class(14, Arrays.asList(15,16)));
	allASCIIdependencies.add(new Dependency_Class(17, Arrays.asList(15,16)));  
	

}

static List<ArrayList<Integer>> temp_permutation_results_list = new ArrayList<ArrayList<Integer>>();
static List<Dependency_Class> specified_depdendencies = new ArrayList<Dependency_Class>(); 

public static void main(String[] args) {
	

	Main_Class main_method_object = new Main_Class();
	
	
	String inputFile = args[0];
	String fileSchema= args[1];
	String resultIndicies= args[2];
	
	
	
	try {
		main_method_object.read_Csv_data(inputFile, fileSchema, resultIndicies);
	} catch (UnsupportedEncodingException | FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void read_Csv_data(String inputFile, String fileSchema, String resultIndicies) throws UnsupportedEncodingException, FileNotFoundException
{
	   Main_Class readCsvObject = new Main_Class();
	 
	   List<String> recordList = new ArrayList<>();
	   List<String> columns_recordList = new ArrayList<>();
	     
//===============================================================================================================================================================
	   CsvParserSettings settings = new CsvParserSettings();
	   settings.detectFormatAutomatically();
	   settings.setIgnoreLeadingWhitespaces(false);
	   settings.setIgnoreTrailingWhitespaces(false);
	   settings.setKeepQuotes(true);
	   settings.setQuoteDetectionEnabled(false);
	   settings.setSkipEmptyLines(false);
	   settings.setCommentProcessingEnabled(false);
	   settings.setDelimiterDetectionEnabled(true, ',',';','|',':','\t');  
	   
	   CsvParser parser = new CsvParser(settings);
	   List<String[]> rows = parser.parseAll(new File(inputFile));
	   
	   CsvFormat format = parser.getDetectedFormat(); 
	   
	   univocityDetetced_delimiter = format.getDelimiterString();
	   List<Record> allRecords = parser.parseAllRecords(new File(inputFile));
	   
//	   for(Record record : allRecords){
//		   //recordList.add(record.toString());
//		   System.out.println(record);
//	   }
      
	  rowCount= allRecords.size();  
	  
	   int rowNumber = 0;
	  
	   for(String[] valueNew : rows){   
		   StringBuilder tempStoreRecords = new StringBuilder();
		   int counter = 0;
		  
		   if(valueNew.length > listSize)
		   {										
			   listSize = valueNew.length;
		   }
		   for(String value : valueNew){
			   if(counter !=valueNew.length-1)
			   {
				   // row based approach
				   tempStoreRecords.append(value + format.getDelimiter());
				   // column based approach
				   columns_recordList.add(value);
				   columns_recordList.add(format.getDelimiterString());
				   counter++;
			   }
			   else
			   {   
				   // row based approach
				   tempStoreRecords.append(value);
				   // column based approach
				   
					columns_recordList.add(value); 
				    columns_recordList.add("\n");  
				  
			   }
			   
		   }
		   recordList.add(tempStoreRecords.toString()); // row based approach
		   rowNumber++;
		   all_rows_list.add(rowNumber);
		}
	   
	   listSize = listSize+ listSize; 
	   
	   // row based records printing
	   for(String record : recordList){
		 //System.out.println(record);
	   }
	   
	   // column based record printing
//	   for(String column : columns_recordList){
//			 System.out.println("column value  "+column);
//		   }
	   
	  for(int i = 0; i < columns_recordList.size(); i++)
	  {
		  //System.out.print(columns_recordList.get(i));
//		  for(int j = 0; j< columns_recordList.get(i).length(); j++)
//		  {
//			  char ch = columns_recordList.get(i).charAt(j);	// to print column values character by character 
//			  System.out.println((int)ch);
//		  }
		  
	  }
		  
//=================================================================================================================================================================  	
	
	
 readCsvObject.listItems_individual_columns(columns_recordList, fileSchema, resultIndicies);
}




public void listItems_individual_columns(List<String> columnList, String fileSchema, String resultIndicies)
{
	Main_Class listItems_individual_columns_object = new Main_Class();

	List<String> listOFdigits = new ArrayList<String>();
	List<String> listOFUpperAlphabets = new ArrayList<String>();
	List<String> listOFLowerAlphabets = new ArrayList<String>();
	List<String> listOFLetters = new ArrayList<String>();
	List<String> listOFblankspaces = new ArrayList<String>();	
	List<String> listOFDelimiter = new ArrayList<String>();
	List<String> listOFAlphaNumeric = new ArrayList<String>();
	List<String> listOFNumber = new ArrayList<String>();
	List<String> listOFallASCII = new ArrayList<String>();
	List<String> listofEmptyValues = new ArrayList<String>();
	Set<Integer> set_for_dependency_list = new LinkedHashSet<Integer>();
	List<List<String>> listoflist = new ArrayList<List<String>>();

	Map<Integer, List<List<String>>> map_Combined_listoflist_results = new LinkedHashMap<Integer, List<List<String>>>();
	
	
	List<ArrayList<Integer>> store_temp_permutation_digits = new ArrayList<ArrayList<Integer>>(); 
	List<ArrayList<Integer>> store_temp_permutation_nulls = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> store_temp_permutation_UpperLetter = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> store_temp_permutation_LowerLetter = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> store_temp_permutation_BlankSpace = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> store_temp_permutation_text = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> store_temp_permutation_AlphaNumeric = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> store_temp_permutation_fullText = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> store_temp_permutation_number = new ArrayList<ArrayList<Integer>>();
	
	Map<String, ArrayList<String>> digit_class_map = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> null_class_map = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> lower_Letter_class_map = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> upper_Letter_class_map = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> blankSpace_class_map = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> text_class_map = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> alphaNumeric_class_map = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> fullText_class_map = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> number_class_map = new HashMap<String, ArrayList<String>>();
	
//	dependency_list = rearrange_list_with_dependencies(dependency_list);
//	for(int del_run = 0; del_run< 5; del_run++)
//	{
//		list_parsedItems_test_abstraction_results.clear();	
		for (int i = 0; i < columnList.size(); i++) 
		   {
			
			if(!(columnList.get(i) == null) && (columnList.get(i).matches(univocityDetetced_delimiter) || columnList.get(i).matches("[/|]")) ) // updated this code on 08 December 2020
		    {
				listOFDelimiter.clear();
				abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
				abstraction_primitive_object_csvtesting.candidate_delimiter_Check();
				listOFDelimiter.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results 
				listoflist.add(new ArrayList<String>(listOFDelimiter));    			    	
//				    for(String index: listOFDelimiter)
//				   	{
//				  		System.out.println(index);
//				   	}
				   
		     }
			else if(columnList.get(i) == null || columnList.get(i).matches("\\p{Blank}+") || columnList.get(i).matches("\"\\p{Blank}+\"") ||
					 columnList.get(i).matches("NA") || columnList.get(i).matches("NaN") || columnList.get(i).matches("nan") || columnList.get(i).matches("null") ||
					 columnList.get(i).matches("NULL") ||columnList.get(i).matches("\t") ||  columnList.get(i).matches("\"\"") ||columnList.get(i).matches("na") ||
					 columnList.get(i).matches("n/a") || columnList.get(i).matches("N/A") || columnList.get(i).matches("None") || columnList.get(i).matches("NONE"))

			 {
				   if(columnList.get(i) == null)
				   {
					   listofEmptyValues.clear();
					   listofEmptyValues.add(EMPTY_VALUES_CLASS.toString());
					   listofEmptyValues.add(MISSING_VALUES_CLASS.toString());
				   }
				   else
				   {
					   listofEmptyValues.clear();
					   if(store_temp_permutation_nulls.isEmpty())
					   {
						   temp_permutation_results_list.clear();
							set_for_dependency_list.clear();
							//System.out.println(columnList.get(i));
						    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
						    specified_depdendencies = missingValues_dependencies;
						   	
						   	permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
						   	set_for_dependency_list.addAll(permuteLIST);
				    		temp_permutation_results_list.addAll(Main_Class.pruning_Set(Main_Class.powerSet(set_for_dependency_list)));
				    		store_temp_permutation_nulls.addAll(temp_permutation_results_list);
//						   	for(List<Integer> index: temp_permutation_results_list)
//						   	{
//						   		System.out.println(index);
//						   	} 
					   }
				    
					   if(null_class_map.containsKey(columnList.get(i)))
					   	{
						   listofEmptyValues.addAll(null_class_map.get(columnList.get(i)));
					    
					   	}
					   	else
					   	{
					   		for(List<Integer> list : store_temp_permutation_nulls)
				         	  {	
						   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
						   		
							   	 for (Integer index : list)
							   	 {	
							   		if(index == 1)
								   	 {
										abstraction_primitive_object_csvtesting.quotation_Check();
										
								   	 }
							   		if(index ==2)
								   	 {
							   		    abstraction_primitive_object_csvtesting.space_Check();	
							   		    
									 }  
							   		if(index ==3)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();	
							   		   
									 } 
							   		if(index == 4)
								   	 {
										abstraction_primitive_object_csvtesting.upper_letter_Check();
										
								   	 }
							   		if(index ==5)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();	
							   		    
									 }  
							   		if(index ==6)
								   	 {
							   		    abstraction_primitive_object_csvtesting.lower_letter_Check();	
							   		   
									 } 
							   		if(index ==7)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();
							   		    
									 } 
							   		if(index ==8)
								   	 {
							   		    abstraction_primitive_object_csvtesting.symbol_Check();
							   		    
									 } 
							   		if(index ==9)
								   	 {
							   		    abstraction_primitive_object_csvtesting.missing_values_check();
							   		    
									 }
							   	  }
							   	 
							   	if(!listofEmptyValues.contains(getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result())) )
							   	 {
							   		listofEmptyValues.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results  
							   	 }	
							 }
					   		null_class_map.put(columnList.get(i),new ArrayList<>(listofEmptyValues));
					   	}
				   	
				   }
				   	Collections.reverse(listofEmptyValues);
				   	listoflist.add(new ArrayList<String>(listofEmptyValues));  
				   	
//					    for(String index: listofEmptyValues)
//					   	{
//					  		System.out.println(index);
//					   	}
					   
			 }  
			 
			 else if(columnList.get(i).matches("[0-9]+") || columnList.get(i).matches("\"[0-9]+\""))
				{
				
				 listOFdigits.clear();
				if(store_temp_permutation_digits.isEmpty())
				{
					temp_permutation_results_list.clear();
					set_for_dependency_list.clear();
					//System.out.println(columnList.get(i));
				    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
				    specified_depdendencies = paired_dependencies;
				 	permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
				   	set_for_dependency_list.addAll(permuteLIST);
		    		temp_permutation_results_list.addAll(Main_Class.pruning_Set(Main_Class.powerSet(set_for_dependency_list)));
		    		
		    		store_temp_permutation_digits.addAll(temp_permutation_results_list);
//				   	for(List<Integer> index: temp_permutation_results_list)
//				   	{
//				   		System.out.println(index);
//				   	}
				}

			   	if(digit_class_map.containsKey(columnList.get(i)))
			   	{
			    listOFdigits.addAll(digit_class_map.get(columnList.get(i)));
			    
			   	}
			   	else
			   	{
			   		for(List<Integer> list : store_temp_permutation_digits)
		         	  {	
				   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
					   	 for (Integer index : list)
					   	 {	
					   		if(index == 1)
						   	 {
								abstraction_primitive_object_csvtesting.digits_Check();
								
						   	 }
					   		if(index ==2)
						   	 {
					   		    abstraction_primitive_object_csvtesting.updated_sequenceofdigits_Check();	
					   		    
							 }  
					   		if(index ==3)
						   	 {
					   		    abstraction_primitive_object_csvtesting.quotation_Check();	
					   		   
							 } 
					   		if(index ==4)
						   	 {
					   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();
					   		    
							 } 
					   	  }
					   	 
					   	if(!listOFdigits.contains(getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result())) )
					   	 {
					   		listOFdigits.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results  
					   	 }	
					  }
			   		
			   		digit_class_map.put(columnList.get(i), new ArrayList<String>(listOFdigits));
			   	}
			   	
			   	Collections.reverse(listOFdigits);
			   	listoflist.add(new ArrayList<String>(listOFdigits));  
			   	
//				    for(String index: listOFdigits)
//				   	{
//				  		System.out.println(index);
//				   	}
				   
		        }
			
				
				else if(columnList.get(i).matches("[A-Z]+") || columnList.get(i).matches("\"[A-Z]+\""))
			    {
					listOFUpperAlphabets.clear();
					if(store_temp_permutation_UpperLetter.isEmpty())
					{
						temp_permutation_results_list.clear();
						set_for_dependency_list.clear();
						//System.out.println(columnList.get(i));
					    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
					    specified_depdendencies = paired_dependencies;
					     permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
					   	set_for_dependency_list.addAll(permuteLIST);
			    		temp_permutation_results_list.addAll(Main_Class.pruning_Set(Main_Class.powerSet(set_for_dependency_list)));
			    		store_temp_permutation_UpperLetter.addAll(temp_permutation_results_list);
//					   	for(List<Integer> index: temp_permutation_results_list)
//					   	{
//					   		System.out.println(index);
//					   	}	
					}
					
				   	if(upper_Letter_class_map.containsKey(columnList.get(i)))
				   	{
				   		listOFUpperAlphabets.addAll(upper_Letter_class_map.get(columnList.get(i)));
				   	}
				   	else
				   	{
				   		for(List<Integer> list : store_temp_permutation_UpperLetter)
			         	  {	
					   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
						   	 for (Integer index : list)
						   	 {	
						   		if(index == 1)
							   	 {
									abstraction_primitive_object_csvtesting.upper_letter_Check();
							   	 }
						   		if(index == 2)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();	
								 }    
						   		if(index ==3)
							   	 {
						   		    abstraction_primitive_object_csvtesting.quotation_Check();	
								 } 
						   		if(index ==4)
							   	 {
						   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	
								 } 
						   	  }
						   	if(!listOFUpperAlphabets.contains(getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result())) )
						   	 {
						   		listOFUpperAlphabets.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results  
						   	 }					 	
			         	  }
				   		upper_Letter_class_map.put(columnList.get(i), new ArrayList<String>(listOFUpperAlphabets));
				   	}
					
					Collections.reverse(listOFUpperAlphabets);
					listoflist.add(new ArrayList<String>(listOFUpperAlphabets)); 	
//					    for(String index: listOFUpperAlphabets)
//					   	{
//					  		System.out.println(index);
//					   	}
					   
			        
			    }
				
				
				else if(columnList.get(i).matches("[a-z]+") || columnList.get(i).matches("\"[a-z]+\""))
			    {
					listOFLowerAlphabets.clear();
					if(store_temp_permutation_LowerLetter.isEmpty())
					{
						temp_permutation_results_list.clear();
						set_for_dependency_list.clear();
						//System.out.println(columnList.get(i));
					    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
					    specified_depdendencies= paired_dependencies;
					   	permuteLIST = rearrange_list_with_dependencies(permuteLIST);  
					   	set_for_dependency_list.addAll(permuteLIST);
			    		temp_permutation_results_list.addAll(Main_Class.pruning_Set(Main_Class.powerSet(set_for_dependency_list)));
			    		store_temp_permutation_LowerLetter.addAll(temp_permutation_results_list);
//					   	for(List<Integer> index: temp_permutation_results_list)
//					   	{
//					   		System.out.println(index);
//					   	}
					}
					if(lower_Letter_class_map.containsKey(columnList.get(i)))
				   	{
						listOFLowerAlphabets.addAll(lower_Letter_class_map.get(columnList.get(i)));
						
				   	}
				   	else
				   	{
				   		for(List<Integer> list : store_temp_permutation_LowerLetter)
			         	  {	
					   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
						   	 for (Integer index : list)
						   	 {	
						   		if(index == 1)
							   	 {
									abstraction_primitive_object_csvtesting.lower_letter_Check();
							   	 }
						   		if(index == 2)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();	
								 } 
						   		if(index ==3)
							   	 {
						   		    abstraction_primitive_object_csvtesting.quotation_Check();	
								 } 
						   		if(index ==4)
							   	 {
						   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	
								 } 
						   	  }
						   	if(!listOFLowerAlphabets.contains(getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result())) )
						   	 {
						   		listOFLowerAlphabets.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results  
						   	 }
						 }
				   		
				   		lower_Letter_class_map.put(columnList.get(i), new ArrayList<String>(listOFLowerAlphabets));
				   	}
									   	
					
					Collections.reverse(listOFLowerAlphabets);
				   	listoflist.add(new ArrayList<String>(listOFLowerAlphabets)); 
			    	
//					    for(String index: listOFLowerAlphabets)
//					   	{
//					  		System.out.println(index);
//					   	}
					   
			    }
			 

				else if(columnList.get(i).matches("\\p{Blank}+") || columnList.get(i).matches("\"\\p{Blank}+\""))
			    {
					listOFblankspaces.clear();
					if(store_temp_permutation_BlankSpace.isEmpty())
					{
						temp_permutation_results_list.clear();
						set_for_dependency_list.clear();
						//System.out.println(columnList.get(i));
					    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
					    specified_depdendencies= paired_dependencies;
					   	permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
					   	set_for_dependency_list.addAll(permuteLIST);
			    		temp_permutation_results_list.addAll(Main_Class.pruning_Set(Main_Class.powerSet(set_for_dependency_list)));
			    		store_temp_permutation_BlankSpace.addAll(temp_permutation_results_list);
//					   	for(List<Integer> index: temp_permutation_results_list)
//					   	{
//					   		System.out.println(index);
//					   	}
					}
					
				   	if(blankSpace_class_map.containsKey(columnList.get(i)))
				   	{
				   		listOFblankspaces.addAll(blankSpace_class_map.get(columnList.get(i)));
				   	}
				   	else
				   	{
				   		for(List<Integer> list : store_temp_permutation_BlankSpace)
			         	  {	
					   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
						   	 for (Integer index : list)
						   	 {	
						   		if(index == 1)
							   	 {
									abstraction_primitive_object_csvtesting.space_Check();
							   	 }
						   		if(index == 2)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();	
								 }  
						   		if(index ==3)
							   	 {
						   		    abstraction_primitive_object_csvtesting.quotation_Check();	
								 } 
						   		if(index ==4)
							   	 {
						   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	 
								 } 
						   		
						   	  }
						   	if(!listOFblankspaces.contains(getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result())) )
						   	 {
						   		listOFblankspaces.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results  
						   	 }
						 }
				   		blankSpace_class_map.put(columnList.get(i), new ArrayList<String>(listOFblankspaces));
				   	  }
					
					    Collections.reverse(listOFblankspaces);
						listoflist.add(new ArrayList<String>(listOFblankspaces));   			    	
//					    for(String index: listOFblankspaces)
//					   	{
//					  		System.out.println(index);
//					   	}
					   
			     }
				
				

				else if( columnList.get(i).matches("\"(^[a-z]|[A-Z])[a-zA-Z\\p{Blank}]+\"") || 
						columnList.get(i).matches("\"(^[a-z]|[A-Z]|\\p{Blank})[a-zA-Z\\p{Blank}]+\"") || columnList.get(i).matches("(^[a-z]|[A-Z])[a-zA-Z\\p{Blank}]+")
						|| columnList.get(i).matches("(^[a-z]|[A-Z]|\\p{Blank})[a-zA-Z\\p{Blank}]+"))		
			    {
					listOFLetters.clear();
					if(store_temp_permutation_text.isEmpty())
					{
						temp_permutation_results_list.clear();
						set_for_dependency_list.clear();
						//System.out.println(columnList.get(i));
					    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
					    specified_depdendencies= text_class_dependencies;
					    permuteLIST = rearrange_list_with_dependencies(permuteLIST);  
					   	set_for_dependency_list.addAll(permuteLIST);
			    		temp_permutation_results_list.addAll(Main_Class.pruning_Set(Main_Class.powerSet(set_for_dependency_list)));
			    		store_temp_permutation_text.addAll(temp_permutation_results_list);
//					   	for(List<Integer> index: temp_permutation_results_list)
//					   	{
//					   		System.out.println(index);
//					   	}
					}
					
					if(text_class_map.containsKey(columnList.get(i)))
					{
						listOFLetters.addAll(text_class_map.get(columnList.get(i)));
					}
					else
					{
						for(List<Integer> list : store_temp_permutation_text)
			         	  {	
					   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
						   	 for (Integer index : list)
						   	 {	
						   		if(index == 1)
							   	 {
									abstraction_primitive_object_csvtesting.upper_letter_Check();
							   	 }
						   		if(index == 2)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();	
								 }  
						   		if(index == 3)
							   	 {
									abstraction_primitive_object_csvtesting.lower_letter_Check();
							   	 }
						   		if(index == 4)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();	
								 }  
						   		if(index == 5)
							   	 {
									abstraction_primitive_object_csvtesting.space_Check();
							   	 }
						   		if(index == 6)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();	
								 }  
						   		if(index == 7)
							   	 {
						   		    abstraction_primitive_object_csvtesting.quotation_Check();	
								 } 
						   		if(index == 8)
							   	 {
						   		    abstraction_primitive_object_csvtesting.text_format_Check();	
								 } 
						   		if(index == 9)
							   	 {
						   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	
								 } 
						   	  }
						   	 if(!listOFLetters.contains(getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result())) )
						   	 {
						   		listOFLetters.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results  
						   	 }
						 }
						text_class_map.put(columnList.get(i), new ArrayList<String>(listOFLetters));
					  }
					
					    Collections.reverse(listOFLetters);
						listoflist.add(new ArrayList<String>(listOFLetters));   			    	
//					    for(String index: listOFLetters)
//					   	{
//					  		System.out.println(index);
//					   	}   
					   
			    }
			
				else if(columnList.get(i).matches("[0-9.+-]+") || columnList.get(i).matches("\"[0-9.+-]+\""))
				{
					listOFNumber.clear();
					if(store_temp_permutation_number.isEmpty())
					{
						temp_permutation_results_list.clear();
						set_for_dependency_list.clear();
						//System.out.println(columnList.get(i));
					    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
					    specified_depdendencies= number_class_dependencies;
					   	permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
					   	set_for_dependency_list.addAll(permuteLIST);
			    		temp_permutation_results_list.addAll(Main_Class.pruning_Set(Main_Class.powerSet(set_for_dependency_list)));
			    		store_temp_permutation_number.addAll(temp_permutation_results_list);
//					   	for(List<Integer> index: temp_permutation_results_list)
//					   	{
//					   		System.out.println(index);
//					   	}
					 
					}
					
					if(number_class_map.containsKey(columnList.get(i)))
					{
						listOFNumber.addAll(number_class_map.get(columnList.get(i)));
					}
					else
					{
						for(List<Integer> list : store_temp_permutation_number)
			         	  {	
					   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
						   	 for (Integer index : list)
						   	 {	
						   		if(index == 1)
							   	 {
						   			abstraction_primitive_object_csvtesting.digits_Check();
							   	 }
						   		if(index == 2)
							   	 {
						   			abstraction_primitive_object_csvtesting.updated_sequenceofdigits_Check();
								 }  
								if(index == 3)
								{
									abstraction_primitive_object_csvtesting.quotation_Check();	
								}
						   		if(index == 4)
							   	 {
						   			abstraction_primitive_object_csvtesting.number_Check();
							   	 }
						   		if(index == 5)
							   	 {
						   			abstraction_primitive_object_csvtesting.arithmetic_opr_Check();
							   	 }
						   		if(index == 6)
							   	 {
						   			abstraction_primitive_object_csvtesting.symbol_Check();
							   	 }
						   	  }
						   	 if(!listOFNumber.contains(getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result())) )
						   	 {
						   		listOFNumber.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results  
						   	 }
						 }
						number_class_map.put(columnList.get(i), new ArrayList<String>(listOFNumber));
					  }
					
					    Collections.reverse(listOFNumber);
						listoflist.add(new ArrayList<String>(listOFNumber));   			    	
//					    for(String index: listOFNumber)
//					   	{
//					  		System.out.println(index);
//					   	}   
				}
			
				else if( columnList.get(i).matches("[a-zA-Z0-9\\p{Blank}]+") || columnList.get(i).matches("\"[a-zA-Z0-9\\p{Blank}]+\""))		
			    {
					listOFAlphaNumeric.clear();
					if(store_temp_permutation_AlphaNumeric.isEmpty())
					{
						temp_permutation_results_list.clear();
						set_for_dependency_list.clear();
						//System.out.println(columnList.get(i));
					    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
					    specified_depdendencies= alphaNumeric_class_dependencies;
					    permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
					   	set_for_dependency_list.addAll(permuteLIST);
			    		temp_permutation_results_list.addAll(Main_Class.pruning_Set(Main_Class.powerSet(set_for_dependency_list)));
			    		store_temp_permutation_AlphaNumeric.addAll(temp_permutation_results_list);
//					   	for(List<Integer> index: temp_permutation_results_list)
//					   	{
//					   		System.out.println(index);
//					   	}
					 
					}
					
					if(alphaNumeric_class_map.containsKey(columnList.get(i)))
					{
					listOFAlphaNumeric.addAll(alphaNumeric_class_map.get(columnList.get(i)));
					}
					else
					{
						for(List<Integer> list : store_temp_permutation_AlphaNumeric)
			         	  {	
					   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
						   	 for (Integer index : list)
						   	 {	
						   		if(index == 1)
							   	 {
									abstraction_primitive_object_csvtesting.upper_letter_Check();
							   	 }
						   		if(index == 2)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();	
								 }  
						   		if(index == 3)
							   	 {
									abstraction_primitive_object_csvtesting.lower_letter_Check();
							   	 }
						   		if(index == 4)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();	
								 }  
						   		if(index == 5)
								{
									abstraction_primitive_object_csvtesting.digits_Check();
								}
								if(index == 6)
								{
									abstraction_primitive_object_csvtesting.updated_sequenceofdigits_Check();
								}
						   		if(index == 7)
							   	 {
									abstraction_primitive_object_csvtesting.space_Check();
							   	 }
						   		if(index == 8)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();	
								 }  
						   		if(index == 9)
							   	 {
						   		    abstraction_primitive_object_csvtesting.quotation_Check();	
								 } 
						   		if(index == 10)
							   	 {
						   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	
								 } 
						   	  }
						   	 if(!listOFAlphaNumeric.contains(getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result())) )
						   	 {
						   		listOFAlphaNumeric.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results  
						   	 }
						 }
						alphaNumeric_class_map.put(columnList.get(i), new ArrayList<String>(listOFAlphaNumeric));
					  }
					
					    Collections.reverse(listOFAlphaNumeric);
						listoflist.add(new ArrayList<String>(listOFAlphaNumeric));   			    	
//					    for(String index: listOFAlphaNumeric)
//					   	{
//					  		System.out.println(index);
//					   	}   
					   
			    }
				
				else if(columnList.get(i).matches("[\\x00-\\x7F]+") ) // remove [^\r\n] if line breaks are required
			    {
					listOFallASCII.clear();
					if(columnList.get(i).length() > 50 && !(StringUtils.isAlphanumeric(columnList.get(i))) ) 
					{
						
						if(!listOFallASCII.contains("<FullText>") )
					   	 {
					   		listOFallASCII.add("<FullText>");   
					   	 }	
					}
					else
					{
						listOFallASCII.clear();
						if(store_temp_permutation_fullText.isEmpty())
						{
							//System.out.println(columnList.get(i));
							temp_permutation_results_list.clear();
							set_for_dependency_list.clear();
						    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17));
						    specified_depdendencies= allASCIIdependencies;
						    permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
						   	set_for_dependency_list.addAll(permuteLIST);
				    		temp_permutation_results_list.addAll(Main_Class.pruning_Set(Main_Class.powerSet(set_for_dependency_list)));
				    		store_temp_permutation_fullText.addAll(temp_permutation_results_list);
//						   	for(List<Integer> index: temp_permutation_results_list)
//						   	{
//						   		System.out.println(index);
//						   	}
						   	
						}
						
						if(fullText_class_map.containsKey(columnList.get(i)))
						{
							listOFallASCII.addAll(fullText_class_map.get(columnList.get(i)));
						}
						else
						{
							for(List<Integer> list : store_temp_permutation_fullText)
				         	  {	
						   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
							   	 for (Integer index : list)
							   	 {	
							   		if(index ==1)
							   		{
							   			abstraction_primitive_object_csvtesting.upper_letter_Check();
							   		}
							   		else if(index==2)
							   		{
							   			abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();
							   		}
							   		else if(index==3)
							   		{
							   			abstraction_primitive_object_csvtesting.lower_letter_Check();
							   		}
							   		else if(index ==4)
							   		{
							   			abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();
							   		}
							   		else if(index == 5)
							   		{
							   			abstraction_primitive_object_csvtesting.digits_Check();
							   		}
							   		else if(index == 6)
							   		{
							   			abstraction_primitive_object_csvtesting.updated_sequenceofdigits_Check();
							   		}	
							   		else if(index == 7)
							   		{
							   			abstraction_primitive_object_csvtesting.space_Check();
							   		}
							   		else if(index == 8) 
							   		{
							   			abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();
							   		}
							   		else if(index == 9)
							   		{
							   			abstraction_primitive_object_csvtesting.quotation_Check();
							   		}
							   		else if(index == 10)
							   		{
							   			abstraction_primitive_object_csvtesting.brackets_Check();
							   		}
							   		else if(index == 11)
							   		{
							   			abstraction_primitive_object_csvtesting.arithmetic_opr_Check();
							   		}	
							   		else if(index == 12)
							   		{
							   			abstraction_primitive_object_csvtesting.symbol_Check();
							   		}
							   		else if(index == 13)
							   		{
							   			abstraction_primitive_object_csvtesting.text_format_Check();
							   		}
							   		else if(index == 14)
							   		{
							   			abstraction_primitive_object_csvtesting.date_format_Check();
							   		}
							   		else if(index == 15)
							   		{
							   			//abstraction_primitive_object_csvtesting.fullText_format_Check();
							   		}
							   		else if(index == 16)
							   		{															
							   			abstraction_primitive_object_csvtesting.lineBreak();    
							   		}  
							   		else if(index == 17)
							   		{															
							   			abstraction_primitive_object_csvtesting.number_Check();    
							   		}  

							   	  }
							   	if(!listOFallASCII.contains(getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result())) )
							   	 {
							   		listOFallASCII.add( getStringRepresentation(abstraction_primitive_object_csvtesting.get_abstraction_result()));  // get abstraction results  
							   	 }
							 }
							fullText_class_map.put(columnList.get(i), new ArrayList<String>(listOFallASCII));
						  }
					  }
						
					
					    Collections.reverse(listOFallASCII);
					    listoflist.add(new ArrayList<String>(listOFallASCII));
//					    for(String index: listOFallASCII)
//					   	{
//					  		System.out.println(index);
//					   	}   
			     }
				
			 
				if(columnList.get(i) == "\n")
			    {
					
					int listSizeDifference = 0;
					if(listSize == 0)
					{
						listSize =listoflist.size();	
					}															
					else if(listSize >listoflist.size())						
					{
				     listSizeDifference = listSize-listoflist.size();		
					}
					
					if(listSizeDifference >0)
					{
						for(int k = 0; k<listSizeDifference ; k++)
						{																	 
							List appendNullList = new ArrayList<>();						
																							
							
							appendNullList.add(EMPTY_VALUES_CLASS.toString());
							appendNullList.add(MISSING_VALUES_CLASS.toString());
							listoflist.add(listoflist.size()-1,appendNullList);
						}
					}
					
			    	for(int index = 0; index<listoflist.size(); index++)
				   	{
			    		if(map_Combined_listoflist_results.containsKey(index))
			    		{
			    			map_Combined_listoflist_results.get(index).add(listoflist.get(index));
			    		}
			    		else
			    		{
			    			map_Combined_listoflist_results.computeIfAbsent(index, k -> new ArrayList()).add(listoflist.get(index));	
			    		}

					}
					
			    	listoflist.clear();
		     }
			    
		   }
		
		
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
						            
					    	     
//					    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(0));
//					    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(1));    
//					    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(6));
//						    	 optimal_pattern.add(optimal_patter_subList); 
						                                                             
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
					            
							   
							   
//							     List optimal_patter_subList = new ArrayList<>() ;
//							     optimal_patter_subList.add(maximum_weighted_List.get(i).get(0));
//					    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(1));
//					    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(6));
//						    	 optimal_pattern.add(optimal_patter_subList); 	                                             // to add delimiter record 
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
//										 optimal_patter_subList.add(maximum_weighted_List.get(i).get(0));
//										 optimal_patter_subList.add(maximum_weighted_List.get(j).get(1));
//										 optimal_patter_subList.add(maximum_weighted_List.get(i).get(6));     
									
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
									 
//									 optimal_patter_subList.add(maximum_weighted_List.get(i).get(0));
//						    	     optimal_patter_subList.add(maximum_weighted_List.get(j).get(1));
//						    	     optimal_patter_subList.add(maximum_weighted_List.get(i).get(6));
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
			
			
			List<List<String>> position_list_indicies = new ArrayList<List<String>>();
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
			
			
			
			
			System.out.println("Program executed successfuly (END)!  ");
			
			
	//end of del_run for loop ( to try all possible delimiters)
	//}

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

public static List<List<String>> generate_Cartesian_product(List<List<String>> allLists) {
	int n = allLists.size();  
    int solutions = 1; 
    for(List<String> list : allLists) {
        solutions *= list.size();            
    }
    List<List<String>> allCombinations = new ArrayList<List<String>>();
    
    for(int i = 0; i < solutions; i++) {
    	List<String> combination = new ArrayList<String>();
        int j = 1;
        for(List<String> list : allLists) {
        	combination.add(list.get((i/j)%list.size()));
            j *= list.size();
        }
        allCombinations.add(combination);
    }
    
    return allCombinations;
}



public static List<ArrayList<Integer>> pruning_Set(Set<Set<Integer>> input_set)
{
	List<ArrayList<Integer>> rearrange_set= new ArrayList<ArrayList<Integer>>();
	for(Set<Integer> set :input_set)
	{
		if(dependent_permutation_check(new ArrayList<Integer>(set)))
		{
			rearrange_set.add(new ArrayList<Integer>(set));
		}
	}
	
	return rearrange_set;
}

public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
	
    Set<Set<T>> sets = new LinkedHashSet<Set<T>>();
    if (originalSet.isEmpty()) {
        sets.add(new LinkedHashSet<T>());
        return sets;
    }
    List<T> list = new ArrayList<T>(originalSet);
    T head = list.get(0);
    Set<T> rest = new LinkedHashSet<T>(list.subList(1, list.size())); 
    for (Set<T> set : powerSet(rest)) {
        Set<T> newSet = new LinkedHashSet<T>();
        newSet.add(head);
        newSet.addAll(set);
        sets.add(newSet);
        sets.add(set);
    }       
    return sets;
}  



public static boolean dependent_permutation_check(List<Integer> permutation_combintaion)
{
	
	for(Dependency_Class d: specified_depdendencies)
	{
		d.reset();
	}
     
    for(int i:permutation_combintaion)
    {
    	for(Dependency_Class d: specified_depdendencies)
    	{
    		if(!d.add(i))
    			return false;
    	}
    	
    }
    return true;
    
}


public List<Integer> rearrange_list_with_dependencies(List<Integer> list_rearrange_data)
{
	for(Dependency_Class d: specified_depdendencies)
	{
		d.reset();
	}											
     											
    for(int i:list_rearrange_data)
    {
    	for(Dependency_Class d: specified_depdendencies)
    	{
    		if(!d.add(i))
    		{
    			swap(list_rearrange_data, list_rearrange_data.indexOf(i),  list_rearrange_data.indexOf(d.get_conflict_value()) );
    			rearrange_list_with_dependencies(list_rearrange_data);
    		}	
    	}
    }
    return list_rearrange_data;
}


public List<Integer> swap(List<Integer> list_rearrange_data, int i, int j) 
{ 
	Collections.swap(list_rearrange_data, i, j);
	return list_rearrange_data;
} 


public String getStringRepresentation(Map<Integer, Object> input ) 
{
		StringBuilder str_builder_getStringRepresentation = new StringBuilder();
		for(Object o : input.values()) 
			str_builder_getStringRepresentation.append(o.toString());
		return str_builder_getStringRepresentation.toString();
	
}



}

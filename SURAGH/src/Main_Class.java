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
	
static int row_T = 1;
static int col_T = 20;
	
static int listSize;
static List<Integer> all_rows_list = new ArrayList<Integer>();
static float rowCount;
static String univocityDetetced_delimiter;

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

	public void read_Csv_data(String inputFile, String fileSchema, String resultIndicies ) throws UnsupportedEncodingException, FileNotFoundException
	{
		 Main_Class readCsvObject = new Main_Class();
		 List<String> columns_recordList = new ArrayList<>();
		 
		 
		 List<List<Object>> output_patterns_input_data = new ArrayList<List<Object>>(); 
		 
		 
		 columns_recordList = readCsvObject.dataParsingColumns(inputFile);
		 output_patterns_input_data = readCsvObject.inputData(columns_recordList, fileSchema, resultIndicies);
	    
	      
	    
	}

	
	public List<List<Object>> inputData(List<String> columnList, String fileSchema, String resultIndicies)
	{
		   PatternGeneration patternGeneration_object = new PatternGeneration();  // to generate patterns for each column value 
		   PruningPatterns  pruningPatterns_object = new PruningPatterns();     // to prune patterns for each column value 
		   PatternSchema patternSchema_Object = new PatternSchema();            // row pattern generation and sanitized output
		   
		   List<List<Object>> output_patterns_input_data = new ArrayList<List<Object>>(); 
		   
		   Map<Integer, List<List<String>>> map_Combined_listoflist_results = new LinkedHashMap<Integer, List<List<String>>>();  // Main_Class definition "map_Combined_listoflist_results"
		   map_Combined_listoflist_results = patternGeneration_object.patternComputation(columnList, listSize, univocityDetetced_delimiter); // get results from PatternGeneration "map_Combined_listoflist_results"
		   
		   
		   List<List<Object>> optimal_pattern = new ArrayList<List<Object>>(); // Main_Class definition "optimal_pattern"
		   optimal_pattern = pruningPatterns_object.patternWeights_patternPruning(map_Combined_listoflist_results, col_T, row_T, rowCount); // send "map_Combined_listoflist_results" to PruningPatterns to get optimal patterns
		   
		   output_patterns_input_data = patternSchema_Object.schemaGeneration(row_T, all_rows_list, optimal_pattern, fileSchema, resultIndicies);  // for schema generation and sanitized output
		   
//		   System.out.println("Input Data Size Statistics: "+ listSize+"              "+ rowCount);
//		   System.out.println("Program executed successfuly for the Input Data!  ");
			
		   return output_patterns_input_data;
	}	

	
	
	public List<String> dataParsingColumns(String inputFile)
	{
		  List<String> recordList = new ArrayList<>();
		  List<String> columns_recordList = new ArrayList<>();
		  listSize = 0;
		  all_rows_list.clear();
		  
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
			  
	 return columns_recordList;
	 
	}
	
} 

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

public class Csv_Writer {

	public static void csvWrite(List<List<Object>> list, String fileSchema, String resultIndicies, List<Integer> parsed_rows_indicies_LIST, List<Integer> possible_Outlier_Rows_indicies_LIST)
	{
		 try (Writer outputWriter = new OutputStreamWriter(new FileOutputStream(new File(fileSchema)),"UTF-8")){
			 
			    CsvWriterSettings settings = new CsvWriterSettings();
			    settings.setSkipEmptyLines(false);
				
				CsvWriter writer = new CsvWriter(outputWriter, settings);
				List<List<Object>> sanitized_output = new ArrayList<List<Object>>();
			    List sub_list_ = new ArrayList<>();
				sub_list_.add("Dominant Patterns");
				sub_list_.add("Complying Row Indices");
				sub_list_.add("Size");
				   
				sanitized_output.add(sub_list_);
				
				for(int i = 0; i<list.size(); i++)
				{
					list.get(i).set(1, list.get(i).get(1).toString().replaceAll("[\\[ \\]\\s]", ""));
				}
				sanitized_output.addAll(list);
				   
				for (int i = 0; i < sanitized_output.size(); i++) 
				{
				    writer.writeRow(sanitized_output.get(i));
				}
				writer.close();
		 }
		 catch (IOException e) {
		        // handle exception
		    }
		 
		 
		 try (Writer outputWriter = new OutputStreamWriter(new FileOutputStream(new File(resultIndicies)),"UTF-8")){
			 
			    CsvWriterSettings settings = new CsvWriterSettings();
			    settings.setSkipEmptyLines(false);
				
				CsvWriter writer = new CsvWriter(outputWriter, settings);
				List<List<Object>> sanitized__indices_output = new ArrayList<List<Object>>();
				
				List sub_list_indicies = new ArrayList<>();
				List sub_list_indicies_well = new ArrayList<>();
				List sub_list_indicies_ill = new ArrayList<>();
				
				sub_list_indicies.add("Output Type");
				sub_list_indicies.add("Row Indices");
				sub_list_indicies.add("Size");
				
				sub_list_indicies_well.add("Well-Formed Records");
				sub_list_indicies_well.add(parsed_rows_indicies_LIST.toString().replaceAll("[\\[ \\]\\s]", ""));
				sub_list_indicies_well.add(parsed_rows_indicies_LIST.size());
				
				sub_list_indicies_ill.add("Ill-Formed Records");
				sub_list_indicies_ill.add(possible_Outlier_Rows_indicies_LIST.toString().replaceAll("[\\[ \\]\\s]", ""));
				sub_list_indicies_ill.add(possible_Outlier_Rows_indicies_LIST.size());
				
				sanitized__indices_output.add(sub_list_indicies);
				sanitized__indices_output.add(sub_list_indicies_well);
				sanitized__indices_output.add(sub_list_indicies_ill);
				
				for (int i = 0; i < sanitized__indices_output.size(); i++) 
				{
				    writer.writeRow(sanitized__indices_output.get(i));
				}
				writer.close();
		 }
		 catch (IOException e) {
		        // handle exception
		    }
		 
		 
		System.out.println("Program successfully executed!");
	}
}

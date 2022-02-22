# SURAGH: Syntactic Pattern Matching to Identify Ill-Formed Records
Code repository of the SURAGH project, developed at the Information Systems Group of the Hasso Plattner Institute.

SURAGH takes a CSV file as input and returns the indices of ill- and well-formed records along with the file's pattern schema.

## Setup

1. The code is written using Java 8.
2. Download the available code as a zip file or cloned SURAGH repository.
3. Include the following external libraries: 
	- univocity-parsers-2.9.1 or its latest version
	- commons-lang3-3.11
	- guava-31.0.1-jre
	- json-20210307

   Note: The aforementioned libraries are a part of the project and are available in the same repository. Please update the path for the referenced libraries. To save the output in JSON format, use the method of the JsonWriter class in the PatternSchema class.
   
 4. Input/Output: It can be executed directly from the command line
	-  Open a command prompt window and go to the directory where you saved the SURAGH project code, use `javac` `java` commands to compile and run the program. Note -- entry point is `Main_Class.java`.  
	`OR´
	-  Execute `JAR` file attached to this repository. Open a command prompt window and go to the directory where you saved the SURAGH.jar, for example, C:\Users\UserName\Desktop>java -jar SURAGH.jar "C:\Users\UserName\Desktop\InputFile.csv" "C:\Users\UserName\Desktop\PatternSchema.csv" "C:\Users\UserName\Desktop\ResultIndices.csv
       `OR´
       - For IDE, you can set the arguments in the `Run Configuration`. 

The algorithm takes a CSV file as input and outputs two CSV files, (1) includes pattern schema for the input file, (2) contains indices of ill- and well-formed records.
The three sample (input and output) files including (InputFile, PatternSchema, ResultIndices) are attached to the repository for the reference. The input file is the same one we used as an example in the paper.

- Please follow the following order for the input arguments
	(1) Input file path
	(2) File path for the schema output
	(3) File path for the indices of ill- and well-formed records




## Global Threshold Setting
(Please refer to the paper for this section) 

We performed experiments using the combinations of row and column thresholds and found the "Global Threshold setting" for the finest result. The pattern schema and the detailed results are obtained using the global threshold setting. To increase or decrease the set of patterns and test different threshold settings, please update the "row_t" and "col_t" variables for row threshold and column threshold, respectively, in the project class named "Main_Class.java".


## Ground Truth File

A two-column CSV file, the first column contains a string, e.g., "ill formed rows indices" or "well formed rows indices".  The second column contains the row indices for the records mentioned above. (Please check annotation folder for reference)


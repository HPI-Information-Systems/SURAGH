# SURAGH: Syntactic Pattern Matching to Identify Ill-Formed Records
Code repository of the SURAGH project, developed at the Information Systems Group of the Hasso Plattner Institute.

## Setup

1. The code is written using Java 8.
2. Download the available code as a zip file or cloned SURAGH repository.
3. Include the following external libraries: 
	- univocity-parsers-2.9.1 or its latest version
	- commons-lang3-3.11

   Note: The aforementioned libraries are a part of the project and are available in the same repository. Please update the path for the referenced libraries.
   
 4. Input/Output: It can be executed directly from the command line
	-  Open a command prompt window and go to the directory where you saved the SURAGH project code.
	- `javac SURAGH.java` 
	- `java SURAGH` "arguments"
	
or for IDE, you can set the arguments in the `Run Configuration`. 

The algorithm takes two CSV files as input, (1) input file (2) ground truth. The algorithm outputs two TXT files, (1) includes pattern schema for the input file, (2) contains indices of ill- and well-formed records, and the details of false positives, false negative along with precision, recall, and F-1 scores.
The dataset folder contains input files, while the annotation folder includes the ground truth for the input files. In addition, two sample output files (PatternSchema, Results_indicies) have been attached to the repository for reference. 

- Please follow the following order for the input arguments
	(1) Input file path
	(2) Ground truth file path
	(3) File path for the schema output
	(4) File path for the detailed results




## Global Threshold Setting
(Please refer to the paper for this section) 

We performed experiments using the combinations of row and column thresholds and found the "Global Threshold setting" for the finest result. The pattern schema and the detailed results are obtained using the global threshold setting. To increase or decrease the set of patterns and test different threshold settings, please update the "row_t" and "col_t" variables for row threshold and column threshold, respectively, in the project class named "Main_Class.java".


## Ground Truth File

A two-column CSV file, the first column contains a string, e.g., "ill formed rows indices" or "well formed rows indices".  The second column contains the row indices for the records mentioned above. (Please check annotation folder for reference)


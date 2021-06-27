# SURAGH: Syntactic Pattern Matching to Identify Ill-Formed Records
Code repository of the SURAGH project, developed at the Information Systems Group of the Hasso Plattner Institute.

## Setup

1. The code is written using Java 8.
2. Download the available code as a zip file or cloned SURAGH repository.
3. Include the following external libraries: 
	- univocity-parsers-2.9.1 or its latest version
	- commons-lang3-3.11

   Note: The aforementioned libraries are a part of the project and are available in the same repository. Please update the path for the referenced libraries.
   
 4. Input/Output: It can be executed directly from the command line or for IDE you can set the arguments in the console section. The algorithm takes two CSV files as input, (1) input file (2) ground truth. The algorithm outputs two TXT files, (1) includes pattern schema for the input file (2) contains indices of ill/well-formed records, and the details of false positives, false negative along with precision, recall, and F-1 scores.
The dataset folder contains inout files while annotation section includes the ground truth for the input files. Two sample output files have been attached in the repository for reference. 

Please follow the following order for arguments
	- Input file path
	- Ground truth file path
	- File path for the schema output
	- File path for detailed results



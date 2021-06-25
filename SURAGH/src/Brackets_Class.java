
public class Brackets_Class implements IRepresentation {
	
	private String result; 
	
	public String getResult() {
		return result;
	}

	public void setResult(char input) {
		
		if((int)input == 40 || (int)input == 91 || (int)input == 123 )
			result = "(";
			
		else if((int)input == 93|| (int)input == 41 || (int)input == 125)
			result = ")";
		
	}

	@Override
	public String toString() {
		
	  return "<BRKT>";	
	}
	
	@Override
	public String getRepresentationCharachter(char input) {
		
		String set_value = null;
		if((int)input == 40 || (int)input == 91 || (int)input == 123 )
			set_value = "(";
			
		else if((int)input == 93|| (int)input == 41 || (int)input == 125)
			set_value = ")";
		
		
		return set_value;	
	}

}

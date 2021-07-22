
public class Candidate_Delimiter_Class implements IRepresentation{
	public Candidate_Delimiter_Class()
	{
		
	}

	@Override
	public String toString() {
		return "<DEL>";
	}

	@Override
	public String getRepresentationCharachter(char input) {
		
		return Character.toString(input);
	}
	
}

package abstractions;
public class Candidate_Delimiter_Class implements IAbstraction{
	public Candidate_Delimiter_Class()
	{
		
	}

	@Override
	public String toString() {
		return getRepresentation();
	}

	@Override
	public String getRepresentation() {
		
		return "<DEL>";
	}


}

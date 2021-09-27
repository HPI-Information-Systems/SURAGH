package abstractions;

public class Sequence_Digit_Class implements IAbstraction {

	@Override
	public String toString() {
		return getRepresentation();
	}


	@Override
	public String getRegex() {
		return "[0-9]+";
	}


	@Override
	public String getRepresentation() {
		return "<SEQD>";
	}

	
	
}

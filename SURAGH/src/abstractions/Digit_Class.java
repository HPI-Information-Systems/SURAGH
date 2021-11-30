package abstractions;

public class Digit_Class implements IAbstraction{
	public Digit_Class()
	{
		
	}

	@Override
	public String toString() {
		return getRepresentation();
	}

	@Override
	public String getRepresentation() {
		
		return "<D>";
	}

	@Override
	public String getRegex() {
		
		return "[0-9]";
	}
}

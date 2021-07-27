
public class Delimiter_Class implements IRepresentation  {

	@Override
	public String toString() {
		return "<DEL>";
	}
	
	@Override
	public String getRepresentationCharachter(char input) {
		// TODO Auto-generated method stub
		return Character.toString(input);
	}

}

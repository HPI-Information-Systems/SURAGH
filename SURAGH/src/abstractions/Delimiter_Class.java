package abstractions;
public class Delimiter_Class implements IAbstraction  {

	@Override
	public String toString() {
		return getRepresentation();
	}

	@Override
	public String getRepresentation() {
		
		return "<DEL>";
	}

}

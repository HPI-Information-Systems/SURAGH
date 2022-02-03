package abstractions;

public class Quotation_Class implements IAbstraction{

	

	@Override
	public String toString() {
		return getRepresentation();
	}

	@Override
	public String getRepresentation() {
		// TODO Auto-generated method stub
		return "<\">";
	}
	
}

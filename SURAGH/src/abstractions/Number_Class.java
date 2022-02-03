package abstractions;

public class Number_Class implements IAbstraction {

	@Override
	public String toString() {
		return getRepresentation() ;
	}

	@Override
	public String getRepresentation() {
		// TODO Auto-generated method stub
		return "<NUM>";
	}

}

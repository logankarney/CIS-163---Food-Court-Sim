package backEnd;

public class RegularPerson extends Person {
	public RegularPerson(Person p){
		super(p);
		type = TypeOfPerson.REGULAR;
	}
	
}

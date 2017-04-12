package backEnd;

public class RegularPerson extends Person {
	
	/***
	* Constructor for a RegularPerson
	***/
	public RegularPerson(Person p){
		super(p);
		type = TypeOfPerson.REGULAR;
	}
	
}

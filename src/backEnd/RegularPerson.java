package backEnd;

/******************************************************************************
 * Backend methods of a Person object
 * 
 * @author Logan Karney, Ryan Eisenbarth, Logun DeLeon
 * @version Winter 2017
 *****************************************************************************/
public class RegularPerson extends Person {
	
	/***
	* Constructor for a RegularPerson
	***/
	public RegularPerson(Person p){
		super(p);
		type = TypeOfPerson.REGULAR;
	}
	
}

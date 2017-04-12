package backEnd;

/******************************************************************************
 * Backend methods of a Person object
 * 
 * @author Logan Karney, Ryan Eisenbarth, Logun DeLeon
 * @version Winter 2017
 *****************************************************************************/
public class SpecialNeedsPerson extends Person{
	
	/***
	* Constructor for a SpecialNeedsPerson
	***/
	public SpecialNeedsPerson(Person p){
		super(p);
		
		this.type = TypeOfPerson.SPECIAL_NEEDS;
		setCashierTime((getCashierTime()*2));
		setTickTime((getTickTime()*3));
		setEateryTime(getEateryTime() * 4);

	}
}

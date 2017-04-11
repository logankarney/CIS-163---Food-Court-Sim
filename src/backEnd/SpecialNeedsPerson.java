package backEnd;

public class SpecialNeedsPerson extends Person{
	
	public SpecialNeedsPerson(Person p){
		super(p);
		
		this.type = TypeOfPerson.SPECIAL_NEEDS;
		setCashierTime((getCashierTime()*2));
		setTickTime((getTickTime()*3));
		setEateryTime(getEateryTime() * 4);

	}
}

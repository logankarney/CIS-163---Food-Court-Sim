package backEnd;

public class SpecialNeedsPerson extends Person{
	
	public SpecialNeedsPerson(Person p){
		super(p);
		
		setCashierTime((getCashierTime()*2));
		setTickTime((getTickTime()*3));
		setEateryTime(getEateryTime() * 4);

	}

}
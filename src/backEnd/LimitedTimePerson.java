
public class LimitedTimePerson extends Person{
	public LimitedTimePerson(Person p){
			super(p);
			
			setTickTime((int)(getTickTime() * 0.5));
			setEateryTime(getEateryTime() * .5);
		}
}

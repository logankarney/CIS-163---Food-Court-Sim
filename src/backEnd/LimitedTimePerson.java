package backEnd;

public class LimitedTimePerson extends Person{
		public LimitedTimePerson(){
			super();
			
			setTickTime((int)(getTickTime() * 0.5));
			setEateryTime(getEateryTime() * .5);
		}
	}

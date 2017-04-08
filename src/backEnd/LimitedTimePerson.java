package backEnd;

public class LimitedTimePerson extends Person{

		public LimitedTimePerson(){
			super();
    }
	public LimitedTimePerson(Person p){
			super(p);
			
			setTickTime((int)(getTickTime() * 0.5));
			setEateryTime(getEateryTime() * .5);
		}

	}


package backEnd;

public class LimitedTimePerson extends Person{

		public LimitedTimePerson(){
			super();
			this.type = TypeOfPerson.LIMITED_TIME;
    }
	public LimitedTimePerson(Person p){
			super(p);
			this.type = TypeOfPerson.LIMITED_TIME;
			setTickTime((int)(getTickTime() * 0.5));
			setEateryTime(getEateryTime() * .5);
		}

	}


package backEnd;

/******************************************************************************
 * Backend methods of a Person object
 * 
 * @author Logan Karney, Ryan Eisenbarth, Logun DeLeon
 * @version Winter 2017
 *****************************************************************************/
public class LimitedTimePerson extends Person{
	
		/***
		* Constructor for a LimitedTimePerson
		***/
		public LimitedTimePerson(){
			super();
			this.type = TypeOfPerson.LIMITED_TIME;
    }
	
		/***
		* Constructor for a LimitedTimePerson according to the project guide lines
		***/
	public LimitedTimePerson(Person p){
			super(p);
			this.type = TypeOfPerson.LIMITED_TIME;
			setTickTime((int)(getTickTime() * 0.5));
			setEateryTime(getEateryTime() * .5);
			setQuitTime((int)(getQuitTime() * .8));
		}

	}


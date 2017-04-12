/**
 * 
 */
package backEnd;

import backEnd.Person.TypeOfPerson;

/**
 * @author   Roger Ferguson
 */
public class Eatery implements ClockListener {
	protected CIS163Q<Person> Q = new CIS163Q<Person>();
	
	protected int timeOfNextEvent = 0;
	protected int maxQlength = 0;
	protected Person person;   // this is the person at the Eatery. 
	protected int completed = 0;
	protected int personLeftLine = 0;
	protected boolean isServicing = false;
	protected int eateryTickTime = 0;
	/** The number of regulars who passed through */
	protected int rCount = 0;
	
	/** The number of special needs who passed through */
	protected int snCount = 0;
	
	/** The number of limited timers who passed through */
	protected int ltCount = 0;
	
	/** Total time for regulars passing through */
	protected int rEateryTime = 0;
	
	/** Total time for special needs passing through */
	protected int snEateryTime = 0;
	
	/** Total time for limited timers passing through */
	protected int ltEateryTime = 0;
	
	public void add (Person person)
	{
		Q.enQ(person);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}
	
	public void event (int tick) throws EmptyQException{
		//System.out.println("Eatery Q: " + Q.size());
		if (tick >= timeOfNextEvent) {
			if (person != null) { 			// Notice the delay that takes place here
				if(person.getDestination() != null) {
					person.getDestination().add(person);
				}    // take this person to the next station.
				this.addToAverage(tick - eateryTickTime);
				person = null;				// I have send the person on. 
				isServicing = false;
			}
			
			if (Q.size() >= 1) {
				person = Q.deQ();		// do not send this person as of yet, make them wait. 
				timeOfNextEvent = tick + (int) (person.getBoothTime() + 1);
				isServicing = true;

				if(!person.shouldLeaveLine()) {
					completed++;
					eateryTickTime = tick;
				}
				else {
					person = null;
					personLeftLine++;
					timeOfNextEvent = tick + 1;
				}
			}	
		}
	}
	
	/*********************************************************************************************
	 * Adds the person to the correct average by looking at their enum. Increments the correct count
	 * and adds the time they took to the correct variable.
	 * @param time the amount of time the individual person took.
	 *********************************************************************************************/
	public void addToAverage(int time) {
		if(person.type == TypeOfPerson.REGULAR) {
			this.rCount++;
			this.rEateryTime += time;
		}
		else if(person.type == TypeOfPerson.SPECIAL_NEEDS) {
			this.snCount++;
			this.snEateryTime += time;
		}
		else if(person.type == TypeOfPerson.LIMITED_TIME) {
			this.ltCount++;
			this.ltEateryTime += time;
		}
	}
	
	/*********************************************************************************************
	 * getter for rCount
	 * @return the number of regulars who passed through
	 *********************************************************************************************/
	public int getRCount() {
		return rCount;
	}
	
	/*********************************************************************************************
	 * getter for snCount
	 * @return the number of special needs who passed through
	 *********************************************************************************************/
	public int getSNCount() {
		return snCount;
	}
	
	/*********************************************************************************************
	 * getter for ltCount
	 * @return the number of limited timers who passed through
	 *********************************************************************************************/
	public int getLTCount() {
		return ltCount;
	}
	
	/*********************************************************************************************
	 * getter for rEateryTime
	 * @return the amount of time regulars spent here
	 *********************************************************************************************/
	public int getRTime() {
		return rEateryTime;
	}
	
	/*********************************************************************************************
	 * getter for snEateryTime
	 * @return the amount of time special needs spent here
	 *********************************************************************************************/
	public int getSNTime() {
		return snEateryTime;
	}
	
	/*********************************************************************************************
	 * getter for ltEateryTime
	 * @return the amount of time limited timers spent here
	 *********************************************************************************************/
	public int getLTTime() {
		return ltEateryTime;
	}
	
	public int getLeft() {
		return Q.size();
	}
	
	public int getMaxQlength() {
		return maxQlength;
	}

	public int getThroughPut() {
		return completed;
	}
	
	/**
	* getter for personLeftLine
	**/
	public int getPeopleFailed(){
		return personLeftLine;
	}
	
	/**
	* getter for isServicing
	**/
	public boolean isServicing() {
		return isServicing;
	}
}

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
	protected int rCount = 0;
	protected int snCount = 0;
	protected int ltCount = 0;
	protected int rEateryTime = 0;
	protected int snEateryTime = 0;
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
	
	public int getRCount() {
		return rCount;
	}
	
	public int getSNCount() {
		return snCount;
	}
	
	public int getLTCount() {
		return ltCount;
	}
	
	public int getRTime() {
		return rEateryTime;
	}
	
	public int getSNTime() {
		return snEateryTime;
	}
	
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

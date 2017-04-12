package backEnd;

import backEnd.Person.TypeOfPerson;

public class Cashier extends Eatery{
	
	/** The total time that everyone spent in the food court */
	private int totalTime = 0;
	
	/** The total time that regulars spent in the food court */
	private int rTotTime = 0;
	
	/** The total time that special needs spent in the food court */
	private int snTotTime = 0;
	
	/** The total time that limited timers spent in the food court */
	private int ltTotTime = 0;
	
	/*********************************************************************************************
	 * Adds a person to the cashiers line
	 *********************************************************************************************/
	public void add(Person p) {
		Q.enQ(p);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
		p.setDestination(null);
	}
	
	/*********************************************************************************************
	 * Whenever a person is in its line, it grabs it and checks them out.
	 * Their totals are added to the correct variables.
	 *********************************************************************************************/
	public void event(int tick) throws EmptyQException{
		//System.out.println("Eatery Q: " + Q.size());
		if (tick >= timeOfNextEvent) {
			if (person != null) { 			// Notice the delay that takes place here
				if(person.getDestination() != null) {
					person.getDestination().add(person);
				}   
				totalTime += tick - person.getTickTime();
				
				// adds time to correct category
				if(person.type == TypeOfPerson.REGULAR)
					rTotTime += tick - person.getTickTime();
				else if(person.type == TypeOfPerson.LIMITED_TIME) 
					ltTotTime += tick - person.getTickTime();
				else if(person.type == TypeOfPerson.SPECIAL_NEEDS)
					snTotTime += tick - person.getTickTime();
				addToAverage(tick - eateryTickTime);
				person = null;				// I have send the person on. 
				isServicing = false;        // No longer servicing a customer
			}
			
			if (Q.size() >= 1) {
				person = Q.deQ();		// do not send this person as of yet, make them wait.
				isServicing = true;
				timeOfNextEvent = tick + (int) (person.getCashierTime() + 1);
				eateryTickTime = tick;
				completed++;
			}	
		}
			//System.out.println("Cashier Completed: " + completed);
	}
	
	/*********************************************************************************************
	 * getter for total time
	 * @return the total time that everyone spent in the food court
	 *********************************************************************************************/
	public int getTotTime() {
		return totalTime;
	}
	
	/*********************************************************************************************
	 * getter for rTotTime
	 * @return the total time that regulars spent in the food court
	 *********************************************************************************************/
	public int getRTotTime() {
		return rTotTime;
	}
	
	/*********************************************************************************************
	 * getter for snTotTime
	 * @return the total time special needs spent in the food court
	 *********************************************************************************************/
	public int getSNTotTime() {
		return snTotTime;
	}
	
	/*********************************************************************************************
	 * getter for ltTotTime
	 * @return the total time limited timers spent in the food court
	 *********************************************************************************************/
	public int getLTTotTime() {
		return ltTotTime;
	}
}

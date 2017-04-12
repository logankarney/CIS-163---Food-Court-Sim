/**
 * 
 */
package backEnd;
import java.util.ArrayList;
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
	
	public void add (Person person)
	{
		Q.enQ(person);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}
	
	public void event (int tick) throws EmptyQException{
		System.out.println("Eatery Q: " + Q.size());
		if (tick >= timeOfNextEvent) {
			if (person != null) { 			// Notice the delay that takes place here
				if(person.getDestination() != null) {
					person.getDestination().add(person);
				}    // take this person to the next station. 
				person = null;				// I have send the person on. 
				isServicing = false;
			}
			
			if (Q.size() >= 1) {
				person = Q.deQ();		// do not send this person as of yet, make them wait. 
				timeOfNextEvent = tick + (int) (person.getBoothTime() + 1);
				isServicing = true;

				if(!person.shouldLeaveLine())
					completed++;	
				else
					personLeftLine++;
			}	
		}
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

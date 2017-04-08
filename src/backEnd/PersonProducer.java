/**
 * 
 */
package backEnd;

import java.util.Random;

/**
 * @author Roger Ferguson
 */
public class PersonProducer implements ClockListener {
	
	private int nextPerson = 0;
	private Eatery eatery;
	private int numOfTicksNextPerson;
	private int averageEateryTime;
	
	private Random r = new Random();
	
	public PersonProducer(Eatery eatery, 
			int numOfTicksNextPerson, 
			int averageEateryTime) {
		
		this.eatery = eatery;
		this.numOfTicksNextPerson = numOfTicksNextPerson;
		this.averageEateryTime = averageEateryTime;
		//r.setSeed(13);    // This will cause the same random numbers
	}
	
	public void event(int tick) {
		if (nextPerson <= tick) {
			nextPerson = tick + numOfTicksNextPerson;

			Person person = new Person();

			int rNumber = (int) (Math.random() * 100);

			person.setEateryTime(averageEateryTime * 0.5 * r.nextGaussian() + averageEateryTime + .5);
			person.setTickTime(tick);

			// Determines what type of Person the customer will be
			int rType = (int) (Math.random() * 100);

			Person customer = person;
			if (rType < 70)
				customer = new RegularPerson();
			else if(rType < 90)
				customer = new LimitedTimePerson();
			else
				customer = new RegularPerson();

			eatery.add(customer);

			// person.setDestination(theLocationAfterTheEatery); // You can save
			// off where the person should go.
		}
	}

}

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
	private int cashierTime;

	private int quitTime;

	private Eatery destinationAfter;
	
	private Random r = new Random();
	

	public PersonProducer(Eatery eatery, int numOfTicksNextPerson, int averageEateryTime, int cashierTime, Eatery destinationAfter, int quitTime) {
    
		this.cashierTime = cashierTime;
		this.eatery = eatery;
		this.numOfTicksNextPerson = numOfTicksNextPerson;
		this.averageEateryTime = averageEateryTime;
		this.destinationAfter = destinationAfter;
		this.quitTime = quitTime;

		//r.setSeed(13);    // This will cause the same random numbers
	}
	
	public void event(int tick) {
		if (nextPerson <= tick) {
			nextPerson = tick + numOfTicksNextPerson;

			Person person = new Person();
			
			Random rand;

			int rNumber = rand.nextInt((2 + 1 - 2) - 2);

			person.setEateryTime(averageEateryTime * 0.5 * r.nextGaussian() + averageEateryTime + .5);
			person.setCashierTime(cashierTime + rNumber); 
			
			rNumber = rand.nextInt((5 + 1 - 5) - 5);
			
			person.setQuitTime(quitTime + rNumber);//set leave time random

			person.setTickTime(tick);

			// Determines what type of Person the customer will be
			Random rand = new Random();
			int rType = rand.nextInt(100 + 1);

			Person customer = person;
			if (rType < 70)
				customer = new RegularPerson(person);
			else if(rType < 90)
				customer = new LimitedTimePerson(person);
			else
				customer = new SpecialNeedsPerson(person);

			eatery.add(customer);

			customer.setDestination(destinationAfter); // You can save
			// off where the person should go.
		}
	}

}

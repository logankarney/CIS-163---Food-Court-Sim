/**
 * 
 */
package backEnd;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Roger Ferguson
 */
public class PersonProducer implements ClockListener {
	
	private int nextPerson = 0;
	private ArrayList<Eatery> eateries;
	//private Eatery eatery;
	private int numOfTicksNextPerson;
	private int averageEateryTime;
	private int cashierTime;

	private int quitTime;

	private Eatery destinationAfter;
	
	private Random r = new Random();
	
	public PersonProducer(ArrayList<Eatery> eateries, int numOfTicksNextPerson, int averageEateryTime, int cashierTime, Eatery destinationAfter, int quitTime) {
		this.cashierTime = cashierTime;
		this.eateries = eateries;
		//this.eatery = eatery;
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
			
			Random rand = new Random();

			int rNumber = rand.nextInt(3);
			boolean positive = rand.nextBoolean();
			if(!positive)
				rNumber *= -1;

			person.setEateryTime(averageEateryTime * 0.5 * r.nextGaussian() + averageEateryTime + .5);
			person.setCashierTime(cashierTime + rNumber); 
			
			rNumber = rand.nextInt(6);
			positive = rand.nextBoolean();
			if(!positive)
				rNumber *= -1;
			
			person.setQuitTime(quitTime + rNumber);//set leave time random

			person.setTickTime(tick);

			// Determines what type of Person the customer will be
			int rType = rand.nextInt(100 + 1);

			Person customer = person;
			if (rType < 70)
				customer = new RegularPerson(person);
			else if(rType < 90)
				customer = new LimitedTimePerson(person);
			else
				customer = new SpecialNeedsPerson(person);
			
			this.eateries.get(rand.nextInt(eateries.size())).add(customer);;
			//eatery.add(customer);

			customer.setDestination(destinationAfter); // You can save
			// off where the person should go.
		}
	}

}

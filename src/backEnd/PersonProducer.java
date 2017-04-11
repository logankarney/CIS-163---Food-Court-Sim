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
	private Eatery destinationAfter;
	
	private Random r = new Random();
	
	public PersonProducer(ArrayList<Eatery> eateries, int numOfTicksNextPerson, int averageEateryTime, int cashierTime, Eatery destinationAfter) {
		this.cashierTime = cashierTime;
		this.eateries = eateries;
		//this.eatery = eatery;
		this.numOfTicksNextPerson = numOfTicksNextPerson;
		this.averageEateryTime = averageEateryTime;
		this.destinationAfter = destinationAfter;
		//r.setSeed(13);    // This will cause the same random numbers
	}
	
	public void event(int tick) {
		if (nextPerson <= tick) {
			nextPerson = tick + numOfTicksNextPerson;

			Person person = new Person();

			int rNumber = (int) (Math.random() * 100);

			person.setEateryTime(averageEateryTime * 0.5 * r.nextGaussian() + averageEateryTime + .5);
			person.setCashierTime(cashierTime);
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
			
			this.eateries.get(rand.nextInt(eateries.size())).add(customer);;
			//eatery.add(customer);

			customer.setDestination(destinationAfter); // You can save
			// off where the person should go.
		}
	}

}

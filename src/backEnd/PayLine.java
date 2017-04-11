package backEnd;

import java.util.ArrayList;
import java.util.Random;

public class PayLine extends Eatery {
	private ArrayList<Cashier> checkOut;
	private Random r;
	private int numCheckOut;
	
	public PayLine(ArrayList<Cashier> checkOut) {
		this.checkOut = checkOut;
		r = new Random();
		numCheckOut = checkOut.size();
	}
	
	public void add(Person p) {
		Q.enQ(p);
		//System.out.println("Size is: " + Q.size());
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
		int rNum = r.nextInt(checkOut.size());
		p.setDestination(checkOut.get(rNum));
	}
	
	public void event(int tick) throws EmptyQException{
		System.out.println("Pay Line Q: " + Q.size());
			if (person != null) { 			// Notice the delay that takes place here
				if(!person.getDestination().isServicing()) {
					person.getDestination().add(person);
					person = null;
				}
			}
			if (person == null) {
				if (Q.size() >= 1) {
					person = Q.deQ();		// do not send this person as of yet, make them wait. 
					completed++;
				}
			}
	}
	
	public int getNumCheckOuts() {
		return checkOut.size();
	}
}

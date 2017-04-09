package backEnd;

import java.util.ArrayList;
import java.util.Random;

public class PayLine extends Eatery {
	private ArrayList<Eatery> checkOut;
	private Random r;
	private int numCheckOut;
	
	public PayLine(ArrayList<Eatery> checkOut) {
		this.checkOut = checkOut;
		r = new Random();
		numCheckOut = checkOut.size();
	}
	
	public void add(Person p) {
		Q.enQ(p);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
		int rNum = r.nextInt(numCheckOut);
		p.setDestination(checkOut.get(rNum));
	}
	
	public int getNumCheckOuts() {
		return checkOut.size();
	}
}

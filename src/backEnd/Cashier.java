package backEnd;

public class Cashier extends Eatery{
	
	public void add(Person p) {
		Q.enQ(p);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
		p.setDestination(null);
	}
	
	public void event(int tick) throws EmptyQException{
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
				isServicing = true;
				timeOfNextEvent = tick + (int) (person.getCashierTime() + 1);
				completed++;										
			}	
		}
			System.out.println("Cashier Completed: " + completed);
	}
}

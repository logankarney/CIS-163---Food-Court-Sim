package backEnd;

public class Cashier extends Eatery{
	public void add(Person p) {
		Q.enQ(p);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
		p.setDestination(null);
	}
}

package backEnd;

import java.util.ArrayList;

public class FoodCourtLogic {
	private Clock clk;
	private ArrayList<Eatery> eateries;
	private ArrayList<PersonProducer> producers;
	private Eatery payLine;
	private ArrayList<Eatery> checkOut;
	
	public FoodCourtLogic() {
		clk = new Clock();
		eateries = new ArrayList<Eatery>();
		producers = new ArrayList<PersonProducer>();
		payLine = new Eatery();
		checkOut = new ArrayList<Eatery>();
	}
	
	public void addEatery(Eatery e) {
		eateries.add(e);
		PersonProducer pp = new PersonProducer(e, 20, 18, 18, payLine);
		producers.add(pp);
		clk.add(e);
		clk.add(pp);
	}
	
	
}

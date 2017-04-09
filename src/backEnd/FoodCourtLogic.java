package backEnd;

import java.util.ArrayList;

public class FoodCourtLogic {
	private Clock clk;
	private ArrayList<Eatery> eateries;
	private ArrayList<PersonProducer> producers;
	private PayLine payLine;
	private ArrayList<Eatery> checkOut;
	
	public FoodCourtLogic() {
		clk = new Clock();
		eateries = new ArrayList<Eatery>();
		producers = new ArrayList<PersonProducer>();
		checkOut = new ArrayList<Eatery>();
		payLine = new PayLine(checkOut);
	}
	
	public void addEatery() {
		Eatery e = new Eatery();
		eateries.add(e);
		PersonProducer pp = new PersonProducer(e, 20, 18, 18, payLine);
		producers.add(pp);
		clk.add(e);
		clk.add(pp);
	}
	
	public void addCheckOut() {
		checkOut.add(new Eatery());
	}
	
	public void run() throws EmptyQException {
		clk.run(10000);
	}
	
	public int getThroughput() {
		int throughput = 0;
		for(Eatery e: checkOut) {
			throughput += e.getThroughPut();
		}
		return throughput;
	}
	
	public static void main(String[] args) {
		FoodCourtLogic fcl = new FoodCourtLogic();
		fcl.addCheckOut();
		fcl.addCheckOut();
		fcl.addEatery();
		fcl.addEatery();
		fcl.addEatery();
		try {
			fcl.run();
		}
		catch(EmptyQException e) {
			e.printStackTrace();
		}
		
		System.out.println("Throughput: " + fcl.getThroughput());
	}
}

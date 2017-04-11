package backEnd;

import java.util.ArrayList;

public class FoodCourtLogic {
	private Clock clk;
	public ArrayList<Eatery> eateries;
	private PersonProducer producer;
	//private ArrayList<PersonProducer> producers;
	private PayLine payLine;
	private ArrayList<Cashier> checkOut;
	
	public FoodCourtLogic(int numTicksPerPerson, int averageEateryTime, int averageCashierTime) {
		clk = new Clock();
		eateries = new ArrayList<Eatery>();
		
		checkOut = new ArrayList<Cashier>();
		payLine = new PayLine(checkOut);
		producer = new PersonProducer(eateries, numTicksPerPerson, averageEateryTime, averageCashierTime, payLine);
		//producers = new ArrayList<PersonProducer>();
		clk.add(payLine);
		clk.add(producer);
	}
	
	public void addEatery() {
		Eatery e = new Eatery();
		//PersonProducer pp = new PersonProducer(e, 20, 18, 18, payLine);
		eateries.add(e);
		//clk.add(pp);
		clk.add(e);
	}
	
	public void addCheckOut() {
		Cashier c = new Cashier();
		checkOut.add(c);
		clk.add(c);
	}
	
	public void run(int endTime) throws EmptyQException {
		clk.run(endTime);
	}
	
	public int getThroughput() {
		int throughput = 0;
		for(Eatery e: checkOut) {
			throughput += e.getThroughPut();
		}
		return throughput;
	}
	
	public static void main(String[] args) {
		FoodCourtLogic fcl = new FoodCourtLogic(10, 30, 30);
		fcl.addCheckOut();
		fcl.addCheckOut();
		fcl.addEatery();
		fcl.addEatery();
		try {
			fcl.run(100000);
		}
		catch(EmptyQException e) {
			e.printStackTrace();
		}
		
		System.out.println("Throughput: " + fcl.getThroughput());
		System.out.println("Throughput Pay: " + fcl.payLine.getThroughPut());
		System.out.println("Percent through check out 1: " + (double)fcl.checkOut.get(0).getThroughPut() / fcl.getThroughput());
	}
}

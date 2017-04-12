package backEnd;

import java.util.ArrayList;

public class FoodCourtLogic {
	private Clock clk;
	public ArrayList<Eatery> eateries;
	private PersonProducer producer;
	//private ArrayList<PersonProducer> producers;
	private PayLine payLine;
	private ArrayList<Cashier> checkOut;
	private int quitTime;
	private boolean end;
	private int numTicksPerPerson;
	

	public FoodCourtLogic(int numTicksPerPerson, int averageEateryTime, int averageCashierTime, int quitTime) {
		this.end = true;
		clk = new Clock();
		eateries = new ArrayList<Eatery>();
		this.numTicksPerPerson = numTicksPerPerson;
		this.quitTime = quitTime;
		checkOut = new ArrayList<Cashier>();
		payLine = new PayLine(checkOut);
		producer = new PersonProducer(eateries, numTicksPerPerson, averageEateryTime, averageCashierTime, payLine, quitTime);
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
	public int findMaxEateryLine(){
		int m = 0;

		for(int i = 0; i < eateries.size(); i++){
			if(eateries.get(i).getMaxQlength() > m)
				m = eateries.get(i).getMaxQlength();
		}
		return m;
	}
	public int findPeopleLeft(){
		int m = 0;

		for(int i = 0; i < eateries.size(); i++){
			m += eateries.get(i).getLeft();	
		}
		return m;
	}
	public double averageTime(){
		double m = 0;
		for(int i = 0; i < checkOut.size(); i++){
			m += checkOut.get(i).getTotTime();
		}
		return m / getThroughput();
	}
	public void addCheckOut() {
		Cashier c = new Cashier();
		checkOut.add(c);
		clk.add(c);
	}

	public void run() throws EmptyQException {
		clk.run(quitTime);
		end = false;
	}

	public int getThroughput() {
		int throughput = 0;
		for(Eatery e: checkOut) {
			throughput += e.getThroughPut();
		}
		return throughput;
	}
	
	public int getMaxThrougput() {
		return quitTime / numTicksPerPerson;
	}
	
	public boolean getEnd(){
		return end;
	}
	
	public double getRAvgTime() {
		double m = 0;
		int rCount = 0;
		for(int i = 0; i < checkOut.size(); i++){
			m += checkOut.get(i).getRTotTime();
			rCount += checkOut.get(i).getRCount();
		}
		return m / rCount;
	}
	
	public double getSNAvgTime() {
		double m = 0;
		int snCount = 0;
		for(int i = 0; i < checkOut.size(); i++){
			m += checkOut.get(i).getSNTotTime();
			snCount += checkOut.get(i).getSNCount();
		}
		return m / snCount;
	}
	
	public double getLTAvgTime() {
		double m = 0;
		int ltCount = 0;
		for(int i = 0; i < checkOut.size(); i++){
			m += checkOut.get(i).getLTTotTime();
			ltCount += checkOut.get(i).getLTCount();
		}
		return m / ltCount;
	}
	
	public int getRCount() {
		int m = 0;
		for(int i = 0; i < checkOut.size(); i++) {
			m += checkOut.get(i).getRCount();
		}
		return m;
	}
	
	public int getLTCount() {
		int m = 0;
		for(int i = 0; i < checkOut.size(); i++) {
			m += checkOut.get(i).getLTCount();
		}
		return m;
	}
	
	public int getSNCount() {
		int m = 0;
		for(int i = 0; i < checkOut.size(); i++) {
			m += checkOut.get(i).getSNCount();
		}
		return m;
	}
	
	public double getREateryTime() {
		int m = 0;
		int totTime = 0;
		for(Eatery e: eateries) {
			m += e.getRCount();
			totTime += e.getRTime();
		}
		return (double) totTime / m;
	}
	
	public double getSNEateryTime() {
		int m = 0;
		int totTime = 0;
		for(Eatery e: eateries) {
			m += e.getSNCount();
			totTime += e.getSNTime();
		}
		return (double) totTime / m;
	}
	
	public double getLTEateryTime() {
		int m = 0;
		int totTime = 0;
		for(Eatery e: eateries) {
			m += e.getLTCount();
			totTime += e.getLTTime();
		}
		return (double) totTime / m;
	}
	
	public double getRCashierTime() {
		int m = 0;
		int totTime = 0;
		for(Cashier c: checkOut) {
			m += c.getRCount();
			totTime += c.getRTime();
		}
		return (double) totTime / m;
	}
	
	public double getSNCashierTime() {
		int m = 0;
		int totTime = 0;
		for(Cashier c: checkOut) {
			m += c.getSNCount();
			totTime += c.getSNTime();
		}
		return (double) totTime / m;
	}
	
	public double getLTCashierTime() {
		int m = 0;
		int totTime = 0;
		for(Cashier c: checkOut) {
			m += c.getLTCount();
			totTime += c.getLTTime();
		}
		return (double) totTime / m;
	}
	
	public static void main(String[] args) {
		FoodCourtLogic fcl = new FoodCourtLogic(10, 30, 30, 10000);
		fcl.addCheckOut();
		fcl.addCheckOut();
		fcl.addEatery();
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
		System.out.println("Throughput Pay: " + fcl.payLine.getThroughPut());
		System.out.println("Percent through check out 1: " + (double)fcl.checkOut.get(0).getThroughPut() / fcl.getThroughput());
	}
}

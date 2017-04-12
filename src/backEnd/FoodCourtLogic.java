package backEnd;

import java.util.ArrayList;

/************************************************************************
 * The main logic connecting all components of the Food Court simulation.
 * @author Ryan Eisenbarth, Logan Karney, Logun DeLeon
 * @version 4/12/2017
 ***********************************************************************/
public class FoodCourtLogic {
	/** The main clock that 'ticks' to signify time passing */
	private Clock clk;
	
	/** Every eatery involved in the food court */
	public ArrayList<Eatery> eateries;
	
	/** The object making Persons and adding them to eateries */
	private PersonProducer producer;
	
	/** The line that people must wait in before being checked out */
	private PayLine payLine;
	
	/** The check out counters where people pay */
	private ArrayList<Cashier> checkOut;
	
	/** The last tick on the clock before it stops */
	private int quitTime;
	
	/** Is the clock still running? */
	private boolean end;
	
	/*********************************************************************************************
	 * The constructor sets up each instance variable and adds the clock listeners to the clock.
	 * @param numTicksPerPerson how many ticks before another Person comes in.
	 * @param averageEateryTime the average time spent at the eatery.
	 * @param averageCashierTime the average time spent at the cashier.
	 * @param quitTime the last tick before the clock stops.
	 *********************************************************************************************/
	public FoodCourtLogic(int numTicksPerPerson, int averageEateryTime, int averageCashierTime, int quitTime) {
		this.end = true;
		clk = new Clock();
		
		eateries = new ArrayList<Eatery>();
		this.quitTime = quitTime;
		checkOut = new ArrayList<Cashier>();
		payLine = new PayLine(checkOut);
		producer = new PersonProducer(eateries, numTicksPerPerson, averageEateryTime, averageCashierTime, payLine, quitTime);
		
		clk.add(payLine);
		clk.add(producer);
	}
	
	/*********************************************************************************************
	 * Adds a new eatery to the Food Court. Also adds the eatery to the clock.
	 *********************************************************************************************/
	public void addEatery() {
		Eatery e = new Eatery();
		eateries.add(e);
		clk.add(e);
	}
	
	/*********************************************************************************************
	 * Finds the maximum line achieved by an Eatery in the simulation.
	 * @return the maximum line acheived by an Eatery.
	 *********************************************************************************************/
	public int findMaxEateryLine(){
		int m = 0;
		
		// iterate through each eatery's max line.
		// find the biggest.
		for(int i = 0; i < eateries.size(); i++){
			if(eateries.get(i).getMaxQlength() > m)
				m = eateries.get(i).getMaxQlength();
		}
		return m;
	}
	
	/*********************************************************************************************
	 * Returns how many people are still in a Queue.
	 * @return the number of people still in a Queue.
	 *********************************************************************************************/
	public int findPeopleLeft(){
		int m = 0;
		
		// add the Q sizes of each eatery together.
		for(int i = 0; i < eateries.size(); i++){
			m += eateries.get(i).getLeft();	
		}
		// add the Q size of the payLine.
		m += payLine.getLeft();
		return m;
	}
	
	/*********************************************************************************************
	 * Returns the average time it took to get through the food court for everyone.
	 * @return the average time it took for everyone to get through.
	 *********************************************************************************************/
	public double averageTime(){
		double m = 0;
		for(int i = 0; i < checkOut.size(); i++){
			m += checkOut.get(i).getTotTime();
		}
		return m / getThroughput();
	}
	
	/*********************************************************************************************
	 * Adds a check out area to the Food Court.
	 *********************************************************************************************/
	public void addCheckOut() {
		Cashier c = new Cashier();
		checkOut.add(c);
		clk.add(c);
	}

	/*********************************************************************************************
	 * Starts the clock and begins the simulation.
	 * @throws EmptyQException whenever a method tries to deQ an empty Q.
	 *********************************************************************************************/
	public void run() throws EmptyQException {
		clk.run(quitTime);
		end = false;
	}
	
	/*********************************************************************************************
	 * Returns the total amount of people who got through the Food Court.
	 * @return the amount of people who got through the food court.
	 *********************************************************************************************/
	public int getThroughput() {
		int throughput = 0;
		for(Eatery e: checkOut) {
			throughput += e.getThroughPut();
		}
		return throughput;
	}
	
	/*********************************************************************************************
	 * Returns whether the clock is running or not.
	 * @return true whenever the clock is running and false when it isn't.
	 *********************************************************************************************/
	public boolean getEnd(){
		return end;
	}
	
	/*********************************************************************************************
	 * Calculates and returns the average time for RegularPersons to get through the food court.
	 * @return the average time for a regular person to get through the food court.
	 *********************************************************************************************/
	public double getRAvgTime() {
		double m = 0;
		int rCount = 0;
		for(int i = 0; i < checkOut.size(); i++){
			m += checkOut.get(i).getRTotTime();
			rCount += checkOut.get(i).getRCount();
		}
		return m / rCount;
	}
	
	/*********************************************************************************************
	 * Calculates and returns the average time for SpecialNeedsPersons to get through the food court.
	 * @return the average time for a special needs person to get through the food court.
	 *********************************************************************************************/
	public double getSNAvgTime() {
		double m = 0;
		int snCount = 0;
		for(int i = 0; i < checkOut.size(); i++){
			m += checkOut.get(i).getSNTotTime();
			snCount += checkOut.get(i).getSNCount();
		}
		return m / snCount;
	}
	
	/*********************************************************************************************
	 * Calculates and returns the average time for a LimitedTimePerson to get through the food court.
	 * @return the average time for a limited time person to get through the food court.
	 *********************************************************************************************/
	public double getLTAvgTime() {
		double m = 0;
		int ltCount = 0;
		for(int i = 0; i < checkOut.size(); i++){
			m += checkOut.get(i).getLTTotTime();
			ltCount += checkOut.get(i).getLTCount();
		}
		return m / ltCount;
	}
	
	/*********************************************************************************************
	 * Returns the amount of regular people who got through the food court.
	 * @return the amount of regular people who got through the food court.
	 *********************************************************************************************/
	public int getRCount() {
		int m = 0;
		for(int i = 0; i < checkOut.size(); i++) {
			m += checkOut.get(i).getRCount();
		}
		return m;
	}
	
	/*********************************************************************************************
	 * Returns the amount of limited time people who got through the food court.
	 * @return the amount of limited time people who got through the food court.
	 *********************************************************************************************/
	public int getLTCount() {
		int m = 0;
		for(int i = 0; i < checkOut.size(); i++) {
			m += checkOut.get(i).getLTCount();
		}
		return m;
	}
	
	/*********************************************************************************************
	 * Returns the amount of special needs people who got through the food court.
	 * @return the amount of special needs people who got through the food court.
	 *********************************************************************************************/
	public int getSNCount() {
		int m = 0;
		for(int i = 0; i < checkOut.size(); i++) {
			m += checkOut.get(i).getSNCount();
		}
		return m;
	}
	
	/*********************************************************************************************
	 * Returns the average time for a regular person to get through an eatery
	 * @return the average time for a regular person to get through an eatery.
	 *********************************************************************************************/
	public double getREateryTime() {
		int m = 0;
		int totTime = 0;
		for(Eatery e: eateries) {
			m += e.getRCount();
			totTime += e.getRTime();
		}
		return (double) totTime / m;
	}
	
	/*********************************************************************************************
	 * Returns the average time for a special needs person to get through an eatery
	 * @return the average time for a special needs person to get through an eatery
	 *********************************************************************************************/
	public double getSNEateryTime() {
		int m = 0;
		int totTime = 0;
		for(Eatery e: eateries) {
			m += e.getSNCount();
			totTime += e.getSNTime();
		}
		return (double) totTime / m;
	}
	
	/*********************************************************************************************
	 * Returns the average time for a time limited person to get through an eatery
	 * @return the average time for a time limited person to get through an eatery.
	 *********************************************************************************************/
	public double getLTEateryTime() {
		int m = 0;
		int totTime = 0;
		for(Eatery e: eateries) {
			m += e.getLTCount();
			totTime += e.getLTTime();
		}
		return (double) totTime / m;
	}
	
	/*********************************************************************************************
	 * Returns the average time for a regular person to get through a checkout.
	 * @return the average time for a regular person to get through a checkout.
	 *********************************************************************************************/
	public double getRCashierTime() {
		int m = 0;
		int totTime = 0;
		for(Cashier c: checkOut) {
			m += c.getRCount();
			totTime += c.getRTime();
		}
		return (double) totTime / m;
	}
	
	/*********************************************************************************************
	 * Returns the average time for a special needs person to get through a checkout.
	 * @return the average time for a special needs person to get through a checkout.
	 *********************************************************************************************/
	public double getSNCashierTime() {
		int m = 0;
		int totTime = 0;
		for(Cashier c: checkOut) {
			m += c.getSNCount();
			totTime += c.getSNTime();
		}
		return (double) totTime / m;
	}
	
	/*********************************************************************************************
	 * Returns the average time for a limited time person to get through a checkout.
	 * @return the average time for a limited time person to get through a checkout.
	 **********************************************************************************************/
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

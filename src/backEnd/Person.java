/**
 * 
 */
package backEnd;

/**
 * @author   Roger Ferguson
 */
public class Person implements ClockListener{
	
	private enum TypeOfPerson {
		LIMITED_TIME, SPECIAL_NEEDS, REGULAR
	}
	
	private TypeOfPerson type;
	private int tickTime;
	private Eatery Destination;
	
	// max time person stays in line
	protected double boothTime;
	private int cashierTime;
	
	//the amount of time a person has spent in the simulation
	private int personalTime;
	
	private TypeOfPerson type;
	
	private boolean queueed;
	
	public Person(){
		personalTime = 0;
	}
	
	public Person(Person p){
		this.tickTime = p.tickTime;
		this.Destination = p.Destination;
		this.boothTime = p.boothTime;
		this.cashierTime = p.cashierTime;
		personalTime = 0;
	}
	
	public double getBoothTime() {
		return boothTime;
	}
	
	public Eatery getDestination() {
		return Destination;
	}

	public void setDestination(Eatery destination) {
		Destination = destination;
	}
	
	public int getTickTime(){
		return this.tickTime;
	}

	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}
		
	public double getEateryTime(){
		return this.boothTime;
	}

	public void setEateryTime(double time) {
		this.boothTime = time;
	}
	
	public int getCashierTime(){
		return this.cashierTime;
	}
	
	public void setCashierTime(int cashierTime){
		this.cashierTime = cashierTime;
	}
	
	public int getPersonalTime(){
		return personalTime;
	}
	
	@Override
	public void event(int tick) {
		personalTime++;
	}
	
	public void setQueue(boolean queueed){
		this.queueed = queueed;
	}
	
	public boolean getQueue(){
		return this.queueed;
	}
}

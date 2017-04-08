/**
 * 
 */
package backEnd;

/**
 * @author   Roger Ferguson
 */
public class Person {
	
	private enum TypeOfPerson {
		LIMITED_TIME, SPECIAL_NEEDS, REGULAR
	}
	
	private TypeOfPerson type;
	private int tickTime;
	private Eatery Destination;
	
	// max time person stays in line
	protected double boothTime;
	
	public double getBoothTime() {
		return boothTime;
	}
	
	public Eatery getDestination() {
		return Destination;
	}

	public void setDestination(Eatery destination) {
		Destination = destination;
	}
	
	public int getTickTime() {
		return tickTime;
	}
	
	public int getTickTime(){
		return this.tickTime;
	}

	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}
		
	public int getEateryTime(){
		return this.boothTime;
	}

	public void setEateryTime(double time) {
		this.boothTime = time;
	}
}

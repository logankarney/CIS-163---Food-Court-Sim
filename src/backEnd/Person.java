/**
 * 
 */
package backEnd;

/**
 * @author   Roger Ferguson
 */
public class Person implements ClockListener{

  /** Enum that describes what type of person the object will be**/
	public enum TypeOfPerson {
		LIMITED_TIME, SPECIAL_NEEDS, REGULAR
	}
	
  /** instance variable of the object's enum type **/
	protected TypeOfPerson type;

  
  /** objects clock time **/
	private int tickTime;
	
	/** the person's destination **/
	private Eatery Destination;
	
	/** max time person stays in line **/
	protected double boothTime;
	
	/** time a person spends in the cashier line **/
	private int cashierTime;
	
	/** the amount of time a person has spent in the simulation **/
	private int personalTime;
	
	/** how many ticks till a person gets fed up and leaves **/
	private int quitTime;
	

	/***
	* Creates a empty Person object
	*
  **/
	public Person(){
		personalTime = 0;
		quitTime = 0;
	}
	
	/***
	* Creates a empty Person object off of the parameters of another Person object
	*
	* @param p
	*	Person whose instance variables are borrowed from
	***/
	public Person(Person p){
		this.tickTime = p.tickTime;
		this.Destination = p.Destination;
		this.boothTime = p.boothTime;
		this.cashierTime = p.cashierTime;
		this.personalTime = 0;
		this.quitTime = p.quitTime;
	}
	
	/**
	* getter for boothTime
	**/
	public double getBoothTime() {
		return boothTime;
	}
	
	/**
	* getter for Destination
	**/
	public Eatery getDestination() {
		return Destination;
	}
	
	/**
	* setter for Destination
	* @param destination
	**/
	public void setDestination(Eatery destination) {
		Destination = destination;
	}
	
	/**
	* getter for tickTime
	**/
	public int getTickTime(){
		return this.tickTime;
	}
	
	/**
	* setter for tickTime
	* @param tickTime
	**/
	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}
	
	/**
	* getter for boothTime
	**/
	public double getEateryTime(){
		return this.boothTime;
	}

	/**
	* setter for boothTime
	* @param time
	**/
	public void setEateryTime(double time) {
		this.boothTime = time;
	}
	
	/**
	* getter for cashierTime
	**/
	public int getCashierTime(){
		return this.cashierTime;
	}
	
	/**
	* setter for cashierTime
	* @param cashierTime
	**/
	public void setCashierTime(int cashierTime){
		this.cashierTime = cashierTime;
	}
	
	/**
	* getter for personalTime
	**/
	public int getPersonalTime(){
		return personalTime;
	}
	
	/**
	* getter for quitTime
	**/
	public int getQuitTime(){
		return this.quitTime;
	}
	
	/**
	* setter for quitTime
	* @param quitTime
	**/
	public void setQuitTime(int quitTime){
		this.quitTime = quitTime;
	}
	
	/**
	* increments personalTime
	* @param tick
	**/
	@Override
	public void event(int tick) {
		personalTime++;
	}
	
	/**
	* determines if the person should leave the line
	**/
	public boolean shouldLeaveLine(){
		if(quitTime < personalTime) {
			
			//resets the variables if the person leaves
			this.boothTime = 0;
			this.cashierTime = 0;
			this.personalTime = 0;
			return true;
		}
		return false;
	}
}

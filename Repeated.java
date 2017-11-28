
/**
 * class to store necessary information for a repeated chore
 * @author Sam Rennie 
 * SN: 8881891
 * Email: srenn096@uottawa.ca
 *
 */

public class Repeated {
	
	//defines when the chore is to be repeated
	public enum repeatType{DAILY,WEEKLY,MONTHLY};
	
	//instance variables
	private int numRepeats;
	private repeatType repeatStatus;
	
	//constructor
	public Repeated(int repeat) {
		this.numRepeats=repeat;
	}
	
	//getters and setters
	public void setStatus(repeatType status) {
		this.repeatStatus = status;
	}
	
	public void setRepeats(int repeat) {
		this.numRepeats=repeat;
	}
	
	public int getRepeats() {
		return this.numRepeats;
	}
	
	public repeatType getType() {
		return this.repeatStatus;
	}
}
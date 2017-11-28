/**
 * Chore class containing all the information about a created chore
 * @author Sam Rennie
 * SN: 8881891
 * email: 8881891
 */
import java.util.Date;

public class Chore {
	
	//status' the chore can hold
	private enum status {COMPLETE,INCOMPLETE,PARTIALLY_COMPLETE,UNASSIGNED,ACTIVE,LATE_COMPLETION};

	
	//Instance variables
	private String name;
	private String description;
	private String notes;
	private int rewardPoints;
	private Repeated repeat;
	private status completionStatus;
	private Date deadline;
	private User assignedTo;
	private ChoreManagerProfile main;
	
	//constructor 1 - user not specified during chore creation, is repeated
	public Chore(String name, String description, String note, int pointsWorth, Repeated isRepeated, Date deadline, ChoreManagerProfile main) {
		this.name=name;
		this.main=main;
		this.description=description;
		this.notes=note;
		this.rewardPoints=pointsWorth;
		this.repeat=isRepeated;
		this.assignedTo=null;
		this.deadline=deadline;
		this.completionStatus= status.ACTIVE;
	}
	
	//constructor 2 - user specified
	public Chore(String name, String description, String note, int pointsWorth, Repeated isRepeated, Date deadline, User assigned) {
		this.name=name;
		this.description=description;
		this.notes=note;
		this.rewardPoints=pointsWorth;
		this.repeat=isRepeated;
		this.assignedTo=assigned;
		this.deadline=deadline;
		this.completionStatus= status.ACTIVE;
	}

	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public Repeated getRepeat() {
		return repeat;
	}

	public void setRepeat(Repeated repeat) {
		this.repeat = repeat;
	}

	public status getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(status completionStatus) {
		this.completionStatus = completionStatus;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	//rewards full points if the chore is completed before the deadline and 
		//half points if completed afterwards
		public int calcRewardPoints() {
			int points=0;
			int possible = this.getRewardPoints();
			Date toCompare = this.getDeadline();
			if(toCompare.before(main.getDate())) {
				points = possible;
			}
			else if(toCompare.after(main.getDate())) {
				points = possible/2;
			}
			return points;
		}

}

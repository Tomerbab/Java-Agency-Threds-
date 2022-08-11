
public class Task {

	private int id;
	private String victimName;
	private String level;
	private int cType;
	private int callTime;
	private Queue <Task> tasks; 

	public Task (int id, String victimName, String level, int cType, int callTime) { // task constructor 
		this.id=id;
		this.victimName=victimName;
		this.level=level;
		this.cType=cType;
		this.callTime=callTime;
	}

	public void setTasks(Queue <Task> tasks) {
		this.tasks = tasks;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setVictimName(String victimName) {
		this.victimName = victimName;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public void setcType(int cType) {
		this.cType = cType;
	}
	
	public void setCallTime(int callTime) {
		this.callTime = callTime;
	}
	
	public int getId() {
		return id;
	}

	public String getVictimName() {
		return victimName;
	}

	public Queue <Task> getTasks() {
		return tasks;
	}	

	public String getLevel() {
		return level;
	}

	public int getcType() {
		return cType;
	}

	public int getCallTime() {
		return callTime;
	}

	public String toString () {
		return "id: " +id+ ", victimName: "+victimName+", level: "+level+ ", cType: "+cType+", callTime: " +callTime+ "\n";
	}
}
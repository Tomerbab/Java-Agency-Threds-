import java.util.Vector;

public class Operation {

	private int id;
	private String name;
	private int level;
	private int cType;
	private long opTime;
	private Vector <Vehicle> vehiclesList;
	private Vector <Agent> agentsList;
	
	public Operation(int id,String name,int level,int cType,long opTime) { // operation constructor
		this.id=id;
		this.name=name;
		this.level=level;
		this.cType=cType;
		this.opTime=opTime;
		this.agentsList = new Vector <Agent>();
		this.vehiclesList = new Vector <Vehicle>();
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setcType(int cType) {
		this.cType = cType;
	}
	
	public void setOpTime(int opTime) {
		this.opTime = opTime;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getcType() {
		return cType;
	}

	public long getOpTime() {
		return opTime;
	}

	public Vector<Vehicle> getVehicles(){
		return this.vehiclesList;
	}
	
	public Vector<Agent> getAgents(){
		return this.agentsList;
	}
	
	public String toString () {
		return "id: " +id+ ", name: "+name+", level: "+level+ ", cType: "+cType+", opTime: " +opTime+ "\n";
	}
}

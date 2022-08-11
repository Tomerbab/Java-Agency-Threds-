
public class Call extends Thread{

	private String victimName;
	private String level;
	private String cType;
	private int eta; // estimated time of arrival
	private int handlingTime;
	private Queue <Call> callsList; 
	private boolean done;

	public Call(String victimName,String level,String cType,int eta, int handlingTime,Queue <Call> callsList) { // change the Queue to static // call constructor
		this.victimName= victimName;
		this.level=level;
		this.cType=cType;
		this.eta=eta;
		this.handlingTime=handlingTime;
		this.done=false;
		this.callsList=callsList;
	}
	
	public void run() {
		try {
			Thread.sleep(this.eta*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		callsList.insert(this);//insert the call into the queue
		waitingRoom(); 
	}
	
	public void setCalls(Queue <Call> calls) {
		this.callsList = calls;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setcType(String cType) {
		this.cType = cType;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public void setHandlingTime(int handlingTime) {
		this.handlingTime = handlingTime;
	}

	public void setVictimName(String callername) {
		this.victimName = callername;
	}

	public synchronized void setFlagTrue() { // means call is ended
		this.done = true;
		this.notify();
	}

	public Queue <Call> getCalls() {
		return callsList;
	}

	public String getLevel() {
		return level;
	}

	public String getcType() {
		return cType;
	}

	public int getEta() {
		return eta;
	}

	public int getHandlingTime() {
		return handlingTime;
	}

	public String getVictimName() {
		return victimName;
	}

	public String toString () {
		return "victimName: " +victimName+ ", level: "+level+", cType: "+cType+ ", eta: "+eta+", handlingTime: " +handlingTime+ "\n";
	}

	public boolean isFlag() {
		return done;
	}

	public synchronized void waitingRoom() { // waiting until the handling time ends
		while(!done)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}

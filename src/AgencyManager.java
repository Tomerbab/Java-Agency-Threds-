
public class AgencyManager extends Thread {

	private String mName;
	private int numOfOps;// from GUI
	private int opCounter; 
	private double totalOpTime; 
	private Queue<Task> tasks;  
	private BoundedQueue <Operation> operations; 
	private InformationSystem strategies;

	public AgencyManager(String mName,Queue<Call> calls,Queue<Task> tasks,BoundedQueue <Operation> operations,InformationSystem strategies,int numOfOperations) { // agency manager constructor
		this.mName=mName;
		this.opCounter = 0;
		this.totalOpTime = 0;
		this.tasks=tasks;
		this.operations=operations;
		this.strategies=strategies;
		this.numOfOps = numOfOperations;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmName() {
		return this.mName;
	}
	public void run() {
		while (!dayFinshed()) {
			waitManager();
		}
		endOfDay();
		System.out.println("The number of operations is: " + opCounter + "\n" + "The total time of all the operations is: " + this.totalOpTime);
	}

	private synchronized void waitManager() {
		try {
			this.wait(1);
		} 
		catch(InterruptedException e) {
		}
	}
	
	public synchronized boolean dayFinshed(){ // checking if should finish the day
		if (opCounter==this.numOfOps) {
			return true;
		}
		return false;
	}

	public synchronized void endOp(double opTime, Operation o) { // updating when operation finished 
		opCounter++;
		this.totalOpTime = totalOpTime + opTime;		
	}

	private void endOfDay() { // "killing" the threads
		this.tasks.insert(null);
		this.strategies.setDayIsOver();
		this.operations.insert(null);
	}
}
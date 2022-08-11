import java.util.Vector;

public class Secretary extends Thread {

	private String sName;
	private Queue <Call> callsList;
	private Queue <Task> tasksList;
	private boolean noMoreCalls;
	private Vector <Call> calls;

	public Secretary(String sName,Queue <Call> callsList,Queue <Task> tasksList,Vector <Call> calls) { // secretary constructor
		this.sName=sName;
		this.callsList=callsList;
		this.tasksList=tasksList;
		this.noMoreCalls = false;
		this.calls = calls;
	}
	
	public void run() {
		while(!noMoreCalls) { // runs until there are no more calls
			Call c = callsList.extract();
			if(c==null) { // means no more calls
				this.noMoreCalls=true;
				callsList.insert(c);
				continue;
			}
			int cType =this.cTypeClassify(c.getcType());
			try {
				long l;
				if(cType<3)
					l = (long) Math. floor(Math. random()*(1000-500+1)+500)*cType; // make sure okay
				else
					l = (long) Math. floor(Math. random()*(3000-2000+1)+2000);
				Thread.sleep((c.getHandlingTime()*1000+l));
				Task t = new Task(calls.indexOf(c)+1,c.getVictimName(),c.getLevel(),cType,c.getEta()); // find a way to count
				tasksList.insert(t);
				calls.remove(c);
				if(calls.size()==0) { // means no more calls
					this.noMoreCalls=true;
					callsList.insert(null);
					continue;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.setFlagTrue();
		}
	}
	
	public void setSName(String sName) {
		this.sName = sName;
	}
	
	public String getSName() {
		return sName;
	}
	
	private int cTypeClassify(String s) { //converts the costumer type (string) into an integer variable 
		int cType = 0;
		if(s.equals("private"))
			cType = 1;
		if(s.equals("business"))
			cType = 2;
		if(s.equals("government"))
			cType = 3;
		return cType;
	}

	public Queue <Call> getCalls() {
		return callsList;
	}

	public void setCalls(Queue <Call> calls) {
		this.callsList = calls;
	}
	
	public String toString () {
		return "Name: " +sName+ "\n";
	}
}

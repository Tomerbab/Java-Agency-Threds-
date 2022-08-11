import java.util.Vector;

public class Executive extends Thread {

	private String eName;
	private BoundedQueue <Operation> operations; 
	private boolean dayIsOver;
	Vector <Operator> operList;


	public Executive(String eName, BoundedQueue <Operation> operations,Vector <Operator> operList) { // executive constructor
		this.eName=eName;
		this.operations=operations;
		this.operList=operList;
		this.dayIsOver=false;
	}

	public void run() { // threads run 
		while(!dayIsOver) {
			Operation o = operations.extract();
			if(o==null) { // means day is over
				this.dayIsOver=true; 
				operations.insert(o);
				continue;
			}
			double l = (o.getAgents().size()+o.getVehicles().size()+(Math.random()*6+2))*1000; // try different solutions
			try {
				Thread.sleep((long) (l));

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			BackToAvailability(o);
			for(int i=0;i<this.operList.size();i++)
				this.operList.elementAt(i).setcanInitializeTrue();
			Agency.AM.endOp(l,o);
		}
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public boolean getDayIsOver() {
		return dayIsOver;
	}

	public String geteName() {
		return eName;
	}

	private void BackToAvailability(Operation o) { // returns the vehicles and agents to availability 
		for(int i=0;i<o.getVehicles().size();i++) {
			o.getVehicles().elementAt(i).setIsAvailable();
		}
		for(int i=0;i<o.getAgents().size();i++) {
			o.getAgents().elementAt(i).setIsAvailable();
		}
	}

	public String toString () {
		return "Name: " + eName + "\n";
	}
}
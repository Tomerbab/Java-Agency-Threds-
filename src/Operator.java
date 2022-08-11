public class Operator extends Thread {

	private String oName;
	private boolean dayIsOver;
	private InformationSystem strategies;
	private BoundedQueue <Operation> operations; 
	private boolean canInitialize;
	private double timeOfWork;

	public Operator(String oName,InformationSystem strategies,BoundedQueue <Operation> operations,double timeOfWork) { // operator constructor
		this.oName=oName;
		this.dayIsOver  = false;
		this.strategies=strategies;
		this.operations=operations;
		this.timeOfWork=timeOfWork;
	}
	
	public void run() { // threads run
		while(!dayIsOver) {
			Strategy s = strategies.extract();
			if(s==null) { // means day is over
				this.dayIsOver=true;
				continue;
			}
			Operation o = new Operation(s.getId(),s.getName(),s.getLevel(),s.getcType(),s.getOpTime());
			while(!initializeOp(o)) { // if cant execute an operation , wait
				waitingRoom(); 
			} 
			operations.insert(o);
			try {
				Thread.sleep((long)timeOfWork);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.canInitialize=false;
		}
	}
	
	public synchronized void setcanInitializeTrue() { // means executive return from a operation
		this.canInitialize = true;
		this.notify();
	}
	
	public void setOperations(BoundedQueue <Operation> operations) {
		this.operations = operations;
	}
	
	public void setoName(String oName) {
		this.oName = oName;
	}
	
	public boolean getDayIsOver() {
		return dayIsOver;
	}
	
	public BoundedQueue <Operation> getOperations() {
		return operations;
	}
	
	public String getoName() {
		return oName;
	}

	private static void motorcycleInsert(Operation o, int required) { // motorcycle insert
		int i=0;
		while(required > 0) {
			if(Agency.motorcyclesList.elementAt(i).getIsAvailable()) {
				o.getVehicles().add(Agency.motorcyclesList.elementAt(i));
				Agency.motorcyclesList.elementAt(i).setUnAvailable();
				i++;
				required--;
			} else 
				i++;
		}
	}

	private static void carInsert(Operation o, int required) { // car insert
		int i=0;
		while(required > 0) {
			if(Agency.carsList.elementAt(i).getIsAvailable()) {
				o.getVehicles().add(Agency.carsList.elementAt(i));
				Agency.carsList.elementAt(i).setUnAvailable();
				i++;
				required--;
			} else 
				i++;
		}
	}

	private static void investigatorInsert(Operation o, int required) { //investigator insert
		int i=0;
		while(required > 0) {
			if(Agency.investigatorsList.elementAt(i).getIsAvailable()) {
				o.getAgents().add(Agency.investigatorsList.elementAt(i));
				Agency.investigatorsList.elementAt(i).setUnAvailable();
				i++;
				required--;
			} else
				i++;
		}
	}

	private static void detectiveInsert(Operation o, int required) { // detective insert
		int i=0;
		while(required > 0) {
			if(Agency.detectivesList.elementAt(i).getIsAvailable()) {
				o.getAgents().add(Agency.detectivesList.elementAt(i));
				Agency.detectivesList.elementAt(i).setUnAvailable();
				i++;
				required--;
			} else
				i++;
		}
	}

	private static int availableCars() { // counts the number of available cars 
		int counter =0;
		for(int i=0;i<Agency.carsList.size();i++) {
			if(Agency.carsList.elementAt(i).getIsAvailable())
				counter++;
		}
		return counter;
	}

	private static int availableMotorcycles() { // counts the number of available motorcycles
		int counter =0;
		for(int i=0;i<Agency.motorcyclesList.size();i++) {
			if(Agency.motorcyclesList.elementAt(i).getIsAvailable())
				counter++;
		}
		return counter;
	}
	
	private static int availableDetectives() { // counts the number of available Detectives
		int counter =0;
		for(int i=0;i<Agency.detectivesList.size();i++) {
			if(Agency.detectivesList.elementAt(i).getIsAvailable())
				counter++;
		}
		return counter;
	}
	private static int availableInvestigators() { // counts the number of available Investigators
		int counter =0;
		for(int i=0;i<Agency.investigatorsList.size();i++) {
			if(Agency.investigatorsList.elementAt(i).getIsAvailable())
				counter++;
		}
		return counter;
	}

	private static synchronized boolean initializeOp(Operation o) { // inserts vehicles and agents to the operation, if possible
		int vacantCars = availableCars(); 
		int vacantMotors = availableMotorcycles();
		int vacantDetec = availableDetectives();
		int vacantInvest = availableInvestigators();
		if(vacantCars == 0 && vacantMotors == 0)
			return false;
		if(o.getLevel() == 1) {
			if(vacantInvest > 1) {
				if(vacantMotors > 1) {
					investigatorInsert(o,2);
					motorcycleInsert(o,2);
					return true;
				}
				else if(vacantCars > 0 && vacantMotors==0) {
					investigatorInsert(o,2);
					carInsert(o,1);
					return true;
				} else
					return false;
			} 
		}
		if(o.getLevel() == 2) {
			if(vacantInvest > 2 && vacantDetec > 1) {
				if(vacantMotors > 3) {
					investigatorInsert(o,3);
					detectiveInsert(o,2);
					motorcycleInsert(o,4);
					return true;
				}
				else if(vacantCars > 0 && vacantMotors==0) {
					investigatorInsert(o,3);
					detectiveInsert(o,2);
					carInsert(o,1);
					return true;
				}
				else
					return false;
			} 
		}
		if(o.getLevel() == 3) {
			if(vacantInvest > 0 && vacantDetec > 4) {
				if(vacantCars > 0 && vacantMotors > 0) {
					investigatorInsert(o,1);
					detectiveInsert(o,5);
					carInsert(o,1);
					motorcycleInsert(o,1);
					return true;
				}
				else if (vacantCars > 1 && vacantMotors == 0) {
					investigatorInsert(o,1);
					detectiveInsert(o,5);
					carInsert(o,2);
					return true;
				}
				else if (vacantCars==0 && vacantMotors > 5) {
					investigatorInsert(o,1);
					detectiveInsert(o,5);
					motorcycleInsert(o,6);
					return true;
				}
				else
					return false;
			} 
		}
		if(o.getLevel() == 4) {
			if(vacantInvest > 3 && vacantDetec > 5) {
				if(vacantCars > 2) {
					investigatorInsert(o,4);
					detectiveInsert(o,6);
					carInsert(o,2);
					return true;
				}
				else if (vacantCars == 1 && vacantMotors > 4) {
					investigatorInsert(o,4);
					detectiveInsert(o,6);
					carInsert(o,1);
					motorcycleInsert(o,5);
					return true;
				}
				else if (vacantMotors > 9) {
					investigatorInsert(o,4);
					detectiveInsert(o,6);
					motorcycleInsert(o,10);
					return true;
				}
				else
					return false;
			} 
		}
		if(o.getLevel() == 5) {
			if(vacantInvest > 6 && vacantDetec > 7) {
				if(vacantCars > 2 ) {
					investigatorInsert(o,7);
					detectiveInsert(o,8);
					carInsert(o,3);
					return true;
				}
				else if(vacantCars == 2 && vacantMotors > 4) {
					investigatorInsert(o,7);
					detectiveInsert(o,8);
					carInsert(o,2);
					motorcycleInsert(o,5);
					return true;
				}
				else if(vacantCars == 1 && vacantMotors > 9) {
					investigatorInsert(o,7);
					detectiveInsert(o,8);
					carInsert(o,1);
					motorcycleInsert(o,10);
					return true;
				}
				else if(vacantMotors > 14) {
					investigatorInsert(o,7);
					detectiveInsert(o,8);
					motorcycleInsert(o,15);
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}
	
	private synchronized void waitingRoom() { // waiting until executive finished an operation
		while(!canInitialize)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public String toString () {
		return "Name: " +oName+ "\n";
	}
}
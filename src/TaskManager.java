public class TaskManager extends Thread{
	
	private String TMName;
	private Queue <Task> tasks;
	private InformationSystem strategies;
	private boolean dayIsOver;

	public TaskManager(String TMName,Queue <Task> tasks,InformationSystem strategies) { // task manager constructor
		this.TMName = TMName;
		this.tasks=tasks;
		this.dayIsOver=false;
		this.strategies=strategies;
	}
	
	public void run() { // threads run
		while(!dayIsOver) {
			Task t = tasks.extract();
			if(t==null) { // means day is over
				this.dayIsOver=true;
				tasks.insert(t);
				continue;
			}
			int level = levelClassify(t.getLevel());
			long OpTime = level*t.getcType()*1000;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String name = anagram(t.getVictimName());
			Strategy s = new Strategy(t.getId(),name,level,t.getcType(),OpTime);
			this.strategies.insert(s);
		}
	}
	
	public void setTMName(String TMName) {
		this.TMName = TMName;
	}
	
	public void setTasks(Queue <Task> tasks) {
		this.tasks = tasks;
	}
	
	public synchronized void setdayIsOverTrue() {
		this.dayIsOver = true;
		this.notifyAll();
	}
	
	public boolean getdayIsOver() {
		return dayIsOver;
	}
	
	public String getTMName() {
		return this.TMName;
	}
	
	public Queue <Task> getTasks() {
		return this.tasks;
	}
	
	private String anagram(String str) { // returns an anagram for the strategy name 
		char temp; 
		int j=str.length()-1;
		char[] charArray = str.toCharArray(); 
		for(int i=0;i<str.length()-1;i++) {
				temp = charArray[j];
				charArray[j]=charArray[i];
				charArray[i] = temp;
				j--;
		}
		return String.valueOf(charArray);
	}  
	
	private int levelClassify(String s) { // classifies the type of the operation level by the string received
		int level = 0;
		if(s.equals("inquiry"))
			level = 1;
		if(s.equals("Background check"))
			level = 2;
		if(s.equals("surveillance"))
			level = 3;
		if(s.equals("fraud and illegal activity"))
			level = 4;
		if(s.equals("missing people"))
			level = 5;
		return level;
	}

	public String toString () {
		return "Name: " +TMName+ "\n";
	}
}
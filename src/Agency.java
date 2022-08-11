import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Agency {

	private Queue<Call> calls = new Queue <Call>(); 
	private Queue<Task> tasks = new Queue <Task>(); 
	private BoundedQueue <Operation> operations = new BoundedQueue <Operation>(); 
	private InformationSystem strategies = new InformationSystem();
	private Vector <Call> callsList;
	private Vector <Secretary> secList;
	private Vector <TaskManager> TMList;
	private Vector <Executive> executiveList;
	private Vector <Operator> operList;
	public static AgencyManager AM;
	private Vector <Thread> threadsList;
	public static Vector <Motorcycle> motorcyclesList;
	public static Vector <Car> carsList;
	public static Vector <Investigator> investigatorsList;
	public static Vector <Detective> detectivesList;
	
	public void startDay(String name,int numOfOperations,int numOfExecutives,double operationTime) { // initializes all of the variables and starts the threads
		this.threadsList = new Vector <Thread>();
		this.callsList = new Vector <Call>();
		vehiclesCreator();
		agentsCreator();
		readFile(name);
		System.out.println(callsList);
		secCreator();
		TMCreator();
		operCreator(operationTime); // received from GUI
		execCreator(numOfExecutives); // received from GUI
		AMCreator(numOfOperations);
		for(int i=0;i<threadsList.size();i++) {
			threadsList.get(i).start();
		}
	}

	private void vehiclesCreator() { // creates vehicles for the agency
		motorcyclesList = new Vector <Motorcycle>();
		for(int i=1; i<51; i++) {
			Motorcycle m = new Motorcycle();
			motorcyclesList.add(m);
		}
		carsList = new Vector <Car>();
		for(int i=50; i<60; i++) {
			Car c = new Car();
			carsList.add(c);
		}
	}

	private void agentsCreator() { // creates agents for the agency
		investigatorsList = new Vector <Investigator>();
		for(int i=1; i<41; i++) {
			Investigator invest1 = new Investigator();
			investigatorsList.add(invest1);
		}
		detectivesList = new Vector <Detective>();
		for(int i=40; i<101; i++) {
			Detective d = new Detective();
			detectivesList.add(d);
		}
	}
	private void AMCreator(int numOfOperations) { // creates an agency manager for the agency
		AM = new AgencyManager("Pep",calls,tasks,operations,strategies,numOfOperations);
		Thread t = new Thread(AM);
		t.start();
	}

	private void secCreator() { // creates secretaries for the agency
		this.secList = new Vector <Secretary>();
		Secretary s1 = new Secretary ("Tomer",calls,tasks,callsList);
		this.secList.add(s1);
		Secretary s2 = new Secretary ("Doron",calls,tasks,callsList);
		this.secList.add(s2);
		Secretary s3 = new Secretary ("Iniesta",calls,tasks,callsList);
		this.secList.add(s3);
		Secretary s4 = new Secretary ("Xavi",calls,tasks,callsList);
		this.secList.add(s4);
		Secretary s5 = new Secretary ("Messi",calls,tasks,callsList);
		this.secList.add(s5);
		for(int i =0; i<secList.size(); i++) {
			Thread t = new Thread (secList.elementAt(i));
			threadsList.add(t);
		}
	}

	private void TMCreator() { // creates task managers for the agency
		this.TMList = new Vector <TaskManager>();
		TaskManager tm1 = new TaskManager ("Busquets",tasks,strategies);
		this.TMList.add(tm1);
		TaskManager tm2 = new TaskManager ("Alba",tasks,strategies);
		this.TMList.add(tm2);
		TaskManager tm3 = new TaskManager ("TerStegen",tasks,strategies);
		this.TMList.add(tm3);
		for(int i =0; i<TMList.size(); i++) {
			Thread t = new Thread (TMList.elementAt(i));
			threadsList.add(t);
		}
	}

	private void operCreator(double operationTime) { // creates operators for the agency
		this.operList = new Vector <Operator>();
		Operator o1 = new Operator ("Pedri",strategies,operations,operationTime);
		this.operList.add(o1);
		Operator o2 = new Operator ("Gavi",strategies,operations,operationTime);
		this.operList.add(o2);
		Operator o3 = new Operator ("Ferran",strategies,operations,operationTime);
		this.operList.add(o3);
		for(int i =0; i<3; i++) {
			Thread t = new Thread (operList.elementAt(i));
			threadsList.add(t);
		}
	}

	private void execCreator(int numOfExecutives) { // creates executives for the agency
		this.executiveList = new Vector <Executive>();
		Executive e1 = new Executive ("Neymar",operations,operList);
		this.executiveList.add(e1);
		Executive e2 = new Executive ("Suarez",operations,operList);
		this.executiveList.add(e2);
		Executive e3 = new Executive ("Valdes",operations,operList);
		this.executiveList.add(e3);
		Executive e4 = new Executive ("Puyol",operations,operList);
		this.executiveList.add(e4);
		Executive e5 = new Executive ("Araujo",operations,operList);
		this.executiveList.add(e5);
		for(int i = 0; i< numOfExecutives; i++) {
			Executive e = new Executive("Executive", operations, operList);
			this.executiveList.add(e);
		}
		for(int i =0; i<executiveList.size(); i++) {
			Thread t = new Thread(executiveList.elementAt(i));
			threadsList.add(t);
		}
	}

	private void readFile (String Name) { // converting the data from the document to an array of fields 
		BufferedReader inFile=null;
		try {
			FileReader fr = new FileReader (Name);
			inFile = new BufferedReader (fr);
			String S= inFile.readLine();
			S= inFile.readLine();
			while (S!=null){
				String arr [] = S.split("\t");
				addCall(arr);
				S= inFile.readLine();			
			}
		}
		catch (FileNotFoundException exception)
		{
			System.out.println ("The file " + Name + " was not found.");
		}
		catch (IOException exception)
		{
			System.out.println (exception);
		}
		finally{
			try{
				inFile.close();
			} catch(IOException exception){
				exception.printStackTrace();
			}
		}
	}
	
	private void addCall (String arr []) { // adds a call 
		String victimName=arr[0];
		String level=(arr[1]);
		String cType=(arr[2]);
		int eta=Integer.parseInt(arr[3]);
		int handlingTime=Integer.parseInt(arr[4]);
		Call c= new Call(victimName,level,cType,eta,handlingTime,calls);
		callsList.add(c);
		Thread t = new Thread(c);
		threadsList.add(t);
	}
}
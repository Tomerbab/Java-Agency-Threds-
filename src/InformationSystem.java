

public class InformationSystem {

	private Database StrategyList;
	private boolean dayIsOver;


	public InformationSystem() { // information system constructor
		StrategyList = new Database();
		StrategyList.dropTable("Strategies");
		StrategyList.addTable("Strategies");
		dayIsOver = false;
	}
	public synchronized void setDayIsOver(){
		this.dayIsOver = true;
		this.notifyAll();
	}
	public synchronized void insert(Strategy item) {
		StrategyList.insert("Strategies",item);
		this.notifyAll(); 
	}

	public synchronized Strategy extract() { 
		Strategy s = null;
		s = StrategyList.extractFromTable("Strategies");
		while (s==null&&!dayIsOver){
			try {
				this.wait();
				s= StrategyList.extractFromTable("Strategies");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return s;
	}
}
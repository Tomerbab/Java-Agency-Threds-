public class Strategy implements Comparable<Strategy> {

	private int id;
	private String name;
	private int level;
	private int cType;
	private long opTime;

	public Strategy(int id,String name,int level,int cType, long opTime) { //strategy constructor
		this.id=id;
		this.name=name;
		this.level=level;
		this.cType=cType;
		this.opTime=opTime;
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
	
	public int getId() {
		return id;
	}
	
	public int compareTo(Strategy s) {
		if(getOpTime() > (s.getOpTime()))
			return 1;
		else if(getOpTime() < (s.getOpTime()))
			return -1;
		else
			return 0;
	}

	public String toString () {
		return "id: " +id+ ", name: "+name+", level: "+level+ ", cType: "+cType+", opTime: " +opTime+ "\n";
	}
}
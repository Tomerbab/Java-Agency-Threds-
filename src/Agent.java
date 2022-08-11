
abstract class Agent{
	protected boolean isAvailable; // availability of an agent
	
	public void setIsAvailable() { // sets the agent to be available
		this.isAvailable = true;
	}
	public void setUnAvailable() { // sets the agent to be unavailable
		this.isAvailable = false;
	}
	public boolean getIsAvailable() {
		return this.isAvailable;
	}
}
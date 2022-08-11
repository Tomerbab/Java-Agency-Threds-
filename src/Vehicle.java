
abstract class Vehicle{
	protected int capacity;
	protected boolean isAvailable; // availability of the vehicle

	public void setIsAvailable() { // sets the vehicle to be available
		this.isAvailable = true;
	}
	
	public void setUnAvailable() { // sets the vehicle to be unavailable
		this.isAvailable = false;
	}
	
	public boolean getIsAvailable() {
		return this.isAvailable;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
}

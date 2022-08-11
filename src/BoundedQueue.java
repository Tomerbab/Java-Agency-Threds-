import java.util.Vector;

public class BoundedQueue<T> {

	private Vector<T> BoundedQueue;
	private int maxSize =15;

	public BoundedQueue(){
		BoundedQueue = new Vector<T>();
	}

	public synchronized void insert(T item) {
		while(BoundedQueue.size()>=maxSize)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace(); 
			}
		BoundedQueue.add(item);
		this.notifyAll(); 
	}

	public synchronized T extract(){
		while (BoundedQueue.isEmpty())
			try {
				this .wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		T item = BoundedQueue.elementAt(0);
		BoundedQueue.remove(item);
		this.notifyAll();
		return item;}

}

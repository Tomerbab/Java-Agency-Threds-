import java.util.Vector;

public class Queue<T>{ 

	public Vector <T> queue;

	public Queue() {
		queue = new Vector <T>();
	}

	public synchronized void insert(T item) {
		queue.add(item);
		this.notifyAll();
	}

	public synchronized T extract() {
		while (queue.isEmpty())
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		T t = queue.elementAt(0);
		queue.remove(t);
		return t;
	}
}

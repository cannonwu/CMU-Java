//Cannon Wu
//AndrewID: clwu

package lab9;

public class ImpatientCustomer extends Customer{
	static int impatientCustomerCount;
	
	ImpatientCustomer() {
		impatientCustomerCount++;
	}
	
	boolean joinQueue() {
		if (MovieHall.customerQueue.size() > MovieHall.balkQueueLength) {
			System.out.println("***ImpatientCustomer" + this.id + " balked");
			return false;
		} else {
			synchronized (MovieHall.customerQueue) {
				MovieHall.customerQueue.offer(this);
			}
			System.out.println("ImpatientCustomer" + this.id + " joined Q");
			return true;
		}
	}
}

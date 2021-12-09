//Cannon Wu
//AndrewID: clwu

package lab9;

import java.util.Random;

public class Customer implements Comparable<Customer>{
	static int customerCount;
	int id;
	int numberOfTickets;
	
	Customer() {		//constructor
		customerCount++;
		id = customerCount;
		Random r = new Random();
		numberOfTickets = r.nextInt(MovieHall.MAX_TICKETS - MovieHall.MIN_TICKETS + 1) + MovieHall.MIN_TICKETS;
	}
	
	boolean joinQueue() {
		synchronized (MovieHall.customerQueue) {
			MovieHall.customerQueue.offer(this);
		}
		System.out.println("Customer" + this.id + " joined Q");
		//
		return true;
	}

	@Override
	public int compareTo(Customer o) {
		return o.numberOfTickets - this.numberOfTickets;
	}
	
}

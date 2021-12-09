//Cannon Wu
//AndrewID: clwu

package lab9;

import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.List;

public class TicketWindow implements Runnable{
	static int ticketSoldCount;
	static volatile boolean isWindowOpen = true;
	int ticketProcessingTime;
	List<Customer> customerList = new ArrayList<>();
	
	TicketWindow(int ticketProcessingTime) {
		this.ticketProcessingTime = ticketProcessingTime;
	}
	
	@Override
	public void run() {
		while (isWindowOpen) {
			Customer c = null;
			synchronized (MovieHall.customerQueue) {
				c = MovieHall.customerQueue.poll();
			}
			if (c != null) {
				try {
					Thread.sleep(ticketProcessingTime * c.numberOfTickets);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (c instanceof Customer && !(c instanceof ImpatientCustomer)) {
					System.out.println("\t\t\tCustomer" + c.id + " bought " + c.numberOfTickets + " tickets");
				} else {
					System.out.println("\t\t\tImpatientCustomer" + c.id + " bought " + c.numberOfTickets + " tickets");
				}
				ticketSoldCount += c.numberOfTickets;
				customerList.add(c);
				if (ticketSoldCount >= MovieHall.maxSeats) {
					isWindowOpen = false;
				}
			}
		}
		
	}

}

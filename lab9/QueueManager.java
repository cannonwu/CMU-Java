//Cannon Wu
//AndrewID: clwu

package lab9;

import java.util.Random;

public class QueueManager implements Runnable{
	int customerDelay;
	int balkCount;
	
	QueueManager(int customerDelay)	{
		this.customerDelay = customerDelay;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random r = new Random();
		if (MovieHall.examPart == 1) {
			while (TicketWindow.isWindowOpen) {
				try {
					Thread.sleep(r.nextInt(customerDelay + 1));
					Customer c = new Customer();
					c.joinQueue();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			while (TicketWindow.isWindowOpen) {
				try {
					Thread.sleep(r.nextInt(customerDelay + 1));
					Customer c = null;
					if (r.nextInt(2) == 0) {
						c = new Customer();
					} else {
						c = new ImpatientCustomer();
					}
					if (c.joinQueue() == false) {
						balkCount++;
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}

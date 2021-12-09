//Cannon Wu
//AndrewID: clwu

package lab8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Shop implements Runnable{
	
	static Queue<Customer> customerQ = new LinkedList<>(); 
	static boolean isShopOpen = true;  //starts as true. Set to false when all customers served
	static int maxCustomer; //Maximum number of customers created
	static int customersServed; //Incremented after serving each customer
	static int processingTime;	//Time for SalesCounter to process one sale-item
	static long totalQueueTime; //Incremented  after serving each customer
	
	SalesCounter salesCounters; //instances of SalesCounter
	Thread salesCounterThreads; //threads to run the salesCounters 
	int customerGapTime;	//interval between customer arrivals
	
	/** setupCounters() takes user inputs, creates SalesCounter object, 
	 * assigns it to salesCounterThread, and starts it 
	 **/
	void setupCounters() {
		//write your code here
		Scanner input = new Scanner(System.in);
		System.out.println("Sales item processing time?");
		processingTime = input.nextInt();
		System.out.println("Max customer count?");
		maxCustomer = input.nextInt();
		System.out.println("Customer gap time?");
		customerGapTime = input.nextInt();
		
		salesCounters = new SalesCounter();
		salesCounterThreads = new Thread(salesCounters);
		salesCounterThreads.start();
		
		input.close();
	}

	/** joinQueue() adds customer c to customeQ, 
	 * Prints the message "SalesCounter0: CustomerX joined with Y items. Q length:Z". 
	 * Initialize c’s enqueueTime to current time
	 */
	synchronized public void joinQueue(Customer c) {
		//write your code here
		//customerQ = new LinkedList<>();
		customerQ.offer(c);
		System.out.println("SalesCounter0: Customer" + c.id + " joined with " + c.itemsBought + " items. Q length: " + customerQ.size());
		c.enqueueTime = System.currentTimeMillis();
	}

	/** run() invokes setupCounters(), and runs the following 
	 * as long as CustomersServed < maxCustomer 
	 * - 	If Customer.count < maxCustomer, create new customer and pass it to joinQueue() 
	 * -	Sleep for customerGapTime 
	 * -	Set isShopOpen to false 
	 * -	Wait for salesCounterThread to join 
	 * */
	@Override
	public void run() {
		//write your code here
		setupCounters();
		while (customersServed < maxCustomer) {
			if (Customer.count < maxCustomer) {
				Customer c = new Customer();
				joinQueue(c);
			}
			try {
				Thread.sleep(customerGapTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		isShopOpen = false;
		try {
			salesCounterThreads.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

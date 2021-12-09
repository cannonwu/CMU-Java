//Cannon Wu
//AndrewID: clwu

package lab9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MovieHall {
	static int examPart;
	static int maxSeats;
	final static int MIN_TICKETS = 1;
	final static int MAX_TICKETS = 10;
	static Queue<Customer> customerQueue = new LinkedList<>();
	
	static int balkQueueLength; //used in part 2 only
	
	int ticketProcessingTime;
	int customerDelay;
	long startTime;
	long endTime;
	QueueManager queueManager;
	TicketWindow ticketWindow;
	
	
	//DO NOT change this method
	public static void main(String[] args) {
	MovieHall movieHall = new MovieHall();
	movieHall.getInputs();
	movieHall.startThreads();
	movieHall.printReport();
	movieHall.testResults();
	}
	
	void getInputs() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Part 1 or Part 2");
		examPart = input.nextInt();
		
		System.out.println("Enter single ticket processing time (ms):");
		ticketProcessingTime = input.nextInt();
		
		System.out.println("Enter max tickets to be sold:");
		maxSeats = input.nextInt();
		
		System.out.println("Enter max customer delay (ms):");
		customerDelay = input.nextInt();
		
		if (examPart == 2) {
			System.out.println("Enter impatient customer's balk-queue-length");
			balkQueueLength = input.nextInt();
		}
		
		input.close();
	}
	
	void startThreads() {
		queueManager = new QueueManager(customerDelay);
		ticketWindow = new TicketWindow(ticketProcessingTime);
		Thread thread1 = new Thread(queueManager);
		Thread thread2 = new Thread(ticketWindow);
		startTime = System.currentTimeMillis();
		System.out.println("------------ Detailed Customer Report ------------");
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) { 
			e.printStackTrace(); 
		}
		endTime = System.currentTimeMillis();
	}
	
	void printReport() {
		System.out.println("------------ Part 1 Report ------------");
		System.out.println("Total window open duration \t\t: " + (endTime - startTime) + "ms");
		System.out.println("Total customers \t\t\t: " + Customer.customerCount);
		System.out.println("Customers who bought tickets \t\t: " + ticketWindow.customerList.size());
		System.out.println("Customers in queue when window closed \t: " + (customerQueue.size()));
		System.out.println();
		System.out.println("Total tickets sold \t\t\t: " + TicketWindow.ticketSoldCount);
		
		//part 2 report
		if (examPart == 2) {
			System.out.println("------------ Part 2 Report ------------");
			System.out.println("Impatient customers \t\t\t: " + ImpatientCustomer.impatientCustomerCount);
			System.out.println("Customers who balked\t\t\t: " + queueManager.balkCount);
			
		}
		
		//edit
		System.out.println("------------ Customer Summary Report ------------");
		int num = 1;
		int ticketSummary = 0;
		Collections.sort(ticketWindow.customerList);
		for (Customer c : ticketWindow.customerList) {
			if (c instanceof Customer && !(c instanceof ImpatientCustomer)) {
				System.out.print(num + ".\t    Customer " + c.id + " bought: " + c.numberOfTickets + " tickets.");
			} else {
				System.out.print(num + ". ImpatientCustomer " + c.id + " bought " + c.numberOfTickets + " tickets.");
			}
			ticketSummary += c.numberOfTickets;
			System.out.println("\tCumulative total: " + ticketSummary);
			num++;
		}
		
		
	}
	
	
	

	//DO NOT change this method
	void testResults() {

		int ticketsSold = 0;  //total tickets sold

		int minTickets = MAX_TICKETS, maxTickets = MIN_TICKETS;

		//find the min, max, and total tickets sold from the customerList

		for (Customer c : ticketWindow.customerList) {

		ticketsSold += c.numberOfTickets;
		if (minTickets > c.numberOfTickets) minTickets = c.numberOfTickets;
		if (maxTickets < c.numberOfTickets) maxTickets = c.numberOfTickets;

		}

		//test whether total customerCount matches the sum of customers in customerList, //customers in customerQueue, and customers who balked

		assertEquals("Total customers", Customer.customerCount,

		ticketWindow.customerList.size() + customerQueue.size() + queueManager.balkCount);

		//test total tickets sold calculated above matches the total count at TicketWindow

		assertEquals("Total tickets sold", ticketsSold, TicketWindow.ticketSoldCount );

		//test that total tickets sold is equal to or more than maxSeats 

		assertTrue(ticketsSold >= maxSeats);

		//test the minTickets calculated above is greater than or equal to MIN_TICKETS

		assertTrue("Min tickets", minTickets >= MIN_TICKETS);

		//test the maxTickets calculated above is less than or equal to MIN_TICKETS

		assertTrue("Max tickets", maxTickets <= MAX_TICKETS);

		}
}

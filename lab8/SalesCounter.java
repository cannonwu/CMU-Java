//Cannon Wu
//AndrewID: clwu

package lab8;

public class SalesCounter implements Runnable {
	int id; 			//Unique sequential identifier for each sales counter
	static int count;	//Counts SalesCounter objects created so far

	
	/**SalesCounter() increments count, initializes id  */
	SalesCounter() {
		//Write your code here
		count++;
		id = count;
	}
	
	/** run() runs while isShopOpen is true. It does the following: 
	 * -	Poll next customer 
	 * -	Print the message: "Salescounter0: CustomerX served. Q length: Y " 
	 * -	Sleep for (processingTime x itemsBought) by Customer 
	 * -	Assign current time to Customer’s dequeueTime 
	 * -	Shop.totalQueueTime += dequeueTime – enqueueTime 
	 * -	Increment Shop.customersServed 
	 * */
	@Override
	public void run() {
		//write your code here
		while (Shop.isShopOpen) {
			Customer next = null;
			synchronized (Shop.customerQ) {
				next = Shop.customerQ.poll();
			}
			
			if (next != null) {
				System.out.println("Salescounter0: Customer" + next.id + " served. Q length: " + Shop.customerQ.size());
				try {
					Thread.sleep(Shop.processingTime * next.itemsBought);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				next.dequeueTime = System.currentTimeMillis();
				Shop.totalQueueTime += next.dequeueTime - next.enqueueTime;
				Shop.customersServed++;
			}
		}
	}
}


//Cannon Wu
//Andrew ID: clwu

package finals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Road implements Runnable{
	static Queue<Vehicle> vehicleQ = new LinkedList<>();
	static int maxVehicles;
	static int vehiclesPassed;
	static int vehiclesExited;		//used in part 2
	static int problemPart;
	long startTime;
	long endTime;
	
    public static void main(String[] args) {
        Road road = new Road();
        road.startRoad();
        road.printReport();
        road.checkAssertions();
    }

	void startRoad() {
		Scanner input = new Scanner(System.in);
		System.out.println("Part 1 or Part 2");
		problemPart = input.nextInt();
		
		System.out.println("How many vehicles?");
		maxVehicles = input.nextInt();
		
		//initialize threads with class objects
		Road road = new Road();
		Traffic traffic = new Traffic();
		TrafficLight trafficLight = new TrafficLight();
		Thread threadRoad = new Thread(road);
		Thread threadTraffic = new Thread(traffic);
		Thread threadTrafficLight = new Thread(trafficLight);
		startTime = System.currentTimeMillis();
		
		//start threads
		threadRoad.start();
		threadTraffic.start();
		threadTrafficLight.start();
		
		//wait for threads to join back and record endTime
		try {
			threadRoad.join();
			threadTraffic.join();
			threadTrafficLight.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();
		
		input.close();
	}
    
    @Override
	public void run() {
		if (problemPart == 1) {
	    	while (Vehicle.vehicleCount < maxVehicles) {
				Vehicle v = null;
				if (TrafficLight.isGreen) {
					synchronized (vehicleQ) {
						v = vehicleQ.poll();
					}
					
					if (v != null) {
						vehiclesPassed++;
						System.out.println("Green: Vehicle " + v.id + " passed. Q length: " + vehicleQ.size());
					}
					
				}
			}
		} else {
	    	while (Vehicle.vehicleCount < maxVehicles) {
				Vehicle v = null;
				if (TrafficLight.isGreen) {
					synchronized (vehicleQ) {
						v = vehicleQ.poll();
					}
					
					if (v != null && !(v instanceof ImpatientVehicle)) {
						vehiclesPassed++;
						System.out.println("Green: Vehicle " + v.id + " passed. Q length: " + vehicleQ.size());
					} else if (v != null) {
						vehiclesPassed++;
						System.out.println("Green: ImpatientVehicle " + v.id + " passed. Q length: " + vehicleQ.size());
					}
					
				}
			}
		}
    	
	}
    
    void printReport() {
    	if (problemPart == 1) {
        	System.out.println("-----------TRAFFIC REPORT---------------------------");
        	System.out.println("The program ran for " + (endTime - startTime) + " ms");
        	System.out.println("Max Q length at traffic light was " + Traffic.maxQLength);
        	System.out.println("Final Q length at traffic light was " + vehicleQ.size());
    	}
    	
    	if (problemPart == 2) {
        	System.out.println("-----------TRAFFIC REPORT---------------------------");
        	System.out.println("The program ran for " + (endTime - startTime) + " ms");
        	System.out.println("Max Q length at traffic light was " + Traffic.maxQLength);
        	System.out.println("Final Q length at traffic light was " + vehicleQ.size());
        	System.out.println("Vehicles passed: " + vehiclesPassed);
        	System.out.println("Vehicles exited: " + vehiclesExited);
    	}
    }
    
    void checkAssertions() {
        assertEquals(maxVehicles, vehiclesPassed + vehiclesExited + vehicleQ.size());
        assertTrue(Traffic.maxQLength >= vehicleQ.size());
        assertTrue(Vehicle.vehicleCount == maxVehicles );
    }
	    
}

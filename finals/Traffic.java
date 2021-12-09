//Cannon Wu
//Andrew ID: clwu

package finals;

import java.util.Random;

public class Traffic implements Runnable{
	static final int MIN_VEHICLE_DELAY = 5;
	static final int MAX_VEHICLE_DELAY = 10;
	static int maxQLength = 0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random r = new Random();
		
		if (Road.problemPart == 1) {
			
			while (Vehicle.vehicleCount < Road.maxVehicles) {
				try {
					Thread.sleep(r.nextInt(MAX_VEHICLE_DELAY - MIN_VEHICLE_DELAY + 1));	//+1 because upper bound non-inclusive
					Vehicle v = new Vehicle();
					v.joinVehicleQ();
					//update maxQLength
					if (maxQLength < Road.vehicleQ.size()) {
						maxQLength = Road.vehicleQ.size();
					}
					
					if (! TrafficLight.isGreen) {		//if red, print red message
						System.out.println("RED: Vehicle " + v.id + " in Q. Q length "+ Road.vehicleQ.size());
						
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			
		} else {
			
			while (Vehicle.vehicleCount < Road.maxVehicles) {
				Vehicle v = null;
				try {
					Thread.sleep(r.nextInt(MAX_VEHICLE_DELAY - MIN_VEHICLE_DELAY + 1));
					
					if (r.nextInt(5) == 0) {	//1 in 4, non-inclusive
						v = new ImpatientVehicle();
					} else {
						v = new Vehicle();
					}
					
					if (v != null) {
						v.joinVehicleQ();
						//update maxQLength
						if (maxQLength < Road.vehicleQ.size()) {
							maxQLength = Road.vehicleQ.size();
						}
						
						if (v != null) {		//check if null again, may have been removed
							if (! TrafficLight.isGreen && !(v instanceof ImpatientVehicle) ) {		//if red, print red message
								System.out.println("RED: Vehicle " + v.id + " in Q. Q length "+ Road.vehicleQ.size());
								
							}
						}
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	}

}

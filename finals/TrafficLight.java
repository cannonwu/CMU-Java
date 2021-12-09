//Cannon Wu
//Andrew ID: clwu

package finals;

public class TrafficLight implements Runnable{
	static final int TRAFFIC_LIGHT_DELAY = 100;
	volatile static boolean isGreen = true;
	
	
	@Override
	public void run() {
		while (Vehicle.vehicleCount < Road.maxVehicles) {
			try {
				Thread.sleep(TRAFFIC_LIGHT_DELAY + 1);	//+1 because upper bound is non-inclusive
				if (isGreen) {
					isGreen = false;
				} else {
					isGreen = true;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}

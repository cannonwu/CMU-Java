//Cannon Wu
//Andrew ID: clwu

package finals;

public class Vehicle {	//extended by impatient vehicle
	static int vehicleCount;
	int id;
	
	Vehicle() {
		vehicleCount++;
		id = vehicleCount;
	}
	
	boolean joinVehicleQ() {		//synchronized
		synchronized (Road.vehicleQ) {
			Road.vehicleQ.offer(this);
		}
		return true;
	}
}

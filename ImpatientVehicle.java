//Cannon Wu
//Andrew ID: clwu

package finals;

public class ImpatientVehicle extends Vehicle{
	static final int Q_TOO_LONG_LENGTH = 5;
	
	@Override
	boolean joinVehicleQ() {
		if (Road.vehicleQ.size() >= Q_TOO_LONG_LENGTH) {
			System.out.println("************Red: ImpatientVehicle " + this.id + " exiting. Q length " + Road.vehicleQ.size());
			synchronized (Road.vehicleQ) {
				Road.vehicleQ.remove(this);
			}
			Road.vehiclesExited++;
			return false;
		} else {
			synchronized (Road.vehicleQ) {
				Road.vehicleQ.offer(this);
			}
			return true;
		}
	}
}

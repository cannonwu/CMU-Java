//Cannon Wu
//AndrewID: clwu

package hw3;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")
public class DataException extends RuntimeException{

	DataException(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data error");
		//show alert based on thrown message from method
		alert.setContentText(message);
		alert.showAndWait();
	}
	
}

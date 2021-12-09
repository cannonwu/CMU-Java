//Cannon Wu
//AndrewID: clwu

package hw3;

import java.time.LocalDate;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddCaseView extends CaseView{

	AddCaseView(String header) {
		super(header);
	}

	@Override
	Stage buildView() {
		updateButton.setText("Add case");
		//set date picker to current date
		caseDatePicker.setValue(LocalDate.now());
		//create scene
		stage.setScene(new Scene(updateCaseGridPane, CASE_WIDTH, CASE_HEIGHT));
		return stage; 
	}

}

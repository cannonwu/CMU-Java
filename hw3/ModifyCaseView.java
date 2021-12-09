//Cannon Wu
//AndrewID: clwu

package hw3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyCaseView extends CaseView{

	ModifyCaseView(String header) {
		super(header);
	}

	@Override
	Stage buildView() {
		updateButton.setText("Modify case");
		//populate caseView text fields with currentCase data
		titleTextField.setText(CyberCop.currentCase.getCaseTitle());
		caseDatePicker.setValue(LocalDate.parse(CyberCop.currentCase.getCaseDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		caseTypeTextField.setText(CyberCop.currentCase.getCaseType());
		caseNumberTextField.setText(CyberCop.currentCase.getCaseNumber());
		categoryTextField.setText(CyberCop.currentCase.getCaseCategory());
		caseLinkTextField.setText(CyberCop.currentCase.getCaseLink());
		caseNotesTextArea.setText(CyberCop.currentCase.getCaseNotes());
		//create scene
		stage.setScene(new Scene(updateCaseGridPane, CASE_WIDTH, CASE_HEIGHT));
		return stage;
	}

}

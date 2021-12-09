//Cannon Wu
//AndrewID: clwu

package hw3;

import java.io.File;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CyberCop extends Application{

	public static final String DEFAULT_PATH = "data"; //folder name where data files are stored
	public static final String DEFAULT_HTML = "/CyberCop.html"; //local HTML
	public static final String APP_TITLE = "Cyber Cop"; //displayed on top of app

	CCView ccView = new CCView();
	CCModel ccModel = new CCModel();

	CaseView caseView; //UI for Add/Modify/Delete menu option

	GridPane cyberCopRoot;
	Stage stage;

	static Case currentCase; //points to the case selected in TableView.

	public static void main(String[] args) {
		launch(args);
	}

	/** start the application and show the opening scene */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		primaryStage.setTitle("Cyber Cop");
		cyberCopRoot = ccView.setupScreen();  
		setupBindings();
		Scene scene = new Scene(cyberCopRoot, ccView.ccWidth, ccView.ccHeight);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		ccView.webEngine.load(getClass().getResource(DEFAULT_HTML).toExternalForm());
		primaryStage.show();
	}

	/** setupBindings() binds all GUI components to their handlers.
	 * It also binds disableProperty of menu items and text-fields 
	 * with ccView.isFileOpen so that they are enabled as needed
	 */
	void setupBindings() {
		//write your code here
		//bind menu items with ccView.isFileOpen
		ccView.openFileMenuItem.disableProperty().bind(ccView.isFileOpen);
		ccView.closeFileMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.addCaseMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.modifyCaseMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.deleteCaseMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.saveFileMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.caseCountChartMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		
		//bind text fields and buttons with ccView.isFileOpen
		ccView.titleTextField.disableProperty().bind(ccView.isFileOpen.not());
		ccView.caseTypeTextField.disableProperty().bind(ccView.isFileOpen.not());
		ccView.yearComboBox.disableProperty().bind(ccView.isFileOpen.not());
		ccView.caseNumberTextField.disableProperty().bind(ccView.isFileOpen.not());
		ccView.caseNotesTextArea.disableProperty().bind(ccView.isFileOpen.not());
		ccView.searchButton.disableProperty().bind(ccView.isFileOpen.not());
		ccView.clearButton.disableProperty().bind(ccView.isFileOpen.not());
		
		//bind open file menu item handler
		ccView.openFileMenuItem.setOnAction(new OpenMenuItemHandler());
		
		//bind close file menu item handler
		ccView.closeFileMenuItem.setOnAction(new CloseMenuItemHandler());
		
		//bind exit menu item handler
		ccView.exitMenuItem.setOnAction(new ExitMenuItemHandler());
		
		//bind clear button handler
		ccView.clearButton.setOnAction(new ClearButtonHandler());
		
		//bind search button handler
		ccView.searchButton.setOnAction(new SearchButtonHandler());
		
		//bind add case menu item handler
		ccView.addCaseMenuItem.setOnAction(new CaseMenuItemHandler());
		
		//bind modify case menu item handler
		ccView.modifyCaseMenuItem.setOnAction(new CaseMenuItemHandler());
		
		//bind delete case menu item handler
		ccView.deleteCaseMenuItem.setOnAction(new CaseMenuItemHandler());
		
		//bind save menu item handler
		ccView.saveFileMenuItem.setOnAction(new SaveFileMenuItemHandler());
		
		//bind chart menu item handler
		ccView.caseCountChartMenuItem.setOnAction(new CaseCountChartMenuItemHandler());
		
	}
	
	//open file menu item handler
	class OpenMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select file");
			fileChooser.setInitialDirectory(new File(DEFAULT_PATH));
			File file = fileChooser.showOpenDialog(stage);
			
			ccModel.readCases(file.getPath());
			ccModel.buildYearMapAndList();
			
			//populate caseTableView & combobox
			ccView.caseTableView.setItems(ccModel.caseList);
			ccView.yearComboBox.setItems(ccModel.yearList);
			
			//point currentCase to first record in caseTableView, update text areas
			ccView.caseTableView.getSelectionModel().select(0);
			currentCase = ccView.caseTableView.getSelectionModel().getSelectedItem();
			ccView.titleTextField.setText(currentCase.getCaseTitle());
			ccView.caseTypeTextField.setText(currentCase.getCaseType());
			ccView.yearComboBox.setValue(currentCase.getCaseDate().substring(0, 4));
			ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
			ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
			//(web helper code) display first case web info
			if (currentCase.getCaseLink() == null || currentCase.getCaseLink().isBlank()) {  //if no link in data
				URL url = getClass().getClassLoader().getResource(DEFAULT_HTML);  //default html
				if (url != null) ccView.webEngine.load(url.toExternalForm());
			} else if (currentCase.getCaseLink().toLowerCase().startsWith("http")){  //if external link
				ccView.webEngine.load(currentCase.getCaseLink());
			} else {
				URL url = getClass().getClassLoader().getResource(currentCase.getCaseLink().trim());  //local link
				if (url != null) ccView.webEngine.load(url.toExternalForm());
			}
			
			//add listener to update currentCase on user selection
			ccView.caseTableView.getSelectionModel().selectedItemProperty().addListener((obsSelection, oldSelection, newSelection) -> {
				if (newSelection != null) {
					currentCase = newSelection;
					ccView.titleTextField.setText(currentCase.getCaseTitle());
					ccView.caseTypeTextField.setText(currentCase.getCaseType());
					ccView.yearComboBox.setValue(currentCase.getCaseDate().substring(0, 4));
					ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
					ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
					//(web helper code) display currentCase web info
					if (currentCase.getCaseLink() == null || currentCase.getCaseLink().isBlank()) {  //if no link in data
						URL url = getClass().getClassLoader().getResource(DEFAULT_HTML);  //default html
						if (url != null) ccView.webEngine.load(url.toExternalForm());
					} else if (currentCase.getCaseLink().toLowerCase().startsWith("http")){  //if external link
						ccView.webEngine.load(currentCase.getCaseLink());
					} else {
						URL url = getClass().getClassLoader().getResource(currentCase.getCaseLink().trim());  //local link
						if (url != null) ccView.webEngine.load(url.toExternalForm());
					}
				}
			});
			
			//update stage title, messageLabel
			stage.setTitle("Cyber Cop: " + file.getName());
			ccView.messageLabel.setText(ccModel.caseList.size() + " cases.");
			
			//set isFileOpen to true
			ccView.isFileOpen.setValue(true);
		}
	}
	
	//close menu item handler
	class CloseMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			//reset GUI components
			ccView.titleTextField.clear();
			ccView.caseTypeTextField.clear();
			ccView.yearComboBox.getSelectionModel().clearSelection();
			ccView.yearComboBox.getItems().removeAll(ccView.yearComboBox.getItems());	//remove old ComboBox items
			ccView.caseNumberTextField.clear();
			ccView.caseNotesTextArea.clear();
			ccView.messageLabel.setText("");
			ccView.caseTableView.setItems(null);
			stage.setTitle(APP_TITLE);
			//reset web view
			ccView.webEngine.load(getClass().getResource(DEFAULT_HTML).toExternalForm());
			//set isFileOpen to false
			ccView.isFileOpen.setValue(false);
		}
	}
	
	//exit menu item handler
	class ExitMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			System.exit(0);
		}
	}
	
	//clear button handler
	class ClearButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			ccView.titleTextField.setText(null);
			ccView.caseTypeTextField.setText(null);
			ccView.yearComboBox.getSelectionModel().clearSelection();
			ccView.caseNumberTextField.setText(null);
			ccView.messageLabel.setText("");
		}
	}
	
	//search button handler
	class SearchButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			List<Case> searchResultsList = ccModel.searchCases(ccView.titleTextField.getText(), ccView.caseTypeTextField.getText(), ccView.yearComboBox.getValue(), ccView.caseNumberTextField.getText());
			ObservableList<Case> obsSearchResults = FXCollections.observableArrayList(searchResultsList);
			ccView.caseTableView.setItems(obsSearchResults);
			ccView.messageLabel.setText(obsSearchResults.size() + " cases.");
			
			//point currentCase to first record in caseTableView, update text areas
			ccView.caseTableView.getSelectionModel().select(0);
			currentCase = ccView.caseTableView.getSelectionModel().getSelectedItem();
			ccView.titleTextField.setText(currentCase.getCaseTitle());
			ccView.caseTypeTextField.setText(currentCase.getCaseType());
			ccView.yearComboBox.setValue(currentCase.getCaseDate().substring(0, 4));
			ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
			ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
		}
	}
	
	//case menu item handler
	class CaseMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			MenuItem caseMenuItem = (MenuItem) arg0.getSource();
			if (caseMenuItem.getText().equals("Add case")) {
				caseView = new AddCaseView("Add case");
				caseView.updateButton.setOnAction(new AddButtonHandler());
				caseView.closeButton.setOnAction(new CaseViewCloseButtonHandler());
				caseView.clearButton.setOnAction(new CaseViewClearButtonHandler());
				caseView.buildView().show();
			}
			else if (caseMenuItem.getText().equals("Modify case")) {
				caseView = new ModifyCaseView("Modify case");
				caseView.updateButton.setOnAction(new ModifyButtonHandler());
				caseView.closeButton.setOnAction(new CaseViewCloseButtonHandler());
				caseView.clearButton.setOnAction(new CaseViewClearButtonHandler());
				caseView.buildView().show();
			}
			else if (caseMenuItem.getText().equals("Delete case")) {
				caseView = new DeleteCaseView("Delete case");
				caseView.updateButton.setOnAction(new DeleteButtonHandler());
				caseView.closeButton.setOnAction(new CaseViewCloseButtonHandler());
				caseView.clearButton.setOnAction(new CaseViewClearButtonHandler());
				caseView.buildView().show();
			}
		}
	}
	
	//caseView add button handler
	class AddButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			//store all user inputed data and create new case object
			String caseTitle = caseView.titleTextField.getText();
			String caseDate = caseView.caseDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String caseType = caseView.caseTypeTextField.getText();
			String caseNumber = caseView.caseNumberTextField.getText();
			String caseLink = caseView.caseLinkTextField.getText();
			String caseCategory = caseView.categoryTextField.getText();
			String caseNotes = caseView.caseNotesTextArea.getText();
			//check for missing date, title, type, case number, or duplicate case number
			try {
				if (!caseTitle.isBlank() && !caseDate.isBlank() && !caseType.isBlank() && !caseNumber.isBlank()) {
					if (ccModel.caseMap.containsKey(caseNumber)) {
						throw new DataException("Duplicate case number");
					} else {
						Case newCase = new Case(caseDate, caseTitle, caseType, caseNumber, caseLink, caseCategory, caseNotes);
						//update caseList, caseMap
						ccModel.caseList.add(newCase);
						ccModel.caseMap.put(newCase.getCaseNumber(), newCase);
						//update GUI: caseTableView, combobox, messageLabel
						ccModel.yearMap.clear();	//reset yearMap
						ccModel.yearList.clear();	//reset yearList		
						
						ccModel.buildYearMapAndList();	//rebuild yearMap and yearList
						ccView.caseTableView.setItems(ccModel.caseList);
						ccView.yearComboBox.setItems(ccModel.yearList);
						ccView.messageLabel.setText(ccModel.caseList.size() + " cases.");
						
						ccView.yearComboBox.getSelectionModel().clearSelection();	//clear null from combo box
						ccView.yearComboBox.setValue(currentCase.getCaseDate().substring(0, 4));
					}
				} else {
					throw new DataException("Case must have date, title, type, and number");
				}
				
			} catch (DataException dataE) {
				
			}
			
		}
	}
	
	//caseView close button handler
	class CaseViewCloseButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			caseView.stage.hide();
		}	
	}
	
	//caseView clear button handler
	class CaseViewClearButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			caseView.titleTextField.setText(null);
			caseView.caseDatePicker.setValue(null);
			caseView.caseTypeTextField.setText(null);
			caseView.caseNumberTextField.setText(null);
			caseView.categoryTextField.setText(null);
			caseView.caseLinkTextField.setText(null);
			caseView.caseNotesTextArea.setText(null);
		}
	}
	
	//caseView modify button handler
	class ModifyButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			//update currentCase data with caseView text field data
			String caseTitle = caseView.titleTextField.getText();
			String caseDate = caseView.caseDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String caseType = caseView.caseTypeTextField.getText();
			String caseNumber = caseView.caseNumberTextField.getText();
			String caseLink = caseView.caseLinkTextField.getText();
			String caseCategory = caseView.categoryTextField.getText();
			String caseNotes = caseView.caseNotesTextArea.getText();
			//check for missing date, title, type, case number, or duplicate case number
			try {
				if (!caseTitle.isBlank() && !caseDate.isBlank() && !caseType.isBlank() && !caseNumber.isBlank()) {
					if (ccModel.caseMap.containsKey(caseNumber) && !caseNumber.equalsIgnoreCase(currentCase.getCaseNumber())) {
						throw new DataException("Duplicate case number");
					} else {
						currentCase.setCaseTitle(caseTitle);
						currentCase.setCaseDate(caseDate);
						currentCase.setCaseType(caseType);
						currentCase.setCaseNumber(caseNumber);
						currentCase.setCaseCategory(caseCategory);
						currentCase.setCaseLink(caseLink);
						currentCase.setCaseNotes(caseNotes);
						//update ccView text field data for currentCase
						ccView.titleTextField.setText(currentCase.getCaseTitle());
						ccView.caseTypeTextField.setText(currentCase.getCaseType());
						ccView.caseNumberTextField.setText(currentCase.getCaseNumber());
						ccView.caseNotesTextArea.setText(currentCase.getCaseNotes());
						//update GUI: caseTableView, combobox
						ccModel.yearMap.clear();	//reset yearMap
						ccModel.yearList.clear();	//reset yearList
						
						ccModel.buildYearMapAndList();
						ccView.caseTableView.setItems(ccModel.caseList);
						ccView.yearComboBox.setItems(ccModel.yearList);
						
						ccView.yearComboBox.getSelectionModel().clearSelection();	//clear null from combo box
						ccView.yearComboBox.setValue(currentCase.getCaseDate().substring(0, 4));
					}
				} else {
					throw new DataException("Case must have date, title, type, and number");
				}
				
			} catch (DataException dataE) {
				
			}
			
		}	
	}
	
	//caseView delete button handler
	class DeleteButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			//remove currentCase
			ccModel.caseList.remove(currentCase);
			ccModel.caseMap.remove(currentCase.getCaseNumber());
			//update GUI: caseTableView, combobox, messageLabel
			ccModel.yearMap.clear();	//reset yearMap
			ccModel.yearList.clear();	//reset yearList
			
			ccModel.buildYearMapAndList();
			ccView.caseTableView.setItems(ccModel.caseList);
			ccView.yearComboBox.setItems(ccModel.yearList);
			ccView.messageLabel.setText(ccModel.caseList.size() + " cases.");
			
			ccView.yearComboBox.getSelectionModel().clearSelection();	//clear null from combo box
			ccView.yearComboBox.setValue(currentCase.getCaseDate().substring(0, 4));
		}
	}
	
	//save file menu item handler
	class SaveFileMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save File");
			fileChooser.setInitialDirectory(new File(DEFAULT_PATH));
			File saveFile = fileChooser.showSaveDialog(stage);
			//update messageLabel if successful write
			boolean writeSuccess = ccModel.writeCases(saveFile.getAbsolutePath());
			if (writeSuccess) {
				ccView.messageLabel.setText(saveFile.getName() + " saved.");
			}
		}
		
	}
	
	//chart menu item handler
	class CaseCountChartMenuItemHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			ccView.showChartView(ccModel.yearMap);
		}
		
	}

}


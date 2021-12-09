//Cannon Wu
//AndrewID: clwu

package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TSVCaseReader extends CaseReader {

	TSVCaseReader(String filename) {
		super(filename);
	}

	@Override
	List<Case> readCases() {
		List<Case> caseList = new ArrayList<>();
		String[] fileData;
		int rejects = 0;
		Scanner input;
		try {
			input = new Scanner(new File(filename));			//read file into scanner obj
			StringBuilder fileContent = new StringBuilder();
			while (input.hasNextLine()) {
				fileContent.append(input.nextLine() + "\n");	//load file data into StringBuilder obj
			}
			fileData = fileContent.toString().split("\n");		//split StringBuilder obj and load each string into array
			
			for (int i = 0; i < fileData.length; i++) {
				String fileRow[] = fileData[i].split("\t");		//spit fileData on \t
				
				if (!fileRow[0].isBlank() && !fileRow[1].isBlank() && !fileRow[2].isBlank() && !fileRow[3].isBlank()) {
					//create case if first 4 columns are not blank
					Case c = new Case(fileRow[0].trim(), fileRow[1].trim(), fileRow[2].trim(), fileRow[3].trim(), fileRow[4].trim(), fileRow[5].trim(), fileRow[6].trim());
					caseList.add(c);
				} else {
					rejects += 1;  //increment rejects if any of the first 4 columns are blank
				}
				
			}
			
			if (rejects > 0) {
				throw new DataException(rejects + " cases rejected.\nThe file must have cases with\ntab separated date, title, type, and case number!");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DataException dataE) {
			
		}
		
		return caseList;
	}
	
}

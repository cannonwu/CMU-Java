//Cannon Wu
//AndrewID: clwu

package hw3;

public class CaseReaderFactory {
	
	CaseReader createReader(String filename) {
		if (filename.contains(".csv")) {
			CSVCaseReader csvReader = new CSVCaseReader(filename);	//creates CaseReader object for csv files
			return csvReader;
		} else {
			TSVCaseReader tsvReader = new TSVCaseReader(filename);	//creates CaseReader object for tsv files
			return tsvReader;
		}
	}
}

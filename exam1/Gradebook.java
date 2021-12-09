//Cannon Wu
//Andrew ID: clwu

package exam1;

import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Arrays;
import java.util.Scanner;

public class Gradebook {

	String[] fileData; //stores rows of data read from data file
	String[] students;	//names of students
	float[][] scores;	//students' scores in the same order as given in data file 
	float[] averageScores; //each students' average score

	//do not change this method
	public static void main(String[] args) {
		Gradebook gradebook = new Gradebook();
		gradebook.fileData = gradebook.loadData("Gradebook.txt");
		gradebook.students = gradebook.getStudents();
		gradebook.scores = gradebook.getScores();
		gradebook.averageScores = gradebook.getAverages();
		gradebook.printAverages();
	}

	/** loadData() uses filename to read the file and
	 * returns an array of String, with each 
	 * string representing one row in data file
	 * @param filename
	 * @return
	 */
	String[] loadData(String filename) {
		//write your code here
		StringBuilder fileString = new StringBuilder();
		try {
			Scanner tokens = new Scanner(new File(filename));
			while (tokens.hasNextLine()) {
				fileString.append(tokens.nextLine() + "\n");
			}
			fileData = fileString.toString().split("\n");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileData;

	}

	/** getStudents() uses data in fileData array
	 * and extracts student's names from it. It returns
	 * an array of these names.
	 * @return
	 */
	String[] getStudents() {
		//write your code here
		//split each row of fileData by :
		//set students array as splitRow[0][i]
		
		//name of return array: students
		String[][] rowSplit = new String[fileData.length][];
		for (int i = 0; i < fileData.length; i++) {
			rowSplit[i] = fileData[i].split(":");
		}
		
		students = new String[fileData.length];
		
		for (int i = 0; i < rowSplit.length; i++) {
			students[i] = rowSplit[i][0];
		}
		
		return students;
	}

	/** getScores uses data stored in fileData array
	 * and extracts scores of each student in a 2D array of 
	 * float numbers. 
	 * It returns the 2D array. 
	 * @return
	 */
	float[][] getScores() {
		//write your code here
		//split rows by :
		//scores stored in rowSplit[i][1]
		//store in 2d string, split by ,
		//covert each element to float, store in 2d float
		
		String[][] rowSplit = new String[fileData.length][];
		for (int i = 0; i < fileData.length; i++) {
			rowSplit[i] = fileData[i].split(": ");
		}
		
		String[][] stringScores = new String[fileData.length][];
		for (int i = 0; i < rowSplit.length; i++) {
			stringScores[i] = rowSplit[i][1].split(", ");
		}
		
		scores = new float[stringScores.length][];
		
		for (int i = 0; i < stringScores.length; i++) {
			scores[i] = new float[stringScores[i].length];				//initializing each 2D row length
			for (int j = 0; j < stringScores[i].length; j++) {
				scores[i][j] = Float.parseFloat(stringScores[i][j]);
			}
		}

		return scores;
	}

	/** getAverages uses data stored in score[][] array
	 * and computes average score for each student.
	 * It returns an array of these averages.
	 * @return
	 */
	float[] getAverages() {
		//write your code here
		//return averageScores[]

		averageScores = new float[scores.length];
		for (int i = 0; i < averageScores.length; i++) {
			float sum = 0;										//reset sum & avg for each index
			float average = 0;
			for (int j = 0; j < scores[i].length; j++) {
				sum += scores[i][j];
			}
			average = sum / scores[i].length;
			averageScores[i] = average;
		}
		
		return averageScores;
	}

	//do not change this method
	void printAverages() {
		System.out.printf("%-20s\t%s%n", "Student", "Average Score");
		System.out.println("----------------------------------------");
		for (int i = 0; i < fileData.length; i++) {
			System.out.printf("%-20s:\t%10.2f\n", students[i] , averageScores[i] );
		}
	}
}

package exam1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestGradebook {
	
	static Gradebook gradebook;
	
	@BeforeAll
	public static void setupGradebook() {
		gradebook = new Gradebook();
		gradebook.fileData = gradebook.loadData("Gradebook.txt");
		gradebook.students = gradebook.getStudents();
		gradebook.scores = gradebook.getScores();
		gradebook.averageScores = gradebook.getAverages();
	}

	@Test
	void test1_fileData() {
		assertEquals(5, gradebook.fileData.length);
	}
	
	@Test
	void test2_studentsCount() {
		assertEquals(5, gradebook.students.length);
	}
	
	@Test
	void test3_studentsContent() {
		assertEquals("Smith", gradebook.students[2]);
	}
	
	@Test
	void test4_scoreCounts() {
		assertEquals(5, gradebook.scores[0].length); //Zhang has 5 scores
		assertEquals(3, gradebook.scores[1].length); //Liu has 3 scores
		assertEquals(5, gradebook.scores[2].length); //Smith has 5 scores
		assertEquals(4, gradebook.scores[3].length); //Chomsky has 4 scores
		assertEquals(5, gradebook.scores[4].length); //Sharma has 5 scores
		
	}
	
	@Test
	void test5_scoresContent() {
		assertEquals(10, gradebook.scores[3][1]); //Chomsky's second score
	}
	
	@Test
	void test6_averages() {
		assertEquals(8.60, gradebook.averageScores[0], .01);  //Zhang's average
		assertEquals(9.67, gradebook.averageScores[1], .01);  //Liu's average
		assertEquals(8.80, gradebook.averageScores[2], .01);  //Smith's average
		assertEquals(9.25, gradebook.averageScores[3], .01);  //Chomsky's average
		assertEquals(8.40, gradebook.averageScores[4], .01);  //Sharma's average
	}
	
	

}

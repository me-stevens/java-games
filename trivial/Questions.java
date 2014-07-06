/* ---------------------------------------------
 *		CLASS QUESTIONS
 * ---------------------------------------------*/

package trivial;

public class Questions {

	private String[] questions;
	private String[][] answers;
	private String[] solutions;


	/* ---------------------------------------------
	 *		CONSTRUCTOR
	 * --------------------------------------------- */
	public Questions() {

		// QUESTIONS ----------------------------------
		questions     = new String[12];

		questions[0]  = "What's the highest lake of the world?";
		questions[1]  = "Who's the author of 'Crime and Punishment'?";
		questions[2]  = "Where is Trinidad and Tobago?";
		questions[3]  = "How much is a star delayed when passing through the same point after one day?";
		questions[4]  = "Soot is...";
		questions[5]  = "What's a golden oriole?";
		questions[6]  = "Who's the director of Lord of the Rings?";
		questions[7]  = "What's the answer to the ultimate question of Life, the Universe, and Everything?";
		questions[8]  = "Whose song is 'Woman in uniform'?";
		questions[9]  = "In which year was the Treaty of Tordesillas signed?";
		questions[10] = "When did Salvador Dali die?";
		questions[11] = "Who's the composer of 'Parsifal'?";
	
		// ANSWERS ----------------------------------
		answers       = new String[12][3];

		answers[0][0] = "Titicaca.";
		answers[0][1] = "Poopo.";
		answers[0][2] = "Lake Superior.";

		answers[1][0] = "Dostoyewski.";
		answers[1][1] = "Camilo Jose Cela.";
		answers[1][2] = "Pablo Neruda.";

		answers[2][0] = "In Oceania.";
		answers[2][1] = "In Asia.";
		answers[2][2] = "In America.";

		answers[3][0] = "aprox. 10 minutes.";
		answers[3][1] = "aprox. 5 minutes.";
		answers[3][2] = "aprox. 4 minutes.";

		answers[4][0] = "A type of primate.";
		answers[4][1] = "8th day of the Aztec month.";
		answers[4][2] = "Ashes.";

		answers[5][0] = "A geographical formation.";
		answers[5][1] = "A bird.";
		answers[5][2] = "A plant.";

		answers[6][0] = "Milos Forman.";
		answers[6][1] = "Peter Jackson.";
		answers[6][2] = "Peter Forman.";

		answers[7][0] = "There is no answer to that.";
		answers[7][1] = "42.";
		answers[7][2] = "This is just a dream.";

		answers[8][0] = "Manowar.";
		answers[8][1] = "Metallica.";
		answers[8][2] = "Iron Maiden.";

		answers[9][0] = "1492.";
		answers[9][1] = "1602.";
		answers[9][2] = "1502.";

		answers[10][0] = "1981.";
		answers[10][1] = "1970.";
		answers[10][2] = "1989.";

		answers[11][0] = "Richard Wagner.";
		answers[11][1] = "Giuseppe Verdi.";
		answers[11][2] = "Wolfgang Amadeus Mozart.";

		// SOLUTIONS ----------------------------------
		solutions     = new String[12];

		solutions[0]  = "A";
		solutions[1]  = "A";
		solutions[2]  = "C";
		solutions[3]  = "C";
		solutions[4]  = "B";
		solutions[5]  = "B";
		solutions[6]  = "B";
		solutions[7]  = "A";
		solutions[8]  = "C";
		solutions[9]  = "A";
		solutions[10] = "C";
		solutions[11] = "A";
	}


	/* ---------------------------------------------
	 *		QUESTIONS & ANSWERS
	 *    questionr = true  --> return the question
	 *    questionr = false --> return answer r
	 * --------------------------------------------- */
	public String memory ( boolean questionr, int question, int r ) {

		try {
			if (questionr)
				return questions[question];
			else
				return answers[question][r];

		} catch ( ArrayIndexOutOfBoundsException e ) {
			return ("Reset");
		}
	}


	/* ---------------------------------------------
	 *		SOLUTIONS
	 * --------------------------------------------- */
	public String solutions(int question) {

		try {
			return solutions[question];

		} catch ( ArrayIndexOutOfBoundsException e ) {
			return ("Reset");
		}
	}


	/* ---------------------------------------------
	 *		NUMBER OF QUESTIONS
	 * --------------------------------------------- */
	public int totalQuestions() {
		return questions.length;
	}
}

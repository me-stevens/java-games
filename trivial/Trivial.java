/* ---------------------------------------------
 *		CLASS TRIVIAL
 * ---------------------------------------------*/

package trivial;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Trivial extends Frame implements ActionListener {
	
	private double points;
	private int lives;
	private int question;
	private boolean correct;
	private Button S, I, x, a, b, c, N;
	private Panel p1;
	private File ranking;
	ArrayList<Integer> available;

	
	/* ---------------------------------------------
	 *		CONSTRUCTOR
	 * --------------------------------------------- */
	public Trivial() {

		super("TRIVIAL PURSUIT");

		String path     = "trivial" + File.separator;
		String filename = "ranking.txt";
		ranking         = new File (path, filename);
		createRanking();
	
		points    = -1;
		lives     =  3;

		// ARRAY FOR THE 12 QUESTIONS --------------
		// (11 un-answered questions) --------
		available = new ArrayList<Integer>();
		for (int i=0; i<12; i++)
			available.add(i);

		setLayout( new BorderLayout() );
	
		// RIGHT PANEL -----------------------------
		S = new Button("Start");
		I = new Button("Instructions");
		x = new Button("Exit >");

		p1 = new Panel( new GridLayout(3,1) ); // (rows, cols)
		p1.add(S);
		p1.add(I);
		p1.add(x);
		add("East", p1);

		// ANSWER BUTTONS --------------------------
		a = new Button("A");
		b = new Button("B");
		c = new Button("C");
		N = new Button("Next >");

		a.setBounds(15,  60,  20, 25);
		b.setBounds(15,  90,  20, 25);
		c.setBounds(15, 120,  20, 25);
		N.setBounds(15, 160, 120, 20);
	
		a.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		N.addActionListener(this);

		// To close window with x ------------------
		addWindowListener( new CloseWindow() );

		S.addActionListener(this);
		I.addActionListener(this);
		x.addActionListener(this);
	
		setSize(650, 300);
	}

//	public String memory ( boolean questionr, int r ) {

//		// questionr = true  --> return the question
//		// questionr = false --> return answer r

//		String[] questions  =  new String[12];

//		questions[0]  = "What's the highest lake of the world?";
//		questions[1]  = "Who's the author of 'Crime and Punishment'?";
//		questions[2]  = "Where is Trinidad and Tobago?";
//		questions[3]  = "How much is a star delayed when passing through the same point after one day?";
//		questions[4]  = "Soot is...";
//		questions[5]  = "What's a golden oriole?";
//		questions[6]  = "Who's the director of Lord of the Rings?";
//		questions[7]  = "What's the answer to the ultimate question of Life, the Universe, and Everything?";
//		questions[8]  = "Whose song is 'Woman in uniform'?";
//		questions[9]  = "In which year was the Treaty of Tordesillas signed?";
//		questions[10] = "When did Salvador Dali die?";
//		questions[11] = "Who's the composer of 'Parsifal'?";
//	
//		String[][] answers = new String[12][3];

//		answers[0][0] = "Titicaca.";
//		answers[0][1] = "Poopo.";
//		answers[0][2] = "Lake Superior.";

//		answers[1][0] = "Dostoyewski.";
//		answers[1][1] = "Camilo Jose Cela.";
//		answers[1][2] = "Pablo Neruda.";

//		answers[2][0] = "In Oceania.";
//		answers[2][1] = "In Asia.";
//		answers[2][2] = "In America.";

//		answers[3][0] = "aprox. 10 minutes.";
//		answers[3][1] = "aprox. 5 minutes.";
//		answers[3][2] = "aprox. 4 minutes.";

//		answers[4][0] = "A type of primate.";
//		answers[4][1] = "8th day of the Aztec month.";
//		answers[4][2] = "Ashes.";

//		answers[5][0] = "A geographical formation.";
//		answers[5][1] = "A bird.";
//		answers[5][2] = "A plant.";

//		answers[6][0] = "Milos Forman.";
//		answers[6][1] = "Peter Jackson.";
//		answers[6][2] = "Peter Forman.";

//		answers[7][0] = "There is no answer to that.";
//		answers[7][1] = "42.";
//		answers[7][2] = "This is just a dream.";

//		answers[8][0] = "Manowar.";
//		answers[8][1] = "Metallica.";
//		answers[8][2] = "Iron Maiden.";

//		answers[9][0] = "1492.";
//		answers[9][1] = "1602.";
//		answers[9][2] = "1502.";

//		answers[10][0] = "1981.";
//		answers[10][1] = "1970.";
//		answers[10][2] = "1989.";

//		answers[11][0] = "Richard Wagner.";
//		answers[11][1] = "Giuseppe Verdi.";
//		answers[11][2] = "Wolfgang Amadeus Mozart.";
//	
//		try {
//			if (questionr)
//				return questions[question];
//			else
//				return answers[question][r];

//		} catch ( ArrayIndexOutOfBoundsException e ) {
//			reset();
//		}

//		if (questionr)
//			return questions[0];	// Avoids errors
//		else
//			return answers[0][r];
//	}
//	
//	public String solutions() {

//		String[] answers = new String[12];

//		answers[0]  = "A";
//		answers[1]  = "A";
//		answers[2]  = "C";
//		answers[3]  = "C";
//		answers[4]  = "B";
//		answers[5]  = "B";
//		answers[6]  = "B";
//		answers[7]  = "A";
//		answers[8]  = "C";
//		answers[9]  = "A";
//		answers[10] = "C";
//		answers[11] = "A";
//	
//		try {
//			return answers[question];

//		} catch ( ArrayIndexOutOfBoundsException e ) {
//			reset();
//		}

//		return "A";
//	}


	/* -------------------------------------------------------------------------
	 *    GAME FUNCTIONALITY
	 *    Since Trivial is not abstract it has to override abstract method 
	 *    actionPerformed(ActionEvent) in ActionListener
	 * -------------------------------------------------------------------------*/
	public void actionPerformed( ActionEvent event ) {

		if ( event.getActionCommand().equals("Start") )
			Start(); // Restart the values of life, points, etc.

		else if ( event.getActionCommand().equals("Instructions") ) {
			Instructions instructions = new Instructions(this, "Instructions", true);
			instructions.setVisible(true);
		}
		
		else if ( event.getActionCommand().equals("Exit >") )
			System.exit(0);

//		else if ( event.getActionCommand().equals("A") ) {

//			if ( solutions().equals("A")) {
//				correct = true;
//				points += 1000;
//			}
//			else {
//				correct = false;
//				lives--;
//			}

//			antireset();
//		}
//		
//		else if ( event.getActionCommand().equals("B") ) {

//			if ( solutions().equals("B") ) {
//				correct = true;
//				points += 1000;
//			}
//			else {
//				correct = false;
//				lives--;
//			}

//			antireset();
//		}
//		
//		else if ( event.getActionCommand().equals("C") ) {

//			if (solutions().equals("C")) {
//				correct = true;
//				points += 1000;
//			}
//			else {
//				correct = false;
//				lives--;
//			}

//			antireset();
//		}

//		else if ( event.getActionCommand().equals("Next >") )
//			reset();
	}


	/* ---------------------------------------------
	 *		START GAME
	 * --------------------------------------------- */
	public void Start() {

		setLayout(null);
		add(a);
		add(b);
		add(c);
		add(N);

		points = 0;
		lives  = 3;

		reset();
	}


	/* ---------------------------------------------
	 *		RESET LAYOUT
	 * --------------------------------------------- */
	public void reset() {

		randomQuestion();

		correct = false;

		a.setEnabled(true);
		b.setEnabled(true);
		c.setEnabled(true);
		N.setEnabled(false);
		check();
	}


	/* ---------------------------------------------
	 *		RESET LAYOUT REVERSED
	 * --------------------------------------------- */
//	public void antireset() {

//		N.setEnabled(true);
//		a.setEnabled(false);
//		b.setEnabled(false);
//		c.setEnabled(false);
//		check();
//	}


	/* ---------------------------------------------
	 *		LAUNCH QUESTION
	 *      Returns a random number 
	 *      between [0, not-answered-q's)
	 * --------------------------------------------- */
	public void randomQuestion() {

		Random questionNumber = new Random();

		if (available.size() > 0) {

			int pos  = questionNumber.nextInt(available.size());
			question = available.get(pos);
			available.remove(pos);
		}
	}
	

	/* ---------------------------------------------
	 *		CHECK
	 * --------------------------------------------- */
	public void check() {

		if (points == -1) {}
//			g.drawString("Trivial Pursuit.", 30, 77);

//		else {
//			g.drawString(memory(true,  0), 30, 50);
//			g.drawString(memory(false, 0), 45, 77);
//			g.drawString(memory(false, 1), 45, 107);
//			g.drawString(memory(false, 2), 45, 137);

//			// If it is not the first question
//			if (points != 0) {
//				if (correct)
//					g.drawString("Right answer.", 60, 200);
//				else
//					g.drawString("Wrong answer.", 60, 200);
//			}

//			g.drawString("Points: " + points, 70, 220);
//	
//			if (lives > -1) // Avoid showing -1 in lives
//				g.drawString("LIVES: " + lives, 70, 240);
//			else {
//				g.drawString("LIVES: 0", 70, 240);
//				g.drawString("Trivial GAME OVER", 70, 260);
//			}
//		}

//		if (lives <= 0) {
//			lives = -1;
//			N.setEnabled(false); // Deactivate next button
//			g.drawString("Trivial GAME OVER", 70, 260);
//			updateRanking();
//			Name name = new Name(this, "Name", true, ranking);
//			name.setVisible(true);
//		}
	}

	/* ------------------------------------------------------------
	 *  CREATE EMPTY RANKING FILE
	 * ------------------------------------------------------------ */
	public void createRanking() {

		// If file doesn't exist, create it
		if ( !ranking.exists() ) {

			PrintWriter pw = null;

			try {
				pw = new PrintWriter(new FileOutputStream(ranking));
		
				for (int i = 0;  i<10; i++)
					pw.println();
		
				for (int i = 10; i<20; i++)
					pw.println(" -- ");
		
			} catch(IOException e) {
				e.printStackTrace();

			} finally {
				if (pw != null) {
					try {
						pw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}


//	public void updateRanking() {

//		BufferedReader br  = null;
//		PrintWriter pw     = null;
//		String[] pointsStr = new String[20];
//	
//		try {
//			br = new BufferedReader(new FileReader(ranking));
//	
//			for (int i = 0; i<20; i++)
//				pointsStr[i] = br.readLine();

//		} catch(FileNotFoundException e) {
//			createRanking();
//			updateRanking();

//		} catch(IOException e) {
//			e.printStackTrace();

//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	
//		// String to Double conversion
//		double[] pointsDbl = new double[10];
//		for (int i = 0; i<10; i++)
//			pointsDbl[i] = Double.parseDouble(pointsStr[i]);
//	
//		// Sort from highest to lowest
//		double aux = 0;
//		int cont   = 0;
//		for (int i = 0; i<10; i++) {
//			if ( points >= pointsDbl[i]) {
//				aux          = pointsDbl[i];
//				pointsDbl[i] = points;
//				points       = aux;
//				cont++;
//			}
//		}
//	
//		// Double to String conversion
//		for(int i = 0; i<10; i++)
//			pointsStr[i] = String.valueOf(pointsDbl[i]);
//	
//		// Sort the players' names
//		int anticont = 10 - cont;
//		String name  = " ";
//		String auxn  = "";
//		for (int i = 1; i <= 10; i++) {
//			if (i > anticont) {
//				auxn           = pointsStr[9+i];
//				pointsStr[9+i] = name;
//				name           = auxn;
//			}
//		}
//	
//		try {
//			pw = new PrintWriter(new FileOutputStream(ranking));
//			for(int i = 0; i<20; i++)
//				pw.println(pointsStr[i]);

//		} catch(FileNotFoundException e) {
//			createRanking();
//			updateRanking();

//		} catch(Exception e) {
//			e.printStackTrace();

//		} finally {
//			if (pw != null) {
//				try {
//					pw.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
}

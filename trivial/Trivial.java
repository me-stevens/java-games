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
	private ArrayList<Integer> available;
	private Questions questions;
	private Graphics g;
	
	/* ---------------------------------------------
	 *		CONSTRUCTOR
	 * --------------------------------------------- */
	public Trivial() {

		super("TRIVIAL PURSUIT");

		String path     = "trivial" + File.separator;
		String filename = "ranking.txt";
		ranking         = new File (path, filename);
		createRanking();
	
		points = -1;
		lives  =  3;

		// ARRAY FOR THE X QUESTIONS --------------
		// (X-1 un-answered questions) --------
		questions = new Questions();
		available = new ArrayList<Integer>();

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

		S.addActionListener(this);
		I.addActionListener(this);
		x.addActionListener(this);

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

		setSize(650, 300);
	}


	/* -------------------------------------------------------------------------
	 *    GAME FUNCTIONALITY
	 *    Since Trivial is not abstract it has to override abstract method 
	 *    actionPerformed(ActionEvent) in ActionListener
	 * -------------------------------------------------------------------------*/
	public void actionPerformed( ActionEvent event ) {

		// START ---------------------------------------------------------------
		if ( event.getActionCommand().equals("Start") ) {

			setLayout(null);
			add(a);
			add(b);
			add(c);
			add(N);
			S.setEnabled(false);

			points  = 0;
			lives   = 5;
			correct = false;

			available.clear();
			for (int i=0; i<questions.totalQuestions() - 1; i++)
				available.add(i);

			askQuestion(true);
		}

		// INSTRUCTIONS --------------------------------------------------------
		else if ( event.getActionCommand().equals("Instructions") ) {
			Instructions instructions = new Instructions(this, "Instructions", true);
			instructions.setVisible(true);
		}
		
		// EXIT ----------------------------------------------------------------
		else if ( event.getActionCommand().equals("Exit >") )
			System.exit(0);

		// ANSWER BUTTONS ------------------------------------------------------
		else if (  event.getActionCommand().equals("A")
                || event.getActionCommand().equals("B")
                || event.getActionCommand().equals("C") ) {

			if ( questions.solutions(question).equals( event.getActionCommand() ) ) {

				correct = true;
				points += 1000;
			}

			else {

				correct = false;
				lives--;
			}

			askQuestion(false);
		}

		// NEXT BUTTON --------------------------------------------------------
		else if ( event.getActionCommand().equals("Next >") )
			askQuestion(true);
	}


	/* ---------------------------------------------
	 *		ASK QUESTION
	 * --------------------------------------------- */
	public void askQuestion(boolean reset) {

		g = this.getGraphics();

		// RESET LAYOUT ----------------------------
		if (reset) {
			a.setEnabled(true);
			b.setEnabled(true);
			c.setEnabled(true);
			N.setEnabled(false);

			// LAUNCH QUESTION ---------------------------
			// Returns a random number between [0, not-answered-q's)
			Random questionNumber = new Random();
			if (available.size() > 0) {
				int pos  = questionNumber.nextInt(available.size());
				question = available.get(pos);
				available.remove(pos);
			}
			else {
				g.drawString("No more questions in the deck", 200, 260);
				gameOver();
			}
		}

		else {
			a.setEnabled(false);
			b.setEnabled(false);
			c.setEnabled(false);
			N.setEnabled(true);
		}

		// DRAW QUESTION ---------------------------

		if (points == -1)
			g.drawString("Trivial Pursuit.", 30, 77);

		else {
			g.drawString(questions.memory(true,  question, 0), 30, 50);
			g.drawString(questions.memory(false, question, 0), 45, 77);
			g.drawString(questions.memory(false, question, 1), 45, 107);
			g.drawString(questions.memory(false, question, 2), 45, 137);

			// If not the first question
			if (  points != 0 
               || lives  != 5 ) {

				if (correct)
					g.drawString("Right answer.", 60, 200);
				else
					g.drawString("Wrong answer.", 60, 200);
			}

			g.drawString("Points: " + points, 70, 220);

			// Avoid showing -1 in lives
			if (lives > -1)
				g.drawString("LIVES: " + lives,   70, 240);
			else {
				g.drawString("LIVES: 0",          70, 240);
				gameOver();
			}
		}

		if (lives <= 0) {
			lives = -1;

			gameOver();
		}
	}


	/* ------------------------------------------------------------
	 *  GAME OVER
	 * ------------------------------------------------------------ */
	public void gameOver() {

		N.setEnabled(false); 
		S.setEnabled(true);
		g.drawString("Trivial GAME OVER", 70, 260);

		Name name = new Name(this, "Name", true, ranking);
		name.setVisible(true);
		updateRanking( name.getName() );

		name.showRanking( name.getName() );
		name.setVisible(true);
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


	/* ------------------------------------------------------------
	 *  UPDATE RANKING FILE
	 * ------------------------------------------------------------ */
	public void updateRanking(String name) {

		BufferedReader br  = null;
		PrintWriter pw     = null;
		String[] pointsStr = new String[20];

		// Reading the file --------------------------------
		try {
			br = new BufferedReader(new FileReader(ranking));
	
			for (int i = 0; i<20; i++)
				pointsStr[i] = br.readLine();

		} catch( FileNotFoundException e ) {
			createRanking();
			updateRanking(name);

		} catch( IOException e ) {
			e.printStackTrace();

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
		// String to Double conversion ---------------------
		double[] pointsDbl = new double[10];
		for (int i = 0; i<10; i++)
			if ( !pointsStr[i].equals("") )
				pointsDbl[i] = Double.parseDouble(pointsStr[i]);

		// Sort from highest to lowest ---------------------
		double aux  =  0;
		String auxn = "";
		for (int i = 0; i<10; i++) {
			if ( points >= pointsDbl[i]) {
				aux          = pointsDbl[i];
				pointsDbl[i] = points;
				points       = aux;

				auxn            = pointsStr[10+i];
				pointsStr[10+i] = name;
				name            = auxn;
			}
		}
	
		// Double to String conversion ---------------------
		for(int i = 0; i<10; i++)
			pointsStr[i] = String.valueOf(pointsDbl[i]);
	
		// Writing the file --------------------------------
		try {
			pw = new PrintWriter(new FileOutputStream(ranking));
			for(int i = 0; i<20; i++)
				pw.println(pointsStr[i]);

		} catch(FileNotFoundException e) {
			createRanking();
			updateRanking(name);

		} catch(Exception e) {
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

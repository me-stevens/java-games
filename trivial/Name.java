/* ---------------------------------------------i
 *		CLASS NAME
 *   Dialog modal window to enter the name
 * ---------------------------------------------*/

package trivial;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Name extends Dialog implements ActionListener { 

	private Trivial dad;
	private Panel p1, p2, p3, p4;
	private Label name;
	private TextField text;
	private Button close;
	private File ranking;

	
	/* ------------------------------------------------------------
	 *  CONSTRUCTOR
	 * ------------------------------------------------------------ */
	public Name (Frame f, String title, boolean modal, File ranking) {

		super (f, title, modal);
		this.ranking = ranking;

		dad = (Trivial) f;
		p1  = new Panel( new GridLayout( 3, 1) );
		p2  = new Panel();
		p3  = new Panel();
		p4  = new Panel( new GridLayout(10, 1) );

		p1.add( new Label("	Trivial GAME OVER.") );
		p1.add( new Label("	Write your name and press Enter.") );
		add("North", p1);

		name = new Label("Name or alias: ");
		text = new TextField(30);
		text.addActionListener(this);
		p2.add(name);
		p2.add(text);
		add("Center", p2);

		close  = new Button("Close >");
		close.addActionListener(this);
		p3.add(close);
		add("South", p3);
	
		pack();
	}


	/* ------------------------------------------------------------
	 *  EVENT LISTENER
	 * ------------------------------------------------------------ */
	public void actionPerformed (ActionEvent event) {

		Object source = event.getSource();
		if (source == close)
			dispose();
		else if (source == text) {
		}
	}


	/* ------------------------------------------------------------
	 *  SHOW RANKING
	 * ------------------------------------------------------------ */
	public void showRanking(String name) {

		BufferedReader br  = null;
		PrintWriter pw     = null;
		String[] pointsStr = new String[20];

		// Reading the file --------------------------------
		try {
			br = new BufferedReader(new FileReader(ranking));
	
			for (int i = 0; i<20; i++)
				pointsStr[i] = br.readLine();

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

		int pos = 0;
		for(int j=0; j<10; j++) {

			if ( pointsStr[j+10].equals(name) )
				pos = j;

			Label lranking = new Label((j+1) + ": " + pointsStr[j+10] + ": " + pointsStr[j] + " points.");
			p4.add(lranking);
		}
		add("East", p4);

		if (pos != 0)
			p1.add( new Label("	You're not in the top ten.") );
		else
			p1.add( new Label("	CONGRATULATIONS! You are at the top place ") );

		setSize(500, 300);
	}



	/* ------------------------------------------------------------
	 *  RETRIEVE THE ENTERED STRING
	 * ------------------------------------------------------------ */
	public String getName() {
		return text.getText();
	}

}

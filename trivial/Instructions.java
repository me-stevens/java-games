/* ---------------------------------------------
 *		       CLASS INSTRUCTIONS
 *     A pop-up window explaining the game
 * ---------------------------------------------*/

package trivial;

import java.awt.*;
import java.awt.event.*;

public class Instructions extends Dialog implements ActionListener {

	private Trivial dad;
	private Button ok;
	private Panel p1, p2;
	
	// CONSTRUCTOR
	public Instructions( Frame f, String title, boolean modal) {

		super(f, title, modal);
		dad = (Trivial)f;

		// Text
		p1 = new Panel(new GridLayout(3, 1) );
		p1.add( new Label("TRIVIAL PURSUIT") );
		p1.add( new Label("Ask the questions correctly and win 1000 points for each correct one.") );
		add("Center", p1);

		// OK button
		p2 = new Panel();
		ok = new Button("OK");
		ok.addActionListener(this);
		p2.add(ok);
		add("South", p2);
		
		pack();
	}
	
	// EVENTS AND DELEGATES
	public void actionPerformed( ActionEvent event ) {
		Object source = event.getSource();
		if ( source == ok )
			dispose();
	}
}

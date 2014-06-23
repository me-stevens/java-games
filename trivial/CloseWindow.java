/* ---------------------------------------------
 *		CLASS CLOSEWINDOW
 * ---------------------------------------------*/

package trivial;

import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class CloseWindow extends WindowAdapter {

	// Invoked when a window has been opened.
	public void windowOpened( WindowEvent e ) {
	}

	// Invoked when a window has been closed
	public void windowClose( WindowEvent e ) {
	}

	// Invoked when a window is in the process of being closed
	public void windowClosing( WindowEvent e ) {
		System.exit(0);
	}
}

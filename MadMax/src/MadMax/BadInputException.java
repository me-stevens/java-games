/*
 * This class defines a generic exception
 */
package MadMax;

public class BadInputException extends Exception {

	public BadInputException(String msj) {
		super(msj + "\n");
	}
}

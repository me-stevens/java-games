/*
 * This class provides methods for reading from the standard input
 */
package MadMax;

import java.util.Scanner;

public class NumberReader {

	/* 
	 * No exception thrown.
	 * If the input is not a number, it keeps asking,
	 * until a number is provided.
	 */
	public int readInt(String textInput) {
		Scanner reader   = new Scanner(System.in);
		boolean isNumber = true;
		int number       = 0;

		do {
			System.out.print(textInput);
			String test     = reader.nextLine();
			Scanner scanner = new Scanner(test);
			
			if (scanner.hasNextInt()) {
				// You can write 5e3 and it will store a 5000:
				number   = Integer.parseInt(test); 
				isNumber = true;
			}
			
			else {
				System.out.print("ERROR - You should introduce a number. ");
				isNumber = false;
			}

		} while (!isNumber);

		return number;
	}

	// -------------------------------------------------------------------------
	public double readDouble(String textInput) {
		Scanner reader   = new Scanner(System.in);
		boolean isNumber = true;
		double number    = 0;

		do {
			System.out.print(textInput);
			String test     = reader.nextLine();
			Scanner scanner = new Scanner(test);
			
			if (scanner.hasNextDouble()) {
				// You can write 5e3 and it will store a 5000:
				number   = Double.parseDouble(test); 
				isNumber = true;
			}
			
			else {
				System.out.print("ERROR - You should introduce a number. ");
				isNumber = false;
			}

		} while (!isNumber);

		return number;
	}

	/* 
	 * Exception thrown, since it reads from a file, one line at a time, 
	 * and if it is not a number, the line can not be changed.
	 */
	public int readIntFromFile(String test) throws BadInputException {
		Scanner scanner = new Scanner(test);
		int number      = 0;

		if (scanner.hasNextInt())
			number = Integer.parseInt(test);

		else
			throw new BadInputException("ERROR - data from file should be a number. ");

		return number;
	}

	public double readDoubleFromFile(String test) throws BadInputException {
		Scanner scanner = new Scanner(test);
		double number   = 0;

		if (scanner.hasNextDouble())
			number = Double.parseDouble(test);

		else
			throw new BadInputException("ERROR - data from file  should be a number. ");

		return number;
	}

}

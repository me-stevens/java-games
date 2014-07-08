/*
 * Define the particular behaviour for resistants
 */
package MadMax;

import java.util.InputMismatchException;

public class Resistant extends Human {
	
	// Increment to level-up:
	private double delta = 20;
	private double initialExperience = 20;


	/*
	 * Telescopic constructors
	 * Resistants start with lifeMax = 100, experience = 20, and they level-up in deltas of 20
	 */
	public Resistant() throws BadInputException, InputMismatchException {
		setID(0);
		setName("Resistant");
		setLifeMax(100);
		setLife(getLifeMax());
		setExperience(initialExperience);
	}

	public Resistant(String name, double life, double experience) throws BadInputException, InputMismatchException {
		this(0, name, life, experience);
	}

	public Resistant(int id, String name, double life, double experience) throws BadInputException, InputMismatchException {		
		super(id, name, life, experience);
		setLifeMax(100);

		if (experience > initialExperience) {
			setExperience(initialExperience);
			System.out.println("ATENTION: Your maximum initial experience is " + initialExperience + ".");
		}
	}

	// Implementation of parent class methods ----------------------------------

	@Override
	public void die() {
		super.die();
		System.out.println("..........¡NOOOOOO! ¡" + getName() + " DIED!");
	}

	@Override
	protected void levelUp() {
		try {
			setExperience(getExperience() + delta);
		} catch (BadInputException | InputMismatchException ex) {
			System.out.println(ex.getMessage());
		}
		
		try {
			setLifeMax(getLifeMax() + delta);
		} catch (BadInputException | InputMismatchException ex) {
			System.out.println(ex.getMessage());
		}
		
		try {
			setLife(getLife() + delta);
		} catch (BadInputException | InputMismatchException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public String toString() {
		return "ID = " + getID() + "\n" + super.toString();
	}

}
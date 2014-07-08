/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MadMax;

import java.util.InputMismatchException;

public class Enemy extends Human {
	
	// Increment to level-up:
	private double delta = 10;
	private double initialExperience = 10;

	/*
	 * Telescopic constructors
	 * Enemys start with lifeMax = 200, experience = 10, and they level-up in deltas of 10
	 */
	public Enemy() throws BadInputException, InputMismatchException {
		setID(0);
		setName("Enemy");
		setLifeMax(200);
		setLife(getLifeMax());
		setExperience(initialExperience);
	}

	public Enemy(String name, double life, double experience) throws BadInputException, InputMismatchException {
		this(0, name, life, experience);
	}

	public Enemy(int id, String name, double life, double experience) throws BadInputException, InputMismatchException {		
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
		System.out.println("..........One enemy out. R.I.P. " + getName());
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
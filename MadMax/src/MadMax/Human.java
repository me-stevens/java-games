/*
 * Defines a Human abstract class
 */
package MadMax;

import java.util.InputMismatchException;

public abstract class Human {

	private int ID;
	private String name;
	private double lifeMax;
	private double life;
	private double experience;

	public Human() {
		ID         = 0;
		name       = "";
		lifeMax    = 10;
		life       = lifeMax;
		experience = 0.0;
	}

	public Human(int ID, String name, double life, double experience) throws BadInputException, InputMismatchException {
		if (ID < 0)
			throw new BadInputException("ERROR - Negative ID.");
		
		if (name.isEmpty())
			throw new BadInputException("ERROR - Empty name. The mother suffers an abortion");
		
		else if (life <= 0)
			throw new BadInputException("ERROR - Negative life. Kid is born dead.");
		
		else if (experience < 0)
			throw new BadInputException("ERROR - Soul-less zombie. We killed it with fire.");
		
		else {
			this.ID         = ID;
			this.name       = name;
			this.lifeMax    = life;
			this.life       = life;
			this.experience = experience;
		}
	}
	
	// Setters -----------------------------------------------------------------
	public void setName(String name) throws BadInputException, InputMismatchException {
		if (name.isEmpty())
			throw new BadInputException("ERROR - Name is empty. Current name: " + this.name + ".");
		
		else
			this.name = name;
	}

	public void setLife(double life) throws BadInputException, InputMismatchException {
		if (life > this.lifeMax)
			throw new BadInputException("ERROR - The maximum life you can have is " + this.lifeMax + ". Current life: " + this.life + ".");
		
		else if (life < 0)
			die();
		
		else
			this.life = life;
	}

	public void setExperience(double experience) throws BadInputException, InputMismatchException {
		if (experience < 0)
			throw new BadInputException("ERROR - You can't have negative experience. Current experience " + this.experience + ".");
		
		else
			this.experience = experience;
	}

	public void setLifeMax(double lifeMax) throws BadInputException, InputMismatchException {
		if (lifeMax <= 0)
			throw new BadInputException("ERROR - Maximum life is <= 0, suicide is not allowed. Current maximum life: " + this.lifeMax + ".");
		
		else {
			this.lifeMax = lifeMax;
			
			if (this.lifeMax < this.life)
				this.life = this.lifeMax;
		}
	}

	public void setID(int ID) throws BadInputException, InputMismatchException {
		if (ID < 0)
			throw new BadInputException("ERROR - Id number is negative. Current ID: " + this.ID + ".");
		
		else
			this.ID = ID;
	}

	// Getters -----------------------------------------------------------------

	public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}

	public double getLife() {
		return life;
	}

	public double getExperience() {
		return experience;
	}

	public double getLifeMax() {
		return lifeMax;
	}


	// Extra methods -----------------------------------------------------------
	public double doHarm() {
		double harm = (2 * getLife() + 3 * getExperience()) / 5.0;
		return harm;
	}

	public double receiveHarm(double harm) {
		
		if (harm >= getLife()) {
			die();
			return getLife();
		}
		
		else if (harm < 0) {
			harm = Math.abs(harm);
			System.out.println("ATENTION: Negative harm. Updated value: " + harm + ".");
		}
		
		try {
			setLife(getLife() - harm);
		} catch (BadInputException | InputMismatchException ex) {
			System.out.println(ex.getMessage());
		}

		return getLife();
	};

	public void die() {
		this.life = 0;
	}

	protected abstract void levelUp();
	
	public void heal() {
		// If life <= 0 it would be a resurrect and not a heal.
		if (getLife() > 0) {
			try {
				setLife(getLifeMax());
			} catch (BadInputException | InputMismatchException ex) {
			System.out.println(ex.getMessage());
		}
		}
	}
	
	public void hitTarget(Human member) {
		double harm = doHarm();
		member.receiveHarm(harm);
		levelUp();

		System.out.println(getName() + " shoots " + member.getName() + " and takes out " + harm + " points of life.");
	}
	
	@Override
	public String toString() {
		return "Name = " + name + "\nlife = " + life + "\nlife maximum = " + lifeMax + "\nexperience = " + experience + "\n";
	}
}

/*
 * Functionality for every gang
 */
package MadMax;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Gang {

	private ArrayList<Human> listOfMembers;

	public Gang() {
		listOfMembers = new ArrayList<>();
	}

	public ArrayList<Human> getListOfMembers() {
		return listOfMembers;
	}

	public int getCensus() {
		return listOfMembers.size();
	}

	/*
	 * tag: "res" = resistent, "ene" = enemy
	 */
	public void createNewMember(String tag, int id, String name, double life, double experience) throws BadInputException, InputMismatchException {
		Human member = null;

		try {
			if (tag.equalsIgnoreCase("res"))
				member = new Resistant(id, name, life, experience);
				
			else if (tag.equalsIgnoreCase("ene"))
				member = new Enemy(id, name, life, experience);

		} catch (BadInputException | InputMismatchException ex) {
			System.out.println(ex.getMessage());
		} finally {
			addMember(member);
		}
	}
	
	private void addMember(Human member) {

		if (member == null)
			System.out.println("No new member to join the gang.");

		else {
			listOfMembers.add(member);

			System.out.println("\nAuthorized entry.");
			Printer printer = new Printer();
			printer.printCardForName(member);
		}
	}
	
	/*
	 * Add from Screen:
	 *    Standard input from the user.
	 * Add from file:
	 *    Read member data from a file previously produced by the game.
	 */
	public int recruit(String input, String tag) throws BadInputException, InputMismatchException {
		
		if (input.equalsIgnoreCase("screen"))
			addFromScreen(tag);

		else if (input.equalsIgnoreCase("file"))
			addFromFile(tag);

		return listOfMembers.size();
	}

	/*
	 * Ask for the ID first. 
	 * If the ID exists, abort.
	 * Else, create member and add it to the list.
	 */
	public void addFromScreen(String tag) throws BadInputException, InputMismatchException {
		SearchEngine searchEngine = new SearchEngine();
		NumberReader numberReader = new NumberReader();
		int id                    = numberReader.readInt("ID: ");
		
		if (!listOfMembers.isEmpty() && searchEngine.getHumanWithID(listOfMembers, id) != null)
			System.out.println("¡ALERT! ¡Covert spy! Unauthorized entry.");
		
		else {
			Scanner scanner = new Scanner(System.in);

			System.out.print("Name: ");
			String name = scanner.nextLine();			
			double life = numberReader.readDouble("Life: ");
			double experience = numberReader.readDouble("Experience: ");
			createNewMember(tag, id, name, life, experience);
		}
		
		System.out.println("Current census = " + listOfMembers.size());
	}

	
	/* 
	 * Reading SEVERAL members from a file
	 */
	public void addFromFile(String tag) throws BadInputException, InputMismatchException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Name of the file with the member's data: ");
		String filename = scanner.nextLine();
		
		addFromFile(tag, filename);
	}

	/* 
	 * Reading error:
	 *    Stop reading file
	 * Constructor error:
	 *    Object is not created, but continue reading file
	 */
	// MAP: name -> life -> experience -> id
	public void addFromFile(String tag, String filename) throws BadInputException, InputMismatchException {
		int id;
		String name;
		double life, experience;

		NumberReader numberReader = new NumberReader();
		SearchEngine searchEngine = new SearchEngine();
		BufferedReader buffer     = null;
			
		int old = listOfMembers.size();
		String test = null;
		
		try {
			buffer = new BufferedReader(new FileReader(filename));
			name   = buffer.readLine();

			while (name != null) {
				test = buffer.readLine();
				life = numberReader.readDoubleFromFile(test);
				
				test = buffer.readLine();
				experience = numberReader.readDoubleFromFile(test);
				
				test = buffer.readLine();
				id   = numberReader.readIntFromFile(test);

				System.out.println("-----------------");
				System.out.println("Processing entry...");
				
				if (searchEngine.getHumanWithID(listOfMembers, id) != null)
					System.out.println("¡ALERT! ¡Covert spy! Unauthorized entry.");
				
				else
					createNewMember(tag, id, name, life, experience);
				
				System.out.println("Current census = " + listOfMembers.size());
				
				// Start reading next member
				name = buffer.readLine();
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

		System.out.println("Added " + (listOfMembers.size() - old) + " new members.");
	}

	public void fight(Gang gang) {
		
		if (listOfMembers.isEmpty() || gang.getListOfMembers().isEmpty())
			System.out.println("No body to fight. Recruit somebody first.");
		
		else if (getListOfMembers().equals(gang.getListOfMembers()))
			System.out.println("Don't shoot your own gang!");
		
		else {
			int sniper1, sniper2, i = 0;

			Scanner scanner = new Scanner(System.in);
			System.out.println("\nPress any key to start the slaughter.");
			scanner.nextLine();

			do {
				i++;
				System.out.println("-----------------------------------------");
				System.out.println("                 TURN " + i);
				System.out.println("-----------------------------------------");
				
				// Choose random shooter
				sniper1 = (int) (listOfMembers.size() * Math.random());

				// If the resistance starts
				if (listOfMembers.get(sniper1) instanceof Resistant) {
					
					System.out.println("--> Now shooting " + listOfMembers.get(sniper1).getName() + ", from the resistance:");
					shoot(listOfMembers.get(sniper1), gang);
					
					if (!gang.getListOfMembers().isEmpty()) {
						// Choose random shooter
						sniper2 = (int) (gang.getListOfMembers().size() * Math.random());
						System.out.println("--> Now shooting " + gang.listOfMembers.get(sniper2).getName() + ", from the enemy gang:");
						shoot(gang.listOfMembers.get(sniper2), this);
					}
				}
				
				else if (listOfMembers.get(sniper1) instanceof Enemy) {
					System.out.println("--> Now shooting " + listOfMembers.get(sniper1).getName() + ", from the enemy gang:");
					shoot(listOfMembers.get(sniper1), gang);
					
					if (!gang.getListOfMembers().isEmpty()) {
						sniper2 = (int) (gang.getListOfMembers().size() * Math.random());
						System.out.println("--> Now shooting " + gang.listOfMembers.get(sniper2).getName() + ", from the resistance:");
						shoot(gang.listOfMembers.get(sniper2), this);                       
					}
				}
				
				//                if (i % 20 == 0) {
				//                    System.out.println("\nPress any key to display the next 20.");
				//                    scanner.nextLine();
				//                }
			} while (!listOfMembers.isEmpty() && !gang.getListOfMembers().isEmpty());
			
			System.out.println("-----------------------------------------");
			System.out.println("             END OF FIGHT           ");
			System.out.println("          - Took: " + i + " turns - ");
			System.out.println("-----------------------------------------");
		}
	}

	public void shoot(Human member, Gang gang) {
		int target = (int) (1 + gang.getCensus() * Math.random());
		int range  = 10;       
		int hits   = 0;

		for (Human victim : gang.getListOfMembers()) {
			// The shooting affects an environment of 2*range centered in the target
			if ((victim.getID() > target - range) && (victim.getID() < target + range)) {
				
				if (member instanceof Resistant)
					((Resistant) member).hitTarget(victim);
				
				else if (member instanceof Enemy)
					((Enemy) member).hitTarget(victim);
				
				hits++;
			}
		}
		
		if (hits == 0)
			System.out.println(member.getName() + " is a moron and has not hit a single target.");
		else {
			System.out.println("¡" + member.getName() + " has hit " + hits + " targets!");
			gang.cleanDeadBodies();
		}
	}

	/*
	 * With die() you can be resurrected, but not with kill()
	 * Kill doesn't ckeck if life = 0, because you can't kill an already dead person
	 * For that task, we call cleanDeadBodies()
	 */
	public void kill(Human member) {
		if (member != null) {
			String name   = member.getName();
			int id        = member.getID();
			boolean found = listOfMembers.remove(member);
			
			if (found)
				System.out.println(name + ", with ID = " + id + ", has gone to Valhalla.");
			
			else
				System.out.println("This person is not in my list.");

		}
		
		else
			System.out.println("You can't kill a ghost!");
	}

	// kill(member) overload
	public Human kill(int pos) {
		return listOfMembers.remove(pos);
	}

	public void cleanDeadBodies() {
		for (int i = 0; i < this.listOfMembers.size(); i++) {
			if (listOfMembers.get(i) != null && listOfMembers.get(i).getLife() == 0) {
				kill(i);
			}
		}
	}
}

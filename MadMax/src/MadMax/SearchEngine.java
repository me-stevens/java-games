/*
 * Class in charge of searching members of a gang
 */
package MadMax;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {

	/* 
	 * Doesn't prevent from duplicate names.
	 * Returns the first.
	 * Prevents from duplicated IDs.
	 */
	public Human searchMember(ArrayList<Human> listOfMembers, String searchBy) {
		Human member = null;

		if (listOfMembers.isEmpty())
			System.out.println("There is no one here. Recruit somebody first.");

		else if (searchBy.equalsIgnoreCase("name"))
			member = searchByName(listOfMembers);
	
		else if (searchBy.equalsIgnoreCase("id"))
			member = searchByID(listOfMembers);

		return member;
	}

	public Human searchByName(ArrayList<Human> listOfMembers) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Name: ");
		String name  = scanner.nextLine();
		Human member = getHumanNamed(listOfMembers, name);

		Printer printer = new Printer();
		printer.printCardForName(member);
		
		return member;
	}
	
	public Human searchByID(ArrayList<Human> listOfMembers) {
		NumberReader reader = new NumberReader();
		int id       = reader.readInt("ID: ");
		Human member = getHumanWithID(listOfMembers, id);
		
		Printer printer = new Printer();
		printer.printCardForID(member);
		
		return member;
	}

	/*
	 * Returns the first.
	 * There could be duplicates.
	 */
	public Human getHumanNamed(ArrayList<Human> listOfMembers, String name) {
		
		for (Human member : listOfMembers)
			if (member != null && member.getName().equalsIgnoreCase(name))
				return member;

		return null;
	}

	/*
	 * No repeated IDs
	 */
	public Human getHumanWithID(ArrayList<Human> listOfMembers, int ID) {

		for (Human member : listOfMembers)
			if (member != null && member.getID() == ID)
				return member;

		return null;
	}

}
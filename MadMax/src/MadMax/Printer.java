/*
 * A class with printing functions
 */
package MadMax;

import java.util.ArrayList;

public class Printer {

	// -------------------------------------------------------------------------
	public void printCardForName(Human member) {
		
		if (member != null) {
			System.out.println("\nCARD FOR " + member.getName().toUpperCase() + ":");
			System.out.println(member);
		}

		else
			System.out.println("Who? That person is not here.");
	}

	// -------------------------------------------------------------------------
	public void printCardForID(Human member) {
		
		if (member != null) {
			System.out.println("\nCARD FOR ID = " + member.getID() + ":");
			System.out.println(member);
		}

		else
			System.out.println("There's nobody with that ID.");
	}
	

	public void listSurvivors(ArrayList<Human> listOfMembers) {
		
		if (listOfMembers.isEmpty())
			System.out.println("There is no one here. Recruit somebody first.");
		
		else {
			System.out.println("\nSURVIVORS:");
			System.out.printf("%n i \t ID \t    Name\t      Life\t  Experience\t  Life Maximum%n");
			for (Human member : listOfMembers) {
				System.out.printf("%n %d \t %03d \t %10s \t %10.2f \t %10.2f \t %10.2f",
						listOfMembers.indexOf(member),
						member.getID(),
						member.getName(),
						member.getLife(),
						member.getExperience(),
						member.getLifeMax());
			}
			System.out.println("\n");
		}
	}

}

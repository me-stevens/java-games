/*
 * Main Program
 */
package MadMax;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MadMax {

	public static void main(String[] args)  throws BadInputException, InputMismatchException {
		String option, a;
		Gang enemies    = new Gang();
		Gang resistance = new Gang();

		Printer printer = new Printer();
		
		// MENU PRINCIPAL
		System.out.println(" =-=-=-=-=-=-=-=-=-=-=- MAD MAX BATTLE -=-=-=-=-=-=-=-=-=-=-=-=\n");
		System.out.println("          In a post-apocalyptic future, two gangs");
		System.out.println("       will have a fight in the middle of the desert.");
		System.out.println("     BUT THERE'S ONLY SPACE FOR ONE... ¡MWAHWAHWAHAHAHAHA...!");

		do {
			option = menu();
			switch (option) {
				case "1":
					do {
						System.out.println("\n =-=-=-=-=-=- RECRUITING -=-=-=-=-=-=");
						a = submenu1();
						switch (a) {
							case "1":
								enemies.recruit("screen", "ene");
								break;
							case "2":
								enemies.recruit("file", "ene");
								break;
							case "3":
								resistance.recruit("screen", "res");
								break;
							case "4":
								resistance.recruit("file", "res");
								break;
							case "0":
								System.out.println();
								break;
							default:
								System.out.println("Wrong option.");
								break;
						}
					} while (!a.equals("0"));
					break;

				case "2":
					do {
						System.out.println("\n =-=-=-=-=- SPYING / MEETING -=-=-=-=-=");
						SearchEngine searchEngine = new SearchEngine();
						a = submenu2();
						switch (a) {
							case "1":
								searchEngine.searchMember(enemies.getListOfMembers(), "name");
								break;
							case "2":
								searchEngine.searchMember(enemies.getListOfMembers(), "id");
								break;
							case "3":
								searchEngine.searchMember(resistance.getListOfMembers(), "name");
								break;
							case "4":
								searchEngine.searchMember(resistance.getListOfMembers(), "id");
								break;
							case "0":
								System.out.println();
								break;
							default:
								System.out.println("Wrong option.");
								break;
						}
					} while (!a.equals("0"));
					break;

				case "3":
					System.out.println("\n =-=-=-=-=- FIGHT -=-=-=-=-=");
					
					System.out.println("Rolling the dice...");
					boolean first = ((int) (2 * Math.random()) == 0) ? true : false;
					
					if (first) {
						System.out.println("The resistance shoots first!");
						resistance.fight(enemies);
					} else {
						System.out.println("The enemies shoot first!");
						enemies.fight(resistance);
					}
					
					if (resistance.getCensus() == 0) {
						System.out.println("The enemy wins!");
						printer.listSurvivors(enemies.getListOfMembers());
					} else {
						System.out.println("The resistance wins!");
						printer.listSurvivors(resistance.getListOfMembers());
					}
					
					break;

				case "4":
					do {
						System.out.println("\n =-=-=-=-=-=- SEND THE HITMEN -=-=-=-=-=-=");
						System.out.println("Somebody is going for their last walk. Choose who.\n");
						SearchEngine searchEngine = new SearchEngine();
						a = submenu2();
						switch (a) {
							case "1":
								enemies.kill(searchEngine.searchMember(enemies.getListOfMembers(), "name"));
								break;
							case "2":
								enemies.kill(searchEngine.searchMember(enemies.getListOfMembers(), "id"));
								break;
							case "3":
								resistance.kill(searchEngine.searchMember(resistance.getListOfMembers(), "name"));
								break;
							case "4":
								resistance.kill(searchEngine.searchMember(resistance.getListOfMembers(), "id"));
								break;
							case "0":
								System.out.println();
								break;
							default:
								System.out.println("Wrong option.");
								break;
						}
					} while (!a.equals("0"));
					break;

				case "5":
					System.out.println("\n =-=-=-=-=-=- LIST SURVIVORS -=-=-=-=-=-=");
					System.out.println("\nENEMY GANG:");
					printer.listSurvivors(enemies.getListOfMembers());
					System.out.println("\nRESISTANCE GANG:");
					printer.listSurvivors(resistance.getListOfMembers());
					break;

				case "0":
					System.out.println("\n =-=-=-=-=-=- RUN AWAY AS A CHICKEN -=-=-=-=-=-=");
					System.out.println("\nGoing back to the present in");
					System.out.println("3...");
					System.out.println("2...");
					System.out.println("1...");
					System.out.println("\n    ¡¡¡¡¡¡¡¡ BEHIND YOU !!!!!!\n");
					break;

				default:
					System.out.println("I'm afraid that's not an option, comrade.\n");
					break;
			}
		} while (!option.equals("0"));
	}

	public static String menu() {
		Scanner scanner = new Scanner(System.in);
		String a;

		System.out.println();
		System.out.println("(1) RECRUIT.");
		System.out.println("(2) SPYING / MEETING ");
		System.out.println("(3) FIGHT");
		System.out.println("(4) SEND THE HITMEN");
		System.out.println("(5) LIST SURVIVORS");
		System.out.println("(0) RUN AWAY AS A CHICKEN");
		System.out.print("\nSelect an option if you have what it takes to: ");
		a = scanner.nextLine();
		return a;
	}

	public static String submenu1() {
		Scanner scanner = new Scanner(System.in);
		String a;

		System.out.println(" (1) Recruit members of the enemy gang (manually).");
		System.out.println(" (2) Recruit members of the enemy gang (read file).");
		System.out.println(" (3) Recruit comrades in the resistance (manually).");
		System.out.println(" (4) Recruit comrades in the resistance (read file).");
		System.out.println(" (0) Go back to the main menu.");
		System.out.print("\nSelect: ");
		a = scanner.nextLine();
		return a;
	}

	public static String submenu2() {
		Scanner scanner = new Scanner(System.in);
		String a;

		System.out.println(" (1) Spy an enemy. Search by name.");
		System.out.println(" (2) Spy an enemy. Search by ID.");
		System.out.println(" (3) Meeting with a comrade. Search by name.");
		System.out.println(" (4) Meeting with a comrade. Search by ID.");
		System.out.println(" (0) Go back to the main menu.");
		System.out.print("\nSelect: ");
		a = scanner.nextLine();
		return a;
	}
}

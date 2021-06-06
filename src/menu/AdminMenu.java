package menu;

import java.util.Scanner;

import entity.Material;
import entity.Service;
import exceptions.InvalidInputException;
import main.Organization;
import user.Admin;
import user.Beneficiary;
import user.User;

public class AdminMenu {
	
	public AdminMenu(Admin user, Scanner scanner, Organization org) {
		
		System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("Welcome [Admin] "+user.getName()+" ("+user.getPhone()+")!");
		int input = 0;
		
		do {
			try {
				switch(input) {
					case 1:
						System.out.println("1. Material ("+org.getMaterialsList().size()+")");
						System.out.println("2. Service ("+org.getServiceList().size()+")");
						
						input = scanner.nextInt();
	
						int i = 1;
						if (input==1) {
							for (Material mat : org.getMaterialsList()) {
								System.out.println((i++)+". "+mat.getName());
							}
						} else if (input==2) {
							for (Service service : org.getServiceList()) {
								System.out.println((i++)+". "+service.getName());
							}
							
						} else {
							//throw new InvalidInputException("Invalid input. Please use 1 for material or 2 for service");
						}

						break;
						
					case 2:
						System.out.println("1. Show Beneficiaries ("+org.getBeneficiaries().size()+")");
						System.out.println("2. Show Donators ("+org.getDonators().size()+")");
						System.out.println("3. Reset beneficiaries");
						
						int input2 = scanner.nextInt();
						if (input2==1) {
							for (User usr : org.getBeneficiaries()) {
								System.out.println(usr.toString());
							}
						} else if (input2==2) {
							for (User usr : org.getDonators()) {
								System.out.println(usr.toString());
							}
						} else if (input==3) {
							System.out.println("Are you sure you want to reset the received list of all beneficiaries");
							if (scanner.next().equalsIgnoreCase("y")) {
								for (User usr : org.getBeneficiaries()) {
									((Beneficiary) usr).resetReceivedList();
								}
							}
						} else {
							throw new InvalidInputException("Wrong input!");
						}
						break;
						
					case 3:
						return;
					
					default:
						
						break;
				}
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				input = 1;
			}

			System.out.println("\n\n");
			System.out.println("1. View");
			System.out.println("2. Show Offers");
			System.out.println("3. Logout");
			System.out.println("4. Exit");
		} while ((input = scanner.nextInt())!=4);

		System.out.println("Thank you for choosing us. Goodbye!");
		System.exit(0);
	}
	
}

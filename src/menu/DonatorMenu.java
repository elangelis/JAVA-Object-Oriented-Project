package menu;

import java.util.Scanner;

import RequestDonationList.RequestDonation;
import entity.Entity;
import entity.Material;
import entity.Service;
import exceptions.ExcistingException;
import exceptions.InvalidInputException;
import main.Organization;
import main.Utils;
import user.Donator;

public class DonatorMenu {
	
	public DonatorMenu(Donator user, Scanner scanner, Organization org) {
		
		System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("Welcome [Donator] "+user.getName()+" ("+user.getPhone()+")!");
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
								System.out.println((i++)+". "+mat.getName()+"  Stock: "+mat.getQuantity());
							}
						} else if (input==2) {
							for (Service service : org.getServiceList()) {
								System.out.println((i++)+". "+service.getName()+"  Offers: "+service.getQuantity());
							}
							
						} else {
							//throw new InvalidInputException("Invalid input. Please use 1 for material or 2 for service");
						}
						
						System.out.println("Do you want to offer? (y/n)");
						if (scanner.next().equalsIgnoreCase("Y")) {
							Entity selected = null;
							
							System.out.println("What do you want to offer? ");
							int input2 = scanner.nextInt();
							if (input==1)
								selected = org.getMaterialsList().get(input2-1);
							else
								selected = org.getServiceList().get(input2-1);
							
	
							if (user.contains(selected)) throw new ExcistingException("You already have a donation offer for "+selected.getName());
							if (selected instanceof Material) System.out.println("How much do you want to offer?");
							RequestDonation donation = new RequestDonation(selected, (selected instanceof Material)?scanner.nextInt():1);
							user.add(donation);
							System.out.println("Your offer has been completed. Thank you for your donation!");
						} else {
							input = 0;
						}
						break;
						
					case 2:
						user.monitor();
						System.out.println("Enter the ID of the donation you want to edit or remove, use \"clear\" to remove all donations, use \"commit\" to complete all donations or \"back\" to return to the previus menu");
						
						String input2 = scanner.next();
						if (input2.equalsIgnoreCase("clear")) {
							user.reset();
						} else if (input2.equalsIgnoreCase("commit")) {
							input = 3;
							continue;
						} else if (Utils.isInteger(input2)) {
							RequestDonation rq = user.get(Integer.parseInt(input2));
							if (rq!=null) {
								System.out.println("Enter \"remove\" or \"edit\" to remove/edit "+rq.getEntity().getName());
								
								input2 = scanner.next();
								if (input2.equalsIgnoreCase("remove")) user.remove(rq);
								if (input2.equals("edit")) {
									System.out.println("Please enter new quantity for "+rq.getEntity().getName());
									rq.setQuantity(scanner.nextDouble());
								}
							}
						} else {
							throw new InvalidInputException("Enter the ID of the request you want to edit or remove, use \"clear\" to remove all donations, use \"commit\" to complete all donations or \"back\" to return to the previus menu");
						}
						break;
						
					case 3:
						user.commit();
						input = 0;
						System.out.println("Donation has been succesfully commited.");
						break;
						
					case 4:
						return;
					
					default:
						
						break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				input = 1;
			}
			
			System.out.println("\n\n");
			System.out.println("1. Add offer");
			System.out.println("2. Show Offers");
			System.out.println("3. Commit");
			System.out.println("4. Logout");
			System.out.println("5. Exit");
			
		}
		while (input==3 || (input = scanner.nextInt())!=5);

		System.out.println("Thank you for choosing us. Goodbye!");
		System.exit(0);
	}
	
}

package menu;

import java.util.Scanner;

import entity.Material;
import entity.Service;
import main.Organization;
import main.Utils;
import user.Admin;
import user.Beneficiary;
import user.Donator;
import user.User;

public class Menu {
	
	private static Organization org;
	
	public static void main(String[] args) {
		
		setup();
		
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Welcome to the Volunteer-Donor Organization System!\n");
			System.out.print("Please enter your phone number to login: ");
			
			String phoneNumberString = "a";
			while (!Utils.isLong(phoneNumberString = scanner.next())) {
				System.out.print("Please enter your phone number to login: ");
			}
			long phoneNumber = Long.parseLong(phoneNumberString);
			

			User user = org.getUser(phoneNumber);
			
			if (user==null) {
				System.out.println("This account does not exist. Do you want to create one? (Y/N)");
				
				if (!scanner.next().toLowerCase().equals("y")) {
					System.out.println("Cancelled account creation");
				} else {
					System.out.print("Before we begin, please write your full name: ");
					String fullname = scanner.next()+" "+scanner.next();
					
					System.out.println("Would you like to register as 1.Donator or 2.Beneficiary?");
					
					String answerString = "a";
					while (!Utils.isInteger(answerString = scanner.next())) {
						System.out.print("Would you like to register as 1.Donator or 2.Beneficiary?");
					}
					int answer = Integer.parseInt(answerString);
					
					
					while (answer!=1 && answer!=2) {
						System.out.println("Wrong input \""+answer+"\". Please use \"1\" for Donator or \"2\" for Benefiaciary");
						answerString = "a";
						while (!Utils.isInteger(answerString = scanner.next())) {
							System.out.print("Would you like to register as 1.Donator or 2.Beneficiary?");
						}
						answer = Integer.parseInt(answerString);
					}
					if (answer==1) {
						user = new Donator(fullname, phoneNumber);
					} else {
						
						System.out.println("Finally, how many members does your family consist of?");
						String familyString = "a";
						while (!Utils.isInteger(familyString = scanner.next())) {
							System.out.print("Finally, how many members does your family consist of?");
						}
						int family = Integer.parseInt(familyString);
						
						user = new Beneficiary(fullname, phoneNumber, family);
					}
					org.insertUser(user);
				}
			}

			try {
				if (user instanceof Donator)
					new DonatorMenu((Donator) user, scanner, org); //Moving to donator menu
				if (user instanceof Beneficiary)
					new BeneficiaryMenu((Beneficiary) user, scanner, org); //Moving to beneficiary menu
				if (user instanceof Admin)
					new AdminMenu((Admin) user, scanner, org); //Moving to beneficiary menu
			} catch (Exception e) {
				System.out.println("A problem occured from an invalid input. Please login again. *this is for your security*");
				scanner.next();
			}
		}
		
		
		
		
	}

	private static void setup() {
		
		Admin admin = new Admin("admin", 6970276163L);
		
		org = new Organization("Name", admin);
		
		org.addEntity(new Material("Milk", "1LT cartoon of milk", 1, 3, 5));
		org.addEntity(new Material("Rice", "1kg bag of rice", 1, 3, 5));
		org.addEntity(new Material("Toiler paper", "Bag of 6 toilet paper rolls", 1, 2, 3));

		org.addEntity(new Service("Medical Support", "Medical care for all ages"));
		org.addEntity(new Service("Nursery Support", "Professional care for the elderly"));
		org.addEntity(new Service("BabySitting", "Professional care for children"));
		
		org.insertUser(new Beneficiary("Tasos Menelaou", 6970276164L, 1));
	}
	
}

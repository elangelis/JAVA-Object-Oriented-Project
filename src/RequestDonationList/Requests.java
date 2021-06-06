package RequestDonationList;

import entity.Material;
import exceptions.InvalidOrExceedingQuantityException;
import user.Beneficiary;
import user.User;

public class Requests extends RequestDonationList {
	
	@Override
	public void add(User user, RequestDonation donation) {
		try {
			if (validRequestDonation(user, donation)) super.add(user, donation);
		} catch (InvalidOrExceedingQuantityException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void modify(RequestDonation donation, double newQuantity) {
		donation.setQuantity(newQuantity);
	}
	
	
	public boolean validRequestDonation(User user, RequestDonation donation) throws InvalidOrExceedingQuantityException {
		
		if (donation.getQuantity()<1 || donation.getQuantity()> donation.getEntity().getQuantity()) throw new InvalidOrExceedingQuantityException("You cannot set a negative amount or an amount that exceeds the stock.");
		if ((donation.getEntity() instanceof Material) && donation.getQuantity()>((Material) donation.getEntity()).getMaxAmount(((Beneficiary) user).getNoPersons())) throw new InvalidOrExceedingQuantityException("You cannot have more than "+((Material) donation.getEntity()).getMaxAmount(((Beneficiary) user).getNoPersons()));
		
		return true;
	}
	
	public void commit() {
		
	}
}

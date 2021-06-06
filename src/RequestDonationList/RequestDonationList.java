package RequestDonationList;

import java.util.ArrayList;
import java.util.Collection;

import entity.Entity;
import user.User;

public class RequestDonationList {
	
	private Collection<RequestDonation> rdEntities = new ArrayList<RequestDonation>();
	
	public RequestDonation get(int id) {
		for (RequestDonation donation : rdEntities) {
			if (donation.getEntity().getId()==id) return donation;
		}
		return null;
	}
	
	public void add(User user, RequestDonation donation) {
		rdEntities.add(donation);
		System.out.println("Your action has been submitted.");
	}
	
	public void remove(RequestDonation donation) {
		rdEntities.remove(donation);
	}
	
	public void modify(RequestDonation donation, double newQuantity) {
		donation.setQuantity(newQuantity);
	}
	
	public void monitor() {
		for (RequestDonation donation : rdEntities) {
			Entity entity = donation.getEntity();
			System.out.println("#"+entity.getId()+" "+entity.getName()+" "+donation.getQuantity());
		}
		if (rdEntities.isEmpty()) System.out.println("You have not made any donations yet");
	}
	
	public void reset() {
		rdEntities.clear();
	}

	public Collection<RequestDonation> getEntities() {
		return rdEntities;
	}
	
	public void add(Requests requestsList) {
		for (RequestDonation req : requestsList.getEntities()) {
			rdEntities.add(req);
		}
		
	}
	
}

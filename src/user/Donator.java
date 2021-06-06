package user;

import RequestDonationList.Offers;
import RequestDonationList.RequestDonation;
import entity.Entity;

public class Donator extends User {

	private Offers offersList = new Offers();
	
	public Donator(String name, long phone) {
		super(name, phone);
	}

	public void add(RequestDonation donation) {
		offersList.add(this, donation);
	}
	
	public void remove(RequestDonation donation) {
		offersList.remove(donation);
	}
	
	public void monitor() {
		offersList.monitor();
	}
	
	public void modify(RequestDonation donation, double newQuantity) {
		offersList.modify(donation, newQuantity);
	}
	
	public void commit() {
		offersList.commit();
	}
	
	public void reset() {
		offersList.reset();
	}

	public RequestDonation get(int parseInt) {
		return offersList.get(parseInt);
	}

	public boolean contains(Entity selected) {
		return offersList.get(selected.getId())!=null;
	}
}

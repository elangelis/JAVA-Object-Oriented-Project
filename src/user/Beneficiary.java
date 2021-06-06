package user;

import RequestDonationList.RequestDonation;
import RequestDonationList.RequestDonationList;
import RequestDonationList.Requests;
import entity.Entity;

public class Beneficiary extends User {

	private int noPersons = 1;
	private RequestDonationList receivedList = new RequestDonationList();
	private Requests requestsList = new Requests();
	
	public Beneficiary(String name, long phone, int noPersons) {
		super(name, phone);
		
	}

	public int getNoPersons() {
		return noPersons;
	}

	public void add(RequestDonation req) {
		requestsList.add(this, req);
	}

	public void monitor() {
		requestsList.monitor();
		
	}

	public void reset() {
		requestsList.reset();
		
	}
	
	public void resetReceivedList() {
		receivedList.reset();
		
	}

	public boolean contains(Entity selected) {
		return requestsList.get(selected.getId())!=null || receivedList.get(selected.getId())!=null;
	}

	public RequestDonation get(int parseInt) {
		return requestsList.get(parseInt);
	}

	public void remove(RequestDonation rq) {
		requestsList.remove(rq);
		
	}

	public void commit() {
		for (RequestDonation request : requestsList.getEntities()) {
			request.getEntity().removeQuantity(request.getQuantity());
		}
		receivedList.add(requestsList);
		requestsList.reset();
		
	}
	
	
	
}

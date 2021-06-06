package RequestDonationList;

public class Offers extends RequestDonationList {
	
	public void commit() {
		
		for (RequestDonation req : getEntities()) {
			req.getEntity().addQuantity(req.getQuantity());
		}
		
		reset();
	}
	
}

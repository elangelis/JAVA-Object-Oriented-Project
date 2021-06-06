package main;
import java.util.ArrayList;
import java.util.List;

import RequestDonationList.RequestDonation;
import RequestDonationList.RequestDonationList;
import entity.Entity;
import entity.Material;
import entity.Service;
import user.Admin;
import user.Beneficiary;
import user.Donator;
import user.User;

public class Organization {
	
	private String name;
	private Admin admin;
	private ArrayList<Entity> entityList = new ArrayList<Entity>();
	private ArrayList<User> userList = new ArrayList<User>();
	private RequestDonationList requestDonationList = new RequestDonationList();
	
	public Organization(String name, Admin admin) {
		this.name = name;
		this.admin = admin;
		userList.add(admin);
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void addEntity(Entity entity) {
		entityList.add(entity);
	}

	public void removeEntity(Entity entity) {
		entityList.remove(entity);
	}
	
	public void insertUser(User user) {
		userList.add(user);
	}

	public void removeUser(User user) {
		userList.remove(user);
	}
	
	public List<Material> getMaterialsList() {
		List<Material> temp = new ArrayList<Material>();
		
		for (Entity en : entityList)
			if (en instanceof Material) temp.add((Material) en);
		
		return temp;
	}
	
	public List<Service> getServiceList() {
		List<Service> temp = new ArrayList<Service>();
		
		for (Entity en : entityList)
			if (en instanceof Service) temp.add((Service) en);
		
		return temp;
	}

	public boolean containsUser(long phoneNumber) {
		for (User user : userList) 
			if (user.getPhone()==phoneNumber) return true;
		return false;
	}

	public User getUser(long phoneNumber) {
		for (User user : userList) 
			if (user.getPhone()==phoneNumber) return user;
		return null;
	}

	public void monitor() {
		requestDonationList.monitor();
	}

	public void reset() {
		requestDonationList.reset();
		
	}

	public RequestDonation get(int parseInt) {
		return requestDonationList.get(parseInt);
	}

	public void remove(RequestDonation rq) {
		requestDonationList.remove(rq);
		
	}

	public void commit() {
		for (User user : userList) {
			if (user instanceof Donator) ((Donator) user).commit();
		}
	}

	public List<Beneficiary> getBeneficiaries() {
		List<Beneficiary> temp = new ArrayList<Beneficiary>();
		for (User user : userList) {
			if (user instanceof Beneficiary) temp.add((Beneficiary) user);
		}
		return temp;
	}
	
	public List<Donator> getDonators() {
		List<Donator> temp = new ArrayList<Donator>();
		for (User user : userList) {
			if (user instanceof Donator) temp.add((Donator) user);
		}
		return temp;
	}

	public String getName() {
		return name;
	}
	
	
	
}

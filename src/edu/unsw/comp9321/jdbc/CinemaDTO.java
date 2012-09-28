package edu.unsw.comp9321.jdbc;

public class CinemaDTO {
	
	private String name;
	private String location;
	private int capacity;
	private String[] amenities;
	private int id;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public CinemaDTO(int id, String name, String location, int capacity,
			String[] amenities) {
		super();
		this.name = name;
		this.location = location;
		this.capacity = capacity;
		this.amenities = amenities;
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String[] getAmenities() {
		return amenities;
	}
	public void setAmenities(String[] amenities) {
		this.amenities = amenities;
	}

}

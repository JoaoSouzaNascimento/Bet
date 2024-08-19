package model;

public class Time {
	private int id;
	private String name;
	private String logo;
	
	public Time(int id, String name, String logo) {
		super();
		this.id = id;
		this.name = name;
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getId() {
		return id;
	}
}

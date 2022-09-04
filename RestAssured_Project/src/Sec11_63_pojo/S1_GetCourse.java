package Sec11_63_pojo;

public class S1_GetCourse {

	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private S2_Courses Courses;
	private String linkedIn;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public Sec11_63_pojo.S2_Courses getCourses() {
		return Courses;
	}

	public void setCourses(Sec11_63_pojo.S2_Courses courses) {
		Courses = courses;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

}

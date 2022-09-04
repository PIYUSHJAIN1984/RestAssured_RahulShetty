package Sec11_63_pojo;

import java.util.List;

public class S2_Courses {

	private List<S3_WebAutomation> webAutomation;
	private List<S4_API> api;
	private List<S5_Mobile> mobile;

	public List<S3_WebAutomation> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<S3_WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	public List<S4_API> getAPI() {
		return api;
	}

	public void setApi(List<S4_API> api) {
		this.api = api;
	}

	public List<S5_Mobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<S5_Mobile> mobile) {
		this.mobile = mobile;
	}

}

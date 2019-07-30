
public class User {
	private String Fname;
	private String Sname;
	private String email;
	private String pword;
	private boolean admin;
	
	public User(String fname, String sname, String email, String pword, boolean admin) {
		super();
		Fname = fname;
		Sname = sname;
		this.email = email;
		this.pword = pword;
		this.admin = admin;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}



	

}

package member.ex01;

import java.util.Date;

public class MemberBean {

	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	private String hobby;
	
	public MemberBean() { }
	public MemberBean(String id, String pwd, String name, String email, String hobby) { 
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.hobby = hobby;
	}
	public MemberBean(String id, String pwd, String name, String email, Date joinDate, String hobby) { 
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.joinDate = joinDate;
		this.hobby = hobby;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
}

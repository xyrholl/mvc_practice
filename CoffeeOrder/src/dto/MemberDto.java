package dto;
/*
CREATE TABLE MEMBER(
ID VARCHAR2(30) PRIMARY KEY,
PWD VARCHAR2(30) NOT NULL,
NAME VARCHAR2(50) NOT NULL,
EMAIL VARCHAR2(50) UNIQUE,
AUTH NUMBER(1) NOT NULL);
*/
public class MemberDto {

	private String id;
	private String pwd;
	private String name;
	private int age;
	private String phone;
	private int auth; // 사용자 or 관리자 구분하는 필드값.
	
	public MemberDto() {
	}

	public MemberDto(String id, String pwd, String name, int age, String phone, int auth) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.auth = auth;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAuth() {
		return auth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", age=" + age + ", phone=" + phone
				+ ", auth=" + auth + "]";
	}

	
}

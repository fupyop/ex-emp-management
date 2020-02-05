package jp.co.sample.domain;

public class Administrator {
	/**
	 * ID(主キ)
	 */
	private Integer id;
	/**
	 * 名前
	 */
	private String name;
	/**
	 * メールアドレス
	 */
	private String mail_address;
	/**
	 * パスワード
	 */
	private String password;
	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Administrator(Integer id, String name, String mail_address, String password) {
		super();
		this.id = id;
		this.name = name;
		this.mail_address = mail_address;
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", mail_address=" + mail_address + ", password="
				+ password + "]";
	}
}

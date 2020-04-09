package pojo;

import org.springframework.web.multipart.MultipartFile;

public class Member {
	private String user_phone;
	private String user_password;
	private String real_name;
	private String id_number;
	private String email;
	private String img;
	private MultipartFile file;

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Member [user_phone=" + user_phone + ", user_password=" + user_password + ", real_name=" + real_name
				+ ", id_number=" + id_number + ", email=" + email + ", img=" + img + ", file=" + file + "]";
	}

}

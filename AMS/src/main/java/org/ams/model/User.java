package org.ams.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key ID
	@Column(name="id")
	private int id;

	@Basic
	@Column(name = "firstname", nullable = false, length = 50)
	@Size(min=2, max=25,  message="First Name must be between 2 and 16 characters long.")
	@NotBlank(message="First Name is required.")
	private String firstName;

	@Basic
	@Column(name = "lastname", nullable = false, length = 50)
	@Size(min=2, max=25,  message="Last Name must be between 2 and 16 characters long.")
	@NotBlank(message="Last Name is required.")
	private String lastName;

	@Basic
	@Column(name = "email", nullable = true)
	@Email(message = "Please enter a valid email")
	@NotBlank(message="Email is required.")
	private String email;

	@Basic
	@Column(name = "username", nullable = false, length = 50, unique = true)
	@Size(min=2, max=25,  message="User Name must be between 6 and 25 characters long.")
	@NotBlank(message="User Name is required.")
	private String userName;

	@Basic
	@Column(name = "password", nullable = true, length = 50)
	@Size(min=8, max=16,  message="Password must be between 6 and 16 characters long.")
	@NotBlank(message="Password is required.")
	private String password;

	public User() {
		super();
	}

	public User(int id, String firstName, String lastName, String email, String userName,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}


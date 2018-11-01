package com.mycompany.asset.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "staffmember")
public class StaffMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_member_id")
	private Integer staffMemberId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "residence_address")
	private String address;

	@Column(name = "app_user")
	private Integer appUser;

	@Column(name = "app_user_id")
	private String appUserId;

	@Column(name = "app_password")
	private String appPassword;

	@Column(name = "DOJ")
	private Date doj;

	@ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	@JoinTable(name = "staff_member_role_mapping", joinColumns = { @JoinColumn(name = "staff_member_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> staffRoles = new HashSet<Role>();

		
	public Integer getStaffMemberId() {
		return staffMemberId;
	}

	public void setStaffMemberId(Integer staffMemberId) {
		this.staffMemberId = staffMemberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAppUser() {
		return appUser;
	}

	public void setAppUser(Integer appUser) {
		this.appUser = appUser;
	}

	public String getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}

	public String getAppPassword() {
		return appPassword;
	}

	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Set<Role> getStaffRoles() {
		return staffRoles;
	}

	public void setStaffRoles(Set<Role> staffRoles) {
		this.staffRoles = staffRoles;
	}

}

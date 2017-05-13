package com.techgig.wallet.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.simple.JSONObject;

@Entity
@Table(name = "Users")
public class UserBean {
	
	@Id
	@GeneratedValue
	private int userId;
	@Column(nullable = false)
	private String name;
	@Column(unique=true, nullable=false)
	private String email;
	@Column(nullable = false)
	@Lob
	private String password;
	@OneToOne
	@JoinColumn(name = "walletId")
	private WalletBean walletBean;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public WalletBean getWalletBean() {
		return walletBean;
	}
	public void setWalletBean(WalletBean walletBean) {
		this.walletBean = walletBean;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		
		JSONObject object = new JSONObject();
		object.put("userId", new Integer(userId).toString());
		object.put("email", email);
		object.put("name", name);
		object.put("walletBean", walletBean);
		return object.toJSONString();
	}
	

}

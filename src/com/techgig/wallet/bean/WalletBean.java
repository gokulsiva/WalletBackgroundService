package com.techgig.wallet.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.json.simple.JSONObject;

@Entity
@Table(name = "Wallets")
public class WalletBean {
	
	@Id
	@GeneratedValue
	private int walletId;
	@Column(nullable = false)
	private long balance;
	@Column(nullable = false)
	@Lob
	private String password;
	
	public int getWalletId() {
		return walletId;
	}
	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		JSONObject object = new JSONObject();
		object.put("walletId", new Integer(walletId).toString());
		object.put("balance", new Long(balance).toString());
		object.put("walletPassword", password);
		return object.toJSONString();
	}

}

/**
 * 
 */
package com.tutuka.recon.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mridula
 *
 */
public class Transaction {
	private String walletRefernce;
	private String profileName;
	private String transactionNarrative;
	private String transactionDescription;
	private long transactionID;
	private int transactionType;
	private Date transactionDate;
	private BigDecimal transactionAmount;
	
	public String getWalletRefernce() {
		return walletRefernce;
	}
	public void setWalletRefernce(String walletRefernce) {
		this.walletRefernce = walletRefernce;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getTransactionNarrative() {
		return transactionNarrative;
	}
	public void setTransactionNarrative(String transactionNarrative) {
		this.transactionNarrative = transactionNarrative;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((profileName == null) ? 0 : profileName.hashCode());
		result = prime * result + ((transactionAmount == null) ? 0 : transactionAmount.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((transactionDescription == null) ? 0 : transactionDescription.hashCode());
		result = prime * result + (int) (transactionID ^ (transactionID >>> 32));
		result = prime * result + ((transactionNarrative == null) ? 0 : transactionNarrative.hashCode());
		result = prime * result + transactionType;
		result = prime * result + ((walletRefernce == null) ? 0 : walletRefernce.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (profileName == null) {
			if (other.profileName != null)
				return false;
		} else if (!profileName.equals(other.profileName))
			return false;
		if (transactionAmount == null) {
			if (other.transactionAmount != null)
				return false;
		} else if (!transactionAmount.equals(other.transactionAmount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionDescription == null) {
			if (other.transactionDescription != null)
				return false;
		} else if (!transactionDescription.equals(other.transactionDescription))
			return false;
		if (transactionID != other.transactionID)
			return false;
		if (transactionNarrative == null) {
			if (other.transactionNarrative != null)
				return false;
		} else if (!transactionNarrative.equals(other.transactionNarrative))
			return false;
		if (transactionType != other.transactionType)
			return false;
		if (walletRefernce == null) {
			if (other.walletRefernce != null)
				return false;
		} else if (!walletRefernce.equals(other.walletRefernce))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction [walletRefernceId=" + walletRefernce + ", profileName=" + profileName
				+ ", transactionNarrative=" + transactionNarrative + ", transactionDescription="
				+ transactionDescription + ", transactionID=" + transactionID + ", transactionType=" + transactionType
				+ ", transactionDate=" + transactionDate + ", transactionAmount=" + transactionAmount + "]";
	}
	
	
	

}

/**
 * 
 */
package com.tutuka.recon.entities;

import java.util.List;
import java.util.Map;

/**
 * @author Mridula
 *
 */
public class UploadResult {

	private int totalTutukaTxn;
	private int totalClientTxn;
	private int totalInValidTTxn;
	private int totalInValidCTxn;
	private int totalValidTTxn;
	private int totalValidCTxn;	
	private int totalIgnoredTTxn;
	private int totalIgnoredCTxn;	
	private Map<String, Transaction> tutukaTransactions;
	private Map<String, Transaction> clientTransactions;
	private List<Transaction> duplicateTutuka;
	private List<Transaction> duplicateClient;
	private List<String> invalidTutuka;
	private List<String> invalidClient;
	
	public int getTotalTutukaTxn() {
		return totalTutukaTxn;
	}
	public void setTotalTutukaTxn(int totalTutukaTxn) {
		this.totalTutukaTxn = totalTutukaTxn;
	}
	public int getTotalClientTxn() {
		return totalClientTxn;
	}
	public void setTotalClientTxn(int totalClientTxn) {
		this.totalClientTxn = totalClientTxn;
	}

	public int getTotalInValidTTxn() {
		return totalInValidTTxn;
	}
	public void setTotalInValidTTxn(int totalInValidTTxn) {
		this.totalInValidTTxn = totalInValidTTxn;
	}
	public int getTotalInValidCTxn() {
		return totalInValidCTxn;
	}
	public void setTotalInValidCTxn(int totalInValidCTxn) {
		this.totalInValidCTxn = totalInValidCTxn;
	}
	public int getTotalValidTTxn() {
		return totalValidTTxn;
	}
	public void setTotalValidTTxn(int totalValidTTxn) {
		this.totalValidTTxn = totalValidTTxn;
	}
	public int getTotalValidCTxn() {
		return totalValidCTxn;
	}
	public void setTotalValidCTxn(int totalValidCTxn) {
		this.totalValidCTxn = totalValidCTxn;
	}
	public int getTotalIgnoredTTxn() {
		return totalIgnoredTTxn;
	}
	public void setTotalIgnoredTTxn(int totalIgnoredTTxn) {
		this.totalIgnoredTTxn = totalIgnoredTTxn;
	}
	public int getTotalIgnoredCTxn() {
		return totalIgnoredCTxn;
	}
	public void setTotalIgnoredCTxn(int totalIgnoredCTxn) {
		this.totalIgnoredCTxn = totalIgnoredCTxn;
	}
	public Map<String, Transaction> getTutukaTransactions() {
		return tutukaTransactions;
	}
	public void setTutukaTransactions(Map<String, Transaction> tutukaTransactions) {
		this.tutukaTransactions = tutukaTransactions;
	}
	public Map<String, Transaction> getClientTransactions() {
		return clientTransactions;
	}
	public void setClientTransactions(Map<String, Transaction> clientTransactions) {
		this.clientTransactions = clientTransactions;
	}
	public List<Transaction> getDuplicateTutuka() {
		return duplicateTutuka;
	}
	public void setDuplicateTutuka(List<Transaction> duplicateTutuka) {
		this.duplicateTutuka = duplicateTutuka;
	}
	public List<Transaction> getDuplicateClient() {
		return duplicateClient;
	}
	public void setDuplicateClient(List<Transaction> duplicateClient) {
		this.duplicateClient = duplicateClient;
	}
	public List<String> getInvalidTutuka() {
		return invalidTutuka;
	}
	public void setInvalidTutuka(List<String> invalidTutuka) {
		this.invalidTutuka = invalidTutuka;
	}
	public List<String> getInvalidClient() {
		return invalidClient;
	}
	public void setInvalidClient(List<String> invalidClient) {
		this.invalidClient = invalidClient;
	}
	@Override
	public String toString() {
		return "UploadResult [totalTutukaTxn=" + totalTutukaTxn + ", totalClientTxn=" + totalClientTxn
				+ ", totalInValidTTxn=" + totalInValidTTxn + ", totalInValidCTxn=" + totalInValidCTxn
				+ ", totalValidTTxn=" + totalValidTTxn + ", totalValidCTxn=" + totalValidCTxn + ", totalIgnoredTTxn="
				+ totalIgnoredTTxn + ", totalIgnoredCTxn=" + totalIgnoredCTxn + ", tutukaTransactions="
				+ tutukaTransactions + ", clientTransactions=" + clientTransactions + ", duplicateTutuka="
				+ duplicateTutuka + ", duplicateClient=" + duplicateClient + ", invalidTutuka=" + invalidTutuka
				+ ", invalidClient=" + invalidClient + "]";
	}
	


}

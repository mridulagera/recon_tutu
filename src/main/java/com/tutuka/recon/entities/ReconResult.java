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
public class ReconResult {

	private int totalMatchTxn;
	private int totalPartialMatchTxn;
	private int totalUnMatchTTxn;
	private int totalDuplicateTTxn;
	private int totalUnMatchCTxn;
	private int totalDuplicateCTxn;
	private Map<String, Transaction> partialMatchTutuka;
	private Map<String, Transaction> partialMatchClient;
	private Map<String, Transaction> unmatchedTutuka;
	private Map<String, Transaction> unmatchedClient;
	
	public int getTotalMatchTxn() {
		return totalMatchTxn;
	}

	public void setTotalMatchTxn(int totalMatchTxn) {
		this.totalMatchTxn = totalMatchTxn;
	}
	
	public int getTotalPartialMatchTxn() {
		return totalPartialMatchTxn;
	}

	public void setTotalPartialMatchTxn(int totalPartialMatchTxn) {
		this.totalPartialMatchTxn = totalPartialMatchTxn;
	}

	public int getTotalUnMatchTTxn() {
		return totalUnMatchTTxn;
	}

	public void setTotalUnMatchTTxn(int totalUnMatchTTxn) {
		this.totalUnMatchTTxn = totalUnMatchTTxn;
	}

	public int getTotalDuplicateTTxn() {
		return totalDuplicateTTxn;
	}

	public void setTotalDuplicateTTxn(int totalDuplicateTTxn) {
		this.totalDuplicateTTxn = totalDuplicateTTxn;
	}

	public int getTotalUnMatchCTxn() {
		return totalUnMatchCTxn;
	}

	public void setTotalUnMatchCTxn(int totalUnMatchCTxn) {
		this.totalUnMatchCTxn = totalUnMatchCTxn;
	}

	public int getTotalDuplicateCTxn() {
		return totalDuplicateCTxn;
	}

	public void setTotalDuplicateCTxn(int totalDuplicateCTxn) {
		this.totalDuplicateCTxn = totalDuplicateCTxn;
	}

	private List<Transaction> possibleDuplicateTutukaTransactions;
	private List<Transaction> possibleDuplicateClientTransactions;

	public Map<String, Transaction> getPartialMatchTutuka() {
		return partialMatchTutuka;
	}

	public void setPartialMatchTutuka(Map<String, Transaction> partialMatchTutuka) {
		this.partialMatchTutuka = partialMatchTutuka;
	}
	public Map<String, Transaction> getPartialMatchClient() {
		return partialMatchClient;
	}
	public void setPartialMatchClient(Map<String, Transaction> partialMatchClient) {
		this.partialMatchClient = partialMatchClient;
	}
	public Map<String, Transaction> getUnmatchedTutuka() {
		return unmatchedTutuka;
	}
	public void setUnmatchedTutuka(Map<String, Transaction> unmatchedTutuka) {
		this.unmatchedTutuka = unmatchedTutuka;
	}
	public Map<String, Transaction> getUnmatchedClient() {
		return unmatchedClient;
	}
	public void setUnmatchedClient(Map<String, Transaction> unmatchedClient) {
		this.unmatchedClient = unmatchedClient;
	}
	public List<Transaction> getPossibleDuplicateTutukaTransactions() {
		return possibleDuplicateTutukaTransactions;
	}
	public void setPossibleDuplicateTutukaTransactions(List<Transaction> possibleDuplicateTutukaTransactions) {
		this.possibleDuplicateTutukaTransactions = possibleDuplicateTutukaTransactions;
	}
	
	public List<Transaction> getPossibleDuplicateClientTransactions() {
		return possibleDuplicateClientTransactions;
	}
	public void setPossibleDuplicateClientTransactions(List<Transaction> possibleDuplicateClientTransactions) {
		this.possibleDuplicateClientTransactions = possibleDuplicateClientTransactions;
	}
	
	@Override
	public String toString() {
		return "ReconResult [totalPartialMatchTxn=" + totalPartialMatchTxn + ", totalUnMatchTTxn=" + totalUnMatchTTxn
				+ ", totalDuplicateTTxn=" + totalDuplicateTTxn + ", totalUnMatchCTxn=" + totalUnMatchCTxn
				+ ", totalDuplicateCTxn=" + totalDuplicateCTxn + ", partialMatchTutuka=" + partialMatchTutuka
				+ ", partialMatchClient=" + partialMatchClient + ", unmatchedTutuka=" + unmatchedTutuka
				+ ", unmatchedClient=" + unmatchedClient + ", possibleDuplicateTutukaTransactions="
				+ possibleDuplicateTutukaTransactions + ", possibleDuplicateClientTransactions="
				+ possibleDuplicateClientTransactions + "]";
	}

	

}

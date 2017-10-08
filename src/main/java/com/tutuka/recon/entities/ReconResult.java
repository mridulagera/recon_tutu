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
	
	private Map<String, Transaction> closeMatchTutuka;
	private Map<String, Transaction> closeMatchClient;
	private Map<String, Transaction> partialMatchTutuka;
	private Map<String, Transaction> partialMatchClient;
	private Map<String, Transaction> unmatchedTutuka;
	private Map<String, Transaction> unmatchedClient;
	private List<Transaction> duplicateTutuka;
	private List<Transaction> duplicateClient;
	
	public Map<String, Transaction> getCloseMatchTutuka() {
		return closeMatchTutuka;
	}
	public void setCloseMatchTutuka(Map<String, Transaction> closeMatchTutuka) {
		this.closeMatchTutuka = closeMatchTutuka;
	}
	public Map<String, Transaction> getCloseMatchClient() {
		return closeMatchClient;
	}
	public void setCloseMatchClient(Map<String, Transaction> closeMatchClient) {
		this.closeMatchClient = closeMatchClient;
	}
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
	
	

}

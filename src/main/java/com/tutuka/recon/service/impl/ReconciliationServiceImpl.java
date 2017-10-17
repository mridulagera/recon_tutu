package com.tutuka.recon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutuka.recon.actions.ReconTransactionConfig;
import com.tutuka.recon.entities.ReconResult;
import com.tutuka.recon.entities.Transaction;
import com.tutuka.recon.model.PaymentFields;
import com.tutuka.recon.model.PaymentFields.Field;
import com.tutuka.recon.service.ReconciliationService;

@Service("reconService")
public class ReconciliationServiceImpl implements ReconciliationService{


	private PaymentFields.Field<Transaction>[] closeMatchFieldsL1;
	private PaymentFields.Field<Transaction>[] closeMatchFieldsL2;
	
	@Autowired
	public ReconciliationServiceImpl() {
		this.closeMatchFieldsL1 = new PaymentFields.Field[] {
				PaymentFields.TRANSACTION_AMOUNT,
				PaymentFields.TRANSACTION_DATE_TIME,
				PaymentFields.TRANSACTION_TYPE,
				PaymentFields.WALLET_REFERENCE,
			};
		this.closeMatchFieldsL2 = 	new PaymentFields.Field[] {
				PaymentFields.TRANSACTION_AMOUNT,
				PaymentFields.TRANSACTION_DATE,
				PaymentFields.TRANSACTION_TYPE,
				PaymentFields.WALLET_REFERENCE,
			};
				
	}
	

	@Override
	public ReconResult reconcileRecords(Map<String, Transaction> clientTransactions,
			Map<String, Transaction> tutukaTransactions){		
		if (tutukaTransactions == null || tutukaTransactions.isEmpty())
		{
			throw new NullPointerException("Tutuka Transactions Data is null");
		}
		
		if (clientTransactions == null || clientTransactions.isEmpty())
		{
			throw new NullPointerException("Client Transactions Data is null");
		}	

		ReconResult result = new ReconResult();
		Set<String> tutukaTxnKeys = tutukaTransactions.keySet();
		Iterator<String> itrTutukaTxnKeys = tutukaTxnKeys.iterator();
		int matchingTxnCount = 0;
		while(itrTutukaTxnKeys.hasNext())
		{
			String tutukaTxnKey = itrTutukaTxnKeys.next();
			//System.out.println(tutukaTxnKey);
			Transaction tutukaTxn = tutukaTransactions.get(tutukaTxnKey);
			if (!clientTransactions.isEmpty() &&  clientTransactions.containsKey(tutukaTxnKey))
			{
				Transaction clientTxn = clientTransactions.get(tutukaTxnKey);
				boolean matchResult = matchTxns(clientTxn, tutukaTxn);
				if (!matchResult)
				{
					//if no match inspite of same txn id add to partial match
					//System.out.println("tutukaTxnKey " + tutukaTxnKey);
					updatePartialMatch(result, clientTxn, tutukaTxn, tutukaTxnKey);
				}
				else {
					matchingTxnCount++;
				}
				clientTransactions.remove(tutukaTxnKey);
				itrTutukaTxnKeys.remove();
				
			}
			else {
				updateUnMatchedTutukaResults(result, tutukaTxn);
			}
		}
		if (!clientTransactions.isEmpty())
		{
			updateUnMatchedClientResults(result, clientTransactions);
		}
		
		updateSummary(result, matchingTxnCount);
		
		
		return result;
	}
	
	private void updateSummary(ReconResult result, int matchingTxnCount) {
		result.setTotalMatchTxn(matchingTxnCount);
		result.setTotalPartialMatchTxn(
				result.getPartialMatchClient() == null?0:result.getPartialMatchClient().size());
    	
		result.setTotalDuplicateCTxn(
				result.getPossibleDuplicateClientTransactions() == null?0:result.getPossibleDuplicateClientTransactions().size());
		result.setTotalDuplicateTTxn(
				result.getPossibleDuplicateTutukaTransactions() == null?0:result.getPossibleDuplicateTutukaTransactions().size());
		result.setTotalUnMatchCTxn(
				result.getUnmatchedClient() == null?0:result.getUnmatchedClient().size());
		result.setTotalUnMatchTTxn(
				result.getUnmatchedTutuka() == null?0:result.getUnmatchedTutuka().size());  
		
	}


	private void updatePartialMatch(ReconResult result, Transaction clientTxn, Transaction tutukaTxn, String tutukaTxnKey)
	{
		Map<String, Transaction> clientPartialMatch = result.getPartialMatchClient();
		Map<String, Transaction> tutukaPartialMatch = result.getPartialMatchTutuka();
		if (clientPartialMatch == null)
		{
			clientPartialMatch = new HashMap<String, Transaction>();
			tutukaPartialMatch = new HashMap<String, Transaction>();
			result.setPartialMatchClient(clientPartialMatch);
			result.setPartialMatchTutuka(tutukaPartialMatch);
		}
		clientPartialMatch.put(tutukaTxnKey, clientTxn);
		tutukaPartialMatch.put(tutukaTxnKey, tutukaTxn);
	}
	
	private void updateUnMatchedTutukaResults(ReconResult result, Transaction tutukaTxn)
	{
		String tutukaTxnKey = createTxnKey (closeMatchFieldsL1, tutukaTxn);
		
		Map<String, Transaction> tutukaUnMatchMatch = result.getUnmatchedTutuka();
		
		if (tutukaUnMatchMatch == null)
		{			
			tutukaUnMatchMatch = new HashMap<String, Transaction>();
			result.setUnmatchedTutuka(tutukaUnMatchMatch);
		}
		if (tutukaUnMatchMatch.containsKey(tutukaTxnKey))
		{
			//we mark transactions as possible duplicate but still add to un-mapped list
			updateDuplicateTutukaResults(result, tutukaUnMatchMatch.get(tutukaTxnKey), tutukaTxn);
			tutukaTxnKey = tutukaTxnKey+tutukaTxn.getTransactionID();
		}
		tutukaUnMatchMatch.put(tutukaTxnKey, tutukaTxn);
	}
	
	private void updateDuplicateTutukaResults(ReconResult result, Transaction duplicateTrans, Transaction txn)
	{
		//System.out.println("updateDuplicateTutukaResults" + duplicateTrans + txn);
		List<Transaction> dupTransactions = result.getPossibleDuplicateTutukaTransactions();
		if (dupTransactions == null)
		{
			dupTransactions = new ArrayList<Transaction>();
			result.setPossibleDuplicateTutukaTransactions(dupTransactions);
		}
		dupTransactions.add(txn);
		dupTransactions.add(duplicateTrans);
	}
	
	private void updateDuplicateClientResults(ReconResult result, Transaction duplicateTrans, Transaction txn)
	{
		List<Transaction> dupTransactions = result.getPossibleDuplicateClientTransactions();
		if (dupTransactions == null)
		{
			dupTransactions = new ArrayList<Transaction>();
			result.setPossibleDuplicateClientTransactions(dupTransactions);
		}
		dupTransactions.add(txn);
		dupTransactions.add(duplicateTrans);
	}
	
	private void updateUnMatchedClientResults(ReconResult result, Map<String, Transaction> clientTransactions)
	{
		Set<String> clientTxnKeys = clientTransactions.keySet();
		Iterator<String> itrClientTxnKeys = clientTxnKeys.iterator();
		Map<String, Transaction> tutukaUnMatchedTxns = result.getUnmatchedTutuka();
	
		while(itrClientTxnKeys.hasNext())
		{
			String clientCurrentKey = itrClientTxnKeys.next();
			Transaction clientTxn = clientTransactions.get(clientCurrentKey);
		
			//create key with close match fields for potential match
			String clientTxnKey = createTxnKey (closeMatchFieldsL1, clientTxn);
			if (tutukaUnMatchedTxns.containsKey(clientTxnKey))
			{
				//if close match fields match in two txn then add to potential match list & remove from un-matched list
				//System.out.println(clientTxnKey);
				updatePartialMatch (result, clientTxn, tutukaUnMatchedTxns.get(clientTxnKey), clientTxnKey);
				tutukaUnMatchedTxns.remove(clientTxnKey);				
			}
			else {
			
				Map<String, Transaction> clientUnMatchMatch = result.getUnmatchedClient();				
				if (clientUnMatchMatch == null)
				{			
					clientUnMatchMatch = new HashMap<String, Transaction>();
					result.setUnmatchedClient(clientUnMatchMatch);
				}
				if (clientUnMatchMatch.containsKey(clientTxnKey))
				{
					//we mark transactions as possible duplicate but still add to un-mapped list
					updateDuplicateClientResults(result, clientUnMatchMatch.get(clientTxnKey), clientTxn);
					clientTxnKey = clientTxnKey+clientTxn.getTransactionID();
				}
				clientUnMatchMatch.put(clientTxnKey, clientTxn);
			}
		}
	}
	
	private boolean matchTxns(Transaction clientTxn, Transaction tutukaTxn)
	{
		if (!clientTxn.equals(tutukaTxn))
		{
			boolean closeMatch = validateFields(closeMatchFieldsL1, clientTxn, tutukaTxn);
		  if (!closeMatch)
		  {
			  boolean possibleMatch = validateFields(closeMatchFieldsL2, clientTxn, tutukaTxn);
			  if (!possibleMatch) {
				  
				  return false;
			  }
		  }
		}
		return true;
	}

	private boolean validateFields(Field<Transaction>[] matchFields, Transaction clientTxn,
			Transaction tutukaTxn) {
		boolean match = true;
		for (Field<Transaction> field: matchFields) {
			//System.out.println(field);
			  if (field.getValue(clientTxn)!= null &&					  
					  !field.getValue(clientTxn).equals(field.getValue(tutukaTxn)))
			  {
				  match=false;
				  break;
			  }
		  }
		return match;
	}
	
	
	private String createTxnKey(Field<Transaction>[] matchFields, Transaction Txn) {
		StringBuffer key = new StringBuffer();
		for (Field<Transaction> field: matchFields) {
			key.append(field.getValue(Txn));
		  }
		return key.toString();
	}
	

}

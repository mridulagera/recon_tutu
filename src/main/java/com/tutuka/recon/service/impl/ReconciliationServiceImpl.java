package com.tutuka.recon.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tutuka.recon.entities.ReconResult;
import com.tutuka.recon.entities.Transaction;
import com.tutuka.recon.model.PaymentFields;
import com.tutuka.recon.model.PaymentFields.Field;
import com.tutuka.recon.service.ReconciliationService;


public class ReconciliationServiceImpl implements ReconciliationService{
	
	private PaymentFields.Field<Transaction>[] closeMatchFields;
	private PaymentFields.Field<Transaction>[] possibleMatchFields;
	
	public ReconciliationServiceImpl(PaymentFields.Field<Transaction>[] closeMatchFields, 
			PaymentFields.Field<Transaction>[] possibleMatchFields) {
		this.closeMatchFields = closeMatchFields;
		this.possibleMatchFields = 	possibleMatchFields;
				
	}
	

	@Override
	public ReconResult reconcileRecords(Map<String, Transaction> clientTransactions,
			Map<String, Transaction> tutukaTransactions) {
		ReconResult result = new ReconResult();
		
		Set<String> tutukaTxnKeys = tutukaTransactions.keySet();
		Iterator<String> itrTutukaTxnKeys = tutukaTxnKeys.iterator();
	
		while(itrTutukaTxnKeys.hasNext())
		{
			String tutukaTxnKey = itrTutukaTxnKeys.next();
			Transaction tutukaTxn = tutukaTransactions.get(tutukaTxnKey);
			if (clientTransactions.containsKey(tutukaTxnKey))
			{
				Transaction clientTxn = clientTransactions.get(tutukaTxnKey);
				boolean matchResult = matchTxns(clientTxn, tutukaTxn);
				if (!matchResult)
				{
					//if no match inspite of same txn id add to partial match
					updatePartialMatch(result, clientTxn, tutukaTxn, tutukaTxnKey);
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
		return result;
	}
	
	private void updatePartialMatch(ReconResult result, Transaction clientTxn, Transaction tutukaTxn, String tutukaTxnKey)
	{
		Map<String, Transaction> clientPartialMatch = result.getPartialMatchClient();
		Map<String, Transaction> tutukaPartialMatch = result.getPartialMatchClient();
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
		String tutukaTxnKey = createTxnKey (closeMatchFields, tutukaTxn);
		
		Map<String, Transaction> tutukaUnMatchMatch = result.getUnmatchedTutuka();
		
		if (tutukaUnMatchMatch == null)
		{			
			tutukaUnMatchMatch = new HashMap<String, Transaction>();
			result.setUnmatchedTutuka(tutukaUnMatchMatch);
		}
		tutukaUnMatchMatch.put(tutukaTxnKey, tutukaTxn);
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
			String clientTxnKey = createTxnKey (closeMatchFields, clientTxn);
			if (tutukaUnMatchedTxns.containsKey(clientTxnKey))
			{
				//if close match fields match in two txn then add to potential match list & remove from un-matched list
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
				clientUnMatchMatch.put(clientTxnKey, clientTxn);
			}
		}
	}
	
	private boolean matchTxns(Transaction clientTxn, Transaction tutukaTxn)
	{
		if (!clientTxn.equals(tutukaTxn))
		{
			boolean closeMatch = validateFields(closeMatchFields, clientTxn, tutukaTxn);
		  if (!closeMatch)
		  {
			  boolean possibleMatch = validateFields(possibleMatchFields, clientTxn, tutukaTxn);
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
			  if (!field.getValue(clientTxn).equals(field.getValue(tutukaTxn)))
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

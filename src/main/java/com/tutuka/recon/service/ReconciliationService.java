package com.tutuka.recon.service;

import java.util.Map;

import com.tutuka.recon.entities.ReconResult;
import com.tutuka.recon.entities.Transaction;

public interface ReconciliationService {
	
	
	ReconResult reconcileRecords(Map<String, Transaction> clientTransactions,
				Map<String, Transaction> tutukaTransactions);

}

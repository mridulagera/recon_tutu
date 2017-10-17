package com.tutuka.recon.config;

import org.springframework.stereotype.Service;

import com.tutuka.recon.model.PaymentFields;

@Service("reconTransactionConfig")
public class ReconTransactionConfig {
	
	private PaymentFields.Field[] closeMatchFields;
	private PaymentFields.Field[] potentialMatchFields;
	
	public ReconTransactionConfig() {
		closeMatchFields = new PaymentFields.Field[] {
			PaymentFields.TRANSACTION_AMOUNT,
			PaymentFields.TRANSACTION_DATE_TIME,
			PaymentFields.TRANSACTION_TYPE,
			PaymentFields.WALLET_REFERENCE,
		};
		
		potentialMatchFields = new PaymentFields.Field[] {
				PaymentFields.TRANSACTION_AMOUNT,
				PaymentFields.TRANSACTION_DATE,
				PaymentFields.TRANSACTION_TYPE,
				PaymentFields.WALLET_REFERENCE,
			};
		
	}

	public PaymentFields.Field[] getCloseMatchFields() {
		return closeMatchFields;
	}

	public void setCloseMatchFields(PaymentFields.Field[] closeMatchFields) {
		this.closeMatchFields = closeMatchFields;
	}

	public PaymentFields.Field[] getPotentialMatchFields() {
		return potentialMatchFields;
	}

	public void setPotentialMatchFields(PaymentFields.Field[] potentialMatchFields) {
		this.potentialMatchFields = potentialMatchFields;
	}
	
	
}

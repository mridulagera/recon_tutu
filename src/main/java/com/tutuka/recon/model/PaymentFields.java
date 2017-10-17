package com.tutuka.recon.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.tutuka.recon.entities.Transaction;

public interface PaymentFields {
	
	PaymentFields.Field<Transaction> TRANSACTION_AMOUNT = new PaymentFields.Field<Transaction>() {

		@Override
		public String getName() {
			return "transactionAmount";
		}

		@Override
		public String getValue(Transaction var1) {
			if (var1.getTransactionAmount() == null)
				return null;
			return var1.getTransactionAmount().toPlainString();
		}
		
	};

	PaymentFields.Field<Transaction> TRANSACTION_TYPE = new PaymentFields.Field<Transaction>() {

		@Override
		public String getName() {
			return "transactionType";
		}

		@Override
		public String getValue(Transaction var1) {
			return ((Integer)var1.getTransactionType()).toString();
		}
		
	};
	
	PaymentFields.Field<Transaction> TRANSACTION_DATE = new PaymentFields.Field<Transaction>() {

		@Override
		public String getName() {
			return "transactionDate";
		}

		@Override
		public String getValue(Transaction var1) {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if (var1.getTransactionDate() != null)
				return format.format(var1.getTransactionDate());
			else return null;
		}
		
	};
	
	PaymentFields.Field<Transaction> TRANSACTION_DATE_TIME = new PaymentFields.Field<Transaction>() {

		@Override
		public String getName() {
			return "transactionDateTime";
		}

		@Override
		public String getValue(Transaction var1) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			if (var1.getTransactionDate() != null)
				return format.format(var1.getTransactionDate());
			else return null;
		}
		
	};
	
	PaymentFields.Field<Transaction> WALLET_REFERENCE = new PaymentFields.Field<Transaction>() {

		@Override
		public String getName() {
			return "walletReference";
		}

		@Override
		public String getValue(Transaction var1) {
			return var1.getWalletRefernce();
		}
		
	};
	
	public abstract static class Field<T>{
		public Field()
		{
			
		}
		public abstract String getName();
		
		public abstract String getValue(T var1);
	}
}

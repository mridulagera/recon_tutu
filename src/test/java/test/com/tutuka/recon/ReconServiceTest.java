package test.com.tutuka.recon;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.tutuka.recon.entities.ReconResult;
import com.tutuka.recon.entities.Transaction;
import com.tutuka.recon.service.ReconciliationService;
import com.tutuka.recon.service.impl.ReconciliationServiceImpl;
import com.tutuka.recon.utils.DateUtil;


public class ReconServiceTest{ 
	
	ReconciliationService instance;
 
   @Before
   public void before(){
	   instance = new ReconciliationServiceImpl();
   }
 
   @Test( expected = NullPointerException.class )
   public void whenreconforNullTutukaTransactions_thenException(){
      // When
      this.instance.reconcileRecords(null, null);
   }
   
   @Test( expected = NullPointerException.class )
   public void whenreconforNullClientTransactions_thenException(){
      // When
	  Map<String, Transaction> tutukaTrans = new HashMap<String, Transaction>();
	  tutukaTrans.put("1234", new Transaction());
      this.instance.reconcileRecords(null, tutukaTrans);
   }
 
   @Test
   public void whenAllReconile_thenReconResultIsReturned(){
      // When
	   Map<String, Transaction> tutukaTrans = new HashMap<String, Transaction>();
	   Transaction trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   tutukaTrans.put("1234DEDUCT", trans);
	   
	   Map<String, Transaction> clientTrans = new HashMap<String, Transaction>();
	   clientTrans.put("1234DEDUCT", trans);	
	   
	   ReconResult result = instance.reconcileRecords(clientTrans, tutukaTrans);
      // Then
      
	   assertEquals(null, result.getUnmatchedClient());
	   assertEquals(null, result.getUnmatchedTutuka());
	   assertEquals(null, result.getPartialMatchClient());
	   assertEquals(null, result.getPartialMatchTutuka());	   
   }
   
   @Test
   public void whenNarrativeMistMatch_thenReconResultIsReturned(){
      // When
	   Map<String, Transaction> tutukaTrans = new HashMap<String, Transaction>();
	   Transaction trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Tutuka");
	   tutukaTrans.put("1234DEDUCT", trans);
	   
	   Map<String, Transaction> clientTrans = new HashMap<String, Transaction>();
	   trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Client");
	   clientTrans.put("1234DEDUCT", trans);	
	   
	   ReconResult result = instance.reconcileRecords(clientTrans, tutukaTrans);
      // Then
      
	   assertEquals(null, result.getUnmatchedClient());
	   assertEquals(null, result.getUnmatchedTutuka());
	   assertEquals(null, result.getPartialMatchClient());
	   assertEquals(null, result.getPartialMatchTutuka());	   
   }
   
   @Test
   public void whenL1MisMatch_thenReconResultIsReturned(){
      // When
	   Map<String, Transaction> tutukaTrans = new HashMap<String, Transaction>();
	   Transaction trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Tutuka");
	   tutukaTrans.put("1234DEDUCT", trans);
	   
	   Map<String, Transaction> clientTrans = new HashMap<String, Transaction>();
	   trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1235.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Client");
	   clientTrans.put("1234DEDUCT", trans);	
	   
	   ReconResult result = instance.reconcileRecords(clientTrans, tutukaTrans);
      // Then
      
	   assertEquals(null, result.getUnmatchedClient());
	   assertEquals(null, result.getUnmatchedTutuka());
	   assertTrue(result.getPartialMatchClient().containsKey("1234DEDUCT"));
	   assertTrue(result.getPartialMatchTutuka().containsKey("1234DEDUCT"));
   }
   
   @Test
   public void whenL2MisMatch_thenReconResultIsReturned(){
      // When
	   Map<String, Transaction> tutukaTrans = new HashMap<String, Transaction>();
	   Transaction trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Tutuka");
	   tutukaTrans.put("1234DEDUCT", trans);
	   
	   Map<String, Transaction> clientTrans = new HashMap<String, Transaction>();
	   trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 14:07:07"));
	   trans.setTransactionNarrative("Test Client");
	   clientTrans.put("1234DEDUCT", trans);	
	   
	   ReconResult result = instance.reconcileRecords(clientTrans, tutukaTrans);
      // Then
      
	   assertEquals(null, result.getUnmatchedClient());
	   assertEquals(null, result.getUnmatchedTutuka());
	   assertEquals(null, result.getPartialMatchClient());
	   assertEquals(null, result.getPartialMatchTutuka());	
   }
   
   @Test
   public void whenOneTransExtra_thenReconResultIsReturned(){
      // When
	   Map<String, Transaction> tutukaTrans = new HashMap<String, Transaction>();
	   Transaction trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Tutuka");
	   tutukaTrans.put("1234DEDUCT", trans);
	   
	   trans = new Transaction();
	   trans.setTransactionID(12341);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Tutuka");
	   tutukaTrans.put("12341DEDUCT", trans);
	   
	   Map<String, Transaction> clientTrans = new HashMap<String, Transaction>();
	   trans = new Transaction();
	   trans.setTransactionID(12341);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Client");
	   clientTrans.put("12341DEDUCT", trans);	
	   
	   ReconResult result = instance.reconcileRecords(clientTrans, tutukaTrans);
      // Then
	  // System.out.println(result);
	   assertEquals(null, result.getUnmatchedClient());
	   assertEquals(1, result.getUnmatchedTutuka().size());
	   assertEquals(null, result.getPartialMatchClient());
	   assertEquals(null, result.getPartialMatchTutuka());	
   }
   
   @Test
   public void whenTransIdMisMatch_thenReconResultIsReturned(){
      // When
	   Map<String, Transaction> tutukaTrans = new HashMap<String, Transaction>();
	   Transaction trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Tutuka");
	   tutukaTrans.put("1234DEDUCT", trans);
	   
	     
	   Map<String, Transaction> clientTrans = new HashMap<String, Transaction>();
	   trans = new Transaction();
	   trans.setTransactionID(12341);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setTransactionNarrative("Test Client");
	   clientTrans.put("12341DEDUCT", trans);	
	   
	   ReconResult result = instance.reconcileRecords(clientTrans, tutukaTrans);
      // Then
      
	   
	   assertEquals(null, result.getUnmatchedClient());
	   assertEquals(0, result.getUnmatchedTutuka().size());
	   assertEquals(1, result.getPartialMatchClient().size());
	   assertEquals(1, result.getPartialMatchTutuka().size());	
   }
   
   @Test
   public void whenAllMisMatch_thenReconResultIsReturned(){
      // When
	   Map<String, Transaction> tutukaTrans = new HashMap<String, Transaction>();
	   Transaction trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setWalletRefernce("Wallet1");
	   trans.setTransactionNarrative("Test Tutuka");
	   tutukaTrans.put("1234DEDUCT", trans);
	   
	   Map<String, Transaction> clientTrans = new HashMap<String, Transaction>();
	   trans = new Transaction();
	   trans.setTransactionID(12341);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1235.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-14 14:07:07"));
	   trans.setWalletRefernce("Wallet2");
	   trans.setTransactionNarrative("Test Client");
	   clientTrans.put("12341DEDUCT", trans);	
	   
	   ReconResult result = instance.reconcileRecords(clientTrans, tutukaTrans);
      // Then
      
	   assertEquals(1, result.getUnmatchedClient().size());
	   assertEquals(1, result.getUnmatchedTutuka().size());
	   assertEquals(null, result.getPartialMatchClient());
	   assertEquals(null, result.getPartialMatchTutuka());	
   }
   
   @Test
   public void whenPossibleDup_thenReconResultIsReturned(){
      // When
	   Map<String, Transaction> tutukaTrans = new HashMap<String, Transaction>();
	   Transaction trans = new Transaction();
	   trans.setTransactionID(1234);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setWalletRefernce("Wallet1");
	   trans.setTransactionNarrative("Test Tutuka");
	   tutukaTrans.put("1234DEDUCT", trans);
	   trans = new Transaction();
	   trans.setTransactionID(12342);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1234.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-13 07:07:07"));
	   trans.setWalletRefernce("Wallet1");
	   trans.setTransactionNarrative("Test Tutuka");
	   tutukaTrans.put("12342DEDUCT", trans);

	   
	   Map<String, Transaction> clientTrans = new HashMap<String, Transaction>();
	   trans = new Transaction();
	   trans.setTransactionID(12341);
	   trans.setTransactionDescription("DEDUCT");
	   trans.setTransactionAmount(new BigDecimal("1235.00"));
	   trans.setTransactionDate(DateUtil.getDate("2017-12-14 14:07:07"));
	   trans.setWalletRefernce("Wallet2");
	   trans.setTransactionNarrative("Test Client");
	   clientTrans.put("12341DEDUCT", trans);	
	   
	   ReconResult result = instance.reconcileRecords(clientTrans, tutukaTrans);
      // Then
     // System.out.println(result);
	   assertEquals(2, result.getPossibleDuplicateTutukaTransactions().size());
	   assertEquals(1, result.getUnmatchedClient().size());
	   assertEquals(2, result.getUnmatchedTutuka().size());
	   assertEquals(null, result.getPartialMatchClient());
	   assertEquals(null, result.getPartialMatchTutuka());	
   }
 
}

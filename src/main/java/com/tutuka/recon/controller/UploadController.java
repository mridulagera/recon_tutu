package com.tutuka.recon.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tutuka.recon.entities.ReconResult;
import com.tutuka.recon.entities.Transaction;
import com.tutuka.recon.entities.UploadResult;
import com.tutuka.recon.service.ReconciliationService;
import com.tutuka.recon.utils.DateUtil;

@Controller
@SessionAttributes("uploadResult")
public class UploadController {
	
    private static int PF_NM_INX  = 0;
    private static int TXN_DT_INX  = 1;
    private static int TXN_AMT_INX  = 2;
    private static int TXN_NAR_INX  = 3;
    private static int TXN_DESC_INX  = 4;
    private static int TXN_ID_INX  = 5;
    private static int TXN_TYP_INX  = 6;
    private static int WAL_REF_INX  = 7;
    
    @GetMapping("/")
    public String index() {
        return "fileinput";
    }
    
    @PostMapping("/upload") // //new annotation since 4.3
    public String fileUpload(@RequestParam("tutukaFile") MultipartFile tfile, 
    							@RequestParam("clientFile") MultipartFile cfile,
    							RedirectAttributes redirectAttributes, HttpSession session) {

        if (tfile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select Tutuka Transactions file to upload");
            return "uploadStatus";
        }
        if (cfile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select Client Transactions file to upload");
            return "uploadStatus";
        }

        try {
        	//read the file & load the data in various collections 
        	UploadResult uploadResult = new UploadResult();
        	System.out.println("Loading tutuka transactions");
        	Map<String, Transaction> transactions = new HashMap<String, Transaction>();
        	List<Transaction> duplicateTrans =	new ArrayList<Transaction>();
        	List<String>invalidTrans =	new ArrayList<String>();
        	//read tutuka transcations file & bucket transaction in valid/invalid/duplicate transactions
        	readFileValidate(tfile, transactions, duplicateTrans, invalidTrans);        	
        	
        	uploadResult.setTutukaTransactions(transactions);
        	uploadResult.setDuplicateTutuka(duplicateTrans);
        	uploadResult.setInvalidTutuka(invalidTrans);
        	
        	uploadResult.setTotalInValidTTxn(invalidTrans.size());
        	uploadResult.setTotalTutukaTxn(transactions.size()+duplicateTrans.size()+ invalidTrans.size());
        	uploadResult.setTotalValidTTxn(transactions.size());
        	uploadResult.setTotalIgnoredTTxn(duplicateTrans.size());
        	
        	System.out.println("Loading Client transactions");
        	transactions = new HashMap<String, Transaction>();
        	duplicateTrans =	new ArrayList<Transaction>();
        	invalidTrans =	new ArrayList<String>();
        	//read Client transcations file & bucket transaction in valid/invalid/duplicate transactions
        	readFileValidate(cfile, transactions, duplicateTrans,invalidTrans);
        	
        	uploadResult.setClientTransactions(transactions);
        	uploadResult.setDuplicateClient(duplicateTrans);
        	uploadResult.setInvalidClient(invalidTrans);

        	uploadResult.setTotalInValidCTxn(invalidTrans.size());
        	uploadResult.setTotalClientTxn(transactions.size()+duplicateTrans.size()+ invalidTrans.size());
        	uploadResult.setTotalValidCTxn(transactions.size());
        	uploadResult.setTotalIgnoredCTxn(duplicateTrans.size());
        	
        	session.setAttribute("uploadResult", uploadResult);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + tfile.getOriginalFilename() + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/uploadStatus";
    }

    private void readFileValidate(MultipartFile file, Map<String, Transaction> transactions, 
    		List<Transaction> dupTransaction, List<String> invalidTrans)  { 
    	String cvsSplitBy = ",";     
        BufferedReader br;
        try {
             String line;
             InputStream is = file.getInputStream();
             br = new BufferedReader(new InputStreamReader(is));
             
             //ignore first line
             line = br.readLine();
             while ((line = br.readLine()) != null) {
            	 //ignore empty lines
            	 if (StringUtils.isEmpty(line)) continue;
            	 String[] transaction = line.split(cvsSplitBy);
            	 Transaction t;
				try {
					//build transaction object from the attributes in file
					//if field level validations fail then raise exception & mark the transaction as invalid
					t = buildTransaction(transaction);
				} catch (Exception e) {
					invalidTrans.add(line);
					System.err.println(e.getMessage());
					continue;
				}
				
				//add transaction to duplicate/valid transaction pool
            	 updateMaps(transactions, t, dupTransaction);
             }
          } catch (IOException e) {
            System.err.println(e.getMessage());       
          }	
	}

	private void updateMaps(Map<String, Transaction> transactions, Transaction t, 
			List<Transaction> dupTransaction) {
		String key = buildKey(t);
		if (transactions.containsKey(key))
		{
			Transaction existing = transactions.get(key);
			if (existing.equals(t))
			{			
				System.out.println("Duplication Transaction!");				
				dupTransaction.add(t);
				return;
			}
		}
		transactions.put(key, t);
		
	}
	
	/**
	 * Transaction is uniquely identified by combination of Transaction Id & Transaction Description
	 * Build the key using these params
	 * @param t
	 * @return
	 */
	private String buildKey(Transaction t) {
		return t.getTransactionID() + t.getTransactionDescription();
	}

	private Transaction buildTransaction(String[] transaction) throws Exception {
		Transaction trans = new Transaction();
		int len = transaction.length;
		
		if (len <= WAL_REF_INX)
		{
			throw new Exception("Invalid Transcation format");
		}
		trans.setProfileName(transaction[PF_NM_INX]);	
		//System.out.println(Long.parseLong(transaction[TXN_ID_INX]));
		if (StringUtils.isEmpty(transaction[TXN_DT_INX]))throw new Exception("Invalid Transcation Date format");
		trans.setTransactionDate(DateUtil.getDate(transaction[TXN_DT_INX]));
		trans.setTransactionAmount(new BigDecimal(transaction[TXN_AMT_INX]));
		trans.setTransactionNarrative(transaction[TXN_NAR_INX]);
		if (StringUtils.isEmpty(transaction[TXN_DESC_INX])) throw new Exception("Invalid Transcation Description format");
		trans.setTransactionDescription(transaction[TXN_DESC_INX]);
		trans.setTransactionID(Long.parseLong(transaction[TXN_ID_INX]));
		trans.setTransactionType(Integer.parseInt(transaction[TXN_TYP_INX]));
		trans.setWalletRefernce(transaction[WAL_REF_INX].trim());
		return trans;
	}
	
	@GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
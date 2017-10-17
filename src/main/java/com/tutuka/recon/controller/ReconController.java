package com.tutuka.recon.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tutuka.recon.entities.ReconResult;
import com.tutuka.recon.entities.UploadResult;
import com.tutuka.recon.service.ReconciliationService;

@Controller
public class ReconController {
	
	@Autowired
	private ReconciliationService reconService;
    
    @PostMapping("/recon" ) // //new annotation since 4.3
    public ModelAndView reconTransactions(ModelMap model, RedirectAttributes redirectAttributes,  HttpSession session) {

    	UploadResult uploadResult = (UploadResult)session.getAttribute("uploadResult");
    //	ModelAndView model = new ModelAndView("redirect:uploadStatus");
        if (uploadResult == null) {
            redirectAttributes.addFlashAttribute("message", "Uploaded Data is Empty");
            return new ModelAndView("reconStatus",model);
        }
        
        try {
        	ReconResult reconResult = reconService.reconcileRecords(uploadResult.getClientTransactions(), 
        									uploadResult.getTutukaTransactions());  
        	      	

        	//System.out.println("recon result " + reconResult);
        	
        	model.addAttribute("reconResult", reconResult);
        	session.removeAttribute("uploadResult");
        	
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("reconStatus", model);
    }
    
 	
	@GetMapping("/reconStatus")
    public String reconStatus() {
        return "reconStatus";
    }

	public ReconciliationService getReconService() {
		return reconService;
	}

	public void setReconService(ReconciliationService reconService) {
		this.reconService = reconService;
	}

}
package com.tutuka.recon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

	@GetMapping("/api/1.0/{email}") 
	public ModelAndView printName(HttpServletRequest request,@PathVariable String email) {
		ModelAndView mv = new ModelAndView();
//		mv.setViewName("sample-view");
		return mv;
	}
	
	
}



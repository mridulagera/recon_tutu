package com.tutuka.recon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil { 
	
	public static final Date getDate(String sdate)
	{
		
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdate);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}		
		return date;
	}

}

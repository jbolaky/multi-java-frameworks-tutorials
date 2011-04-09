package com.mytutorials.spring.webflow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.mytutorials.spring.webflow.service.api.LibraryService;

public class DefaultLibraryServiceImpl implements LibraryService {

	public List<Date> getHolidays() {
		List<Date> holidays = new ArrayList<Date>();
		holidays.add(new GregorianCalendar(2007, 11, 25).getTime());
		holidays.add(new GregorianCalendar(2008, 0, 1).getTime());
		return holidays;
	}
}

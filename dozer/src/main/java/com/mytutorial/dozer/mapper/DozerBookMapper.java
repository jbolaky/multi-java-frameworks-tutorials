package com.mytutorial.dozer.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerEventListener;

import com.mytutorial.dozer.converter.GenderConverter;
import com.mytutorial.dozer.converter.ItemListConverter;
import com.mytutorial.dozer.entity.structure.one.Booking;
import com.mytutorial.dozer.entity.structure.two.BookingVO;
import com.mytutorial.dozer.entity.structure.two.enumeration.Gender;
import com.mytutorial.dozer.eventlistener.PreWritingBookingValue;

public class DozerBookMapper {

	private DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
	private List<String> beanMappingFiles = new ArrayList<String>();
	private List<CustomConverter> customConverters = new ArrayList<CustomConverter>();
	private List<DozerEventListener> dozerEventListeners = new ArrayList<DozerEventListener>();
	private static final String BEAN_MAPPING_FILE = "BookingBeanMapping.xml";

	public DozerBookMapper() {
		super();

		beanMappingFiles.add(BEAN_MAPPING_FILE);

		dozerEventListeners.add(new PreWritingBookingValue());
		
		customConverters.add(new GenderConverter(String.class, Gender.class));
		customConverters.add(new ItemListConverter(ArrayList.class, ArrayList.class));
	}

	public void map(Booking booking, BookingVO bookingVO) {
		dozerBeanMapper.setCustomConverters(customConverters);
		dozerBeanMapper.setEventListeners(dozerEventListeners);
		dozerBeanMapper.setMappingFiles(beanMappingFiles);
		dozerBeanMapper.map(booking, bookingVO);
	}
}

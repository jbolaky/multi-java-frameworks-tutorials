package com.mytutorial.spring.dozer.mapper;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;

import com.mytutorial.spring.dozer.entity.structure.one.Booking;
import com.mytutorial.spring.dozer.entity.structure.two.BookingVO;

public class DozerBookingMapper {

	@Resource(name = "dozerMapper")
	private DozerBeanMapper dozerBeanMapper;

	public void map(Booking booking, BookingVO bookingVO) {
		dozerBeanMapper.map(booking, bookingVO);
	}
}

package com.mytutorials.dozer.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mytutorial.dozer.entity.structure.one.Booking;
import com.mytutorial.dozer.entity.structure.one.Item;
import com.mytutorial.dozer.entity.structure.two.BookingVO;
import com.mytutorial.dozer.entity.structure.two.BuyerVO;
import com.mytutorial.dozer.mapper.DozerBookMapper;

public class DozerBookMapperTest {

	DozerBookMapper dozerBookMapper = new DozerBookMapper();

	@Test
	public void testMap() {

		BookingVO bookingVO = new BookingVO();

		BuyerVO buyerVO = new BuyerVO();
		buyerVO.setFirstname("Hello");
		bookingVO.setBuyerVO(buyerVO);
		
		
		Item item = new Item(1L, "Car");

		Booking booking = new Booking();
		booking.setFirstname("Javaid");
		booking.setLastname("B");
		booking.setGender("M");
		booking.setTelephoneNumber(12345);
		booking.addItem(item);

		dozerBookMapper.map(booking, bookingVO);

		assertTrue(booking.isBookingConfirm() == bookingVO.getConfirm());
		assertEquals(booking.getFirstname(), bookingVO.getBuyerVO().getFirstname());
		assertEquals(booking.getLastname(), bookingVO.getBuyerVO().getLastname());
		assertTrue(booking.getTelephoneNumber().toString().equals(bookingVO.getBuyerVO().getTelephoneNumber()));
		assertEquals(booking.getGender(), bookingVO.getBuyerVO().getGender().getCode());
		assertTrue(booking.getItems().size() == bookingVO.getBookedItems().size());
		assertTrue(booking.getItems().get(0).getItemName().equals(bookingVO.getBookedItems().get(0)));
	}

}

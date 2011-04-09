package com.mytutorials.spring.dozer.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mytutorial.spring.dozer.entity.structure.one.Booking;
import com.mytutorial.spring.dozer.entity.structure.one.Item;
import com.mytutorial.spring.dozer.entity.structure.one.StatusRecord;
import com.mytutorial.spring.dozer.entity.structure.one.StatusRecordKey;
import com.mytutorial.spring.dozer.entity.structure.two.BookingVO;
import com.mytutorial.spring.dozer.entity.structure.two.BuyerVO;
import com.mytutorial.spring.dozer.mapper.DozerBookingMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:default-spring-dozer-context.xml" })
public class DozerBookMapperTest {

	@Resource(name = "dozerBookingMapper")
	private DozerBookingMapper dozerBookingMapper;

	@Test
	public void testMap() {

		BookingVO bookingVO = new BookingVO();

		Item item = new Item(1L, "Car");

		Booking booking = new Booking();
		booking.setFirstname("Javaid");
		booking.setLastname("B");
		booking.setGender("M");
		booking.setTelephoneNumber(12345);
		booking.addItem(item);

		dozerBookingMapper.map(booking, bookingVO);

		assertTrue(booking.isBookingConfirm() == bookingVO.getConfirm());
		assertEquals(booking.getFirstname(), bookingVO.getBuyerVO().getFirstname());
		assertEquals(booking.getLastname(), bookingVO.getBuyerVO().getLastname());
		assertTrue(booking.getTelephoneNumber().toString().equals(bookingVO.getBuyerVO().getTelephoneNumber()));
		assertEquals(booking.getGender(), bookingVO.getBuyerVO().getGender().getCode());
		assertTrue(booking.getItems().size() == bookingVO.getBookedItems().size());
		assertTrue(booking.getItems().get(0).getItemName().equals(bookingVO.getBookedItems().get(0)));
	}

	@Test
	public void testNullValueListener() {

		BookingVO bookingVO = new BookingVO();

		BuyerVO buyerVO = new BuyerVO();
		buyerVO.setFirstname("Obama");
		bookingVO.setBuyerVO(buyerVO);
		bookingVO.setConfirm(true);

		Item item = new Item(1L, "Car");

		Booking booking = new Booking();
		booking.setFirstname("Javaid");
		booking.setLastname("B");
		booking.setGender("M");
		booking.setTelephoneNumber(12345);
		booking.addItem(item);

		dozerBookingMapper.map(booking, bookingVO);

		assertTrue(booking.isBookingConfirm() != bookingVO.getConfirm());
		assertTrue(bookingVO.getConfirm());
		assertFalse(booking.getFirstname().equals(bookingVO.getBuyerVO().getFirstname()));
		assertTrue(bookingVO.getBuyerVO().getFirstname().equals("Obama"));
		assertEquals(booking.getLastname(), bookingVO.getBuyerVO().getLastname());
		assertTrue(booking.getTelephoneNumber().toString().equals(bookingVO.getBuyerVO().getTelephoneNumber()));
		assertEquals(booking.getGender(), bookingVO.getBuyerVO().getGender().getCode());
		assertTrue(booking.getItems().size() == bookingVO.getBookedItems().size());
		assertTrue(booking.getItems().get(0).getItemName().equals(bookingVO.getBookedItems().get(0)));
	}

	@Test
	public void testStatusRecordConverterWhenAddingNewStatusRecord() {

		BookingVO bookingVO = new BookingVO();

		BuyerVO buyerVO = new BuyerVO();
		buyerVO.setFirstname("Obama");
		bookingVO.setBuyerVO(buyerVO);
		bookingVO.setConfirm(true);

		Item item = new Item(1L, "Car");

		Booking booking = new Booking();
		booking.setFirstname("Javaid");
		booking.setLastname("B");
		booking.setGender("M");
		booking.setTelephoneNumber(12345);
		booking.addItem(item);

		StatusRecordKey statusRecordKey = new StatusRecordKey();
		statusRecordKey.setId(1L);
		statusRecordKey.setCode("0101");

		StatusRecord statusRecord = new StatusRecord();
		statusRecord.setStatusRecordKey(statusRecordKey);
		statusRecord.setCreationDate(new Date());

		List<StatusRecord> statusRecords = new ArrayList<StatusRecord>();
		statusRecords.add(statusRecord);

		booking.setStatusHistory(statusRecords);

		dozerBookingMapper.map(booking, bookingVO);

		assertTrue(booking.isBookingConfirm() != bookingVO.getConfirm());
		assertTrue(bookingVO.getConfirm());
		assertFalse(booking.getFirstname().equals(bookingVO.getBuyerVO().getFirstname()));
		assertTrue(bookingVO.getBuyerVO().getFirstname().equals("Obama"));
		assertEquals(booking.getLastname(), bookingVO.getBuyerVO().getLastname());
		assertTrue(booking.getTelephoneNumber().toString().equals(bookingVO.getBuyerVO().getTelephoneNumber()));
		assertEquals(booking.getGender(), bookingVO.getBuyerVO().getGender().getCode());
		assertTrue(booking.getItems().size() == bookingVO.getBookedItems().size());
		assertTrue(booking.getItems().get(0).getItemName().equals(bookingVO.getBookedItems().get(0)));
		for (StatusRecord statusRecord2 : statusRecords) {
			for (com.mytutorial.spring.dozer.entity.structure.two.StatusRecord statusRecordFrmArray : bookingVO
					.getStatusRecords()) {
				assertEquals(statusRecordFrmArray.getCode(), statusRecord2.getStatusRecordKey().getCode());
				assertEquals(statusRecordFrmArray.getId(), statusRecord2.getStatusRecordKey().getId());
				assertEquals(statusRecordFrmArray.getDate(), statusRecord2.getCreationDate());
			}
		}
	}

	@Test
	public void testStatusRecordConverterWhenStatusRecordKeyAlreadyExist() {

		BookingVO bookingVO = new BookingVO();

		com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[] statusRecordArray = new com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[1];
		com.mytutorial.spring.dozer.entity.structure.two.StatusRecord statusRecord1 = new com.mytutorial.spring.dozer.entity.structure.two.StatusRecord();
		statusRecord1.setCode("0101");
		statusRecord1.setId(1L);
		
		statusRecordArray[0] = statusRecord1;
		bookingVO.setStatusRecords(statusRecordArray);
		
		BuyerVO buyerVO = new BuyerVO();
		buyerVO.setFirstname("Obama");
		bookingVO.setBuyerVO(buyerVO);
		bookingVO.setConfirm(true);

		Item item = new Item(1L, "Car");

		Booking booking = new Booking();
		booking.setFirstname("Javaid");
		booking.setLastname("B");
		booking.setGender("M");
		booking.setTelephoneNumber(12345);
		booking.addItem(item);

		StatusRecordKey statusRecordKey = new StatusRecordKey();
		statusRecordKey.setId(1L);
		statusRecordKey.setCode("0101");

		StatusRecord statusRecord = new StatusRecord();
		statusRecord.setStatusRecordKey(statusRecordKey);
		statusRecord.setCreationDate(new Date());

		List<StatusRecord> statusRecords = new ArrayList<StatusRecord>();
		statusRecords.add(statusRecord);

		booking.setStatusHistory(statusRecords);

		dozerBookingMapper.map(booking, bookingVO);

		assertTrue(booking.isBookingConfirm() != bookingVO.getConfirm());
		assertTrue(bookingVO.getConfirm());
		assertFalse(booking.getFirstname().equals(bookingVO.getBuyerVO().getFirstname()));
		assertTrue(bookingVO.getBuyerVO().getFirstname().equals("Obama"));
		assertEquals(booking.getLastname(), bookingVO.getBuyerVO().getLastname());
		assertTrue(booking.getTelephoneNumber().toString().equals(bookingVO.getBuyerVO().getTelephoneNumber()));
		assertEquals(booking.getGender(), bookingVO.getBuyerVO().getGender().getCode());
		assertTrue(booking.getItems().size() == bookingVO.getBookedItems().size());
		assertTrue(booking.getItems().get(0).getItemName().equals(bookingVO.getBookedItems().get(0)));
		assertTrue(booking.getStatusHistory().size()==bookingVO.getStatusRecords().length);
		for (StatusRecord statusRecord2 : statusRecords) {
			for (com.mytutorial.spring.dozer.entity.structure.two.StatusRecord statusRecordFrmArray : bookingVO
					.getStatusRecords()) {
				assertEquals(statusRecordFrmArray.getCode(), statusRecord2.getStatusRecordKey().getCode());
				assertEquals(statusRecordFrmArray.getId(), statusRecord2.getStatusRecordKey().getId());
			}
		}
	}
}

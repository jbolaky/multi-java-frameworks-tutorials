package com.mytutorials.spring.mvc.webapp.service.api;

import java.util.List;

import com.mytutorials.spring.mvc.webapp.domain.entity.Reservation;

public interface ReservationService {

	public List<Reservation> query(String courtName);

}

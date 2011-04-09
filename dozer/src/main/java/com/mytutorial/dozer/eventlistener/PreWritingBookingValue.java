package com.mytutorial.dozer.eventlistener;

import org.dozer.DozerEventListener;
import org.dozer.event.DozerEvent;
import org.dozer.fieldmap.FieldMap;

public class PreWritingBookingValue implements DozerEventListener {

	@Override
	public void mappingFinished(DozerEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mappingStarted(DozerEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postWritingDestinationValue(DozerEvent dozerEvent) {

	}

	@Override
	public void preWritingDestinationValue(DozerEvent dozerEvent) {

		FieldMap fieldMap = dozerEvent.getFieldMap();
		System.out.println(fieldMap.getDestFieldName());

	}

}

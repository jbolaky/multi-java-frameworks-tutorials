package com.mytutorial.spring.dozer.eventlistener;

import org.dozer.DozerEventListener;
import org.dozer.event.DozerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NullValueInNestedPathException;

public class NullValueListener implements DozerEventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(NullValueListener.class);
	private Object oldDestinationObjectValue = null;

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

		if (oldDestinationObjectValue != null) {
			BeanWrapper beanWrapper = new BeanWrapperImpl(dozerEvent.getDestinationObject());
			beanWrapper.setPropertyValue(dozerEvent.getFieldMap().getDestFieldName(), oldDestinationObjectValue);
			oldDestinationObjectValue = null;
		}
	}

	@Override
	public void preWritingDestinationValue(DozerEvent dozerEvent) {

		BeanWrapper beanWrapper = new BeanWrapperImpl(dozerEvent.getDestinationObject());
		Object actualDestinationValue = null;

		try {
			actualDestinationValue = beanWrapper.getPropertyValue(dozerEvent.getFieldMap().getDestFieldName());
		} catch (NullValueInNestedPathException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage());
			}
		}

		if (actualDestinationValue != null) {
			oldDestinationObjectValue = actualDestinationValue;
		}

	}

}

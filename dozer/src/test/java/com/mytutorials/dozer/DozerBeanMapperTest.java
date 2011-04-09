package com.mytutorials.dozer;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;

public class DozerBeanMapperTest {

	@Test
	public void testDozerBeanMapper() {

		Mapper mapper = new DozerBeanMapper();
		List<String> stringArrayList = new ArrayList<String>();
		List<String> strArrayList = new ArrayList<String>();

		stringArrayList.add("Javaid");
		stringArrayList.add("B");

		mapper.map(stringArrayList, strArrayList);

		assertTrue(strArrayList.size() == 2);
		assertTrue(strArrayList.get(0).equals("Javaid"));
		assertTrue(strArrayList.get(1).equals("B"));
	}
}

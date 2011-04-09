package com.mytutorial.spring.dozer.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerConverter;

import com.mytutorial.spring.dozer.entity.structure.one.StatusRecord;

@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class StatusRecordsConverter extends
		DozerConverter<List, com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[]> {

	public StatusRecordsConverter(Class<List> prototypeA,
			Class<com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[]> prototypeB) {
		super(prototypeA, prototypeB);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List convertFrom(com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[] destination, List source) {

		return null;
	}

	@Override
	public com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[] convertTo(List source,
			com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[] destination) {

		List<StatusRecord> statusRecords = source;

		if (destination == null) {
			destination = new com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[0];
		}

		List<com.mytutorial.spring.dozer.entity.structure.two.StatusRecord> statusRecords2 = Arrays
				.asList((com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[]) destination);
		List<com.mytutorial.spring.dozer.entity.structure.two.StatusRecord> statusRecords3 = new ArrayList<com.mytutorial.spring.dozer.entity.structure.two.StatusRecord>();
		statusRecords3.addAll(statusRecords2);

		if (statusRecords != null && !statusRecords.isEmpty()) {
			if (statusRecords2 != null && !statusRecords2.isEmpty()) {
				for (StatusRecord statusRecord : statusRecords) {
					for (com.mytutorial.spring.dozer.entity.structure.two.StatusRecord statusRecordFrmArray : statusRecords2) {
						if (statusRecord.getStatusRecordKey() != null
								&& statusRecord.getStatusRecordKey().getId() != statusRecordFrmArray.getId()
								&& statusRecord.getStatusRecordKey().getCode() != statusRecordFrmArray.getCode()) {
							statusRecords3.add(mapStatusRecord(statusRecord));
						}
					}
				}
			} else {
				for (StatusRecord statusRecord : statusRecords) {
					statusRecords3.add(mapStatusRecord(statusRecord));
				}
			}
		}

		com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[] statusRecords4 = new com.mytutorial.spring.dozer.entity.structure.two.StatusRecord[statusRecords3
				.size()];

		for (int i = 0; i < statusRecords3.size(); i++) {
			statusRecords4[i] = statusRecords3.get(i);
		}

		return statusRecords4;
	}

	private com.mytutorial.spring.dozer.entity.structure.two.StatusRecord mapStatusRecord(StatusRecord statusRecord) {

		com.mytutorial.spring.dozer.entity.structure.two.StatusRecord record = new com.mytutorial.spring.dozer.entity.structure.two.StatusRecord();
		record.setCode(statusRecord.getStatusRecordKey().getCode());
		record.setDate(statusRecord.getCreationDate());
		record.setId(statusRecord.getStatusRecordKey().getId());

		return record;
	}
}

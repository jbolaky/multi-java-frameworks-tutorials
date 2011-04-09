package com.mytutorial.dozer.converter;

import java.util.ArrayList;

import org.dozer.DozerConverter;

import com.mytutorial.dozer.entity.structure.one.Item;

@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class ItemListConverter extends DozerConverter<ArrayList, ArrayList> {

	public ItemListConverter(Class<ArrayList> prototypeA, Class<ArrayList> prototypeB) {
		super(prototypeA, prototypeB);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Item> convertFrom(ArrayList destinations, ArrayList sources) {

		ArrayList<String> destination = destinations;
		ArrayList<Item> source = sources;

		if (destination != null && !destination.isEmpty()) {

			if (source == null) {
				source = new ArrayList<Item>();
			}

			for (String itemName : destination) {
				source.add(new Item(null, itemName));
			}
		}

		return source;
	}

	@Override
	public ArrayList<String> convertTo(ArrayList sources, ArrayList destinations) {

		ArrayList<String> destination = destinations;
		ArrayList<Item> source = sources;

		if (source != null && !source.isEmpty()) {

			if (destination == null) {
				destination = new ArrayList<String>();
			}

			for (Item item : source) {
				destination.add(item.getItemName());
			}
		}

		return destination;
	}
}

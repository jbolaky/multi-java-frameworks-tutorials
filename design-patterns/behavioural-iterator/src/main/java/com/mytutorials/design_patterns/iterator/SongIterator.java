package com.mytutorials.design_patterns.iterator;

import java.util.Iterator;

import com.mytutorials.design_patterns.iterator.context.SongInfo;

public interface SongIterator {

	public Iterator<SongInfo> createIterator();

}

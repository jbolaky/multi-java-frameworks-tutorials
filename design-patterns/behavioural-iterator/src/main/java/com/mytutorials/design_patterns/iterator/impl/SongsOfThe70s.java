package com.mytutorials.design_patterns.iterator.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mytutorials.design_patterns.iterator.SongIterator;
import com.mytutorials.design_patterns.iterator.context.SongInfo;

public class SongsOfThe70s implements SongIterator {

	// ArrayList holds SongInfo objects

	private List<SongInfo> bestSongs;

	public SongsOfThe70s() {

		bestSongs = new ArrayList<SongInfo>();

		addSong("Imagine", "John Lennon", 1971);
		addSong("American Pie", "Don McLean", 1971);
		addSong("I Will Survive", "Gloria Gaynor", 1979);

	}

	// Add a SongInfo object to the end of the ArrayList

	public void addSong(String songName, String bandName, int yearReleased) {

		SongInfo songInfo = new SongInfo(songName, bandName, yearReleased);

		bestSongs.add(songInfo);

	}

	// Get rid of this
	// Return the ArrayList filled with SongInfo Objects

	public List<SongInfo> getBestSongs() {

		return bestSongs;

	}

	// NEW By adding this method I'll be able to treat all
	// collections the same

	public Iterator<SongInfo> createIterator() {
		// TODO Auto-generated method stub
		return bestSongs.iterator();
	}

}

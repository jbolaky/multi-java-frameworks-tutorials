package com.mytutorials.design_patterns.iterator.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mytutorials.design_patterns.iterator.SongIterator;
import com.mytutorials.design_patterns.iterator.context.SongInfo;
import com.mytutorials.design_patterns.iterator.impl.SongsOfThe70s;
import com.mytutorials.design_patterns.iterator.impl.SongsOfThe80s;
import com.mytutorials.design_patterns.iterator.impl.SongsOfThe90s;

public class DiscJockey {

	private SongsOfThe70s songs70s;
	private SongsOfThe80s songs80s;
	private SongsOfThe90s songs90s;

	// NEW Passing in song iterators

	private SongIterator iter70sSongs;
	private SongIterator iter80sSongs;
	private SongIterator iter90sSongs;

	/*
	 * OLD WAY public DiscJockey(SongsOfThe70s newSongs70s, SongsOfThe80s
	 * newSongs80s, SongsOfThe90s newSongs90s) {
	 * 
	 * songs70s = newSongs70s; songs80s = newSongs80s; songs90s = newSongs90s;
	 * 
	 * }
	 */

	// NEW WAY Initialize the iterators

	public DiscJockey(SongIterator newSongs70s, SongIterator newSongs80s,
			SongIterator newSongs90s) {

		iter70sSongs = newSongs70s;
		iter80sSongs = newSongs80s;
		iter90sSongs = newSongs90s;

	}

	public void showTheSongs() {

		// Because the SongInfo Objects are stored in different
		// collections everything must be handled on an individual
		// basis. This is BAD!

		List<SongInfo> aL70sSongs = songs70s.getBestSongs();

		System.out.println("Songs of the 70s\n");

		for (int i = 0; i < aL70sSongs.size(); i++) {

			SongInfo bestSongs = (SongInfo) aL70sSongs.get(i);

			System.out.println(bestSongs.getSongName());
			System.out.println(bestSongs.getBandName());
			System.out.println(bestSongs.getYearReleased() + "\n");

		}

		SongInfo[] array80sSongs = songs80s.getBestSongs();

		System.out.println("Songs of the 80s\n");

		for (int j = 0; j < array80sSongs.length; j++) {

			SongInfo bestSongs = array80sSongs[j];

			System.out.println(bestSongs.getSongName());
			System.out.println(bestSongs.getBandName());
			System.out.println(bestSongs.getYearReleased() + "\n");

		}

		Map<Integer, SongInfo> ht90sSongs = songs90s.getBestSongs();

		System.out.println("Songs of the 90s\n");

		for (Integer e : ht90sSongs.keySet()) {
			SongInfo bestSongs = ht90sSongs.get(e);

			System.out.println(bestSongs.getSongName());
			System.out.println(bestSongs.getBandName());
			System.out.println(bestSongs.getYearReleased() + "\n");

		}

	}

	// Now that I can treat everything as an Iterator it cleans up
	// the code while allowing me to treat all collections as 1

	public void showTheSongs2() {

		System.out.println("NEW WAY WITH ITERATOR\n");

		Iterator<SongInfo> Songs70s = iter70sSongs.createIterator();
		Iterator<SongInfo> Songs80s = iter80sSongs.createIterator();
		Iterator<SongInfo> Songs90s = iter90sSongs.createIterator();

		System.out.println("Songs of the 70s\n");
		printTheSongs(Songs70s);

		System.out.println("Songs of the 80s\n");
		printTheSongs(Songs80s);

		System.out.println("Songs of the 90s\n");
		printTheSongs(Songs90s);

	}

	public void printTheSongs(Iterator<SongInfo> iterator) {

		while (iterator.hasNext()) {

			SongInfo songInfo = (SongInfo) iterator.next();

			System.out.println(songInfo.getSongName());
			System.out.println(songInfo.getBandName());
			System.out.println(songInfo.getYearReleased() + "\n");

		}

	}
}

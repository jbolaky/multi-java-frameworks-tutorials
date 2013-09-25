package com.mytutorials.design_patterns.structural.client;

import com.mytutorials.design_patterns.structural.component.SongComponent;

public class DiscJockey {

	private SongComponent songList;

	// newSongList contains every Song, SongGroup,

	// and any Songs saved in SongGroups

	public DiscJockey(SongComponent newSongList) {

		songList = newSongList;

	}

	// Calls the displaySongInfo() on every Song

	// or SongGroup stored in songList

	public void getSongList() {

		songList.displaySongInfo();

	}
}

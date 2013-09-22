package com.mytutorials.design_patterns.bridge.abstraction;

import com.mytutorials.design_patterns.bridge.implementor.EntertainmentDevice;

public class DVDRemote extends RemoteButton {

	private boolean play = true;

	public DVDRemote(EntertainmentDevice newDevice) {

		super(newDevice);

	}

	public void buttonNinePressed() {

		play = !play;

		System.out.println("DVD is Playing: " + play);

	}

}

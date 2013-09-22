package com.mytutorials.design_patterns.bridge.abstraction;

import com.mytutorials.design_patterns.bridge.implementor.EntertainmentDevice;

public class TVRemotePause extends RemoteButton {

	public TVRemotePause(EntertainmentDevice newDevice) {

		super(newDevice);

	}

	public void buttonNinePressed() {

		System.out.println("TV was Paused");

	}
}

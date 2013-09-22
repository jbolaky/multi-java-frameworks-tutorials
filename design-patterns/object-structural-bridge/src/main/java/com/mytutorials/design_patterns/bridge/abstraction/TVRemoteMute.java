package com.mytutorials.design_patterns.bridge.abstraction;

import com.mytutorials.design_patterns.bridge.implementor.EntertainmentDevice;

public class TVRemoteMute extends RemoteButton {

	public TVRemoteMute(EntertainmentDevice newDevice) {

		super(newDevice);
	}

	public void buttonNinePressed() {

		System.out.println("TV was Muted");
	}

}

package com.mytutorials.design_patterns.command.receiver.impl;

import com.mytutorials.design_patterns.command.receiver.ElectronicDevice;

public class Television implements ElectronicDevice {

	private int volume = 0;

	public void on() {

		System.out.println("TV is on");

	}

	public void off() {

		System.out.println("TV is off");

	}

	public void volumeUp() {

		volume++;

		System.out.println("TV Volume is at: " + volume);

	}

	public void volumenDown() {

		volume--;

		System.out.println("TV Volume is at: " + volume);

	}

}

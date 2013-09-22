package com.mytutorials.design_patterns.command.concrete_command;

import java.util.List;

import com.mytutorials.design_patterns.command.Command;
import com.mytutorials.design_patterns.command.receiver.ElectronicDevice;

public class TurnItAllOff implements Command {

	private List<ElectronicDevice> theDevices;

	public TurnItAllOff(List<ElectronicDevice> newDevices) {
		theDevices = newDevices;
	}

	public void execute() {

		for (ElectronicDevice device : theDevices) {
			device.off();
		}

	}

	public void undo() {

		for (ElectronicDevice device : theDevices) {
			device.on();
		}

	}
}

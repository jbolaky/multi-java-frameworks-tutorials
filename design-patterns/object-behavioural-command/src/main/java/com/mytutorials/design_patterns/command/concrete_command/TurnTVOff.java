package com.mytutorials.design_patterns.command.concrete_command;

import com.mytutorials.design_patterns.command.Command;
import com.mytutorials.design_patterns.command.receiver.ElectronicDevice;

public class TurnTVOff implements Command {

	private ElectronicDevice theDevice;

	public TurnTVOff(ElectronicDevice newDevice) {

		theDevice = newDevice;

	}

	public void execute() {

		theDevice.off();

	}

	// Used if you want to allow for undo
	// Do the opposite of execute()

	public void undo() {

		theDevice.on();

	}

}
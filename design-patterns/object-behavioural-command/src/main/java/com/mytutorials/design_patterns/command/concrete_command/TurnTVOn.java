package com.mytutorials.design_patterns.command.concrete_command;

import com.mytutorials.design_patterns.command.Command;
import com.mytutorials.design_patterns.command.receiver.ElectronicDevice;

public class TurnTVOn implements Command {

	private ElectronicDevice theDevice;

	public TurnTVOn(ElectronicDevice newDevice) {

		theDevice = newDevice;

	}

	public void execute() {

		theDevice.on();

	}

	public void undo() {

		theDevice.off();

	}

}
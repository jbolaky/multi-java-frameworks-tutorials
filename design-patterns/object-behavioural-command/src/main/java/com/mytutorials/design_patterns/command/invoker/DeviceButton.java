package com.mytutorials.design_patterns.command.invoker;

import com.mytutorials.design_patterns.command.Command;

public class DeviceButton {

	private Command theCommand;

	public DeviceButton(Command newCommand) {

		theCommand = newCommand;

	}

	public void press() {

		theCommand.execute();

	}

	// Now the remote can undo past commands

	public void pressUndo() {

		theCommand.undo();

	}
}

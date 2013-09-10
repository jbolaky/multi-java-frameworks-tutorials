package com.mytutorials.design_patterns.command.concrete_command;

import com.mytutorials.design_patterns.command.Command;
import com.mytutorials.design_patterns.command.receiver.ElectronicDevice;

public class TurnTVUp implements Command {

	private ElectronicDevice electronicDevice;
	
	public TurnTVUp(ElectronicDevice electronicDevice) {
		super();
		this.electronicDevice = electronicDevice;
	}

	public void execute() {
		
		electronicDevice.volumeUp();

	}

	public void undo() {
		
		electronicDevice.volumenDown();
		
	}

}

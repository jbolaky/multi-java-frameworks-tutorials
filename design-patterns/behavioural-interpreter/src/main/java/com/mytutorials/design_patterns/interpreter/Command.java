package com.mytutorials.design_patterns.interpreter;

public interface Command {

	public void execute();

	// You may want to offer the option to undo a command
	public void undo();

}

package com.mytutorials.design_patterns.builder.api;

import com.mytutorials.design_patterns.builder.client.api.Robot;

//Defines the methods needed for creating parts
//for the robot

public interface RobotBuilder {

	public void buildRobotHead();

	public void buildRobotTorso();

	public void buildRobotArms();

	public void buildRobotLegs();

	public Robot getRobot();
}

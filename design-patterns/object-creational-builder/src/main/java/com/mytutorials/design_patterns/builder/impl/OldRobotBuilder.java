package com.mytutorials.design_patterns.builder.impl;

import com.mytutorials.design_patterns.builder.api.RobotBuilder;
import com.mytutorials.design_patterns.builder.client.api.Robot;
import com.mytutorials.design_patterns.builder.client.impl.RobotImpl;

//The concrete builder class that assembles the parts
//of the finished Robot object

public class OldRobotBuilder implements RobotBuilder {

	private RobotImpl robot;

	public OldRobotBuilder() {

		this.robot = new RobotImpl();

	}

	public void buildRobotHead() {

		robot.setRobotHead("Tin Head");

	}

	public void buildRobotTorso() {

		robot.setRobotTorso("Tin Torso");

	}

	public void buildRobotArms() {

		robot.setRobotArms("Blowtorch Arms");

	}

	public void buildRobotLegs() {

		robot.setRobotLegs("Rollar Skates");

	}

	public Robot getRobot() {

		return this.robot;

	}

}

package com.mytutorials.design_patterns.builder.client.impl;

import com.mytutorials.design_patterns.builder.client.api.Robot;

//The concrete Robot class based on the Robot interface

public class RobotImpl implements Robot {

	private String robotHead;

	private String robotTorso;

	private String robotArms;

	private String robotLegs;

	public void setRobotHead(String robotHead) {
		this.robotHead = robotHead;
	}

	public void setRobotTorso(String robotTorso) {
		this.robotTorso = robotTorso;
	}

	public void setRobotArms(String robotArms) {
		this.robotArms = robotArms;
	}

	public void setRobotLegs(String robotLegs) {
		this.robotLegs = robotLegs;
	}

	public String getRobotHead() {
		return robotHead;
	}

	public String getRobotTorso() {
		return robotTorso;
	}

	public String getRobotArms() {
		return robotArms;
	}

	public String getRobotLegs() {
		return robotLegs;
	}

}

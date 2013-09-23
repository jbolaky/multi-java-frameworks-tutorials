package com.mytutorials.design_patterns.adapter.impl;

import com.mytutorials.design_patterns.adapter.EnemyAttacker;
import com.mytutorials.design_patterns.adapter.adaptee.EnemyRobot;

//The Adapter must provide an alternative action for
//the the methods that need to be used because
//EnemyAttacker was implemented.

//This adapter does this by containing an object
//of the same type as the Adaptee (EnemyRobot)
//All calls to EnemyAttacker methods are sent
//instead to methods used by EnemyRobot

public class EnemyRobotAdapter implements EnemyAttacker {

	private EnemyRobot theRobot;

	public EnemyRobotAdapter(EnemyRobot newRobot) {

		theRobot = newRobot;

	}

	public void fireWeapon() {

		theRobot.smashWithHands();

	}

	public void driveForward() {

		theRobot.walkForward();

	}

	public void assignDriver(String driverName) {

		theRobot.reactToHuman(driverName);

	}

}

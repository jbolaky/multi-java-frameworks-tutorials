package com.mytutorials.design_patterns.adapter.client;

import com.mytutorials.design_patterns.adapter.EnemyAttacker;
import com.mytutorials.design_patterns.adapter.adaptee.EnemyRobot;
import com.mytutorials.design_patterns.adapter.impl.EnemyRobotAdapter;
import com.mytutorials.design_patterns.adapter.impl.EnemyTank;

public class TestEnemyAttackers {

	public static void main(String[] args) {

		EnemyAttacker rx7Tank = new EnemyTank();

		EnemyRobot fredTheRobot = new EnemyRobot();

		EnemyAttacker robotAdapter = new EnemyRobotAdapter(fredTheRobot);

		System.out.println("The Robot");

		fredTheRobot.reactToHuman("Paul");

		fredTheRobot.walkForward();

		fredTheRobot.smashWithHands();

		System.out.println();

		System.out.println("The Enemy Tank");

		rx7Tank.assignDriver("Frank");

		rx7Tank.driveForward();

		rx7Tank.fireWeapon();

		System.out.println();

		System.out.println("The Robot with Adapter");

		robotAdapter.assignDriver("Mark");

		robotAdapter.driveForward();

		robotAdapter.fireWeapon();

	}
}

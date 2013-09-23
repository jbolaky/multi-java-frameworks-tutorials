package com.mytutorials.design_patterns.adapter.impl;

import java.util.Random;

import com.mytutorials.design_patterns.adapter.EnemyAttacker;

//EnemyTank implements EnemyAttacker perfectly
//Our job is to make classes with different methods
//from EnemyAttacker to work with the EnemyAttacker interface

public class EnemyTank implements EnemyAttacker {

	private Random generator = new Random();

	public void fireWeapon() {

		int attackDamage = generator.nextInt(10) + 1;

		System.out.println("Enemy Tank Does " + attackDamage + " Damage");

	}

	public void driveForward() {

		int movement = generator.nextInt(5) + 1;

		System.out.println("Enemy Tank moves " + movement + " spaces");

	}

	public void assignDriver(String driverName) {

		System.out.println(driverName + " is driving the tank");

	}

}

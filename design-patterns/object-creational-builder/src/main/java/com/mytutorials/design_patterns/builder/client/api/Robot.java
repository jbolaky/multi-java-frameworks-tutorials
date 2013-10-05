package com.mytutorials.design_patterns.builder.client.api;

// This is the interface that will be returned from the builder

public interface Robot {

	String getRobotHead();

	String getRobotTorso();

	String getRobotArms();

	String getRobotLegs();
}

package com.mytutorials.design_patterns.abstract_factory.test;

import com.mytutorials.design_patterns.abstract_factory.EnemyShipBuilding;
import com.mytutorials.design_patterns.abstract_factory.UFOEnemyShipBuilding;
import com.mytutorials.design_patterns.abstract_factory.product.impl.EnemyShip;

public class EnemyShipTesting {

	public static void main(String[] args) {

		// EnemyShipBuilding handles orders for new EnemyShips

		// You send it a code using the orderTheShip method &

		// it sends the order to the right factory for creation

		EnemyShipBuilding MakeUFOs = new UFOEnemyShipBuilding();

		EnemyShip theGrunt = MakeUFOs.orderTheShip("UFO");

		System.out.println(theGrunt + "\n");

		EnemyShip theBoss = MakeUFOs.orderTheShip("UFO BOSS");

		System.out.println(theBoss + "\n");

	}
}

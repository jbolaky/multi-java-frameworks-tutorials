package com.javaid.tutorials.oval.entity;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

@Guarded
public class GuardedCustomer {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(@NotNull String name) {
		this.name = name;
	}
	
}

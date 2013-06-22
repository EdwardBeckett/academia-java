package com.mycompany.mercaderia.event;


public abstract class AbstractEvent<Target> {

	private Target target;

	public AbstractEvent(Target target) {
		this.target = target;
	}
	
	public Target getTarget() {
		return target;
	}
}

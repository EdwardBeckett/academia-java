package com.mycompany.mercaderia.validation;


public interface Validator<Entity> {

	public String validate(Entity e);

}

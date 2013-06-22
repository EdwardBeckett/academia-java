package com.mycompany.mercaderia.validation;

import com.mycompany.mercaderia.model.Mercaderia;
import static javax.validation.Validation.buildDefaultValidatorFactory;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;


public class MercaderiaValidator implements Validator<Mercaderia> {
	
	private static ValidatorFactory factory;
	
	static {
		factory = buildDefaultValidatorFactory();
	}

	@Override
	public String validate(Mercaderia m) {
		StringBuilder sb = new StringBuilder();
		if (m != null) {
			javax.validation.Validator validator = factory.getValidator();
			Set<ConstraintViolation<Mercaderia>> constraintViolations = validator.validate(m);
			
			if (!constraintViolations.isEmpty()) {
				sb.append("Validacion de entidad Mercaderia\n");
				for (ConstraintViolation<Mercaderia> constraint: constraintViolations) {
					sb.append(String.format("%n%s: %s", constraint.getPropertyPath(), constraint.getMessage()));
				}
			}
		}
		return sb.toString();
	}

}

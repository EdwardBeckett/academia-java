package com.mycompany.mercaderia.action;


public final class ConditionalAction extends AbstractAction {


	private AbstractAction action;
	

	private BooleanExpression expression;
	
	private ConditionalAction(){}
	
	
	@Override
	protected void action() {
		if (action == null) {
			throw new IllegalArgumentException("Indique una accion a ser ejecutada, utilize el método addAction.");
		}
		
		if (expression == null) {
			throw new IllegalArgumentException("Indique una expresion condicional de Accion, utilice el método addConditional.");
		}
		
		if (expression.conditional()) {
			action.actionPerformed();
		}
	}
	
	public static ConditionalAction build(){
		return new ConditionalAction();
	}

	public ConditionalAction addAction(AbstractAction action) {
		this.action = action;
		return this;
	}
	

	public ConditionalAction addConditional(BooleanExpression expression) {
		this.expression = expression;
		return this;
	}
	
}

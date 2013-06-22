package com.mycompany.mercaderia.action;

public abstract class AbstractAction {
	
	protected abstract void action();
	

	protected void preAction(){}

	
	protected void posAction(){}
	
	
	protected void actionFailure(){}
	

	public final void actionPerformed() {
    	try {
    		preAction();
    		action();
    		posAction();
    	} catch (Exception ex) {
    		actionFailure();
    		throw new RuntimeException(ex);
    	}
	}

}

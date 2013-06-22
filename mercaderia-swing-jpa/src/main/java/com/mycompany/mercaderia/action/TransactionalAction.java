package com.mycompany.mercaderia.action;

import com.mycompany.mercaderia.controller.PersistenceController;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public final class TransactionalAction extends AbstractAction {


	private AbstractAction action;

	private PersistenceController persistenceController;
	
	private TransactionalAction(){}

	@Override
	protected void action() {
		if (action == null) {
			throw new IllegalArgumentException("Indique una accion que debe ser executada.");
		}
		
		if (persistenceController == null) {
            throw new IllegalArgumentException("Informe sobre el contexto de persistencia.");
        }
		
		EntityManager em = persistenceController.getPersistenceContext();
		if (em == null) {
            throw new IllegalArgumentException("Es el  gerenciador de persistencia.");
        }
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			action.action();
			tx.commit();
		} catch(Exception ex) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
	}
	
	
	@Override
	protected void preAction() {
		if (action != null) {
			action.preAction();
		}
	}
	

	@Override
	protected void posAction() {
		if (action != null) {
			action.posAction();
		}
	}
	
	
	@Override
	protected void actionFailure() {
		if (action != null) {
			action.actionFailure();
		}
	}

	public static TransactionalAction build() {
		return new TransactionalAction();
	}
	

	public TransactionalAction addAction(AbstractAction action) {
		this.action = action;
		return this;
	}
	

	public TransactionalAction persistenceCtxOwner(PersistenceController persistenceController) {
		this.persistenceController = persistenceController;
		return this;
	}
	
}

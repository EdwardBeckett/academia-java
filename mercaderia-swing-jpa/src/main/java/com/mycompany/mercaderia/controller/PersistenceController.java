package com.mycompany.mercaderia.controller;

import com.mycompany.mercaderia.util.JPAUtil;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;




public abstract class PersistenceController extends AbstractController {

	private static Logger log = Logger.getLogger(PersistenceController.class);

    private EntityManager persistenceContext;
    private boolean ownsPersistenceContext;
	
    public PersistenceController(){ }
    
    public PersistenceController(AbstractController parent){
    	super(parent);
    }
    

    protected void loadPersistenceContext() {
    	loadPersistenceContext(null);
    }
    

    protected void loadPersistenceContext(EntityManager persistenceContext) {
    	if (persistenceContext == null) {
            log.debug("Creando un contexto de persistencia (EntityManager).");
            this.persistenceContext = JPAUtil.createEntityManager();
            this.ownsPersistenceContext = true;
        } else {
            log.debug("Utilizando contexto de persistencia (EntityManager) existente.");
            this.persistenceContext = persistenceContext;
            this.ownsPersistenceContext = false;
        }
    }
    
  
    public EntityManager getPersistenceContext() {
		return this.persistenceContext;
	}
    
 
    @Override
    protected void cleanUp() {
        if (ownsPersistenceContext && getPersistenceContext().isOpen()) {
            log.debug("Cerrando el contexto de persistencia (EntityManager).");
            getPersistenceContext().close();
        }
        super.cleanUp();
    }
}

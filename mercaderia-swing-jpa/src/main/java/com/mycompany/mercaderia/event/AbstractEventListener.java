package com.mycompany.mercaderia.event;


public interface AbstractEventListener<M extends AbstractEvent<?>> {

	 public void handleEvent(M event);

}

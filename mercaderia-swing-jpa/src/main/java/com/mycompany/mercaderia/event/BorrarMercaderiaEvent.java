package com.mycompany.mercaderia.event;

import com.mycompany.mercaderia.model.Mercaderia;


public class BorrarMercaderiaEvent extends AbstractEvent<Mercaderia> {
	
	public BorrarMercaderiaEvent(Mercaderia m) {
		super(m);
	}

}

package com.mycompany.mercaderia.event;

import com.mycompany.mercaderia.model.Mercaderia;


public class IncluirMercaderiaEvent extends AbstractEvent<Mercaderia> {
	
	public IncluirMercaderiaEvent(Mercaderia m) {
		super(m);
	}
}

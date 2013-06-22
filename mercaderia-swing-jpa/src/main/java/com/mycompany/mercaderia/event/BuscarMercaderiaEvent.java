package com.mycompany.mercaderia.event;

import java.util.List;


import com.mycompany.mercaderia.model.Mercaderia;


public class BuscarMercaderiaEvent extends AbstractEvent<List<Mercaderia>> {
	
	public BuscarMercaderiaEvent(List<Mercaderia> m) {
		super(m);
	}

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mjc.ui;

import com.mycompany.mjc.model.Mercaderia;

/**
 *
 * @author josediaz
 */
public class EditarMercaderiaFrame extends IncluirMercaderiaFrame{
    
    	public EditarMercaderiaFrame(ListaMercaderiasFrame framePrincipal) {
		super(framePrincipal);
		setTitle("Editar Mercadoria");
		btnEliminar.setEnabled(true);
		
	}
        
        	
	protected Mercaderia loadMercaderiaFromPanel() {
		Mercaderia m = super.loadMercaderiaFromPanel();
		m.setId(getIdMercaderia());
		return m;
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mjc.ui;

import com.mycompany.mjc.model.Mercaderia;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author josediaz
 */
public class MercaderiaTable extends JTable {

	private MercaderiaTableModel modelo;
	
	public MercaderiaTable() {
		modelo = new MercaderiaTableModel();
		setModel(modelo);
	}
	
	public Mercaderia getMercadoriaSelected() {
		int i = getSelectedRow();
		if (i < 0) {
			return null;
		}
		return modelo.getMercadoriaAt(i);
	}
	
	public void reload(List<Mercaderia> mercadorias) {
		modelo.reload(mercadorias);
	}
}

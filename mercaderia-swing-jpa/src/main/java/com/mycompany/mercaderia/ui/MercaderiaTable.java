package com.mycompany.mercaderia.ui;

import com.mycompany.mercaderia.model.Mercaderia;
import java.util.List;

import javax.swing.JTable;



public class MercaderiaTable extends JTable {

	private MercaderiaTableModel modelo;
	
	public MercaderiaTable() {
		modelo = new MercaderiaTableModel();
		setModel(modelo);
	}
	
	/**
	 * @return <code>Mercaderia</code> selecciona de la tabla. En caso la tabla no tenga elementos, retorna <code>null</code>.
	 */
	public Mercaderia getMercadoriaSelected() {
		int i = getSelectedRow();
		if (i < 0) {
			return null;
		}
		return modelo.getMercadoriaAt(i);
	}
	
	/**
	 * Recarga la tabla de <code>Mercaderia</code> con una lista de <code>mercaderias</code>.
	 * @param mercaderias <code>List</code> con los elementos <code>Mercaderia</code> que deben ser mostrados en la tabla.
	 */
	public void reload(List<Mercaderia> mercadorias) {
		modelo.reload(mercadorias);
	}
}

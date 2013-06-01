/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mjc.ui;

import com.mycompany.mjc.model.Mercaderia;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author josediaz
 */
public class MercaderiaTableModel extends AbstractTableModel {

	private List<Mercaderia> mercaderias;
	
	private String[] colNomes = { "Nombre", "Descripcion", "Precio", "Cantidad" };
	private Class<?>[] colTipos = { String.class, String.class, String.class, Integer.class };
	
	public MercaderiaTableModel(){
	}
	
	public void reload(List<Mercaderia> mercaderias) {
		this.mercaderias = mercaderias;
		//atualiza o componente na tela
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int coluna) {
		return colTipos[coluna];
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int coluna) {
		return colNomes[coluna];
	}

	@Override
	public int getRowCount() {
		if (mercaderias == null){
			return 0;
		}
		return mercaderias.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Mercaderia m = mercaderias.get(linha);
		switch (coluna) {
		case 0:
			return m.getNombre();
		case 1:
			return m.getDescripcion();
		case 2:
			return Mercaderia.convertPrecioToString(m.getPrecio());
		case 3:
			return m.getCantidad();
		default:
			return null;
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Mercaderia getMercadoriaAt(int index) {
		return mercaderias.get(index);
	}
}

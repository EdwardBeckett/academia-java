package com.mycompany.mercaderia.ui;

import com.mycompany.mercaderia.model.Mercaderia;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MercaderiaTableModel extends AbstractTableModel {

	private List<Mercaderia> mercaderias;
	
	private String[] colNombres = { "Nombre", "Descripcion", "Precio", "Cantidad" };
	
	private Class<?>[] colTipos = { String.class, String.class, String.class, Integer.class };
	
	public MercaderiaTableModel(){}
	
	public void reload(List<Mercaderia> mercadorias) {
		this.mercaderias = mercadorias;
		
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
		return colNombres[coluna];
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

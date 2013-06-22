package com.mycompany.mercaderia.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.mycompany.mercaderia.model.Mercaderia;



public class IncluirMercaderiaFrame extends JFrame {

	private JTextField tfNome;
	private JFormattedTextField tfQuantidade;
	private JTextField tfDescricao;
	private JTextField tfPreco;
	private JFormattedTextField tfId;
	private JFormattedTextField tfVersion;
	
	private JButton bSalvar;
	private JButton bCancelar;
	private JButton bExcluir;
	
	public IncluirMercaderiaFrame() {
		setSize(300,300);
		setLocationRelativeTo(null);
		setResizable(false);
		inicializaComponentes();
		resetForm();
	}
	
	private void inicializaComponentes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(montaPanelEditarMercadoria(), BorderLayout.CENTER);
		panel.add(montaPanelBotoesEditar(), BorderLayout.SOUTH);
		add(panel);
		
		GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
	}
	
	private JPanel montaPanelBotoesEditar() {
		JPanel panel = new JPanel();

		bSalvar = new JButton("Guardar");
		bSalvar.setActionCommand("guardarIncluirMercaderiaAction");
		bSalvar.setMnemonic(KeyEvent.VK_S);
		panel.add(bSalvar);

		bCancelar = new JButton("Cancelar");
		bCancelar.setActionCommand("cancelarIncluirMercaderiaAction");
		bCancelar.setMnemonic(KeyEvent.VK_C);
		panel.add(bCancelar);
		
		bExcluir = new JButton("Eliminar");
		bExcluir.setActionCommand("excluirMercaderiaAction");
		bExcluir.setMnemonic(KeyEvent.VK_E);
		panel.add(bExcluir);

		return panel;
	}

	private JPanel montaPanelEditarMercadoria() {
		JPanel painel = new JPanel();
		GridLayout layout = new GridLayout(8, 1);
		painel.setLayout(layout);
		
		tfNome = new JTextField();
		tfDescricao = new JTextField();
		tfPreco = new JTextField();
		tfQuantidade = new JFormattedTextField();
		tfQuantidade.setValue(new Integer(1));
		tfId = new JFormattedTextField();
		tfId.setValue(new Integer(0));
		tfId.setEnabled(false);
		tfVersion = new JFormattedTextField();
		tfVersion.setVisible(false);

		painel.add(new JLabel("Nombre:"));
		painel.add(tfNome);
		painel.add(new JLabel("Descripcion:"));
		painel.add(tfDescricao);
		painel.add(new JLabel("Precio:"));
		painel.add(tfPreco);
		painel.add(new JLabel("Cantidad:"));
		painel.add(tfQuantidade);
		painel.add(new JLabel("Id: "));
		painel.add(tfId);

		return painel;
	}
	
	private Mercaderia loadMercadoriaFromPanel() {
		String nome = null;
		if (!tfNome.getText().trim().isEmpty()) {
			nome = tfNome.getText().trim();
		}
		
		String descricao = null;
		if (!tfDescricao.getText().trim().isEmpty()) {
			descricao = tfDescricao.getText().trim();
		}
		
		Integer quantidade = null;
		try {
			if (!tfQuantidade.getText().trim().isEmpty())
			quantidade = Integer.valueOf(tfQuantidade.getText());
		} catch (NumberFormatException nex) {
			throw new RuntimeException("Error durante la conversion del campo cantidad (Integer).\nContenido inválido!");
		}
		
		Double preco = null;
		try {
			if (!tfPreco.getText().trim().isEmpty()) {
				preco = Mercaderia.formatStringToPrecio(tfPreco.getText());
			}
		} catch (ParseException nex) {
			throw new RuntimeException("Error durante la conversion del campo precio (Double).\nContenido inválido!");
		}
		
		Integer id = null;
		try {
			id = Integer.parseInt(tfId.getText());
		} catch (Exception nex) {}
		
		Integer version = null;
		try {
			version = Integer.parseInt(tfVersion.getText());
		} catch (Exception nex) {}
		
		return new Mercaderia(id, nome, descricao, quantidade, preco, version);
	}
	
	public void resetForm() {
		tfId.setValue(null);
		tfNome.setText("");
		tfDescricao.setText("");
		tfPreco.setText("");
		tfQuantidade.setValue(Integer.valueOf(1));
		tfVersion.setValue(null);
		bExcluir.setVisible(false);
	}
	
	private void populaTextFields(Mercaderia m){
		tfId.setValue(m.getId());
		tfNome.setText(m.getNombre());
		tfDescricao.setText(m.getDescripcion());
		tfQuantidade.setValue(m.getCantidad());
		tfPreco.setText(m.getPrecioFormateado());
		tfVersion.setValue(m.getVersion());
	}

	public void setMercadoria(Mercaderia m){
		resetForm();
		if (m != null) {
			populaTextFields(m);
			bExcluir.setVisible(true);
		}
	}
	

	public Mercaderia getMercadoria() {
		return loadMercadoriaFromPanel();
	}
	
	
	public Integer getMercadoriaId() {
		try {
			return Integer.parseInt(tfId.getText());
		} catch (Exception nex) {
			return null;
		}
	}
	
	public JButton getSalvarButton() {
		return bSalvar;
	}
	
	public JButton getCancelarButton() {
		return bCancelar;
	}
	
	public JButton getExcluirButton() {
		return bExcluir;
	}

}

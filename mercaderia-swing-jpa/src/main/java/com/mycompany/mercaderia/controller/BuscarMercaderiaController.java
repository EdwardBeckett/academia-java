package com.mycompany.mercaderia.controller;


import com.mycompany.mercaderia.action.AbstractAction;
import com.mycompany.mercaderia.dao.MercaderiaDAO;
import com.mycompany.mercaderia.dao.MercaderiaDAOJPA;
import com.mycompany.mercaderia.event.BuscarMercaderiaEvent;
import com.mycompany.mercaderia.model.Mercaderia;
import com.mycompany.mercaderia.ui.BuscaMercaderiaFrame;
import java.util.List;




public class BuscarMercaderiaController extends PersistenceController {

	private BuscaMercaderiaFrame frame;
	
	public BuscarMercaderiaController(AbstractController parent) {
		super(parent);
		frame = new BuscaMercaderiaFrame();
		frame.addWindowListener(this);
		
		registerAction(frame.getBuscarButton(), new AbstractAction() {
			
			private List<Mercaderia> list; 
			
			@Override
			public void action() {
				if (frame.getText().length() > 0) {
					MercaderiaDAO dao = new MercaderiaDAOJPA(getPersistenceContext());
					list = dao.getMercadoriasByNome(frame.getText());
				}
			}
			
			@Override
			public void posAction() {
				cleanUp();
				fireEvent(new BuscarMercaderiaEvent(list));
				list = null;
			}
		});
	}
	
	public void show() {
		loadPersistenceContext();
		frame.setVisible(true);
	}

	@Override
	protected void cleanUp() {
		frame.setVisible(false);
		frame.resetForm();
		
		super.cleanUp();
	}
}

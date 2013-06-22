package com.mycompany.mercaderia.controller;

import com.mycompany.mercaderia.action.AbstractAction;
import com.mycompany.mercaderia.action.BooleanExpression;
import com.mycompany.mercaderia.action.ConditionalAction;
import com.mycompany.mercaderia.action.TransactionalAction;
import com.mycompany.mercaderia.dao.MercaderiaDAO;
import com.mycompany.mercaderia.dao.MercaderiaDAOJPA;
import com.mycompany.mercaderia.event.BorrarMercaderiaEvent;
import com.mycompany.mercaderia.event.IncluirMercaderiaEvent;
import com.mycompany.mercaderia.model.Mercaderia;
import com.mycompany.mercaderia.ui.IncluirMercaderiaFrame;
import com.mycompany.mercaderia.validation.MercaderiaValidator;
import com.mycompany.mercaderia.validation.Validator;
import javax.swing.JOptionPane;




public class IncluirMercaderiaController extends PersistenceController {

	private IncluirMercaderiaFrame frame;
	private Validator<Mercaderia> validador = new MercaderiaValidator();
	
	public IncluirMercaderiaController(AbstractController parent) {
		super(parent);
		this.frame = new IncluirMercaderiaFrame();
		
		frame.addWindowListener(this);
		registerAction(frame.getCancelarButton(), new AbstractAction() {
			public void action() {
				cleanUp();
			}
		});
		
		registerAction(frame.getSalvarButton(), 
			ConditionalAction.build()
				.addConditional(new BooleanExpression() {
					
					@Override
					public boolean conditional() {
						Mercaderia m = frame.getMercadoria();
						String msg = validador.validate(m);
						if (!"".equals(msg == null ? "" : msg)) {
							JOptionPane.showMessageDialog(frame, msg, "Validacion", JOptionPane.INFORMATION_MESSAGE);
							return false;
						}
						return true;
					}
				})
				.addAction(
					TransactionalAction.build()
						.persistenceCtxOwner(this)
						.addAction(
							new AbstractAction() {
								private Mercaderia m;
								
								@Override
								protected void action() {
									m = frame.getMercadoria();
									MercaderiaDAO dao = new MercaderiaDAOJPA(getPersistenceContext());
									dao.save(m);
								}
								
								public void posAction() {
									cleanUp();
									fireEvent(new IncluirMercaderiaEvent(m));
								}
							})));
		
		registerAction(frame.getExcluirButton(), 
			TransactionalAction.build()
				.persistenceCtxOwner(this)
				.addAction(new AbstractAction() {
					private Mercaderia m;
					
					@Override
					protected void action() {
						Integer id = frame.getMercadoriaId();
						if (id != null) {
							MercaderiaDAO dao = new MercaderiaDAOJPA(getPersistenceContext());
		                    m = dao.findById(id);
		                    if (m != null) {
		                        dao.remove(m);
		                    }
						}
					}
					
					public void posAction() {
						cleanUp();
						fireEvent(new BorrarMercaderiaEvent(m));
					}
				})
		);
	}
	
	public void show() {
		loadPersistenceContext(((PersistenceController)getParentController()).getPersistenceContext());
		frame.setVisible(true);
	}
	
	public void show(Mercaderia m) {
		frame.setMercadoria(m);
		frame.setTitle("Editar Mercaderia");
		show();
	}
	
	@Override
	protected void cleanUp() {
		frame.setTitle("Incluir Mercaderia");
		frame.setVisible(false);
		frame.resetForm();
		
		super.cleanUp();
	}
	
}

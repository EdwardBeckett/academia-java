package com.mycompany.mercaderia.controller;

import com.mycompany.mercaderia.action.AbstractAction;
import com.mycompany.mercaderia.dao.MercaderiaDAO;
import com.mycompany.mercaderia.dao.MercaderiaDAOJPA;
import com.mycompany.mercaderia.event.AbstractEventListener;
import com.mycompany.mercaderia.event.ActualizarListarMercaderiaEvent;
import com.mycompany.mercaderia.event.BuscarMercaderiaEvent;
import com.mycompany.mercaderia.event.BorrarMercaderiaEvent;
import com.mycompany.mercaderia.event.IncluirMercaderiaEvent;
import com.mycompany.mercaderia.model.Mercaderia;
import com.mycompany.mercaderia.ui.ListaMercaderiasFrame;
import com.mycompany.mercaderia.ui.SobreFrame;
import com.mycompany.mercaderia.util.JPAUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.SwingUtilities;


public class ListaMercaderiaController extends PersistenceController {

	private ListaMercaderiasFrame frame;
	private SobreFrame sobreFrame;
	
	private IncluirMercaderiaController incluirController;
	private BuscarMercaderiaController buscarController;
	
	public ListaMercaderiaController() {
		loadPersistenceContext();
		frame = new ListaMercaderiasFrame();
		frame.addWindowListener(this);
		incluirController = new IncluirMercaderiaController(this);
		buscarController = new BuscarMercaderiaController(this);
		this.sobreFrame = new SobreFrame();
		
		registerAction(frame.getNewButton(), new AbstractAction() {
			public void action() {
				incluirController.show();
			}
		});
		
		registerAction(frame.getRefreshButton(), new AbstractAction() {
			public void action() {
				fireEvent(new ActualizarListarMercaderiaEvent());
			}
		});
		
		registerAction(frame.getFindButton(), new AbstractAction() {
			public void action() {
				buscarController.show();
			}
		});
		
		AbstractAction sobreAction =  new AbstractAction() {
			@Override
			protected void action() {
				sobreFrame.setVisible(true);
			}
		};
		registerAction(frame.getMenuSobre(), sobreAction);
		frame.getMenuAjuda().addListener(sobreAction);
		
		frame.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					Mercaderia m = frame.getTable().getMercadoriaSelected();
					if (m != null) {
						incluirController.show(m);
					}
				}
			}
		});
		
		registerEventListener(IncluirMercaderiaEvent.class, new AbstractEventListener<IncluirMercaderiaEvent>() {
			public void handleEvent(IncluirMercaderiaEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						refreshTable();
					}
				});
			}
		});
		
		registerEventListener(BorrarMercaderiaEvent.class, new AbstractEventListener<BorrarMercaderiaEvent>() {
			public void handleEvent(BorrarMercaderiaEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						refreshTable();
					}
				});
			}
		});
		
		registerEventListener(ActualizarListarMercaderiaEvent.class, new AbstractEventListener<ActualizarListarMercaderiaEvent>() {
			public void handleEvent(ActualizarListarMercaderiaEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						refreshTable();
					}
				});
			}
		});
		
		registerEventListener(BuscarMercaderiaEvent.class, new AbstractEventListener<BuscarMercaderiaEvent>() {
			public void handleEvent(final BuscarMercaderiaEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						List<Mercaderia> list = event.getTarget();
						if (list != null) {
							frame.refreshTable(event.getTarget());
						}
					}
				});
			}
		});
		
		frame.setVisible(true);
		refreshTable();
	}
	
	private void refreshTable() {
		MercaderiaDAO dao = new MercaderiaDAOJPA(getPersistenceContext());
		frame.refreshTable(dao.getAll());
	}
	
	@Override
	protected void cleanUp() {
		super.cleanUp();
		
		JPAUtil.closeEntityManagerFactory();
	}
}

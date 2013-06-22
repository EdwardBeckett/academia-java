package com.mycompany.mercaderia.ui;

import com.mycompany.mercaderia.model.Mercaderia;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListaMercaderiasFrame extends JFrame {

    private MercaderiaTable tabla;
    private JScrollPane scrollPane;
    private JButton bNewMercaderia;
    private JButton bFindMercaderia;
    private JButton bRefreshLista;
    private JMenuBar menubar;
    private MenuF1 menuAjuda;
    private JMenuItem menuSobre;

    public ListaMercaderiasFrame() {
        setTitle("Lista de Mercaderias");

        inicializaComponentes();
        adicionaComponentes();

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicializaComponentes() {
        tabla = new MercaderiaTable();
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(tabla);

        bNewMercaderia = new JButton("Nuevo");
        bNewMercaderia.setActionCommand("nuevoMercaderiaAction");
        bNewMercaderia.setMnemonic(KeyEvent.VK_N);

        bFindMercaderia = new JButton("Buscar");
        bFindMercaderia.setActionCommand("buscarMercaderiasAction");
        bFindMercaderia.setMnemonic(KeyEvent.VK_B);

        bRefreshLista = new JButton("Actualizar");
        bRefreshLista.setActionCommand("actualizarMercaderiasAction");
        bRefreshLista.setMnemonic(KeyEvent.VK_A);

        menubar = new JMenuBar();
        menuAjuda = new MenuF1("Ayuda");
        menuAjuda.setMnemonic(KeyEvent.VK_J);
        menuSobre = new JMenuItem("Sobre    - F1");

        menuAjuda.add(menuSobre);
        menubar.add(menuAjuda);
        setJMenuBar(menubar);
    }

    private void adicionaComponentes() {
        add(scrollPane);
        JPanel panel = new JPanel();
        panel.add(bNewMercaderia);
        panel.add(bFindMercaderia);
        panel.add(bRefreshLista);
        add(panel, BorderLayout.SOUTH);
    }

    public JButton getNewButton() {
        return bNewMercaderia;
    }

    public JButton getRefreshButton() {
        return bRefreshLista;
    }

    public JButton getFindButton() {
        return bFindMercaderia;
    }

    public void refreshTable(List<Mercaderia> mercadorias) {
        tabla.reload(mercadorias);
    }

    public Mercaderia getSelectedMercadoria() {
        return tabla.getMercadoriaSelected();
    }

    public MercaderiaTable getTable() {
        return tabla;
    }

    public JMenuItem getMenuSobre() {
        return menuSobre;
    }

    public MenuF1 getMenuAjuda() {
        return menuAjuda;
    }
}

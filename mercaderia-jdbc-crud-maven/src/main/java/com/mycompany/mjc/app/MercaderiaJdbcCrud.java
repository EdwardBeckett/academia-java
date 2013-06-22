/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mjc.app;

import com.mycompany.mjc.ui.ListaMercaderiasFrame;

/**
 *
 * @author josediaz
 */
public class MercaderiaJdbcCrud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaMercaderiasFrame().setVisible(true);
            }
        });
    }
}

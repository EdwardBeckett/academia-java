/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mjc.exception;

/**
 *
 * @author josediaz
 */
public class PersistenceException extends RuntimeException {
    
    	public PersistenceException(String msg) {
		super(msg);
	}
	
	public PersistenceException(Exception ex) {
		super(ex);
	}
	
	public PersistenceException(String msg, Exception ex) {
		super(msg, ex);
	}
}

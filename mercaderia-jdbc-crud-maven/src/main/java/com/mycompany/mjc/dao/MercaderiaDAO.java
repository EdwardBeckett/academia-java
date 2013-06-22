/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mjc.dao;

import com.mycompany.mjc.model.Mercaderia;
import java.util.List;

/**
 *
 * @author josediaz
 */
public interface MercaderiaDAO {
 
    
    	/**
	 * Insercion o actualizacion en la base de datos
	 * @param mercaderia
	 * @throws <code>PersistenceException</code> si algun problema sucede.
	 */
	void save(Mercaderia mercaderia);
	
	/**
	 * Eliminar una mercaderia de la base de datos
	 * @param mercaderia
	 * @throws <code>PersistenceException</code> si algun problema sucede.
	 */
	void remove(Mercaderia mercadoria);
	
	/**
	 * @return Lista todas las mercaderias obtenidas de la base de datos.
	 * @throws <code>PersistenceException</code> si algun problema ocurre.
	 */
	List<Mercaderia> getAll();
	
	/**
	 * @param nombre es el filtro que se usara con like
	 * @return Lista de mercaderias filtradas por nombre
	 * @throws <code>PersistenceException</code> si algun problema ocurre.
	 */
	List<Mercaderia> getMercaderiasByNombre(String nombre);
	
	/**
	 * @param id filtro por id
	 * @return Mercaderia con filtro dado el id, en caso no exista retorna <code>null</code>.
	 * @throws <code>PersistenceException</code> si algun problema ocurre.
	 */
	Mercaderia findById(Integer id);

	/**
	 * Inicializa o componente de persistência.
	 * @throws <code>PersistenceException</code> se algum problema ocorrer.
	 */
	void init();
}

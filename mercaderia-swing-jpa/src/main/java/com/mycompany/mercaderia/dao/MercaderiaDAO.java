package com.mycompany.mercaderia.dao;

import com.mycompany.mercaderia.model.Mercaderia;
import java.util.List;


public interface MercaderiaDAO {


	Mercaderia save(Mercaderia mercadoria);
	

	void remove(Mercaderia mercadoria);
	

	List<Mercaderia> getAll();
	

	List<Mercaderia> getMercadoriasByNome(String nome);

	Mercaderia findById(Integer id);

}

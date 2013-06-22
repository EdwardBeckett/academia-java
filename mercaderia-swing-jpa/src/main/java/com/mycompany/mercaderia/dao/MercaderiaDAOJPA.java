package com.mycompany.mercaderia.dao;

import com.mycompany.mercaderia.model.Mercaderia;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;




public class MercaderiaDAOJPA extends AbstractDAO<Mercaderia, Integer> implements MercaderiaDAO {

	//Recibe referencia del entity manager
	public MercaderiaDAOJPA(EntityManager em) {
		super(em);
	}

        //Este es un metodo adicional para buscar mercaderias por nombre"
	@SuppressWarnings("unchecked")
	@Override
	public List<Mercaderia> getMercadoriasByNome(String nome) {
		if (nome == null || nome.isEmpty())
			return null;
		Query query = getPersistenceContext().createQuery("SELECT o FROM Mercadoria o WHERE o.nome like :nome");
		query.setParameter("nome", nome.concat("%"));
		return (List<Mercaderia>) query.getResultList();
	}

}

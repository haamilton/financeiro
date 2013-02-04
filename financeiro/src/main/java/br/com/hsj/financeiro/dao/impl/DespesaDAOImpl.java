package br.com.hsj.financeiro.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.hsj.financeiro.dao.DespesaDAO;
import br.com.hsj.financeiro.entidade.Despesa;
import br.com.hsj.financeiro.exception.DAOException;

/**
 * 
 * @author Hamilton dos Santos Junior
 * 
 *
 */
@Repository("despesaDAO")
public class DespesaDAOImpl extends GenericDAOImpl<Long, Despesa> implements DespesaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6068769564866798297L;
	
	@SuppressWarnings("unchecked")
	public List<Despesa> buscarTodasDespesas() throws DAOException {
		Session session = getSessionFactory().getCurrentSession();
		
		Criteria criteria = session.createCriteria(Despesa.class);
		
		criteria.add(Restrictions.isNull("despesaPai"));
		
		return criteria.list();
	}

}

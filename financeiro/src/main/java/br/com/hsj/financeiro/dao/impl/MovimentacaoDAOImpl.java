package br.com.hsj.financeiro.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.hsj.financeiro.dao.MovimentacaoDAO;
import br.com.hsj.financeiro.entidade.Movimentacao;
import br.com.hsj.financeiro.exception.DAOException;

@Repository("movimentacaoDAO")
public class MovimentacaoDAOImpl extends GenericDAOImpl<Long, Movimentacao> implements MovimentacaoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4588218203550651241L;

	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.dao.MovimentacaoDAO#buscarPorData(java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Movimentacao> buscarAteAData(final Date _dataFim)
			throws DAOException {
		if (_dataFim == null) { throw new NullPointerException("_dataFim null"); }
		
		Session session = getSessionFactory().getCurrentSession();
		
		Criteria criteria = session.createCriteria(Movimentacao.class);
		criteria.add(Restrictions.lt("dataPagamento", _dataFim));
		
		criteria.addOrder(Order.asc("dataPagamento"));
		
		return criteria.list();
	}
	
	/* (non-Javadoc)
	 * @see br.com.hsj.financeiro.dao.MovimentacaoDAO#buscarPorData(java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Movimentacao> buscarPorData(final Date _dataInicio, final Date _dataFim)
			throws DAOException {
		if (_dataInicio == null) { throw new NullPointerException("_dataInicio null"); }
		
		Session session = getSessionFactory().getCurrentSession();
		
		Criteria criteria = session.createCriteria(Movimentacao.class);
		criteria.add(Restrictions.ge("dataPagamento", _dataInicio));
		
		if (_dataFim != null) {
			criteria.add(Restrictions.le("dataPagamento", _dataFim));
		}
		
		criteria.addOrder(Order.asc("dataPagamento"));
		
		return criteria.list();
	}
	
}
